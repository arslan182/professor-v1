package com.acme.professor.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/// Repräsentiert eine Lehrveranstaltung (z. B. ein Seminar oder eine Vorlesung),
/// die von einem Professor gehalten wird.
/// Enthält Informationen zur ID, zum Titel und zum zugehörigen Professor.
public class Lehrveranstaltung {
    private Long id1;
    /// Eindeutige universelle Kennung (UUID) der Lehrveranstaltung
    private UUID id;
    /// Titel der Lehrveranstaltung
    private String titel;
    /// Standardkonstruktor für JPA
    public Lehrveranstaltung() {}
    // Konstruktor mit allen notwendigen Argumenten.
    /// @param id Die eindeutige ID
    /// @param titel Der Titel der Lehrveranstaltung
    /// @param professor Der Professor, der die Lehrveranstaltung hält
    public Lehrveranstaltung(final UUID id, final String titel, final Professor professor) {
        this.id = id;
        this.titel = titel;
    }
    public Long getId1() {
        return id1;
    }
    public void setId1(Long id1) {
        this.id1 = id1;
    }
    /// ID ermitteln
    /// @return Die eindeutige ID
    public UUID getId() { return this.id; }
    /// ID setzen
    /// @param id Die eindeutige ID
    public void setId(final UUID id) { this.id = id; }
    /// Titel ermitteln
    /// @return Der Titel der Lehrveranstaltung
    public String getTitel() { return this.titel; }
    /// Titel setzen
    /// @param titel Der Titel der Lehrveranstaltung
    public void setTitel(final String titel) { this.titel = titel; }
    @Override
    public boolean equals(final Object other) {
        return other instanceof Lehrveranstaltung lv && Objects.equals(this.id, lv.id);
    }
    @Override
    public int hashCode() { return Objects.hash(this.id); }
    @Override
    public String toString() {
        return "Lehrveranstaltung{" +
            "id=" + this.id +
            ", titel='" + this.titel + '\'' +
            '}';
    }
}
