import org.json.JSONObject;
import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JokesFetcher {

    public void getJokes(){

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        for(int i=0;i<200;i++) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.chucknorris.io/jokes/random"))
                    .GET()
                    .build();

            try {
                HttpResponse<String> response =
                        client.send(request, HttpResponse.BodyHandlers.ofString());
                getJoke(response.body());
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void getJoke(String response){
        JSONObject jsonObject = new JSONObject(response);
        String joke = jsonObject.getString("value");
        System.out.println(joke);
    }

}
