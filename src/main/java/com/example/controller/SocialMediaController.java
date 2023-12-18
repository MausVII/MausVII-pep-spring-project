package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.entity.*;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    @Autowired
    AccountService accountService;
    @Autowired
    MessageService messageService;

    @PostMapping("/register")
    public Account postAccount(@RequestBody Account account) {
        return null;
    }

    @PostMapping("/login")
    public Account Login(@RequestBody Account account) {
        Account foundAccount = accountService.login(account.getAccount_id(), account.getPassword());
        if (foundAccount != null)
        {
            return foundAccount;
        }
        else
        {
            return null;
        }
    }

    @PostMapping("/messages")
    public Message postMessage(@RequestBody Message msg) {
        return null;
    }

    @GetMapping("/messages")
    public List<Message> getMessages() {
        return null;
    }

    @GetMapping("/messages/{id}")
    public Message getMessage(@PathVariable int id) {
        return null;
    }

    @DeleteMapping("/messages/{id}")
    public Message deleteMessage(@PathVariable int id) {
        return null;
    }

    @PatchMapping("/messages/{id}")
    public Message updateMessage(@PathVariable int id, @RequestBody Message msg) {
        return null;
    }

    @GetMapping("/accounts/{id}/messages")
    public List<Message> getAccountMessages(@PathVariable int id) {
        return null;
    }
}