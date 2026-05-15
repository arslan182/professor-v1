package com.acme.professor.service;
import com.acme.professor.entity.Professor;
import com.acme.professor.repository.ProfessorRepository;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import static com.acme.professor.repository.MockDB.PROFESSOREN;
/// Geschäftslogik für Professoren.
/// Entspricht der Struktur des ProfessorWriteService.
/// ![Klassendiagramm](../../../../../../generated-docs/ProfessorWriteService.svg)

@Service
public class ProfessorWriteService {
    private final ProfessorRepository repo;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfessorWriteService.class);
    /// Konstruktor mit package-private Sichtbarkeit für Spring.
    ///
    /// @param repo Das injizierte Repository für Professoren.
    ProfessorWriteService(final ProfessorRepository repo) {
        this.repo = repo;
    }
    /// Einen neuen Professor anlegen.
    ///
    /// @param professor Das Objekt des neu anzulegenden Professors.
    /// @return Der neu angelegte Professor mit generierter ID.
    /// @throws NotFoundException Falls die Erstellung fehlschlägt (z. B. E-Mail bereits vorhanden)
    public Professor create(final Professor professor) {
        LOGGER.debug("create: {}", professor);
        // Optional: E-Mail-Prüfung entfernen oder als NotFoundException behandeln
        final var professorDb = repo.create(professor);
        if (professorDb == null) {
            throw new NotFoundException(professor.getId());
        }
        LOGGER.debug("create: {}", professorDb);
        return professorDb;
    }
    /// Einen vorhandenen Professor aktualisieren.
    ///
    /// @param professor Das Objekt mit den neuen Daten (ohne ID).
    /// @param id Die ID des zu aktualisierenden Professors.
    /// @throws NotFoundException Falls kein Professor zur ID existiert oder Update fehlschlägt.
    public void update(final Professor professor, final UUID id) {
        LOGGER.debug("update: {}", professor);
        LOGGER.debug("update: id={}", id);
        final var professorDb = repo.findById(id);
        if (professorDb == null) {
            throw new NotFoundException(id);
        }
        // ID setzen und Update durchführen
        professor.setId(id);
        repo.update(professor);
    }
    /// Einen vorhandenen Professor löschen.
    ///
    /// @param id Die ID des zu löschenden Professors.
    /// @throws NotFoundException Falls kein Professor zur ID existiert.
    public void deleteById(final UUID id) {
        LOGGER.debug("deleteById: id={}", id);
        final var professorDb = repo.findById(id);
        if (professorDb == null) {
            throw new NotFoundException(id);
        }
        repo.deleteById(id);
    }
}
