package com.lixinyu.cooperativecoding.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Writer {
    public static File write(String text, String path) throws Exception{
        File file = new File(path);
        PrintStream ps = new PrintStream(new FileOutputStream(file));
        ps.append(text);
        ps.close();
		return file;
	}
}