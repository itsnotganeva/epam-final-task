package com.epam.service;

import com.epam.entity.Person;
import com.epam.entity.PersonRole;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class PersonServiceTest {

    @Test
    public void testRegisterUser() {

        Person person = new Person();

        person.setFirstName("Ivan");
        person.setLastName("Ivanov");
        person.setLogin("user");
        person.setPassword("password");
        person.setEmail("ivanov.ivan@gmail.com");
        person.setRole(PersonRole.USER);

        Person personAfterSave = PersonService.getInstance().registerUser(person);

        Assertions.assertEquals(person, personAfterSave);
    }

    @Test
    public void testGetById() {
        Person person = new Person();

        person.setFirstName("Matvey");
        person.setLastName("Ganevich");
        person.setLogin("ganeva");
        person.setPassword("password");
        person.setEmail("ganevich.matvei@gmail.com");
        person.setRole(PersonRole.USER);

        Person personAfterSave = PersonService.getInstance().registerUser(person);

        Person personById = PersonService.getInstance().getById(personAfterSave.getId());

        Assertions.assertEquals(personAfterSave, personById);
    }

    @Test
    public void testGetByLoginAndPass() {
        Person person = new Person();

        person.setFirstName("Ivan");
        person.setLastName("Ivanov");
        person.setLogin("vanya");
        person.setPassword("password");
        person.setEmail("ganevich.matvei@gmail.com");
        person.setRole(PersonRole.USER);

        Person personAfterSave = PersonService.getInstance().registerUser(person);

        Person personByLoginAndPass = PersonService.getInstance().getByLoginAndPass(personAfterSave.getLogin(), personAfterSave.getPassword());

        Assertions.assertEquals(personAfterSave, personByLoginAndPass);
    }
}
