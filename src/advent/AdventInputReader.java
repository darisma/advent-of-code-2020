package advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class AdventInputReader {
	String prefix="C:\\Users\\tatu\\eclipse-workspace\\advent\\";
	public Stream<String> getStringStream(String fileName) {
		try {
			return Files.lines(Paths.get(prefix+fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
