package com.emc.todolist.controllers;

import com.emc.todolist.models.ListItems;
import com.emc.todolist.repositories.ListItemsRepository;
import com.emc.todolist.services.ListService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/listitems")
public class ToDoListController {
    @Autowired
    private ListItemsRepository listItemsRepository;

    @Autowired
    private ListService listService;

    @GetMapping
    public List<ListItems> list() {
        return listItemsRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public ListItems get(@PathVariable Long id){
        return listItemsRepository.getOne(id);
    }
    /*public ResponseEntity<ListItems> get(@PathVariable Long id) {
        ListItems item = listService.get(id);
        return new ResponseEntity<ListItems>(item, HttpStatus.OK);
    }*/

    @PostMapping
    public ListItems create(@RequestBody final ListItems listItem) {
        return listItemsRepository.saveAndFlush(listItem);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        //will not delete entries with child records
        listItemsRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ListItems update(@PathVariable Long id, @RequestBody ListItems listItem) {
        ListItems existingItem = listItemsRepository.getOne(id);
        BeanUtils.copyProperties(listItem, existingItem, "id");
        return listItemsRepository.saveAndFlush(existingItem);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PATCH)
    public ListItems patch(@PathVariable Long id, @RequestBody ListItems listItem) {
        ListItems existingItem = listItemsRepository.getOne(id);
        BeanUtils.copyProperties(listItem, existingItem, "id");
        return listItemsRepository.saveAndFlush(existingItem);
    }
}
