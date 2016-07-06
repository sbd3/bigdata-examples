package spark.examples;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class CustomFileWriter {

	public static void main(String[] args) throws IOException {
		Writer writer = new FileWriter("d:/seq.txt");
		for (int i = 100000000; i > 0; i--) {
			writer.write(i + "\n");
		}
		writer.flush();
		writer.close();
	}
}
