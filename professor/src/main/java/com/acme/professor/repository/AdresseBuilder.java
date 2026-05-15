package com.acme.professor.repository;

import com.acme.professor.entity.Adresse;

/// Builder-Klasse für die Klasse [Adresse].

@SuppressWarnings({"NullAway.Init", "NotNullFieldNotInitialized", "PMD.AtLeastOneConstructor"})
public class AdresseBuilder {
    private String plz;
    private String ort;
    /// Ein Builder-Objekt für die Klasse [Adresse] bauen.
    ///
    /// @return Das Builder-Objekt.
    public static AdresseBuilder getBuilder() {
        return new AdresseBuilder();
    }
    /// Postleitzahl setzen.
    /// @param plz Die Postleitzahl
    /// @return Builder-Objekt.
    public AdresseBuilder setPlz(final String plz) {
        this.plz = plz;
        return this;
    }
    /// Ort setzen.
    /// @param ort Der Ort.
    /// @return Builder-Objekt.
    public AdresseBuilder setOrt(final String ort) {
        this.ort = ort;
        return this;
    }
    /// Adresse bauen.
    /// @return Adresse-Objekt.
    public Adresse build() {
        return new Adresse(plz, ort);
    }
}
