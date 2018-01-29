package com.lixinyu.cooperativecoding.controller;

import com.lixinyu.cooperativecoding.data.CodeRepository;
import com.lixinyu.cooperativecoding.data.UserRepository;
import com.lixinyu.cooperativecoding.model.Message;
import com.lixinyu.cooperativecoding.model.Output;
import com.lixinyu.cooperativecoding.model.entity.Code;
import com.lixinyu.cooperativecoding.model.entity.Project;
import com.lixinyu.cooperativecoding.model.entity.User;
import com.lixinyu.cooperativecoding.util.Compiler;
import com.lixinyu.cooperativecoding.util.Writer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.util.Set;

@Controller
public class SocketController {

    private static final int MSG_CHAT = 0;
    private static final int MSG_CODE = -1;
    private static final int MSG_RUN = 1;

    private File file;
    private String code_type;
    @Autowired
    private CodeRepository codeRepository;

    @Autowired
    private UserRepository userRepository;


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
                //把project下所有的文件写入 然后执行编译运行
                //首先获取project
                Project project = user.getProject();
                //获取project下所有的文件
                Set<Code> codes = project.getCodes();
                //在src目录下依次写文件
                for (Code c : codes) {
                    System.out.println(c.getContent());
                    if (c.isExecutable()) {
                        code_type = c.getType();
                        file = Writer.write(c.getContent(), "/spring_boot/src/" + c.getCode_title());
                    } else {
                        Writer.write(c.getContent(), "/spring_boot/src/" + c.getCode_title());
                    }
                }

                Output o = Compiler.execute(file, code_type, message.getExtra().split("\\|"));
                if(!o.getError().equals("")){
                    message.setContent("[COMPILER REPORTED AN ERROR]\n" + o.getError());
                }else{
                    message.setContent(o.getOutput());
                }
                break;
        }
        message.setFrom(name);
        return message;
    }
}
