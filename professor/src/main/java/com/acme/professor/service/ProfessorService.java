package com.acme.professor.service;

import com.acme.professor.entity.Professor;
import com.acme.professor.repository.ProfessorRepository;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import static org.apache.logging.log4j.LogManager.getLogger;
/// ![Klassendiagramm](../../../../../../generated-docs/ProfessorService.svg)
@Service // Annotation, die diese Klasse als Service-Komponente markiert.
public class ProfessorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfessorService.class);
    // Datenzugriffsschicht (Abstraktion der Datenbankoperationen)
    private final ProfessorRepository repo;
    // Konstruktor-Injection für das ProfessorRepository
    public ProfessorService(final ProfessorRepository repository) {
        this.repo= repository;
    }
    /**
     * Sucht einen Professor anhand seiner eindeutigen ID.
     *
     * @param id Die eindeutige ID des Professors (UUID)
     * @return Das Professor-Objekt, falls vorhanden
     * @throws NotFoundException wenn kein Professor mit der gegebenen ID gefunden wird.
     */
    public Professor findProfessorById(final UUID id) {
        LOGGER.debug("findProfessorById: id={}", id);
        final Professor professor = repo.findById(id);
        if (professor == null){
            throw new NotFoundException(id);
        }
        LOGGER.debug("findProfessorById: professor={}" , professor);
        return professor;
    }
    public Collection<Professor> find(final Map<String, List<String>> suchparameter){
        getLogger().debug("find: suchparameter={}", suchparameter);
        final var professoren = repo.find(suchparameter);
        if (professoren.isEmpty()){
            throw new NotFoundException(suchparameter);
        }
        getLogger().debug("find: professoren={}", Optional.ofNullable(professoren));
        return professoren;
    }
}
