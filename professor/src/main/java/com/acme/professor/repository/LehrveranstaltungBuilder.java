package com.acme.professor.repository;

import com.acme.professor.entity.Lehrveranstaltung;
import com.acme.professor.entity.Professor;

import java.util.UUID;

/// Builder-Klasse für die Klasse [Lehrveranstaltung].
///
/// @author [Jürgen Zimmermann](mailto:Juergen.Zimmermann@h-ka.de)
@SuppressWarnings({"NullAway.Init", "NotNullFieldNotInitialized", "PMD.AtLeastOneConstructor"})
public class LehrveranstaltungBuilder {
    private UUID id;
    private String titel;
    private Professor professor;
    /// Ein Builder-Objekt für die Klasse [Lehrveranstaltung] bauen.
    ///
    /// @return Das Builder-Objekt.
    public static LehrveranstaltungBuilder getBuilder() {
        return new LehrveranstaltungBuilder();
    }
    /// ID setzen.
    /// @param id Die UUID der Lehrveranstaltung
    /// @return Builder-Objekt.
    public LehrveranstaltungBuilder setId(final UUID id) {
        this.id = id;
        return this;
    }
    /// Titel setzen.
    /// @param titel Der Titel der Lehrveranstaltung
    /// @return Builder-Objekt.
    public LehrveranstaltungBuilder setTitel(final String titel) {
        this.titel = titel;
        return this;
    }
    /// Professor setzen.
    /// @param professor Der Professor, der die Lehrveranstaltung hält
    /// @return Builder-Objekt.
    public LehrveranstaltungBuilder setProfessor(final Professor professor) {
        this.professor = professor;
        return this;
    }
    /// Lehrveranstaltung bauen.
    /// @return Lehrveranstaltung-Objekt.
    public Lehrveranstaltung build() {
        return new Lehrveranstaltung(id, titel, professor);
    }
}

