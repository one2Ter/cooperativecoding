package com.lixinyu.cooperativecoding.Websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {


    @MessageMapping("/message")
    @SendTo("/clients/message")
    public Message greeting(Message message) throws Exception {
        //TODO database操作
        return new Message(message.getId(),message.getContent());
    }
}
