package com.acme.professor.controller;

import com.acme.professor.controller.ProfessorDTO.OnCreate;
import com.acme.professor.service.ProfessorWriteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.groups.Default;
import java.net.URI;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.ResponseEntity.created;
/// ![Klassendiagramm](../../../../../../generated-docs/ProfessorWriteController.svg)
@Controller
@Validated
@RequestMapping("/api/professoren")
@SuppressWarnings({"ClassFanOutComplexity"})
class ProfessorWriteController {
    private final ProfessorWriteService service;
    private final ProfessorMapper mapper;
    private final UriHelper uriHelper;
    private static final Logger logger = LoggerFactory.getLogger(ProfessorWriteController.class);
    ProfessorWriteController(final ProfessorWriteService service,
                             final ProfessorMapper mapper,
                             final UriHelper uriHelper) {
        this.service = service;
        this.mapper = mapper;
        this.uriHelper = uriHelper;
    }
    // -----------------------------------------------------------------------
    // POST: Neuen Professor anlegen
    // -----------------------------------------------------------------------
    @PostMapping
    @Operation(summary = "Einen neuen Professor anlegen", tags = "Professoren-Schreiben")
    @ApiResponse(responseCode = "201", description = "Professor erstellt")
    @ApiResponse(responseCode = "400", description = "Fehler im Request-Body")
    ResponseEntity<Void> post(
        @RequestBody @Validated({Default.class, OnCreate.class}) final ProfessorDTO professorDTO,
        final HttpServletRequest request
    ) {
        logger.debug("post: {}", professorDTO);
        final var professorInput = mapper.toProfessor(professorDTO);
        final var professor = service.create(professorInput);
        final var baseUri = uriHelper.getBaseUri(request).toString();
        final var location = URI.create(baseUri + "/" + professor.getId());
        return created(location).build();
    }
    // -----------------------------------------------------------------------
    // PUT: Professor aktualisieren
    // -----------------------------------------------------------------------
    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Professor aktualisieren", tags = "Professoren-Schreiben")
    @ApiResponse(responseCode = "204", description = "Aktualisiert")
    @ApiResponse(responseCode = "400", description = "Fehler im Request-Body")
    @ApiResponse(responseCode = "404", description = "Professor nicht gefunden")
    void put(
        @PathVariable final UUID id,
        @RequestBody @Validated final ProfessorDTO professorDTO
    ) {
        logger.debug("put: id={}, {}", id, professorDTO);

        final var professorInput = mapper.toProfessor(professorDTO);
        service.update(professorInput, id);
    }
    // -----------------------------------------------------------------------
    // [ExceptionHandler] für [HttpMessageNotReadableException]
    // -----------------------------------------------------------------------
    @ExceptionHandler
    ErrorResponse onMessageNotReadable(final HttpMessageNotReadableException ex) {
        final var msg = ex.getMessage() == null ? "N/A" : ex.getMessage();
        logger.debug("onMessageNotReadable: {}", msg);
        return ErrorResponse.create(ex, BAD_REQUEST, msg);
    }
}
