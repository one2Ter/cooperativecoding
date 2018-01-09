package com.lixinyu.cooperativecoding.controller;

import com.lixinyu.cooperativecoding.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {

    @MessageMapping("/message")
    @SendTo("/clients/message")
    public Message greeting(Message message,Authentication authentication) throws Exception {
        //TODO database操作
        return new Message(message.getId(),message.getContent(),authentication.getName());
    }
}
