package com.epam.service;

import com.epam.dao.PersonDao;
import com.epam.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PersonService {
    public static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);

    private static PersonService instance;

    private PersonService() {
        instance = this;
    }

    public static PersonService getInstance() {
        if (instance == null) {
            PersonService.instance = new PersonService();
        }
        return instance;
    }

    public Person registerUser(Person person) {

        Person save = PersonDao.getInstance().save(person);

        return save;
    }

    public Person getById(Long id) {
        return PersonDao.getInstance().getById(id);
    }

    public Person getByLoginAndPass(String login, String password) {
        Person person = PersonDao.getInstance().findByLoginAndPass(login, password);
        return person;
    }


}
