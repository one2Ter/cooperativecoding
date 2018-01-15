package com.lixinyu.cooperativecoding.service;

import com.lixinyu.cooperativecoding.model.Output;
import org.springframework.security.crypto.codec.Utf8;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class Compiler {

	private static String PATH = "/app/target/";
	
	static String convertStreamToString(java.io.InputStream is) {
	    @SuppressWarnings("resource")
		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}

	public static Output execute(File file, String type) throws Exception {
	    String command = null;

        String timestamp = String.valueOf(System.currentTimeMillis());


        command = "./build.sh "+PATH+timestamp+" "+file.getPath()+" "+type;
        System.out.println(command);
		//build.sh /app/target/265365364.out /app/src/hello.c c|cpp|java|python

		Output output = new Output();
		Process process = Runtime.getRuntime().exec(command, null, new File(PATH));

        OutputStream outputStream = process.getOutputStream();

        //将缓冲区的内容写入到输出流中
        outputStream.write("hello".getBytes());
        outputStream.write(" ".getBytes());
        outputStream.write("world".getBytes());
        outputStream.close();


		output.setError(convertStreamToString(process.getErrorStream()));

		output.setOutput(convertStreamToString(process.getInputStream()));

		process.waitFor();
		process.destroy();

		return output;
	}
}
