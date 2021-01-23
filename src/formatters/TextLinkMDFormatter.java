package formatters;

import components.webdev.BlogPostTextLink;

public class TextLinkMDFormatter<T extends BlogPostTextLink> implements Formatter<T> {

    @Override
    public String format(T summary) {
        return String.format("1. [%s](%s)\n", summary.getDescription(), summary.getLink());
    }

}
