package org.example.dao;

import com.sun.xml.bind.v2.model.core.ID;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO <T> extends SuperDAO{
   //save a new entity to the database
    boolean save(T entity);

    // Find an entity by its ID
    T findById(ID id);

    // Update an existing entity
    boolean update(T entity);

    // Delete an entity by its ID
    boolean delete(ID id);

    // Get all entities
    List<T> getAll();

}
