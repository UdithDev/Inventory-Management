package org.example.dao;

import com.sun.xml.bind.v2.model.core.ID;
import org.example.entity.SuperEntity;
import org.hibernate.Session;


import java.util.ArrayList;
import java.util.List;


public interface CrudDAO<T extends SuperEntity> extends SuperDAO {
    //save a new entity to the database
    boolean save(T entity, Session Session);

    // Update an existing entity
    boolean update(T entity, Session session);

    // Delete an entity by its ID
    Boolean delete(String id, Session session);

    // Get all entities
    List<T> getAll(Session session);

    T search(String id,Session session);

}
