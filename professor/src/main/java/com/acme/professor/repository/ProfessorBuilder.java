package com.acme.professor.repository;

import com.acme.professor.entity.Adresse;
import com.acme.professor.entity.Fakultaet;
import com.acme.professor.entity.Lehrveranstaltung;
import com.acme.professor.entity.Professor;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/// Builder für die Entität Professor
@SuppressWarnings({"NullAway.Init","PMD.AtLeastOneConstructor", "UseOfConcreteClass", "unused"})
public final class ProfessorBuilder {
    private UUID id;
    private String nachname;
    private String email;
    private LocalDate geburtsdatum;
    private Adresse adresse;
    private Fakultaet fakultaet;
    private List<Lehrveranstaltung> lehrveranstaltungen;
    /// Builder starten
    public static ProfessorBuilder builder() {
        return new ProfessorBuilder();
    }
    public static ProfessorBuilder getBuilder() {
        return new ProfessorBuilder();
    }
    /// ID setzen
    public ProfessorBuilder setId(final UUID id) {
        this.id = id;
        return this;
    }
    /// Nachname setzen
    public ProfessorBuilder setNachname(final String nachname) {
        this.nachname = nachname;
        return this;
    }
    /// Email setzen
    public ProfessorBuilder setEmail(final String email) {
        this.email = email;
        return this;
    }
    /// Geburtsdatum setzen
    public ProfessorBuilder setGeburtsdatum(final LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
        return this;
    }
    /// Adresse setzen
    public ProfessorBuilder setAdresse(final Adresse adresse) {
        this.adresse = adresse;
        return this;
    }
    /// Fakultät setzen
    public ProfessorBuilder setFakultaet(final Fakultaet fakultaet) {
        this.fakultaet = fakultaet;
        return this;
    }
    /// Lehrveranstaltungen setzen
    public ProfessorBuilder setLehrveranstaltungen(final List<Lehrveranstaltung> lehrveranstaltungen) {
        this.lehrveranstaltungen = lehrveranstaltungen;
        return this;
    }
    public Professor build() {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(nachname, "nachname must not be null");
        Objects.requireNonNull(email, "email must not be null");
        Objects.requireNonNull(geburtsdatum, "geburtsdatum must not be null");
        Objects.requireNonNull(adresse, "adresse must not be null");
        Objects.requireNonNull(fakultaet, "fakultaet must not be null");
        Objects.requireNonNull(lehrveranstaltungen, "lehrveranstaltungen must not be null");

        return new Professor(id, nachname, email, geburtsdatum, adresse, fakultaet, lehrveranstaltungen);
    }
}


