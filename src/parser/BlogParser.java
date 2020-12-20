package parser;

import java.net.URL;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import connect.Connector;
import parser.webdev.BlogPostSummary;

public class BlogParser implements SiteParser {

    private Document document;
    private Connector connector;

    public BlogParser(Connector connector) {
        this.connector = connector;
    }

    @Override
    public Document parse(String html) {
        this.document = Jsoup.parse(html);
        return this.document;
    }

    public Set<BlogPostSummary> getBlogSummary(Document document) {
        final Elements cards = document.getElementsByClass("w-card-base");
        return cards.stream()
                    .flatMap(element -> element.getElementsByClass("w-card-base__link").stream())
                    .map(element -> new BlogPostSummary(element.text(), processLink(element.attr("href"))))
                    .filter(blogPost -> !blogPost.getName().isBlank() && !blogPost.getLink().isBlank())
                    .collect(Collectors.toSet());
    }

    private String processLink(String href) {
        if (href.startsWith("/")) {
            final URL url = connector.getUrl();
            href = url.getProtocol() + "://" + url.getHost() + href;
        }

        return href;
    }
}
