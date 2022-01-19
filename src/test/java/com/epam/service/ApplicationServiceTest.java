package com.epam.service;

import com.epam.dao.ApplicationDao;
import com.epam.dao.PersonDao;
import com.epam.entity.ApartmentClass;
import com.epam.entity.Application;
import com.epam.entity.Person;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.sql.Date;
import java.util.List;

public class ApplicationServiceTest {

    @Test
    public void testApply() {

        Application application = new Application();

        application.setSeatsNumber(2);
        application.setApartmentsClass(ApartmentClass.ECONOMY);
        String checkIn="2022-01-18";
        application.setCheckInDate(Date.valueOf(checkIn));
        String checkOut="2022-01-25";
        application.setCheckOutDate(Date.valueOf(checkOut));

        List<Person> persons = PersonDao.getInstance().findAll();

        application.setPersonId(persons.get(0).getId());

        Application applicationAfterSave = ApplicationService.getInstance().apply(application);

        Assertions.assertEquals(application, applicationAfterSave);
    }

    @Test
    public void testGetById() {
        Application application = new Application();

        application.setSeatsNumber(2);
        application.setApartmentsClass(ApartmentClass.BUSINESS);
        String checkIn="2022-01-08";
        application.setCheckInDate(Date.valueOf(checkIn));
        String checkOut="2022-01-15";
        application.setCheckOutDate(Date.valueOf(checkOut));

        List<Person> persons = PersonDao.getInstance().findAll();

        application.setPersonId(persons.get(0).getId());

        Application applicationAfterSave = ApplicationService.getInstance().apply(application);

        Application applicationById = ApplicationService.getInstance().getById(applicationAfterSave.getId());

        Assertions.assertEquals(applicationAfterSave, applicationById);
    }

    @Test
    public void testDelete() {
        Application application = new Application();

        application.setSeatsNumber(2);
        application.setApartmentsClass(ApartmentClass.BUSINESS);
        String checkIn="2022-01-08";
        application.setCheckInDate(Date.valueOf(checkIn));
        String checkOut="2022-01-15";
        application.setCheckOutDate(Date.valueOf(checkOut));

        List<Person> persons = PersonDao.getInstance().findAll();

        application.setPersonId(persons.get(0).getId());

        Application applicationAfterSave = ApplicationService.getInstance().apply(application);

        ApplicationService.getInstance().delete(applicationAfterSave);

        Application findApplication = ApplicationService.getInstance().getById(applicationAfterSave.getId());

        Assertions.assertNull(findApplication);
    }
}
