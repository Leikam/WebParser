package formatters;

import parser.webdev.Summary;

public interface Formatter<T extends Summary> {
    String format(T summary);
}
