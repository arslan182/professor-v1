package com.acme.professor.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/// ValueObject für das Neuanlegen und Ändern eines Professors.
/// Wird in ProfessorDTO verwendet.
///
/// @param plz Postleitzahl
/// @param ort Ort
public record AdresseDTO(
    @NotNull
    @Pattern(regexp = PLZ_PATTERN)
    String plz,
    @NotBlank
    String ort
) {
    /// Konstante für den regulären Ausdruck einer Postleitzahl als 5-stellige Zahl
    /// (identisch zum Kundenbeispiel).
    public static final String PLZ_PATTERN = "^\\d{5}$";
}

