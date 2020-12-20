package parser;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

import formatters.Formatter;
import parser.webdev.Summary;

public class BlogPostPrinter<T extends Summary> implements Printer<T> {

    private final Set<T> summaryCollection;
    private final Formatter<T> printer;

    public BlogPostPrinter(Set<T> summaryCollection) {
        this(summaryCollection, summary -> String.format("%s [%s]%n", summary.getName(), summary.getLink()));
    }

    public BlogPostPrinter(Set<T> summaryCollection,  Formatter<T> formatter) {
        this.summaryCollection = summaryCollection;
        this.printer = formatter;
    }

    public Set<T> getSummaryCollection() {
        return summaryCollection;
    }

    @Override
    public void print() {
        getSummaryCollection()
            .stream()
            .map(this.printer::format)
            .forEach(System.out::println);
    }

    @Override
    public void save(String destination) throws IOException {
        Path path = Path.of(destination);

        if (path.isAbsolute()) {
            if (!path.startsWith(System.getProperty("user.dir"))) {
                path = Path.of(path.toString().replaceFirst(path.getFileSystem().getSeparator(), ""));
                path = path.toAbsolutePath();
            }
        }

        final Path directories = path.getParent();

        if (Files.notExists(directories)) {
            Files.createDirectories(directories);
        } else {
            Files.delete(path);
        }

        try (final BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            getSummaryCollection().stream()
                                  .map(this.printer::format)
                                  .forEach(
                                      summary -> {
                                          try {
                                              writer.write(summary);
                                          } catch (IOException e) {
                                              e.printStackTrace();
                                          }
                                      }
                                  );

        }
    }
}
