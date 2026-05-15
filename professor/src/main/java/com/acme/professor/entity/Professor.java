package com.acme.professor.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/// Entität Professor mit allen relevanten Informationen.
/// ![Klassendiagramm](../../../../../../generated-docs/Professor.svg)
/// 1-1 Beziehung zu Adresse, Enum für Fakultät, 1-n zu Lehrveranstaltungen.
public class Professor {
    private UUID id;
    /// Nachname des Professors
    private String nachname;
    /// Email-Adresse des Professors
    private String email;
    /// Geburtsdatum des Professors
    private LocalDate geburtsdatum;
    private Adresse adresse;
    private Fakultaet fakultaet;
    private List<Lehrveranstaltung> lehrveranstaltungList;
    /// Standard-Konstruktor für JPA
    public Professor() {}
    /// Konstruktor mit allen Argumenten
    ///
    /// @param id Die ID des Professors
    /// @param nachname Der Nachname
    /// @param email Die Email-Adresse
    /// @param geburtsdatum Das Geburtsdatum
    /// @param adresse Die Adresse
    /// @param fakultaet Die Fakultät
    /// @param lehrveranstaltungen Die Lehrveranstaltungen
    public Professor(final UUID id, final String nachname, final String email,
                     final LocalDate geburtsdatum, final Adresse adresse,
                     final Fakultaet fakultaet,
                     final List<Lehrveranstaltung> lehrveranstaltungen) {
        this.id = id; //
        this.nachname = nachname; //
        this.email = email; //
        this.geburtsdatum = geburtsdatum; //
        this.adresse = adresse; // [cite: 971]
        this.fakultaet = fakultaet; // [cite: 972, 973]
        this.lehrveranstaltungList = lehrveranstaltungen; // [cite: 974]
    }
    // --- Getter und Setter ---
    /// ID ermitteln
    public UUID getId() { return this.id; } // [cite: 979, 981]
    /// ID setzen
    public void setId(final UUID id) { this.id = id; } // [cite: 983, 985]
    /// Nachname ermitteln
    public String getNachname() { return this.nachname; } // [cite: 986, 988]
    /// Nachname setzen
    public void setNachname(final String nachname) { this.nachname = nachname; } // [cite: 990, 992, 993]
    /// Email ermitteln
    public String getEmail() { return this.email; } // [cite: 995, 996]
    /// Email setzen
    public void setEmail(final String email) { this.email = email; } // [cite: 998, 999]
    /// Geburtsdatum ermitteln
    public LocalDate getGeburtsdatum() { return this.geburtsdatum; } // [cite: 1001, 1003]
    /// Geburtsdatum setzen
    public void setGeburtsdatum(final LocalDate geburtsdatum) { this.geburtsdatum = geburtsdatum; } // [cite: 1004, 1006]
    /// Adresse ermitteln
    public Adresse getAdresse() { return this.adresse; } // [cite: 1008, 1010]
    /// Adresse setzen
    public void setAdresse(final Adresse adresse) { this.adresse = adresse; } // [cite: 1012, 1013, 1014]
    /// Fakultät ermitteln
    public Fakultaet getFakultaet() { return this.fakultaet; } // [cite: 1016, 1019]
    /// Fakultät setzen
    public void setFakultaet(final Fakultaet fakultaet) { this.fakultaet = fakultaet; } // [cite: 1019, 1021, 1022]
    /// Lehrveranstaltungen ermitteln
    public List<Lehrveranstaltung> getLehrveranstaltungen() { return this.lehrveranstaltungList; } // [cite: 1024, 1026]
    /// Lehrveranstaltungen setzen
    public void setLehrveranstaltungen(final List<Lehrveranstaltung> lehrveranstaltungen) { // [cite: 1028, 1030]
        this.lehrveranstaltungList = lehrveranstaltungen; // [cite: 1035]
    }
    // --- Override Methoden ---
    @Override
    public final boolean equals(final Object other) { // [cite: 1036]
        return other instanceof Professor professor && Objects.equals(this.id, professor.id); // [cite: 1037]
    }
    @Override
    public final int hashCode() { return Objects.hash(this.id); } // [cite: 1038, 1039]
    @Override
    public String toString() { // [cite: 1040, 1041]
        return "Professor{" + // [cite: 1042]
            "id=" + this.id + // [cite: 1043]
            ", nachname='" + this.nachname + '\'' + // [cite: 1052]
            ", email='" + this.email + '\'' + // [cite: 1054]
            ", geburtsdatum=" + this.geburtsdatum + // [cite: 1056]
            ", fakultaet=" + this.fakultaet + // [cite: 1056]
            '}'; // [cite: 1059]
    }
}
