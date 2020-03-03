import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class TokenFetcher {

    private String encodedString;
    private byte[] postData;

    public TokenFetcher() {
        setParameters();
    }

    public void setParameters(){
        String user = "0ad1d5f9fcf4481a8b62c1fe8065fec9";
        String password = "FC1NjvR2x9yFiWkXnKOe2lDw1XcmQpT4dsci39dUyuMjp71VDmCRGt0eZBugkolH";
        String auth = user + ":" + password;
        setEncodedString(Base64.getEncoder().encodeToString(auth.getBytes()));

        String urlParameters = "client_id=" + user;
        setPostData(urlParameters.getBytes(StandardCharsets.UTF_8));
    }

    public String getDeviceCode() throws IOException{
        URL url = new URL("https://allegro.pl/auth/oauth/device");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Authorization", "Basic " + encodedString);
        connection.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.write(postData);

        InputStream content = connection.getInputStream();
        BufferedReader in =
                new BufferedReader(new InputStreamReader(content));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }

        JSONObject jsontoken = new JSONObject(response.toString());
        System.out.println(jsontoken.getString("verification_uri_complete"));
        return jsontoken.getString("device_code");
    }

    public String getAccessToken() throws IOException {

        String deviceToken = getDeviceCode();

        InputStream content2;

        while (true) {
            URL url2 = new URL("https://allegro.pl/auth/oauth/token?grant_type=urn%3Aietf%3Aparams%3Aoauth%3Agrant-type%3Adevice_code&device_code=" + deviceToken);
            HttpURLConnection connection2 = (HttpURLConnection) url2.openConnection();
            connection2.setRequestMethod("POST");
            connection2.setDoOutput(true);
            connection2.setRequestProperty("Authorization", "Basic " + encodedString);

            if (connection2.getResponseCode() != 400) {
                content2 = connection2.getInputStream();
                break;
            }
        }

        BufferedReader in2 =
                new BufferedReader(new InputStreamReader(content2));
        StringBuilder response2 = new StringBuilder();
        String line2;
        while ((line2 = in2.readLine()) != null) {
            response2.append(line2);
        }

        JSONObject jsontoken2 = new JSONObject(response2.toString());

        return jsontoken2.getString("access_token");
    }

    public void setEncodedString(String encodedString) {
        this.encodedString = encodedString;
    }

    public void setPostData(byte[] postData) {
        this.postData = postData;
    }
}
