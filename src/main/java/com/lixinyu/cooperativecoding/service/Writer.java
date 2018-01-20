package com.lixinyu.cooperativecoding.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

public class Writer {
    public static File write(String text, String path) {
        List<String> lines = Arrays.asList(text.split("\n"));
        File file = null;
		try {
            file = new File(path);
			PrintStream ps = new PrintStream(new FileOutputStream(file));
			for (String line : lines) {
                ps.append(line).append("\n");
			}
			ps.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return file;
	}
}