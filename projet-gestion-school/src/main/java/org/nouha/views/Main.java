package org.nouha.views;

import org.nouha.repositories.impl.ClasseRepositoryImpl;
import org.nouha.repositories.impl.CourRepositoryImpl;
import org.nouha.repositories.impl.ModulesRepositoryImpl;
import org.nouha.repositories.impl.MysqlImplement;
import org.nouha.repositories.impl.SalleRepositoryImpl;
import org.nouha.services.ClasseService;
import org.nouha.services.CourService;
import org.nouha.services.ModulesService;
import org.nouha.services.SalleService;
import org.nouha.services.impl.ClasseServiceImpl;
import org.nouha.services.impl.CourServiceImpl;
import org.nouha.services.impl.ModulesServiceImpl;
import org.nouha.services.impl.SalleServiceImpl;

import java.util.List;
import java.util.Scanner;

import org.nouha.entities.Classe;
import org.nouha.entities.Cour;
import org.nouha.entities.Modules;
import org.nouha.entities.Professeur;
import org.nouha.entities.Salle;
import org.nouha.repositories.ClasseRepository;
import org.nouha.repositories.CourRepository;
import org.nouha.repositories.Database;
import org.nouha.repositories.ModulesRepository;
import org.nouha.repositories.SalleRepository;


