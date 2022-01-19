package com.epam.dao.mapping;

import com.epam.entity.ApartmentClass;
import com.epam.entity.Application;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationMapper implements RowMapper<Application> {

    @Override
    public Application toObject(ResultSet rs) throws SQLException {
        Application application = new Application();
        application.setId(rs.getLong("id"));
        application.setSeatsNumber(rs.getInt("seats_number"));
        int ordinal = rs.getInt("apartments_class");
        application.setApartmentsClass(ApartmentClass.getByOrdinal(ordinal));
        application.setCheckInDate(rs.getDate("check_in_date"));
        application.setCheckOutDate(rs.getDate("check_out_date"));
        application.setPersonId(rs.getLong("person_id"));
        return application;
    }
}
