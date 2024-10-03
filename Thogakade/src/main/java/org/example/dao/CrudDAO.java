package org.example.dao;

import com.sun.xml.bind.v2.model.core.ID;


import java.util.ArrayList;
import java.util.List;


public interface CrudDAO<T> extends SuperDAO {
    //save a new entity to the database
    boolean save(T entity);

    // Find an entity by its ID
    T findById(ID id);

    // Update an existing entity
    boolean update(T entity);

    // Delete an entity by its ID
    boolean delete(String id);

    // Get all entities
    List<T> getAll();

}
