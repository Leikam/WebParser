package components.webdev;

import java.util.List;
import java.util.Optional;

import components.TextLinkImpl;

public class BlogPostTextLink extends TextLinkImpl {

    private Optional<List<String>> tags;

    public BlogPostTextLink(String description, String link) {
        super(description, link);
    }

    public Optional<List<String>> getTags() {
        return tags;
    }

    public BlogPostTextLink withTags(List<String> tags) {
        this.tags = Optional.ofNullable(tags);
        return this;
    }

    @Override
    public String toString() {
        return "BlogPostTextLink{" +
               "tags=" + tags +
               "} " + super.toString();
    }
}
