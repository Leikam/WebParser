import java.io.IOException;
import java.net.URLConnection;
import java.util.Set;

import connect.Connector;
import formatters.MarkdownFormatter;
import parser.BlogParser;
import parser.TextLinksListPrinter;
import parser.webdev.BlogPostTextLink;

public class Main {

    public static void main(String[] args) throws IOException {

        final Connector connector = new Connector("web.dev");
        final URLConnection blogPage = connector.go("/blog/");
        final String pageContent = connector.getPageContent(blogPage).toString();

        final BlogParser devBlogParser = new BlogParser(connector);
        final Set<BlogPostTextLink> blogSummary = devBlogParser.getSummary(devBlogParser.parse(pageContent));

        final TextLinksListPrinter<BlogPostTextLink> textLinksListPrinter = new TextLinksListPrinter<>(
            blogSummary,
            new MarkdownFormatter<>()
        );

        textLinksListPrinter.print();
        textLinksListPrinter.save("saved/web.dev.summary.md");

    }


}
