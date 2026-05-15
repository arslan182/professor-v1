package com.acme.professor.controller;

/// Konstanten für die Professor-API
/// @author …
final class Constants {
    /// Aktuelle Versionsnummer
    static final String VERSION_1 = "1.0.0+";
    /// Basispfad für die REST-Schnittstelle
    static final String API_PATH = "api/professoren";
    /// Muster für eine UUID
    static final String ID_PATTERN = "[\\da-f]{8}-[\\da-f]{4}-[\\da-f]{4}-[\\da-f]{4}-[\\da-f]{12}";
    /// Version-Header für Swagger
    static final String X_VERSION = "X-Version";
    /// Aktuelle Versionsnummer als Beispiel für Swagger
    static final String VERSION_1_EXAMPLE = "1.0.0";
    private Constants() {
        // Verhindert Instanziierung
    }
}
