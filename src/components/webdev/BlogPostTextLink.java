package components.webdev;

import java.util.List;

import components.TextLinkImpl;

public class BlogPostTextLink extends TextLinkImpl {

    private List<String> tags;

    public BlogPostTextLink(String description, String link) {
        super(description, link);
    }

    public List<String> getTags() {
        return tags;
    }

    public BlogPostTextLink withTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    @Override
    public String toString() {
        return "BlogPostTextLink{" +
               "tags=" + tags +
               "} " + super.toString();
    }
}
