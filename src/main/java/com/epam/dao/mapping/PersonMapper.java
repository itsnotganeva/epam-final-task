package com.epam.dao.mapping;

import com.epam.entity.Person;
import com.epam.entity.PersonRole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person toObject(ResultSet rs) throws SQLException {
        Person person = new Person();
        person.setId(rs.getLong("id"));
        person.setFirstName(rs.getString("first_name"));
        person.setLastName(rs.getString("last_name"));
        person.setLogin(rs.getString("login"));
        person.setPassword(rs.getString("password"));
        person.setEmail(rs.getString("email"));
        int ordinal = rs.getInt("role");
        person.setRole(PersonRole.getByOrdinal(ordinal));
        return person;
    }
}
