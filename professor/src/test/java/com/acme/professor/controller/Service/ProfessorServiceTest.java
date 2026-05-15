package com.acme.professor.controller.Service;

import com.acme.professor.repository.ProfessorRepository;
import com.acme.professor.service.NotFoundException;
import com.acme.professor.service.ProfessorService;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@ExtendWith(SoftAssertionsExtension.class)
public class ProfessorServiceTest {
    private final ProfessorRepository repository = new ProfessorRepository();
    private final ProfessorService service = new ProfessorService(repository);
    // SoftAssertions von AssertJ, um mehrere Assertions innerhalb eines Tests zu sammeln
    @InjectSoftAssertions
    private SoftAssertions softly;
    // Test 1: Alle Professoren abrufen
    @Test
    void findAllProfessors() {
        var professoren = service.find(Collections.emptyMap());
        softly.assertThat(professoren).isNotEmpty();
    }
    // Test 2: Nachname existiert
    @Test
    void findByExistingNachname() {
        var params = Map.of("nachname", List.of("Müller"));
        var professoren = service.find(params);
        softly.assertThat(professoren).isNotEmpty();
        professoren.forEach(p -> softly.assertThat(p.getNachname()).isEqualTo("Müller"));
    }
    // Test 3: Nachname existiert nicht
    @Test
    void findByNonExistingNachname() {
        var params = Map.of("nachname", List.of("NichtVorhanden"));
        softly.assertThatThrownBy(() -> service.find(params))
            .isInstanceOf(NotFoundException.class);
    }
    // Test 4: Professor nach existierender ID suchen
    @Test
    void findByIdExisting() {
        var id = UUID.fromString("00000000-0000-0000-0000-000000000001");
        var professor = service.findProfessorById(id);
        softly.assertThat(professor).isNotNull();
        softly.assertThat(professor.getId()).isEqualTo(id);
    }
    // Test 5: Professor nach nicht existierender ID suchen
    @Test
    void findByIdNonExisting() {
        var id = UUID.randomUUID();
        softly.assertThatThrownBy(() -> service.findProfessorById(id))
            .isInstanceOf(NotFoundException.class);
    }
}


