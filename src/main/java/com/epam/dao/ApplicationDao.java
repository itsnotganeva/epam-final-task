package com.epam.dao;

import com.epam.dao.mapping.ApplicationMapper;
import com.epam.dao.mapping.PersonMapper;
import com.epam.dao.mapping.RowMapper;
import com.epam.entity.Application;

import java.util.Map;

public class ApplicationDao extends AbstractDao<Application>{

    private static ApplicationDao instance;

    private ApplicationDao() {
        super(new ApplicationMapper(), "application");
        instance = this;
    }

    public static ApplicationDao getInstance() {
        if (instance == null) {
            ApplicationDao.instance = new ApplicationDao();
        }
        return instance;
    }

    @Override
    protected Map<String, String> updateValues(Application application) {
        return Map.of("seats_number",application.getSeatsNumber().toString(),
                "apartments_class", String.valueOf(application.getApartmentsClass().ordinal()),
                "check_in_date", "'" + String.valueOf(application.getCheckInDate()) + "'",
                "check_out_date", "'" + String.valueOf(application.getCheckOutDate()) + "'",
                "person_id", application.getPersonId().toString());
    }
}
