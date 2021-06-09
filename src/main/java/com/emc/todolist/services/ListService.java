package com.emc.todolist.services;

import java.util.List;



import javax.transaction.Transactional;


import com.emc.todolist.models.ListItems;
import com.emc.todolist.repositories.ListItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



    @Service
    @Transactional
    public class ListService {



        @Autowired
        private ListItemsRepository repo;



        public List<ListItems> listAll() {
            return repo.findAll();
        }



        public void save(ListItems item) {
            repo.save(item);
        }



        public ListItems get(Long id) {
            return repo.findById(id).get();
        }



        public void delete(Long id) {
            repo.deleteById(id);
        }

}
