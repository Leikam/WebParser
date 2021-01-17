package connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;

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

            return this.url.openConnection();
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

    public StringBuffer getPageContent(URLConnection connection) throws IOException {
        final StringBuffer stringBuffer = new StringBuffer();
        readStream(stringBuffer, connection.getInputStream());
        return stringBuffer;
    }

    private void readStream(StringBuffer stringBuffer, InputStream inputStream) throws IOException {
        final Reader reader = new InputStreamReader(inputStream);
        try (BufferedReader in = new BufferedReader(reader)) {
            while (in.ready()) {
                if (!stringBuffer.isEmpty()) {
                    stringBuffer.append(System.lineSeparator());
                }
                stringBuffer.append(in.readLine());
            }
        }
    }

}
