package com.lixinyu.cooperativecoding.controller;

import com.lixinyu.cooperativecoding.data.CodeRepository;
import com.lixinyu.cooperativecoding.data.UserRepository;
import com.lixinyu.cooperativecoding.model.Message;
import com.lixinyu.cooperativecoding.model.Output;
import com.lixinyu.cooperativecoding.model.entity.Code;
import com.lixinyu.cooperativecoding.model.entity.User;
import com.lixinyu.cooperativecoding.util.Compiler;
import com.lixinyu.cooperativecoding.util.Writer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.io.File;

@Controller
public class SocketController {

    private static final int MSG_CHAT = 0;
    private static final int MSG_CODE = -1;
    private static final int MSG_RUN = 1;
    private static final int MSG_HEARTBEAT = 2;

    private File file;
    private String code_type;

    private final CodeRepository codeRepository;
    private final UserRepository userRepository;

    @Autowired
    public SocketController(CodeRepository codeRepository, UserRepository userRepository) {
        this.codeRepository = codeRepository;
        this.userRepository = userRepository;
    }


    @MessageMapping("/message")
    @SendTo("/clients/message")
    public Message greeting(Message message,Authentication authentication) throws Exception {

        String name = authentication.getName();
        User user = userRepository.findByUsername(name).get();

        //TODO database操作
        switch (message.getId()){
            case MSG_CHAT:
                break;
            case MSG_CODE:
                int code_id = Integer.parseInt(message.getExtra());
                Code code = codeRepository.findOne(code_id);
                code.setContent(message.getContent());
                codeRepository.save(code);
                break;
            case MSG_RUN:
                for (Code c : user.getProject().getCodes()) {
                    if (c.isExecutable()) {
                        code_type = c.getMode();
                        file = Writer.write(c.getContent(), "/spring_boot/src/" + c.getCode_title());
                    } else {
                        Writer.write(c.getContent(), "/spring_boot/src/" + c.getCode_title());
                    }
                }
                Output output = Compiler.execute(file, code_type, message.getExtra().split("\\|"));
                if(!output.getError().equals("")){
                    message.setContent(output.getError());
                }else{
                    message.setContent(output.getOutput());
                }
                break;
            case MSG_HEARTBEAT:
                //将当前时间写入到lastHeartbeat字段
                user.setLastHeartbeat(System.currentTimeMillis());
                userRepository.save(user);
                break;
        }
        message.setFrom(name);
        return message;
    }
}