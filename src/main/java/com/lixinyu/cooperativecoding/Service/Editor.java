package com.lixinyu.cooperativecoding.Service;

import com.lixinyu.cooperativecoding.Entries.Code;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Editor {

	public static File getFileWithCode(Code code) {
		List<String> lines = new ArrayList<String>(Arrays.asList(code.getContent().split("\n")));

		File file = new File("/cooperativecoding/hello.c");
		try {
			PrintStream ps = new PrintStream(new FileOutputStream(file));
			for (String line : lines) {
				ps.append(line + "\n");
			}
			ps.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return file;
	}

	// String Array -> File
	public static void writeStringArrListintoFile(List<String> lines, String path) {
		try {
			File file = new File(path);
			PrintStream ps = new PrintStream(new FileOutputStream(file));

			for (String line : lines) {
				ps.append(line + "\n");
			}
			ps.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
