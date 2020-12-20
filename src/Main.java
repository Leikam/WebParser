import java.io.IOException;
import java.util.Set;

import connect.Connector;
import formatters.MarkdownFormatter;
import parser.BlogParser;
import parser.BlogPostPrinter;
import parser.webdev.BlogPostSummary;

public class Main {

    public static void main(String[] args) throws IOException {

        final Connector connector = new Connector("web.dev");
        final StringBuffer htmlString = connector.getContent(connector.connect("/blog/"));

        final BlogParser parser = new BlogParser(connector);
        final Set<BlogPostSummary> blogSummary = parser.getBlogSummary(parser.parse(htmlString.toString()));

        final BlogPostPrinter<BlogPostSummary> blogPostPrinter = new BlogPostPrinter<>(
            blogSummary,
            new MarkdownFormatter<>()
        );

//        blogPostPrinter.print();
        blogPostPrinter.save("saved/web.dev.summary.md");

    }


}
