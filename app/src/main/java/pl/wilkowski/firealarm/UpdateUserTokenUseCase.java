package pl.wilkowski.firealarm;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

class UpdateUserTokenUseCase {
    private static final String ERRORCODE_OK = "OK";
    private URL updateEndpoint;

    UpdateUserTokenUseCase(URL updateEndpoint) {
        this.updateEndpoint = updateEndpoint;
    }

    void invoke(String token) throws IOException, JSONException {
        HttpURLConnection urlConnection = (HttpURLConnection) updateEndpoint.openConnection();
        try {
            urlConnection.setDoOutput(true);
            urlConnection.setChunkedStreamingMode(0);
            urlConnection.setRequestProperty("Content-Type", "application/json");

            var out = new PrintWriter(new BufferedOutputStream(urlConnection.getOutputStream()));
            out.print(new JSONObject().put("userToken", token).toString());
            out.close();

            var in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            var errorCode = new JSONObject(in.readLine()).getString("errorCode");
            if (!ERRORCODE_OK.equals(errorCode)) {
                throw new IOException("Invalid errorCode returned: " + errorCode);
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
