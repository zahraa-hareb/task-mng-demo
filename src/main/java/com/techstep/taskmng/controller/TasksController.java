package com.techstep.taskmng.controller;

import com.techstep.taskmng.model.Task;
import com.techstep.taskmng.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/tasks")
public class TasksController {

    private final TaskService taskService;

    @GetMapping
    public String getTasks(Model model){
        model.addAttribute("tasks",taskService.findAll());
        model.addAttribute("task", new Task());
        return "tasks/index";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("task") Task task, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("tasks", taskService.findAll());
            return "tasks/index";
        }
        taskService.create(task);
        return "redirect:/tasks";
    }
}
