package com.jeffrey.todoapp.Dao;

import com.jeffrey.todoapp.Model.Todo;
import com.jeffrey.todoapp.Repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TodoService {
    TodoRepository tRep;

    public List<Todo> getAllToDoItems(){
        List<Todo> toDoList = new ArrayList<>();
        tRep.findAll().forEach(todo->  toDoList.add(todo));
        return toDoList;
    }
//    public List<Todo> getAllToDoItems(){
//        return tRep.findAll();
//    }
     public Optional<Todo> getItemsById(int id){
             return tRep.findById(id);
         }

    public   Todo addTodo(Todo todo){
        tRep.save(todo);
        return todo;

    }

    public boolean updateStatus(int id) {
            Todo todo = tRep.findById(id).orElse(null);

            if (todo != null) {
                todo.setStatus("completed");
                addTodo(todo);
                System.out.println("Status updated successfully.");
                return true;
            } else {
                System.out.println("ID not found.");
                return false;
            }
        }

    public boolean deleteToDoItem(int id) {
        tRep.deleteById(id);

        if (tRep.findById(id).isPresent()) {
            System.out.println("Item with ID " + id + " not deleted.");
            return false;
        } else {
            System.out.println("Item with ID " + id + " deleted successfully.");
            return true;
        }
    }





}
