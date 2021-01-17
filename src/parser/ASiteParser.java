package parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import connect.Connector;

public class ASiteParser implements SiteParser {

    private Document document;
    private final Connector connector;

    public ASiteParser(Connector connector) {
        this.connector = connector;
    }

    @Override
    public Document parse(String html) {
        this.document = Jsoup.parse(html);
        return this.document;
    }

    public Document getDocument() {
        return document;
    }

    public Connector getConnector() {
        return connector;
    }
}
