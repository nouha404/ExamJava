package org.nouha.repositories;

import java.util.List;

public interface Repository<T> {
    int insert(T data);
    List<T> findAll();    
    void update(T data);
    void hidden(T data);
    

}
