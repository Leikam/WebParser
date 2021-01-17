package parser;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Set;
import java.util.stream.Collectors;

import components.TextLink;
import formatters.Formatter;

public class TextLinksListPrinter<T extends TextLink> extends APrinter<T> {

    private final Set<T> linksCollection;
    private final Formatter<T> formatter;

    public TextLinksListPrinter(Set<T> linksCollection) {
        this(linksCollection, summary -> String.format("%s [%s]%n", summary.getDescription(), summary.getLink()));
    }

    public TextLinksListPrinter(Set<T> linksCollection, Formatter<T> formatter) {
        this.linksCollection = linksCollection;
        this.formatter = formatter;
    }

    @Override
    protected String getContent() {
        return new StringBuilder(DATE_TIME_FORMATTER.format(Instant.now().atZone(ZoneId.systemDefault()).toLocalDate()) + "\n\n")
            .append(linksCollection.stream().map(this.formatter::format).collect(Collectors.joining("")))
            .toString();
    }

}
