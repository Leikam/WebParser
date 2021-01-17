package components;

public class TextLinkImpl implements TextLink {

    private String description;
    private String link;

    public TextLinkImpl(String description, String link) {
        this.description = description;
        this.link = link;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getLink() {
        return this.link;
    }

    @Override
    public String toString() {
        return "TextLinkImpl{" +
               "description='" + description + '\'' +
               ", link='" + link + '\'' +
               '}';
    }
}
