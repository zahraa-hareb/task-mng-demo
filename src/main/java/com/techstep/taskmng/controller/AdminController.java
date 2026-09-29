package com.techstep.taskmng.controller;

import com.techstep.taskmng.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/admin")
public class AdminController {

    private final AppUserService appUserService;

    @GetMapping
    public String getUsers(Model model){
        model.addAttribute("users",appUserService.findAll());
        return "admin/index";
    }
}
