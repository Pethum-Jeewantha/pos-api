package com.pethumjeewanha.pos.service.custom.impl;

import com.pethumjeewanha.pos.dao.custom.ItemDAO;
import com.pethumjeewanha.pos.dto.ItemDTO;
import com.pethumjeewanha.pos.exception.DuplicateEntityException;
import com.pethumjeewanha.pos.exception.EntityNotFoundException;
import com.pethumjeewanha.pos.service.custom.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.pethumjeewanha.pos.service.util.EntityDTOMapper.INSTANCE;

@Transactional
@Component
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDAO itemDAO;

    @Override
    public void saveItem(ItemDTO item) {
        if (existItem(item.getCode())) {
            throw new DuplicateEntityException(item.getCode() + " already exists");
        }
        itemDAO.save(INSTANCE.fromItemDTO(item));
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public boolean existItem(String code) {
        return itemDAO.existsById(code);
    }

    @Override
    public void updateItem(ItemDTO item) {
        if (!existItem(item.getCode())) {
            throw new EntityNotFoundException("There is no such item associated with the id " + item.getCode());
        }
        itemDAO.update(INSTANCE.fromItemDTO(item));
    }

    @Override
    public void deleteItem(String code) {
        if (!existItem(code)) {
            throw new EntityNotFoundException("There is no such item associated with the id " + code);
        }
        itemDAO.deleteById(code);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public ItemDTO findItem(String code) {
        return INSTANCE.toItemDTO(itemDAO.findById(code).orElseThrow(() -> new EntityNotFoundException("There is no such item associated with the id " + code)));
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<ItemDTO> findAllItems() {
        return INSTANCE.toItemDTOList(itemDAO.findAll());
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<ItemDTO> findAllItems(int page, int size) {
        return INSTANCE.toItemDTOList(itemDAO.findAll(page, size));
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public String generateNewItemCode() {
        String code = itemDAO.getLastItemCode();
        if (code != null) {
            int newItemCode = Integer.parseInt(code.replace("I", "")) + 1;
            return String.format("I%03d", newItemCode);
        } else {
            return "I001";
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public long getItemsCount() {
        return itemDAO.count();
    }

}
