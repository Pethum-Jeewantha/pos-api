/*
 * Copyright (c) 2021 - present Pethum Jeewantha. All rights reserved.
 *  Licensed under the MIT License. See LICENSE.txt in the project root for license information.
 */

package com.pethumjeewanha.pos.dao;

import com.pethumjeewanha.pos.entity.SuperEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CrudDAO<T extends SuperEntity, ID extends Serializable> extends SuperDAO {

    void save(T entity);

    void update(T entity);

    void deleteById(ID key);

    Optional<T> findById(ID key);

    List<T> findAll();

    long count();

    boolean existsById(ID key);

    List<T> findAll(int page, int size);

}
