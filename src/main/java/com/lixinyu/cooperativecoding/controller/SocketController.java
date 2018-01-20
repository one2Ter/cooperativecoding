package com.lixinyu.cooperativecoding.controller;

import com.lixinyu.cooperativecoding.model.Code;
import com.lixinyu.cooperativecoding.model.Message;
import com.lixinyu.cooperativecoding.model.Output;
import com.lixinyu.cooperativecoding.service.Compiler;
import com.lixinyu.cooperativecoding.service.Writer;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {
    @MessageMapping("/message")
    @SendTo("/clients/message")
    public Message greeting(Message message,Authentication authentication) throws Exception {
        Message msg = new Message(message);
        //TODO database操作
        switch (message.getId()){
            case  0:
                break;
            case -1:
                Writer.write(message.getContent(), "/spring_boot/src/hello");
                break;
            case 1:
                Code code = new Code(0,"helloworld",message.getContent(),"c");
                Output o = Compiler.execute(Writer.write(message.getContent(), "/spring_boot/src/hello"), code.getType(), message.getExtra().split("\\|"));
                if(!o.getError().equals("")){
                    msg.setContent(o.getError());
                }else{
                    msg.setContent(o.getOutput());
                }
                break;
        }
        msg.setFrom(authentication.getName());
        return msg;
    }
}
