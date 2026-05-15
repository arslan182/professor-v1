package com.acme.professor.entity;

/// Fakultäten als Enum
public enum Fakultaet {
    INFORMATIK("Informatik"),
    MATHEMATIK("Mathematik"),
    PHYSIK("Physik"),
    CHEMIE("Chemie"),
    BIOLOGIE("Biologie"),
    MEDIZIN("Medizin"),
    WIRTSCHAFT("Wirtschaft"),
    RECHTSWISSENSCHAFT("Rechtswissenschaft");
    /// Anzeige-Name der Fakultät
    private final String name;
    /// Konstruktor
    Fakultaet(final String name) {
        this.name = name;
    }
    /// Name ermitteln
    ///
    /// @return Name der Fakultät
    public String getName() {
        return this.name;
    }
    @Override
    public String toString() {
        return this.name;
    }
}
