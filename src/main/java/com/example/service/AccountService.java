package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account persistAccount(Account account) {
        return accountRepository.save(account);
    }

    /**
     * @param id
     * @return account related to id
     */ 
    public Account getAccountById(int id) {
        Optional<Account> optAccount = accountRepository.findById(id);
        if (optAccount.isPresent())
        {
            return optAccount.get();
        }
        else
        {
            return null;
        }
    }

    /**
     * @param id
     * @param password
     * @return account related to id
     */
    public Account login(int id, String password) {
        Optional<Account> optAccount = accountRepository.findById(id);
        if (optAccount.isPresent())
        {
            Account account = optAccount.get();
            if (account.getPassword() == password)
            {
                return account;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }
}
