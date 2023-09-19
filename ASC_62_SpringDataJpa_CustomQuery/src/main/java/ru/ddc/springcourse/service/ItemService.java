package ru.ddc.springcourse.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ddc.springcourse.model.Item;
import ru.ddc.springcourse.model.Person;
import ru.ddc.springcourse.repository.ItemRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findByName(String name) {
        return itemRepository.findByName(name);
    }

    public List<Item> findByPerson(Person person) {
        return itemRepository.findByPerson(person);
    }
}
