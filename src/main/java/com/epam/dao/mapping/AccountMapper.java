package com.epam.dao.mapping;

import com.epam.entity.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper implements RowMapper<Account> {

    @Override
    public Account toObject(ResultSet rs) throws SQLException {
        Account account = new Account();
        account.setId(rs.getLong("id"));
        account.setPersonId(rs.getLong("person_id"));
        account.setRoomId(rs.getLong("room_id"));
        return account;
    }
}
