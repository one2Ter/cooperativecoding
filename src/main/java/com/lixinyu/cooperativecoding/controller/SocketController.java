package com.lixinyu.cooperativecoding.controller;

import com.lixinyu.cooperativecoding.data.CodeRepository;
import com.lixinyu.cooperativecoding.data.UserRepository;
import com.lixinyu.cooperativecoding.model.Message;
import com.lixinyu.cooperativecoding.model.Output;
import com.lixinyu.cooperativecoding.model.entity.Code;
import com.lixinyu.cooperativecoding.model.entity.User;
import com.lixinyu.cooperativecoding.util.Compiler;
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
    private static final int MSG_LOGIN = 3;

    private File src_file;
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

        switch (message.getChannel()){
            case MSG_CHAT:
                break;
            case MSG_CODE:
                int code_id = Integer.parseInt(message.getExtra());
                Code code = codeRepository.findOne(code_id);
                code.setContent(message.getContent());
                codeRepository.save(code);
                break;
            case MSG_RUN:
                Compiler compiler = new Compiler();
                //For Linux
//                String temp_dir = "/tmp/spring_boot/";

                //For Windows
                //Windows 下需要挂载磁盘
                String temp_dir = "C:\\Users\\Ivan\\AppData\\Local\\Temp";
                String path = temp_dir + name+"_"+System.currentTimeMillis()+"\\";

                //For Linux
//                String path = temp_dir + name+"_"+System.currentTimeMillis()+"/";
                //make dirs
                File rootPath = new File(temp_dir);
                if(!rootPath.exists()){
                    rootPath.mkdir();
                }

                if(new File(path).mkdir()){
                    File file;
                    for (Code c : user.getProject().getCodes()) {
                        String code_path = path + c.getCode_title();
                        String code_text = c.getContent();
                        file = compiler.write(code_text, code_path);
                        if (c.isExecutable()) {
                            code_type = c.getMode();
                            src_file = file;
                            switch (code_type){
                                case "c":
                                    compiler.write(
                                            "#/bin/sh\n"+
                                                    "gcc "+c.getCode_title()+"\n"+
                                                    "./a.out ",
                                            path+"run.sh");
                                    break;
                            }
                        }
                    }
                }

                Output output = compiler.execute(name,src_file, path,code_type, message.getExtra().split("\\|"));
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
            case MSG_LOGIN:
                //将当前时间写入到lastHeartbeat字段
                user.setLastHeartbeat(System.currentTimeMillis());
                userRepository.save(user);
                break;
        }
        message.setFrom(name);
        return message;
    }


}