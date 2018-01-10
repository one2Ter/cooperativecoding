package com.lixinyu.cooperativecoding.service;

import java.io.File;

public class Compiler {

	private static String PATH = "/app/target/";
	
	static String convertStreamToString(java.io.InputStream is) {
	    @SuppressWarnings("resource")
		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}

	public static String excute(File file,String type) throws Exception {
	    String command = null;

        String timestamp = String.valueOf(System.currentTimeMillis());


        command = "./build.sh "+PATH+timestamp+" "+file.getPath()+" "+type;
        System.out.println(command);
		//build.sh /app/target/265365364.out /app/src/hello.c c|cpp|java|python

		String output = null;
		Process process = Runtime.getRuntime().exec(command, null, new File(PATH));
		String error = convertStreamToString(process.getErrorStream());
		output = error+convertStreamToString(process.getInputStream());

		if(error==""){
		    output = "[PROCESS FINISHED SUCCESSFULLY]\n"+output;
        }else{
            output = "[SOME ERROR OCCURRED]\n"+output;
        }
		process.waitFor();
		process.destroy();

		return output;
	}
}
