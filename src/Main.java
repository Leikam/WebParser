import java.io.IOException;
import java.net.URLConnection;
import java.util.Set;

import connect.Connector;
import formatters.TextLinkMDFormatter;
import parser.webdev.BlogParser;
import parser.TextLinksListPrinter;
import components.webdev.BlogPostTextLink;

public class Main {

    public static void main(String[] args) throws IOException {

        final Connector connector = new Connector("web.dev");
        final URLConnection blogPage = connector.go("/blog/");
        final String blogPageContent = connector.getPageStringContent(blogPage);

        final BlogParser devBlogParser = new BlogParser(connector);
        final Set<BlogPostTextLink> blogSummary = devBlogParser.getSummary(devBlogParser.parse(blogPageContent));

        final TextLinksListPrinter<BlogPostTextLink> textLinksListPrinter = new TextLinksListPrinter<>(
            blogSummary,
            new TextLinkMDFormatter<>()
        );

        textLinksListPrinter.print();
        textLinksListPrinter.save("saved/web.dev.summary.md");

    }


}
