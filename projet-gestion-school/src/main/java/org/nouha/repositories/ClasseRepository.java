package org.nouha.repositories;

import java.util.List;

import org.nouha.entities.Classe;

public interface ClasseRepository extends Repository<Classe> {
    List<Classe> findClasseById(int classeId);
    List<Classe> findAll();
    List<Classe> listerClasses();
    Classe findById(int id);
}
