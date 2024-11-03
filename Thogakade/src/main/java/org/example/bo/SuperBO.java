package org.example.bo;

import org.example.dto.SuperDTO;

import java.util.List;

public interface SuperBO<T extends SuperDTO> {
    Boolean save(T entity);

    Boolean update(T entity);

    Boolean delete(String id);

    List<T> getAll();
}
