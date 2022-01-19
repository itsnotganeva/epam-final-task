package com.epam.dao;

import com.epam.dao.jdbc.ConnectionPoolProvider;
import com.epam.dao.mapping.AccountMapper;
import com.epam.dao.mapping.PersonMapper;
import com.epam.entity.Account;
import com.epam.entity.Person;
import com.epam.exception.EntityNotFoundException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AccountDao extends AbstractDao<Account> {

    private static AccountDao instance;

    private AccountDao() {
        super(new AccountMapper(), "account");
        instance = this;
    }

    public static AccountDao getInstance() {
        if (instance == null) {
            AccountDao.instance = new AccountDao();
        }
        return instance;
    }

    @Override
    protected Map<String, String> updateValues(Account account) {
        return Map.of("person_id", account.getPersonId().toString(),
                "room_id", account.getRoomId().toString());
    }

    public List<Account> findByPerson(Long id) {
        String SELECT_BY_LOGIN = String.format(SELECT_STATEMENT, " account ").concat("WHERE person_id = ")
                .concat(id.toString());
        try (Connection connection = ConnectionPoolProvider.getConnection()) {

            ResultSet resultSet = connection.createStatement().executeQuery(SELECT_BY_LOGIN);
            List<Account> accounts = new ArrayList<>();
            while (resultSet.next()) {
                Account account = getRm().toObject(resultSet);
                accounts.add(account);
            } return accounts;

        } catch (SQLException e) {
            LOGGER.error("Something went wrong during account retrieval by id=", e);
            throw new EntityNotFoundException(e);
        }
    }


}
