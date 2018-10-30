package java8.examples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamWordCount {

	public static void main(String[] args) throws IOException {
		Path path = Paths.get("D:\\artefacts\\Amplify\\queries.sql");
		Files.lines(path).flatMap(line -> Arrays.stream(line.split("\\s+")))
				.collect(Collectors.toMap(s -> s, s -> 1, Integer::sum))
				.forEach((k, v) -> System.out.println("Item : " + k + " Count : " + v));
		;
		optionalDemo();
	}
	
	private static void optionalDemo() {
		String[] words = new String[10];
		//words[5] = "wow";
		Optional<String> checkNull = Optional.ofNullable(words[5]);
		if(checkNull.isPresent()) {
			System.out.println(checkNull.get());
		} else {
			System.out.println("Word is null");
		}
	}

}
