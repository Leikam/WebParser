package formatters;

import parser.webdev.Summary;

public class MarkdownFormatter<T extends Summary> implements Formatter<T> {

    @Override
    public String format(T summary) {
        return String.format("* [%s](%s)\n", summary.getName(), summary.getLink());
    }

}
