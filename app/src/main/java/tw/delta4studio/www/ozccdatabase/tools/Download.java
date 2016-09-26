package tw.delta4studio.www.ozccdatabase.tools;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by kamira on 2016/9/26.
 */
public class Download {
    HttpURLConnection connect;
    InputStream is;

    private InputStream download(String urlString) {
        try {
            URL url = new URL(urlString);
            connect = (HttpURLConnection) url.openConnection();
            connect.connect();
            is = connect.getInputStream();
        } catch (IOException e) { }

        return is;
    }

    public String downloadAsString(String urlString){

    }
}
