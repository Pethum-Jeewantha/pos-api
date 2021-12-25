/*
 * Copyright (c) 2021 - present Pethum Jeewantha. All rights reserved.
 *  Licensed under the MIT License. See LICENSE.txt in the project root for license information.
 */

package com.pethumjeewanha.pos.dao;

import javax.persistence.EntityManager;

public interface SuperDAO {

    void setEntityManager(EntityManager em);

}
