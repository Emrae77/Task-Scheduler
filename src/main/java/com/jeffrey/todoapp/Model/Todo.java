package com.jeffrey.todoapp.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "todo" )
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private  int id;

    @NotNull
   private String title;
    @NotNull
   private String content;
    @NotNull
   private LocalDate date;
    @NotNull
   private String status;

   @ManyToOne
   @JoinColumn(name = "user_id")
    private User user;


}
