package parser;

import org.jsoup.nodes.Document;

public interface SiteParser {

    Document parse(String html);

}
