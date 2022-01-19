package com.epam.controllers;

import com.epam.entity.*;
import com.epam.service.AccountService;
import com.epam.service.ApplicationService;
import com.epam.service.PersonService;
import com.epam.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonController {

    @GetMapping()
    public String index(Model model){
        return "index";
    }

    @GetMapping("/registration")
    public String signUp(@ModelAttribute("person") Person person){
        return "signUpPage";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person) {
        person.setRole(PersonRole.USER);
        PersonService.getInstance().registerUser(person);
        return "userPage";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute("person") Person person) {
       return "logInPage";
    }

    @GetMapping("/login/person")
    public String personPage(@RequestParam("login") String login, @RequestParam("password") String password,
                             Model model) {
        model.addAttribute("person", PersonService.getInstance().getByLoginAndPass(login, password));
        if (PersonService.getInstance().getByLoginAndPass(login, password).getRole() == PersonRole.ADMIN) {
            return "adminPage";
        } else if (PersonService.getInstance().getByLoginAndPass(login, password).getRole() == PersonRole.USER) {
            return "userPage";
        }
        else return null;
    }

    @GetMapping("/check")
    public String check(@ModelAttribute("person") Person person) {
        return "check";
    }

    @GetMapping("/check/account")
    public String showAccount(@RequestParam("login") String login, @RequestParam("password") String password,
                              Model model) {
        Person person = PersonService.getInstance().getByLoginAndPass(login, password);
        List<Account> accounts = AccountService.getInstance().getByPersonId(person.getId());
        List<Room> rooms = new ArrayList<>();
        for (Account account : accounts) {
            rooms.add(RoomService.getInstance().getById(account.getRoomId()));
        }
        model.addAttribute("rooms", rooms);
        return "account";
    }

}
