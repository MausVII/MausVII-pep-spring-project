package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> postAccount(@RequestBody Account account) {
        System.out.println(account.toString());
        Account registeredAccount = accountService.register(account);
        if (registeredAccount == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else if (registeredAccount.getAccount_id() == null) {
            return new ResponseEntity<>(null, null, HttpStatus.CONFLICT);
        }
        else
        {
            return new ResponseEntity<>(registeredAccount, null, HttpStatus.OK);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody Account account) {
        System.out.println(account.toString());
        Account foundAccount = accountService.login(account);
        if (foundAccount != null)
        {
            return new ResponseEntity<>(foundAccount, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(null, null, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/messages")
    public ResponseEntity<?> postMessage(@RequestBody Message msg) {
        Account optAccount = accountService.getAccountById(msg.getPosted_by());
        if (optAccount == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Message resMsg = messageService.storeMessage(msg);
        if(resMsg == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); 
        } 
        else
        {
            return new ResponseEntity<>(resMsg, HttpStatus.OK);
        }
    }

    @GetMapping("/messages")
    public List<Message> getMessages() {
        return messageService.getMessages();
    }

    @GetMapping("/messages/{id}")
    public Message getMessage(@PathVariable int id) {
        return messageService.getMessageById(id);
    }

    @DeleteMapping("/messages/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable int id) {
        // Weird work around. I wanted to return 1 or 0, i.e., the number of found messages
        // But test requires empty body if 0
        return new ResponseEntity<>(messageService.deleteMessage(id) == 1 ? 1 : null, HttpStatus.OK);
    }

    @PatchMapping("/messages/{id}")
    public ResponseEntity<?> updateMessage(@PathVariable int id, @RequestBody Message msg) {
        Message resultMessage = messageService.updateMessage(id, msg);
        if (resultMessage == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    @GetMapping("/accounts/{id}/messages")
    public List<Message> getAccountMessages(@PathVariable int id) {
        Account account = accountService.getAccountById(id);
        if (account == null) {
            return null;
        }

        return messageService.getMessageByPostedBy(id);
    }
}