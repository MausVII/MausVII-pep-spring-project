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
     * @param account
     * @return
     */
    public Account register(Account account) {
        if (account.getUsername() == "" || account.getPassword().length() < 4) return null;

        Account existsAccount = accountRepository.findAccountByUsername(account.getUsername());
        if (existsAccount == null) {
            accountRepository.save(account);
            Account retAccount = getAccountById(account.getAccount_id());
            return retAccount;
        }
        else {
            // return account with null id so controller can send correct http code
            return account;
        }
    }

    /**
     * @param account
     * @return account related to id
     */
    public Account login(Account account) {

        Optional<Account> optAccount = accountRepository.findById(9999);
        if (optAccount.isPresent())
        {
            Account foundAccount = optAccount.get();
            if (foundAccount.getUsername().compareTo(account.getUsername()) == 0 && foundAccount.getPassword().compareTo(account.getPassword()) == 0)
            {
                return foundAccount;
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
