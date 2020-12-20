package connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Connector {

    private String www;
    private URL url;

    public Connector(String www) throws MalformedURLException {
        this.www = www;
    }

    public URLConnection connect(String query) {
        try {
            this.url = new URL("https://" + this.www + query);
            return this.url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getWww() {
        return www;
    }

    public URL getUrl() {
        return url;
    }

    public StringBuffer getContent(URLConnection connection) throws IOException {
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
