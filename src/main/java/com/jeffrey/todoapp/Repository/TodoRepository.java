package com.jeffrey.todoapp.Repository;

import com.jeffrey.todoapp.Model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
}
