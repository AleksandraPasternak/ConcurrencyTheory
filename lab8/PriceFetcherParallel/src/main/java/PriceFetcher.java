import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class PriceFetcher {

    private static final String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NzY0MDI0ODksInVzZXJfbmFtZSI6IjczODUzMjI2IiwianRpIjoiN2FmMTEzNDUtODk4Yi00YzdlLWEzMDEtNDUwMmIyZjA4MmEzIiwiY2xpZW50X2lkIjoiMGFkMWQ1ZjlmY2Y0NDgxYThiNjJjMWZlODA2NWZlYzkiLCJzY29wZSI6WyJhbGxlZ3JvX2FwaSJdfQ.5QmOYdg6r6gnc0kHYJ5Li-vBBm_E2KpUxzMFDt0I8tjyXv9xorgvaLC_r_Sz2wl78AcJAzKrn6NSqWta_dBbn5z36IxxefOnBhZeyxTCEhpmmMom5x7tDEMXRqFm3n0_DwddsBP8774dAgabVRi11UuGoCww0cgjiLTkfR_4LW00bmOCgF8Ye2kPNAbKzVQZ4gw2Sqv_ShHm5Lpvt7SyA5R41t2mGTrx0wTPVry9UW0r-6sHFbcbhzj9xhCO9nvxsgaH6tflUWGve9of3RUwQGYnhFhpIRvF8gaauVhHkRm0Eu1vpRZeIGFAZCwkMnYeZwNrM55Sq8ssC64pVq_oSw";

    public void getPrices(ExecutorService executor) throws Exception{

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .executor(executor)
                .build();

        List<URI> uris = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            uris.add(URI.create("https://api.allegro.pl/sale/products?phrase=" + i + "&limit=1"));
        }

        List<CompletableFuture<String>> result = uris.stream()
                .map(url -> client.sendAsync(
                        HttpRequest.newBuilder(url)
                                .GET()
                                .headers("Authorization", "Bearer " + token, "Accept", "application/vnd.allegro.public.v1+json")
                                .build(),
                        HttpResponse.BodyHandlers.ofString())
                        .thenApply(response -> response.body()))
                .collect(Collectors.toList());

        for (CompletableFuture<String> future : result) {
            System.out.println(future.get());
        }

            /*CompletableFuture<HttpResponse<String>> futureResponse =
                    client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

            futureResponse
                .thenAcceptAsync( response -> {
                    if(response.body().contains("Bosch")){
                        try{
                            Thread.sleep(10000);
                            System.out.println("wtf");
                        }catch (InterruptedException e){}
                    }
                    getName(response.body());
                } , executor);*/
    }

    public void getName(String response){
        JSONObject jsonObject = new JSONObject(response);
        if(jsonObject!=null) {
            String name = jsonObject
                    .getJSONArray("products")
                    .getJSONObject(0)
                    .getString("name");
            System.out.println(name);
        }
    }

}
