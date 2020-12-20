package parser;

import java.util.Collection;
import java.util.Set;
import java.util.function.Consumer;

import parser.webdev.BlogPostSummary;
import parser.webdev.Summary;

public class BlogPostPrinter<T extends Summary> implements Printer<T> {

    private final Set<T> blogSummary;
    private final Consumer<T> printer;

    public BlogPostPrinter(Set<T> blogSummary) {
        this(blogSummary, blogPost -> System.out.printf("%s [%s]%n", blogPost.getName(), blogPost.getLink()));
    }

    public BlogPostPrinter(Set<T> blogSummary, Consumer<T> printer) {
        this.blogSummary = blogSummary;
        this.printer = printer;
    }

    @Override
    public void print(Collection<T> collection) {
        collection.forEach(this.printer);
    }
}
