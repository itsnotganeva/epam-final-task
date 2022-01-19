package com.epam.service;

import com.epam.dao.PersonDao;
import com.epam.dao.RoomDao;
import com.epam.entity.Account;
import com.epam.entity.Person;
import com.epam.entity.Room;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class AccountServiceTest {

    @Test
    public void testCreate() {
        Account account = new Account();

        List<Room> rooms = RoomDao.getInstance().findAll();
        List<Person> persons = PersonDao.getInstance().findAll();

        account.setRoomId(rooms.get(1).getId());
        account.setPersonId(persons.get(0).getId());

        Account accountAfterSave = AccountService.getInstance().create(account);
        Assertions.assertEquals(account, accountAfterSave);
    }


    @Test
    public void testGetByPerson() {

        Account account = new Account();

        List<Room> rooms = RoomDao.getInstance().findAll();
        List<Person> persons = PersonDao.getInstance().findAll();

        account.setRoomId(rooms.get(0).getId());
        account.setPersonId(persons.get(0).getId());

        Account accountAfterSave = AccountService.getInstance().create(account);

        List<Account> accounts = AccountService.getInstance().getByPersonId(accountAfterSave.getPersonId());

        Assertions.assertEquals(accountAfterSave.getPersonId(), accounts.get(0).getPersonId());
    }
}
