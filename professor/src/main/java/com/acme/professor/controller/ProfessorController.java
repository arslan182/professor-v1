package com.acme.professor.controller;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import com.acme.professor.entity.Professor;
import com.acme.professor.service.ProfessorService;
/// ![Klassendiagramm](../../../../../../generated-docs/ProfessorController.svg)
@SpringBootApplication(
    exclude = {
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class
    }
)
// REST-Controller für Professor
//Author: Ali Arslan
@RestController
@RequestMapping("/api/professoren")
@SuppressWarnings("UseOfConcreteClass")
class ProfessorController {
    // Service-Schicht für die Geschäftslogik (Abstraktion der Datenzugriffe)
    private final ProfessorService service;
    // Logger zur Protokollierung von Anfragen und Ereignissen
    private static final Logger logger = LoggerFactory.getLogger(ProfessorController.class);
    /**
     * Konstruktor zur Initialisierung des Controllers mit dem zugehörigen Service.
     * @param mservice Instanz des ProfessorService
     */
    ProfessorController(final ProfessorService mservice){
        service = mservice;
    }
    /**
     * REST-Endpunkt zum Abrufen eines Professors anhand seiner eindeutigen ID.
     * Beispielaufruf: GET /api/professoren/{id}
     *
     * @param id Die eindeutige ID des Professors (UUID)
     * @return Das Professor-Objekt, falls vorhanden
     * Swagger-Annotationen:
     * - @Operation: Beschreibung der API-Funktion
     * - @ApiResponse: Erwartete HTTP-Antwortcodes
     */
    @GetMapping("/{id}")
    @Operation(summary = "Suche Professor mit ID", tags = "ProfessorenSuche")
    @ApiResponse(responseCode = "200", description = "Professor gefunden")
    @ApiResponse(responseCode = "404", description = "Professor nicht gefunden")
    Professor getProfessorById(@PathVariable final UUID id){
        // Log-Ausgabe zur Nachverfolgung der Anfrage im Debug-Modus
        logger.debug("getProfessorById: {}", id);
        // Aufruf der Service-Methode zur Suche nach dem Professor
        return service.findProfessorById(id);
    }
    /// Suche mit diversen Query-Parameter.
    ///
    /// @param queryparam Query-Parameter als Map .
    /// @return Gefundenen Professoren als [Collection].
    @GetMapping
    Collection<Professor> get(@RequestParam final MultiValueMap<String, String> queryparam) {
        logger.debug("get: queryparam={}", queryparam);
        // Geschaeftslogik
        final var professoren = service.find(queryparam);
        logger.debug("get: professoren={}", professoren);
        return professoren;
    }
    /// Beispiel für Deprecation.
    ///
    /// @return JSON-Datensatz als Platzhalter.
    @GetMapping(path = "deprecated")
    Map<String, String> deprecated() {
        logger.debug("deprecated");
        return Map.of("deprecated", "Support ist abgelaufen");
    }
}

