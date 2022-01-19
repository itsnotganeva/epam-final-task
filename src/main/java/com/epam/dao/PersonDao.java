package com.epam.dao;

import com.epam.dao.jdbc.ConnectionPoolProvider;
import com.epam.dao.mapping.PersonMapper;
import com.epam.entity.Person;
import com.epam.exception.EntityNotFoundException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class PersonDao extends AbstractDao<Person> {

    private static PersonDao instance;

    private PersonDao() {
        super(new PersonMapper(), "person");
        instance = this;
    }

    public static PersonDao getInstance() {
        if (instance == null) {
            PersonDao.instance = new PersonDao();
        }
        return instance;
    }

    @Override
    protected Map<String, String> updateValues(Person person) {
        return Map.of("first_name", "'" + person.getFirstName() + "'",
                "last_name", "'" + person.getLastName() + "'",
                "login", "'" + person.getLogin() + "'",
                "password", "'" + person.getPassword() + "'",
                "email", "'" + person.getEmail() + "'",
                "role", String.valueOf(person.getRole().ordinal()));
    }

    public Person findByLogin(String login) {
        String SELECT_BY_LOGIN = String.format(SELECT_STATEMENT, " person ").concat("WHERE login = ").concat("'")
                .concat(login).concat("'");
        try (Connection connection = ConnectionPoolProvider.getConnection()) {

            ResultSet resultSet = connection.createStatement().executeQuery(SELECT_BY_LOGIN);

            if (resultSet.next()) {

                Person entity = getRm().toObject(resultSet);
                return entity;
            } else {
                return null;
            }

        } catch (SQLException e) {
            LOGGER.error("Something went wrong during user retrieval by login=" + login, e);
            throw new EntityNotFoundException(e);
        }
    }

    public Person findByLoginAndPass(String login, String password) {
        String SELECT_BY_LOGIN = String.format(SELECT_STATEMENT, " person ").concat("WHERE login = ").concat("'")
                .concat(login).concat("'");
        String SELECT_BY_LOGIN_AND_PASS = String.format(SELECT_BY_LOGIN).concat("AND password = ").concat("'")
                .concat(password).concat("'");
        try (Connection connection = ConnectionPoolProvider.getConnection()) {

            ResultSet resultSet = connection.createStatement().
                    executeQuery(String.format(SELECT_BY_LOGIN_AND_PASS, tableName, login, password));

            if (resultSet.next()) {

                Person entity = getRm().toObject(resultSet);
                return entity;
            } else {
                return null;
            }

        } catch (SQLException e) {
            LOGGER.error("Something went wrong during user retrieval by login=" + login, e);
            throw new EntityNotFoundException(e);
        }
    }
}