public class Main {
    public static void main(String[] args) {

        Database db = new MysqlImplement();
        
        SalleRepository salleRepository = new SalleRepositoryImpl(db);
        CourRepository courRepository = new CourRepositoryImpl(db);
        ClasseRepository classeRepository = new ClasseRepositoryImpl(db, salleRepository, courRepository);
        ModulesRepository moduleRepository = new ModulesRepositoryImpl(db,classeRepository,courRepository);

        ModulesService modulesService = new ModulesServiceImpl(moduleRepository,classeRepository, courRepository);
        CourService courService = new CourServiceImpl(courRepository);
        SalleService salleService = new SalleServiceImpl(salleRepository);
        ClasseService classeService = new ClasseServiceImpl(salleService,courService,classeRepository,salleRepository);

        int choix;
        try (Scanner sc = new Scanner(System.in)) {
            do {
                System.out.println("1-Ajouter une Module");
                System.out.println("2-Lister les Modules");
                System.out.println("22-Lister les Modules d'une classe");
                System.out.println("");

                System.out.println("3-Ajouter une classe");
                System.out.println("4-Lister les classes");
                System.out.println("5-Lister une classe");
                System.out.println("6-Modifier une classe");
                System.out.println("7-Archiver une classe");
                System.out.println("8-Afficher les classes qui font un module");
                System.out.println("9- Afficher les classes d'un professeur ainsi que ses modules enseignés ");
                System.out.println("");

                System.out.println("55-Ajouter salle");
                System.out.println("56-Lister une salle");
                System.out.println("57-Modifier une salle");
                System.out.println("58-Archiver une salle");
                System.out.println("59-Lister tous les salles");
                System.out.println("Lister une salle :");
                System.out.println("");

                System.out.println("60-Lister les cours d'un professeur");
                 System.out.println("61-Lister les cours d'une classe");
                System.out.println("5-Quitter");
                System.out.println("Entrer votre choix ?");
                choix=sc.nextInt();

                switch (choix) {
                    case 1 :
                      sc.nextLine();
                      System.out.println("Entrer le libelle du Module");
                      String libelleModule = sc.nextLine();
                      
                      System.out.println("Entrer l'ID de la classe :");
                      int idClasse = sc.nextInt();

                      System.out.println("Entrer l'ID du cours :");
                      int idCours = sc.nextInt();

                        
                      Modules module = new Modules();
                        module.setLibelleModule(libelleModule);

                        boolean inserted = modulesService.ajouterModule(module,idClasse,idCours);
                        if (inserted) {
                            System.out.println("Le module a été ajouté avec succès.");
                        } else {
                            System.out.println("Erreur lors de l'ajout du module.");
                        }
                    
                    break;
                    case 2:
                        System.out.println("Liste des Modules :");
                        modulesService.listerModules().forEach(System.out::println);
                        break;
                    case 22:
                        System.out.println("Entrez l'ID de la classe pour laquelle vous souhaitez lister les modules :");
                        int idClasseModule = sc.nextInt();
                        List<Modules> moduleDuneClasse = modulesService.listerModulesDuneClasse(idClasseModule);

                        if (!moduleDuneClasse.isEmpty()) {
                            System.out.println("Modules de la classe :");
                            for (Modules modClasse : moduleDuneClasse) {
                                System.out.println(modClasse.toString());
                            }
                        } else {
                            System.out.println("Aucun module trouvé pour la classe avec l'ID : " + idClasseModule);
                        }
                        break;
                    
                    case 3:
                        sc.nextLine();
                        System.out.println("Entrer le libelle de la Classe :");
                        String libelleClasse = sc.nextLine();

                        System.out.println("Entrer l'ID de la salle :");
                        int idSalle = sc.nextInt();
                        
                        System.out.println("Entrer l'ID du cours :");
                        int idCour = sc.nextInt();
                        sc.nextLine();

                        Salle getSal = salleRepository.findById(idSalle);
                        Cour getCou = courRepository.findById(idCour);

                        Classe nouvelleClasse = new Classe();
                        nouvelleClasse.setLibelleClasse(libelleClasse);
                        nouvelleClasse.setArchive(false);
                        nouvelleClasse.setSalle(getSal);
                        nouvelleClasse.setCour(getCou);

                        classeService.ajouterClasse(nouvelleClasse);                       
                        
                        break;

                        case 4:
                            System.out.println("Liste des Classes :");
                            System.out.println(classeService.listerClasse());
                           
                            break;
                        case 5:
                            System.out.println("Entrer id de la classse a lister :");
                            int idClassePrinter = sc.nextInt();

                            Classe classe = new Classe(idClassePrinter);
                            classeService.listerUneClasse(classe).forEach(System.out::println);
                            break;
                        case 6:
                            System.out.println("Entrer l'ID de la classe à modifier :");
                            int idClasseModifier = sc.nextInt();
                            sc.nextLine();
                        
                            System.out.println("Entrer le nouveau libellé de classe :");
                            String newLibelleClasse = sc.nextLine();
                            boolean isArchived = false;

                            System.out.println("Entrer le nouveau id de la Salle :");
                            int idNewSalle = sc.nextInt();

                            System.out.println("Entrer le nouveau id de la Cour :");
                            int idNewCour = sc.nextInt();
                          
                            
                            Classe nouvelClasseModifier = new Classe(idClasseModifier);
                            nouvelClasseModifier.setLibelleClasse(newLibelleClasse);

                            Salle nouvelleSalle = new Salle(idNewSalle);
                            Cour nouveauCour = new Cour(idNewCour);

                            nouvelClasseModifier.setSalle(nouvelleSalle);
                            nouvelClasseModifier.setCour(nouveauCour);
                            nouvelClasseModifier.setArchive(isArchived);

                            classeService.modifierClasse(nouvelClasseModifier);

                            break;
                        case 7:
                            System.out.println("Entrer l'ID de la salle à archiver :");
                            int idClasseArchiver = sc.nextInt();

                            classeService.archiverClasse(idClasseArchiver);
                            break;
                        case 8:
                            System.out.println("Entrer id de la classe pour afficher ses modules");
                            int idClass = sc.nextInt();
                            //je liste dabord les modules pour qu'ils voient
                            //modulesService.listerModules().forEach(System.out::println);
                            Classe getLbClas = classeRepository.findById(idClass);
                            System.out.println("Pour la Classe:" +getLbClas.getLibelleClasse());
                            classeService.listerClasseDunModule(idClass).forEach(System.out::println);
                           
                            break;
                        case 9:
                        System.out.println("Entrer l'id de la classe");
                        int clsId = sc.nextInt();
                        List<Cour> courFoundByCls = courRepository.findCoursByClasse(clsId);
                        //j'ai les cours mtn je peux  prendre prof_id pour remonter jusua prof
                        courFoundByCls.forEach(System.out::println);

                        /*System.out.println("Entrer id du cour que cour pour Afficher les classes d'un professeur ainsi que ses modules enseignés ");
                        int courID = sc.nextInt();
                        Professeur professeur = classeRepository.findProfByCourID(courID);
                        
                        if (professeur != null) {
                            System.out.println("Professeur trouvé : ");
                            System.out.println("ID : " + professeur.getId());
                            System.out.println("Nom complet : " + professeur.getNomComplet());
                            System.out.println("Matière enseignée : " + professeur.getMatiereEnseigner());
                            System.out.println("Archive : " + professeur.isArchive());
                            System.out.println("Cours : " + professeur.getCours());
                        } else {
                            System.out.println("Aucun professeur trouvé pour l'ID de cours spécifié : " + courID);
                        }*/
                            break;

                        case 55:
                            Salle salles = new Salle();
                            System.out.println("Entrer libelleSalle :");
                            String libelleSalle = sc.next();
                            sc.nextLine();

                            System.out.println("Entrer la capacite :");
                            Double capacite = sc.nextDouble();
                            
                            System.out.println("Entrer numeroSalle :");
                            int numeroSalle = sc.nextInt();
                           
                            salles.setLibelleSalle(libelleSalle);
                            salles.setCapacite(capacite);
                            salles.setNumeroSalle(numeroSalle);

                            boolean salleAdded = salleService.ajouterSalle(salles);
                            if (salleAdded) {
                                System.out.println("La salle a été ajoutée avec succès.");
                            } else {
                                System.out.println("Erreur lors de l'ajout de la salle.");
                            }


                            System.out.println(salleService.listerSalles());
                           
                            break;
                        case 56:
                            System.out.println("Entrer id du salle a lister :");
                            int idSallePrinter = sc.nextInt();

                            Salle salle = new Salle(idSallePrinter);
                            salleService.listerUneSalle(salle).forEach(System.out::println);
                            


                            break;
                        case 57:
                            System.out.println("Entrer l'ID de la salle à modifier :");
                            int idSalleModifier = sc.nextInt();
                            sc.nextLine();
                        
                            System.out.println("Entrer le nouveau libellé de la salle :");
                            String newLibelleSalle = sc.nextLine();
                        
                            System.out.println("Entrer la nouvelle capacité de la salle :");
                            double nouvelleCapacite = sc.nextDouble();
                        
                            System.out.println("Entrer le nouveau numéro de la salle :");
                            int nouveauNumeroSalle = sc.nextInt();
                            
                            Salle salleModifier = new Salle(idSalleModifier);
                            salleModifier.setLibelleSalle(newLibelleSalle);
                            salleModifier.setCapacite(nouvelleCapacite);
                            salleModifier.setNumeroSalle(nouveauNumeroSalle);

                            salleService.modifierSalle(salleModifier);
                            
                            break;
                        case 58:
                            System.out.println("Entrer l'ID de la salle à archiver :");
                            int idSalleArchiver = sc.nextInt();

                            salleService.archiverSalle(idSalleArchiver);
                            break;
                        
                            case 60:
                                System.out.println("Entrer l'id du profeseur :");
                                int idCourProf = sc.nextInt();
                                sc.nextLine();
                                System.out.println("Entrer nomComplet du profeseur :");
                                String nomCompletProf = sc.nextLine();
                                
                                Professeur prof = new Professeur(idCourProf,nomCompletProf);
                                courService.ListerCourParProfesseur(prof).forEach(System.out::println);
                            break;

                            case 59:
                                System.out.println("Lister des salles :");
                                salleService.listerSalles().forEach(System.out::println);
                                break;
                        
                            case 61:
                        // Lister les cours d'une classe
                        System.out.println("Entrez l'ID de la classe pour laquelle vous souhaitez afficher les cours :");
                        int idClasseCours = sc.nextInt();

                        List<Cour> courClasse = courRepository.findCoursByClasse(idClasseCours);
                        if (!courClasse.isEmpty()) {
                            System.out.println("Cours de la classe :");
                            for (Cour cour : courClasse) {
                                System.out.println(cour.toString());
                            }
                        } else {
                            System.out.println("Aucun cours trouvé pour la classe avec l'ID : " + idClasseCours);
                        }
                        break;


                        /*case 8:
                            // Test de la méthode findById
                            int idToFind = 1; // Modifier en fonction de l'ID que vous souhaitez rechercher
                            Salle foundSalle = salleRepository.findById(idToFind);

                            if (foundSalle != null) {
                                System.out.println("Salle trouvée : " + foundSalle.toString());
                            } else {
                                System.out.println("Aucune salle trouvée avec l'ID : " + idToFind);
                            }
                            break;*/
                       
                        //case 9:
                            /*// Test de la méthode findById
                            int idf = 2; // Modifier en fonction de l'ID que vous souhaitez rechercher
                            List<Classe> foundClasses = classeRepository.findClasseById(idf);

                            if (foundClasses != null && !foundClasses.isEmpty()) {
                                for (Classe classe : foundClasses) {
                                    System.out.println("Classe trouvée : " + classe.toString());
                                }
                            } else {
                                System.out.println("Aucune classe trouvée avec l'ID : " + idf);
                            }
                            break;*/
                    }
                
            } while (choix!=59);
        }
      
    }
}