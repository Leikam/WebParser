package formatters;

import components.TextLink;

public interface Formatter<T extends TextLink> {
    String format(T summary);
}
