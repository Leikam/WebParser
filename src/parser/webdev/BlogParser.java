package parser.webdev;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import components.webdev.BlogPostTextLink;
import connect.Connector;
import parser.ASiteParser;

public class BlogParser extends ASiteParser {

    public BlogParser(Connector connector) {
        super(connector);
    }

    public Set<BlogPostTextLink> getSummary(Document document) {
        final Elements cards = document.getElementsByClass("w-card-base");

        Set<BlogPostTextLink> shortPosts = new HashSet<>();
        for (Element card : cards) {
            final Elements link = card.getElementsByClass("w-card-base__link");
            final String description = link.text();
            String href = link.attr("href");
            if (href != null && description != null) {
                shortPosts.add(new BlogPostTextLink(description, processLink(href)).withTags(getCardTags(card)));
            }
        }

        return shortPosts;
    }

    private List<String> getCardTags(Element element) {
        return element.getElementsByClass("w-chip").eachText();
    }

    private String processLink(String href) {
        if (href.startsWith("/")) {
            final URL url = getConnector().getUrl();
            href = url.getProtocol() + "://" + url.getHost() + href;
        }

        return href;
    }

}
