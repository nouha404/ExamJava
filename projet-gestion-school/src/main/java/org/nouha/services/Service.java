package org.nouha.services;

import java.util.List;

public interface Service<T> {
    T searchById(List<T> datasList, int id);
}
