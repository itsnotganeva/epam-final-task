package com.epam.service;

import com.epam.dao.AccountDao;
import com.epam.entity.Account;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountService {

    private static AccountService instance;

    private AccountService() {
        instance = this;
    }
    public static AccountService getInstance() {
        if (instance == null) {
            AccountService.instance = new AccountService();
        }
        return instance;
    }

    public Account create(Account account) {
        return AccountDao.getInstance().save(account);
    }

    public List<Account> getByPersonId(Long id) {
        return AccountDao.getInstance().findByPerson(id);
    }

}
