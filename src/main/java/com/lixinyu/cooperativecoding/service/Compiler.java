package com.lixinyu.cooperativecoding.service;

import java.io.File;

public class Compiler {

	private static String PATH = "/cooperativecoding/";
	
	static String convertStreamToString(java.io.InputStream is) {
	    @SuppressWarnings("resource")
		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}

	public static String build(File file) throws Exception {

		String timestamp = String.valueOf(System.currentTimeMillis()) + ".out";
		String command = "./build.sh "+PATH+timestamp+" "+file.getPath();
		System.out.println(command);
		String output = "gg";
		Process process = null;

		try {
			process = Runtime.getRuntime().exec(command, null, new File(PATH));
			output = convertStreamToString(process.getInputStream());
			process.waitFor();
			process.destroy();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output;
	}
}
