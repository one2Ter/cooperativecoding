package com.lixinyu.cooperativecoding.controller;

import com.lixinyu.cooperativecoding.model.Code;
import com.lixinyu.cooperativecoding.model.Message;
import com.lixinyu.cooperativecoding.model.Output;
import com.lixinyu.cooperativecoding.service.Compiler;
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

        Message msg = new Message();
        //TODO database操作
        switch (message.getId()){
            case  0:
                msg = new Message(message);
                msg.setFrom(authentication.getName());
                break;
            case -1:
                List<String> lines = java.util.Arrays.asList(message.getContent().split("\n"));
                Editor.writeStringArrListintoFile(lines,"/app/src/hello");
                msg = new Message(message);
                message.setId(-1);
                msg.setFrom(authentication.getName());
                break;
            case 1:
                msg = new Message(message);

                Code code = new Code(0,"helloworld",message.getContent(),"c");
                Output o = Compiler.execute(Editor.getFileWithCode(code),code.getType());

                if(!o.getError().equals("")){
                    msg.setContent(o.getError());
                }else{
                    msg.setContent(o.getOutput());
                }
                msg.setFrom(authentication.getName());
                break;
        }
        return msg;
    }
}
