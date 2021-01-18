package connect;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;

public class Connector {

    private final String www;
    private URL url;
    private boolean useHttp;

    public Connector(String www) {
        this.www = www;
    }

    public boolean isUseHttp() {
        return useHttp;
    }

    public Connector withUseHttps(boolean useHttps) {
        this.useHttp = useHttps;
        return this;
    }

    public URLConnection go(String query) {
        if (query == null) {
            throw new IllegalArgumentException("Нужно указать ссылку");
        }

        try {

            if (query.charAt(0) != '/') {
                query += "/";
            }

            if (getWebAddress().startsWith("http")) {
                this.url = new URL(getWebAddress() + query);
            } else {
                this.url = new URL((this.isUseHttp() ? "http" : "https"), getWebAddress(), query);
            }

            final HttpURLConnection connection = (HttpURLConnection) this.url.openConnection();
            connection.setReadTimeout(3000);
            connection.setRequestMethod("GET");
            return connection;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getWebAddress() {
        return www;
    }

    public URL getUrl() {
        return url;
    }

    public StringBuilder getPageContent(URLConnection connection) throws IOException {
        final StringBuilder stringBuilder = new StringBuilder();
        readStream(stringBuilder, connection.getInputStream());
        return stringBuilder;
    }

    public String getPageStringContent(URLConnection connection) throws IOException {
        return getPageContent(connection).toString();
    }

    public Document getDocument(URLConnection connection) throws IOException {
        return HttpConnection.connect(connection.getURL()).get();
    }

    private void readStream(StringBuilder stringBuilder, InputStream inputStream) throws IOException {
        final StringBuilder sb = new StringBuilder();
        char[] buffer = new char[4096];
        int read = 0;
        int total = 0;

        try (Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                stringBuilder.append(buffer, 0, read);
                total += read;
            }
        }

        System.out.println("transferred = " + total);
    }

}
