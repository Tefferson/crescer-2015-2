package io.br.com.cwi.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileIO {

	public static String path = "";

	public static void writeLines(List<?> lines, String path) throws IOException {

		File file = new File(path);

		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = null;
		try {
			String newLine = System.getProperty("line.separator");
			fw = new FileWriter(file);

			for (Object line : lines) {
				fw.write(line.toString() + newLine);
			}
		} catch (IOException e) {
			throw new IOException("Erro!!!");
		} finally {
			fw.close();
		}
	}
}
