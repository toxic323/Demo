package files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {

    public static String get(String path) throws IOException {
        String result = new String(Files.readAllBytes(Paths.get(path)));
        return result;
    }
}
