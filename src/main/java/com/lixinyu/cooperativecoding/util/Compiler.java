package com.lixinyu.cooperativecoding.util;


import com.lixinyu.cooperativecoding.model.Output;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class Compiler {
    public Compiler() {
    }

    public File write(String text, String path) throws Exception{
        File file = new File(path);
        PrintStream ps = new PrintStream(new FileOutputStream(file));
        ps.append(text);
        ps.close();
        return file;
    }

    public Output execute(File file,String path, String type, String[] inputs) throws Exception {
        Output output = new Output();

        //Docker即时沙箱环境编译
        //docker run --rm -v {path}:/runtime ubuntu:sandbox /bin/bash -c "chmod +x ./run.sh;./run.sh"
        String[] cmd={
                "docker",
                "run",
                "--rm",
                "-v",
                path+":/runtime",
                "ubuntu:sandbox",
                "/bin/bash",
                "-c",
                "chmod +x ./run.sh;./run.sh"
        };
        ProcessBuilder processBuilder = new ProcessBuilder(cmd);
        System.out.println("docker run --rm -v "+path+":/runtime 4b47415aff4e");


        Process process = processBuilder.start();
        OutputStream outputStream = process.getOutputStream();
        //将缓冲区的内容写入到输出流中
        for (String input : inputs) {
            outputStream.write(input.getBytes());
            outputStream.write(" ".getBytes());
        }
        outputStream.close();
        output.setError(streamToString(process.getErrorStream()));
        output.setOutput(streamToString(process.getInputStream()));
        process.waitFor();
        process.destroy();
        return output;
    }

    private static String streamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
 }
