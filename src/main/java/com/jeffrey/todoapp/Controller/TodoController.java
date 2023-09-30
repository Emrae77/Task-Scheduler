package com.jeffrey.todoapp.Controller;

import com.jeffrey.todoapp.Dao.TodoService;
import com.jeffrey.todoapp.Model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class TodoController {
    @Autowired
    TodoService tService;


    @GetMapping({"/viewToDoList"})
    @ResponseBody // Return the response as JSON
    public List<Todo> viewToDoList() {
        return tService.getAllToDoItems();
    }


    @PostMapping("/addToDoItem")
    @ResponseBody // Return the response as JSON
    public ResponseEntity<String> addToDoItem(@RequestBody Todo todo) {
        Todo addedTodo = tService.addTodo(todo);
        if (addedTodo != null) {
            return ResponseEntity.ok("Todo item added successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to add Todo item");
        }
    }


    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<String> updateStatus(@PathVariable int id) {
        boolean statusUpdated = tService.updateStatus(id);
        if (statusUpdated) {
            return ResponseEntity.ok("Todo item status updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to update Todo item status");
        }
    }




        @DeleteMapping("/deleteTodoItem/{id}")
        public ResponseEntity<String> deleteTodoItem(@PathVariable int id){
            boolean itemDeleted = tService.deleteToDoItem(id);
            if (itemDeleted) {
                return ResponseEntity.ok("Todo item deleted successfully");
            } else {
                return ResponseEntity.badRequest().body("Failed to delete Todo item");
            }
        }
    }



//  @PostMapping("/saveOrUpdateTodoItem")
//    public ResponseEntity<String> saveOrUpdateTodoItem(@RequestBody Todo todo) {
//        Optional<Todo> existingTodo = tService.getItemsById(todo.getId());
//
//    }
//        if (existingTodo.isPresent()) {
//            // The ID exists, update the existing object
//            Todo updatedTodo = tService.updateTodoItem(existingTodo.get(), todo);
//            if (updatedTodo != null) {
//                return ResponseEntity.ok("Todo item updated successfully");
//            } else {
//                return ResponseEntity.badRequest().body("Failed to update Todo item");
//            }
//        } else {
//            // The ID doesn't exist, create a new object
//            Todo savedTodo = tService.addTodo(todo);
//            if (savedTodo != null) {
//                return ResponseEntity.ok("New Todo item saved successfully");
//            } else {
//                return ResponseEntity.badRequest().body("Failed to save new Todo item");
//            }
//        }
//    }


