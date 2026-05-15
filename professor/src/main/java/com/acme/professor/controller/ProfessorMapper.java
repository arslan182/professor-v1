package com.acme.professor.controller;
import com.acme.professor.entity.Professor;
import com.acme.professor.entity.Adresse;
import com.acme.professor.entity.Lehrveranstaltung;
import org.mapstruct.AnnotateWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;
/// Mapper zwischen DTO-Klassen und Entity-Klassen für Professor.
/// Die Implementierung wird von MapStruct generiert.
/// Der generierte Code liegt anschließend unter
/// `build/generated/sources/annotationProcessor/.../ProfessorMapperImpl.java`.
@Mapper(nullValueIterableMappingStrategy = RETURN_DEFAULT, componentModel = "spring")
//@AnnotateWith(ExcludeFromJacocoGeneratedReport.class)
public interface ProfessorMapper {
    /// Ein DTO-Objekt von `ProfessorDTO` in ein Objekt `Professor` konvertieren.
    /// Die ID wird ignoriert, da sie erst beim Anlegen erzeugt wird.
    ///
    /// @param dto DTO-Objekt für Professor
    /// @return konvertiertes Professor-Objekt (ID = null)
    @Mapping(target = "id", ignore = true)
    Professor toProfessor(ProfessorDTO dto);
    /// Ein DTO-Objekt von `AdresseDTO` in ein `Adresse`-Objekt konvertieren.
    ///
    /// @param dto DTO-Objekt für Adresse
    /// @return konvertiertes Adresse-Objekt
    Adresse toAdresse(AdresseDTO dto);
    /// Ein DTO-Objekt von `LehrveranstaltungDTO` in ein `Lehrveranstaltung`-Objekt konvertieren.
    ///
    /// @param dto DTO-Objekt für Lehrveranstaltung
    /// @return konvertiertes Lehrveranstaltung-Objekt
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "titel", source = "titel")
    Lehrveranstaltung toLehrveranstaltung(LehrveranstaltungDTO dto);
}
