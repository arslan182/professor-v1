package com.acme.professor.controller;

import com.acme.professor.entity.Fakultaet;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;
import org.hibernate.validator.constraints.UniqueElements;
import org.jspecify.annotations.Nullable;

/// ValueObject für das Neuanlegen und Ändern eines Professors.
/// Beim Lesen wird eine eigene Model-Klasse verwendet.
///
/// Enthält:
/// - Nachname (mit Muster)
/// - E-Mail-Adresse (muss gültig sein)
/// - Geburtsdatum (muss in der Vergangenheit liegen)
/// - Fakultät (Enum)
/// - Adresse (1-1, Validierung beim POST verpflichtend)
/// - Lehrveranstaltungen (1-n)
///
/// @author Ali ARslan
/// @param nachname Nachname des Professors.
/// @param email Email-Adresse des Professors.
/// @param geburtsdatum Geburtsdatum.
/// @param fakultaet Zugehörige Fakultät.
/// @param adresse Adresse des Professors.
/// @param lehrveranstaltungen Liste der Lehrveranstaltungen.
@SuppressWarnings("RecordComponentNumber")
public record ProfessorDTO(
    @NotNull
    @Pattern(regexp = NACHNAME_PATTERN)
    String nachname,
    @Email
    @NotNull
    String email,
    @Past
    LocalDate geburtsdatum,
    @NotNull
    Fakultaet fakultaet,
    @Valid
    @NotNull(groups = OnCreate.class)
    AdresseDTO adresse,
    @Nullable
    List<@Valid LehrveranstaltungDTO> lehrveranstaltungen
) {
    /// Muster für einen gültigen Nachnamen (gleich wie im Kunden-Beispiel)
    public static final String NACHNAME_PATTERN =
        "(o'|von|von der|von und zu|van)?[A-ZÄÖÜ][a-zäöüß]+(-[A-ZÄÖÜ][a-zäöüß]+)?";
    /// Marker-Interface für _Jakarta Validation_: zusätzliche Validierung beim Neuanlegen.
    public interface OnCreate { }
}
