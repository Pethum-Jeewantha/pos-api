/*
 * Copyright (c) 2021 - present Pethum Jeewantha. All rights reserved.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.pethumjeewanha.pos.service.custom;


import com.pethumjeewanha.pos.dto.ItemDTO;
import com.pethumjeewanha.pos.service.SuperService;

import java.util.List;

public interface ItemService extends SuperService {

    void saveItem(ItemDTO item);

    long getItemsCount();

    boolean existItem(String code);

    void updateItem(ItemDTO item);

    void deleteItem(String code);

    ItemDTO findItem(String code);

    List<ItemDTO> findAllItems();

    List<ItemDTO> findAllItems(int page, int size);

    String generateNewItemCode();
}
