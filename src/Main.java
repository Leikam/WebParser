import java.io.IOException;
import java.net.URLConnection;
import java.util.Set;

import components.webdev.BlogPostTextLink;
import connect.Connector;
import formatters.webdev.BlogTaggedTextLinkMDFormatter;
import parser.APrinter;
import parser.TextLinksListPrinter;
import parser.webdev.BlogParser;

public class Main {

    public static void main(String[] args) throws IOException {

        final Connector connector = new Connector("web.dev");
        final URLConnection blogPageURL = connector.go("/blog/");
        final String blogPageContent = connector.getPageStringContent(blogPageURL);

        final BlogParser devBlogParser = new BlogParser(connector);
        final Set<BlogPostTextLink> blogSummary = devBlogParser.getSummary(devBlogParser.parse(blogPageContent));

        final TextLinksListPrinter<BlogPostTextLink> textLinksListPrinter = new TextLinksListPrinter<>(
            blogSummary,
            new BlogTaggedTextLinkMDFormatter<>()
        );

        textLinksListPrinter.print();
        textLinksListPrinter.save("saved/web.dev.summary.md");

    }


}
