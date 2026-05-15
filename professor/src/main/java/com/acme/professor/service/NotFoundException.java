
package com.acme.professor.service;

import java.io.Serial;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.jspecify.annotations.Nullable;

/// [RuntimeException], falls kein Professor gefunden wurde.
///
@SuppressWarnings({"unused",  "ParameterHidesMemberVariable"})

public final class NotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1101909572340666200L;

    /// Fehlerhafte ID
    @Nullable
    private final UUID id;

    ///  Fehlerhafte Suchparameter
    @Nullable
    private final Map<String, List<String>> suchparameter;

    /// Standardkonstruktor für den [ProfessorService], wenn alle Professor gesucht werden, aber keine existieren.
    NotFoundException() {
        super("Keine Professoren gefunden.");
        id = null;
        suchparameter = null;
    }

    /// Konstruktor für den [ProfessorService] bei fehlerhafter ID.
    ///
    /// @param id Die fehlerhafte ID
    NotFoundException(final UUID id) {
        super("Kein Professor mit der ID " + id + " gefunden.");
        this.id = id;
        suchparameter = null;
    }

    /// Konstruktor für den [ProfessorService] bei fehlerhaften Suchparameter.
    ///
    /// @param suchparameter Die fehlerhaften Suchparameter
    NotFoundException(final Map<String, List<String>> suchparameter) {
        super("Keine Professoren gefunden.");
        id = null;
        this.suchparameter = suchparameter;
    }

    /// id ermitteln.
    ///
    /// @return Die fehlerhafte id.
    public @Nullable UUID getId() {
        return id;
    }

    /// Suchparameter ermitteln.
    ///
    /// @return Die fehlerhaften Suchparameter.
    public @Nullable Map<String, List<String>> getSuchparameter() {
        return suchparameter;
    }
}
