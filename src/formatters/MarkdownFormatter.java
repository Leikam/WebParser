package formatters;

import parser.webdev.Summary;

public class MarkdownFormatter<T extends Summary> implements Formatter<T> {

    @Override
    public String format(T summary) {
        return String.format("1. [%s](%s)\n", summary.getName(), summary.getLink());
    }

}
