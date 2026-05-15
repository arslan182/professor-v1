package com.acme.professor.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

/// ValueObject für das Neuanlegen und Ändern einer Lehrveranstaltung.
/// Wird im Rahmen des Professors verwendet.
///
/// @param titel Titel der Lehrveranstaltung
/// @param professorId Die ID des Professors, der die Lehrveranstaltung hält
public record LehrveranstaltungDTO(
    @NotBlank
    String titel,
    @NotNull
    UUID professorId
) { }

