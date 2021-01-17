package formatters;

import components.TextLink;

public class TextLinkMDFormatter<T extends TextLink> implements Formatter<T> {

    @Override
    public String format(T summary) {
        return String.format("1. [%s](%s)\n", summary.getDescription(), summary.getLink());
    }

}
