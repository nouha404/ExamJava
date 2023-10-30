package org.nouha.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nouha.entities.ENUM.TypeDisponibiliteCour;
import org.nouha.entities.ENUM.TypeEtatCour;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Cour {
    private int id;
    private LocalDate dateCours;
    private LocalDate dateFin;

    private TypeDisponibiliteCour disponibiliteCour;
    private TypeEtatCour etatCour;

    //OneToMany
    List<Module> modules;
    List<Classe> classes;

    Professeur professeur;

    /* LocalDate date = LocalDate.now();
     // Création d'une LocalDate spécifique
    LocalDate specificDate = LocalDate.of(2023, 10, 29);
    */
}
