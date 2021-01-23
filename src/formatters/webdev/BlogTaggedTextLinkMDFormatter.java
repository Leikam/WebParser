package formatters.webdev;

import java.util.List;
import java.util.stream.Collectors;

import components.webdev.BlogPostTextLink;
import formatters.TextLinkMDFormatter;

public class BlogTaggedTextLinkMDFormatter<T extends BlogPostTextLink> extends TextLinkMDFormatter<T> {

    public static final List<String> EMPTY_STRING_LIST = List.of("");

    @Override
    public String format(T summary) {
        return String.format(
            "1. [%s](%s) `%s`\n",
            summary.getDescription(),
            summary.getLink(),
            summary.getTags().orElse(EMPTY_STRING_LIST).stream().map(s -> "#" + s).collect(Collectors.joining(", "))
        );
    }

}
