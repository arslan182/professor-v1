package com.acme.professor.repository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
//import java.util.stream.Stream;
import com.acme.professor.entity.Fakultaet;
import edu.umd.cs.findbugs.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.acme.professor.entity.Professor;
import static com.acme.professor.repository.MockDB.PROFESSOREN;
import static org.apache.logging.log4j.LogManager.getLogger;
///import static java.util.Locale.filter;
@SuppressWarnings({"unused" , "UseOfConcreteClass"})
@Repository
public class ProfessorRepository {
    // Logger für Debug- und Warnmeldungen
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfessorRepository.class);
    // Suche nach ID
    @Nullable
    public static Professor findById(final UUID id) {
        LOGGER.debug("findById: id={}", id);
        // Stream über alle Professoren und Suche nach passender ID
        final Professor professor = PROFESSOREN.stream()
            .filter(professorItem -> professorItem.getId().equals(id))
            .findFirst()
            .orElse(null);
        LOGGER.debug("findById: Professor={}", professor);
        return professor;
    }
    // Suche nach beliebigen Parametern
    public Collection<Professor> find(final Map<String, ? extends List<String>> suchparameter) {
        getLogger().debug("find: suchparameter={}", suchparameter);
        // Keine Filterparameter → alle Professoren zurückgeben
        if (suchparameter.isEmpty()) {
            return findAll();
        }
        // Falls nur ein Parameter vorhanden ist (z. B. nachname oder fakultaet)
        if (suchparameter.size() == 1) {
            final var nachnamen = suchparameter.get("nachname");
            if (nachnamen != null && nachnamen.size() == 1) {
                final var professoren = findByNachname(nachnamen.getFirst());
                getLogger().debug("find (nachname): {}", Optional.ofNullable(professoren));
                return professoren;
            }
            // Filter nach E-Mail
            final var emails = suchparameter.get("email");
            if (emails != null && emails.size() == 1) {
                final var professor = findByEmail(emails.getFirst());
                getLogger().debug("find (email): {}", Optional.ofNullable(professor));
                return professor == null ? List.of() : List.of(professor);
            }
            // Filter nach Fakultät
            final var fakultaeten = suchparameter.get("fakultaet");
            if (fakultaeten != null && fakultaeten.size() == 1) {
                final var professoren = findByFakultaet(fakultaeten.getFirst());
                getLogger().debug("find (fakultaet): {}", Optional.ofNullable(professoren));
                return professoren;
            }
        }
        // Keine passenden Parameter → leere Liste
        getLogger().debug("find: ungueltige Suchparameter={}", suchparameter);
        return List.of();
    }
    // Alle Professoren abrufen
    public List<Professor> findAll() {
        return PROFESSOREN;
    }
    // ================================
    // Hilfsmethoden für Suche
    // Suche nach Nachname (case-insensitive, contains)
    public Collection<Professor> findByNachname(final String nachname) {
        LOGGER.debug("findByNachname: {}", nachname);
        return PROFESSOREN.stream()
            .filter(p -> p.getNachname() != null &&
                p.getNachname().toLowerCase().contains(nachname.toLowerCase()))
            .collect(Collectors.toList());
    }
    // Suche nach E-Mail (exakt, case-insensitive)
    public @org.jspecify.annotations.Nullable Professor findByEmail(final String email) {
        LOGGER.debug("findByEmail: {}", email);
        return PROFESSOREN.stream()
            .filter(p -> p.getEmail() != null &&
                p.getEmail().equalsIgnoreCase(email))
            .findFirst()
            .orElse(null);
    }
    // Suche nach Fakultät (Enum) – ungültige Fakultäten werden abgefangen
    public Collection<Professor> findByFakultaet(final String fakultaetStr) {
        LOGGER.debug("findByFakultaet: {}", fakultaetStr);
        try {
            Fakultaet fakultaet = Fakultaet.valueOf(fakultaetStr.toUpperCase());
            return PROFESSOREN.stream()
                .filter(p -> p.getFakultaet() == fakultaet)
                .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            LOGGER.warn("findByFakultaet: Ungültige Fakultät {}", fakultaetStr);
            return List.of();
        }
    }
    public void deleteById(final UUID id) {
        LOGGER.debug("deleteById: id={}", id);
        final var index = IntStream.range(0, PROFESSOREN.size())
            .filter(i -> Objects.equals(PROFESSOREN.get(i).getId(), id))
            .findFirst();
        LOGGER.trace("deleteById: index={}", index);
        index.ifPresent(PROFESSOREN::remove);
        LOGGER.debug("deleteById: #PROFESSOREN={}", PROFESSOREN.size());
    }
    public void update(final Professor professor) {
        // Index des Professors anhand der ID ermitteln
        final var indexOpt = IntStream.range(0, PROFESSOREN.size())
            .filter(i -> Objects.equals(PROFESSOREN.get(i).getId(), professor.getId()))
            .findFirst();
        // Update durchführen, falls vorhanden
        indexOpt.ifPresent(index -> PROFESSOREN.set(index, professor));
    }
    public Professor create(final Professor professor) {
        PROFESSOREN.add(professor);
        return professor;
    }
}












