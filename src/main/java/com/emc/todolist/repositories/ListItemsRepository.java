package com.emc.todolist.repositories;

import com.emc.todolist.models.ListItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListItemsRepository extends JpaRepository<ListItems, Long> {
}
