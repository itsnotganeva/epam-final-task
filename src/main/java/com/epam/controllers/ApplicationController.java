package com.epam.controllers;

import com.epam.dao.AccountDao;
import com.epam.dao.ApplicationDao;
import com.epam.entity.Account;
import com.epam.entity.Application;
import com.epam.entity.Person;
import com.epam.entity.Room;
import com.epam.service.AccountService;
import com.epam.service.ApplicationService;
import com.epam.service.PersonService;
import com.epam.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/application")
public class ApplicationController {

    private final ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/apply")
    public String newApp(@ModelAttribute("application") Application application) {
        return "apply";
    }

    @PostMapping()
    public String create(@ModelAttribute("application") Application application) {

        String login = application.getPerson().getLogin();
        String password = application.getPerson().getPassword();
        Person person = PersonService.getInstance().getByLoginAndPass(login, password);
        application.setPersonId(person.getId());
        applicationService.apply(application);
        return "/userPage";
    }

    @GetMapping("/all")
    public String showAll(Model model) {
        model.addAttribute("applications", ApplicationService.getInstance().getAll());
        return "apps";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Application application = ApplicationService.getInstance().getById(id);
        model.addAttribute("ap", application);
        Room room = RoomService.getInstance().getRoomByApp(application);
        Account account = new Account();
        account.setPersonId(application.getPersonId());
        account.setRoomId(room.getId());
        AccountService.getInstance().create(account);
        room.setReleaseDate(application.getCheckOutDate());
        RoomService.getInstance().update(room);
        ApplicationService.getInstance().delete(application);
        return "showApp";
    }

}
