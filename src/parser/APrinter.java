package parser;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class APrinter<T> implements Printer<T> {

    @Override
    public void save(String destination) throws IOException {
        Path path = Path.of(destination);

        if (path.isAbsolute()) {
            if (!path.startsWith(System.getProperty("user.dir"))) {
                path = Path.of(path.toString().replaceFirst(path.getFileSystem().getSeparator(), "")).toAbsolutePath();
            }
        }

        final Path directory = path.getParent();

        if (Files.notExists(directory)) {
            Files.createDirectories(directory);
        } else {
            Files.delete(path);
        }

        try (final BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            writer.write(getContent());
        }
    }

    protected abstract String getContent();
}
