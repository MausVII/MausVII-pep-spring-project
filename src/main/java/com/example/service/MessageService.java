package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message persistMessage(Message message) {
        return messageRepository.save(message);
    }

    /**
     * @return all messages
     */
    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    /**
     * @param message to store
     * @return
     */
    public Message storeMessage(Message message) {
        if (message.getMessage_text() == "" || message.getMessage_text().length() > 255) return null;
        return messageRepository.save(message);
    }

    /**
     * @param id of message
     * @return
     */
    public Message getMessageById(int id) {
        Optional<Message> optMessage = messageRepository.findById(id);
        if(optMessage.isPresent())
        {
            return optMessage.get();
        }
        else
        {
            return null;
        }
    }

    /**
     * @param posted_by
     * @return
     */
    public List<Message> getMessageByPostedBy(int posted_by) {
        return messageRepository.findMessagesByPostedBy(posted_by);
    }

    /**
     * @param id
     * @param message
     * @return
     */
    public Message updateMessage(int id, Message newMessageText) {
        if (newMessageText.getMessage_text() == "" || newMessageText.getMessage_text().length() > 255) return null;

        Optional<Message> optMessage = messageRepository.findById(id);

        if(optMessage.isPresent())
        {
            Message updatedMessage = optMessage.get();
            updatedMessage.setMessage_text(newMessageText.getMessage_text());
            messageRepository.save(updatedMessage);
            return updatedMessage;
        }
        else
        {
            return null;
        }
    }

    /**
     * @param id of message to delete
     * @return deleted message
     */
    public int deleteMessage(int id) {
        Optional<Message> optMessage = messageRepository.findById(id);

        int foundMessages;

        if (optMessage.isPresent())
        {
            foundMessages = 1;
            messageRepository.deleteById(id);;
        } 
        else 
        {
            foundMessages = 0;
        }

        return foundMessages;
    }
}
