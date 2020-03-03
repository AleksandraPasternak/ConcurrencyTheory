import org.json.JSONObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class JokesFetcher {

    public void getJokes(ExecutorService executor, ExecutorService executorApply){

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .executor(executor)
                .build();

        List<URI> uris = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            uris.add(URI.create("https://api.chucknorris.io/jokes/random"));
        }

        List<CompletableFuture<String>> result = uris.stream()
                .map(url -> client.sendAsync(
                        HttpRequest.newBuilder(url)
                                .GET()
                                .build(),
                        HttpResponse.BodyHandlers.ofString())
                        .thenApplyAsync(response -> response.body(), executorApply))
                .collect(Collectors.toList());

        for (CompletableFuture<String> future : result) {
            try {
                getJoke(future.get());
            }
            catch(InterruptedException | ExecutionException e){
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
