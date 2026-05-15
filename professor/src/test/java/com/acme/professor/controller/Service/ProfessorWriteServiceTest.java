package com.acme.professor.controller.Service;

import com.acme.professor.entity.Fakultaet;
import com.acme.professor.entity.Professor;
import com.acme.professor.repository.ProfessorRepository;
import com.acme.professor.service.ProfessorService;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("unit")
@Tag("service-write")
@DisplayName("ProfessorWriteService Tests")
@ExtendWith(SoftAssertionsExtension.class)
class ProfessorWriteServiceTest {
    private static final String NEUER_NACHNAME = "Mustermann";
    private static final String NEUE_EMAIL = "professor@test.de";
    private static final LocalDate NEUES_GEBURTSDATUM = LocalDate.of(1970, 1, 1);
    private static final UUID ID_UPDATE = UUID.fromString("00000000-0000-0000-0000-000000000001");
    private static final UUID ID_DELETE = UUID.fromString("00000000-0000-0000-0000-000000000002");
    private final ProfessorRepository repo = new ProfessorRepository();
    private final ProfessorService service = new ProfessorService(repo);
    @InjectSoftAssertions
    private SoftAssertions softly;
    // --- POST: Neuen Professor erstellen ---
    @ParameterizedTest(name = "[{index}] Neuanlegen eines Professors: nachname={0}, email={1}")
    @CsvSource({
        NEUER_NACHNAME + "," + NEUE_EMAIL
    })
    @DisplayName("Neuanlegen eines neuen Professors")
    void createProfessor(final ArgumentsAccessor args) {
        final var nachname = args.getString(0);
        final var email = args.getString(1);

        final var professor = new Professor(
            UUID.randomUUID(),
            nachname,
            email,
            NEUES_GEBURTSDATUM,
            null, // Adresse optional
            Fakultaet.INFORMATIK, // Dummy Fakultät
            List.of() // keine Lehrveranstaltungen
        );

        final var created = repo.create(professor);

        softly.assertThat(created).isNotNull();
        softly.assertThat(created.getId()).isNotNull();
        softly.assertThat(created.getNachname()).isEqualTo(NEUER_NACHNAME);
        softly.assertThat(created.getEmail()).isEqualTo(NEUE_EMAIL);
    }
    // --- PUT: Existierenden Professor aktualisieren ---
    @ParameterizedTest(name = "[{index}] Update eines Professors: id={0}")
    @ValueSource(strings = {"00000000-0000-0000-0000-000000000001"})
    @DisplayName("Update eines vorhandenen Professors")
    void updateProfessor(final String idStr) {
        final var professorId = UUID.fromString(idStr);
        final var professor = repo.findById(professorId);
        assertThat(professor).isNotNull();

        professor.setNachname("GeänderterNachname");
        repo.update(professor); // Update über Repository

        final var result = repo.findById(professorId);
        assertThat(result).isNotNull();
        assertThat(result.getNachname()).isEqualTo("GeänderterNachname");
    }
    // --- DELETE: Existierenden Professor löschen ---
    @ParameterizedTest(name = "[{index}] Delete eines Professors: id={0}")
    @ValueSource(strings = {"00000000-0000-0000-0000-000000000002"})
    @DisplayName("Löschen eines vorhandenen Professors")
    void deleteProfessor(final String idStr) {
        final var professorId = UUID.fromString(idStr);
        repo.deleteById(professorId);
        final var result = repo.findById(professorId);
        assertThat(result).isNull();
    }
}


