package com.acme.professor.repository;

import com.acme.professor.entity.Adresse;
import com.acme.professor.entity.Fakultaet;
import com.acme.professor.entity.Lehrveranstaltung;
import com.acme.professor.entity.Professor;


import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/// Emulation der Datenbasis für persistente Professoren.
/// Struktur angelehnt an MockDB für Professor.
/// Author: Ali Arslan
@SuppressWarnings({"UtilityClassCanBeEnum", "UtilityClass", "MagicNumber", "RedundantSuppression"})
public final class MockDB {
    @SuppressWarnings("StaticCollection")
    public static final List<Professor> PROFESSOREN;
    //private static final ProfessorBuilder ProfessorBuilder = ;
    static {
        PROFESSOREN = Stream.of(
                // Admin / GET-Test
                ProfessorBuilder.getBuilder()
                    .setId(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                    .setNachname("Admin")
                    .setEmail("admin@uni.de")
                    .setGeburtsdatum(LocalDate.of(1970, 1, 1))
                    .setAdresse(new Adresse("00000", "Hauptstadt"))
                    .setFakultaet(Fakultaet.INFORMATIK)
                    .setLehrveranstaltungen(List.of(
                        new Lehrveranstaltung(UUID.randomUUID(), "AdminLV1", null)
                    ))
                    .build(),

                // GET-Test
                ProfessorBuilder.getBuilder()
                    .setId(UUID.fromString("00000000-0000-0000-0000-000000000001"))
                    .setNachname("Müller")
                    .setEmail("mueller@uni.de")
                    .setGeburtsdatum(LocalDate.of(1980, 5, 12))
                    .setAdresse(new Adresse("11111", "Augsburg"))
                    .setFakultaet(Fakultaet.MATHEMATIK)
                    .setLehrveranstaltungen(List.of(
                        new Lehrveranstaltung(UUID.randomUUID(), "Analysis I", null),
                        new Lehrveranstaltung(UUID.randomUUID(), "Lineare Algebra", null)
                    ))
                    .build(),

                // PUT-Test
                ProfessorBuilder.getBuilder()
                    .setId(UUID.fromString("00000000-0000-0000-0000-000000000002"))
                    .setNachname("Schmidt")
                    .setEmail("schmidt@uni.de")
                    .setGeburtsdatum(LocalDate.of(1975, 3, 20))
                    .setAdresse(new Adresse("22222", "Berlin"))
                    .setFakultaet(Fakultaet.PHYSIK)
                    .setLehrveranstaltungen(List.of(
                        new Lehrveranstaltung(UUID.randomUUID(), "Mechanik", null)
                    ))
                    .build(),

                // PATCH-Test
                ProfessorBuilder.getBuilder()
                    .setId(UUID.fromString("00000000-0000-0000-0000-000000000003"))
                    .setNachname("Meier")
                    .setEmail("meier@uni.de")
                    .setGeburtsdatum(LocalDate.of(1985, 7, 15))
                    .setAdresse(new Adresse("33333", "Dortmund"))
                    .setFakultaet(Fakultaet.CHEMIE)
                    .setLehrveranstaltungen(List.of(
                        new Lehrveranstaltung(UUID.randomUUID(), "Organische Chemie", null),
                        new Lehrveranstaltung(UUID.randomUUID(), "Anorganische Chemie", null)
                    ))
                    .build(),

                // DELETE-Test
                ProfessorBuilder.getBuilder()
                    .setId(UUID.fromString("00000000-0000-0000-0000-000000000004"))
                    .setNachname("Fischer")
                    .setEmail("fischer@uni.de")
                    .setGeburtsdatum(LocalDate.of(1965, 11, 8))
                    .setAdresse(new Adresse("44444", "Frankfurt"))
                    .setFakultaet(Fakultaet.BIOLOGIE)
                    .setLehrveranstaltungen(List.of(
                        new Lehrveranstaltung(UUID.randomUUID(), "Botanik", null)
                    ))
                    .build(),

                // Freie Professoren
                ProfessorBuilder.getBuilder()
                    .setId(UUID.fromString("00000000-0000-0000-0000-000000000005"))
                    .setNachname("Weber")
                    .setEmail("weber@uni.de")
                    .setGeburtsdatum(LocalDate.of(1972, 2, 2))
                    .setAdresse(new Adresse("55555", "Essen"))
                    .setFakultaet(Fakultaet.MEDIZIN)
                    .setLehrveranstaltungen(List.of(
                        new Lehrveranstaltung(UUID.randomUUID(), "Anatomie", null)
                    ))
                    .build(),

                ProfessorBuilder.getBuilder()
                    .setId(UUID.fromString("00000000-0000-0000-0000-000000000006"))
                    .setNachname("Becker")
                    .setEmail("becker@uni.de")
                    .setGeburtsdatum(LocalDate.of(1982, 8, 18))
                    .setAdresse(new Adresse("66666", "Freiburg"))
                    .setFakultaet(Fakultaet.WIRTSCHAFT)
                    .setLehrveranstaltungen(List.of(
                        new Lehrveranstaltung(UUID.randomUUID(), "BWL", null),
                        new Lehrveranstaltung(UUID.randomUUID(), "Finanzen", null)
                    ))
                    .build(),

                ProfessorBuilder.getBuilder()
                    .setId(UUID.fromString("00000000-0000-0000-0000-000000000007"))
                    .setNachname("Hoffmann")
                    .setEmail("hoffmann@uni.de")
                    .setGeburtsdatum(LocalDate.of(1978, 9, 9))
                    .setAdresse(new Adresse("77777", "Hamburg"))
                    .setFakultaet(Fakultaet.RECHTSWISSENSCHAFT)
                    .setLehrveranstaltungen(List.of(
                        new Lehrveranstaltung(UUID.randomUUID(), "Strafrecht", null)
                    ))
                    .build(),

                ProfessorBuilder.getBuilder()
                    .setId(UUID.fromString("00000000-0000-0000-0000-000000000008"))
                    .setNachname("Schneider")
                    .setEmail("schneider@uni.de")
                    .setGeburtsdatum(LocalDate.of(1988, 4, 4))
                    .setAdresse(new Adresse("88888", "Köln"))
                    .setFakultaet(Fakultaet.INFORMATIK)
                    .setLehrveranstaltungen(List.of(
                        new Lehrveranstaltung(UUID.randomUUID(), "Programmierung I", null)
                    ))
                    .build(),

                ProfessorBuilder.getBuilder()
                    .setId(UUID.fromString("00000000-0000-0000-0000-000000000009"))
                    .setNachname("Koch")
                    .setEmail("koch@uni.de")
                    .setGeburtsdatum(LocalDate.of(1990, 12, 25))
                    .setAdresse(new Adresse("99999", "München"))
                    .setFakultaet(Fakultaet.MATHEMATIK)
                    .setLehrveranstaltungen(List.of(
                        new Lehrveranstaltung(UUID.randomUUID(), "Statistik", null)
                    ))
                    .build()
            )
            // Lehrveranstaltungen korrekt auf Professor referenzieren
            //.peek(p -> p.getLehrveranstaltungen().forEach(lv -> lv.setProfessor(p)))
            .collect(Collectors.toList());
    }
    private MockDB() {
        // Utility-Klasse
    }
    private static URL buildURL(final String url) {
        try {
            return URI.create(url).toURL();
        } catch (final MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
