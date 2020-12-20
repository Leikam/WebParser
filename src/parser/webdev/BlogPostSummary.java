package parser.webdev;

import java.util.List;

public class BlogPostSummary implements Summary {

    private final String name;
    private final String link;
    private List<String> tags;

    public BlogPostSummary(String name, String link) {
        this.name = name;
        this.link = link;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLink() {
        return link;
    }

    public List<String> getTags() {
        return tags;
    }

    public BlogPostSummary withTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    @Override
    public String toString() {
        return "BlogPostSummary{" +
               "name='" + name + '\'' +
               ", link='" + link + '\'' +
               ", tags=" + tags +
               '}';
    }

}
