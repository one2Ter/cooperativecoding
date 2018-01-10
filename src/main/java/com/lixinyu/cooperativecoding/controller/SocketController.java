package com.lixinyu.cooperativecoding.controller;

import com.lixinyu.cooperativecoding.model.Message;
import com.lixinyu.cooperativecoding.service.Editor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SocketController {

    @MessageMapping("/message")
    @SendTo("/clients/message")
    public Message greeting(Message message,Authentication authentication) throws Exception {
        //TODO database操作
        if(message.getId()==7){
            //String data = message.getContent();
            //List<String> lines = java.util.Arrays.asList(data.split("\n"));
            //data = code.getContent();
            //code.setContent(data);
            //Editor.writeStringArrListintoFile(lines,path);
        }
        return new Message(message.getId(),message.getContent(),authentication.getName());
    }
}
