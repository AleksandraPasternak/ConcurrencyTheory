import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class PriceGetter {

    private static final String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NzY0MDI0ODksInVzZXJfbmFtZSI6IjczODUzMjI2IiwianRpIjoiN2FmMTEzNDUtODk4Yi00YzdlLWEzMDEtNDUwMmIyZjA4MmEzIiwiY2xpZW50X2lkIjoiMGFkMWQ1ZjlmY2Y0NDgxYThiNjJjMWZlODA2NWZlYzkiLCJzY29wZSI6WyJhbGxlZ3JvX2FwaSJdfQ.5QmOYdg6r6gnc0kHYJ5Li-vBBm_E2KpUxzMFDt0I8tjyXv9xorgvaLC_r_Sz2wl78AcJAzKrn6NSqWta_dBbn5z36IxxefOnBhZeyxTCEhpmmMom5x7tDEMXRqFm3n0_DwddsBP8774dAgabVRi11UuGoCww0cgjiLTkfR_4LW00bmOCgF8Ye2kPNAbKzVQZ4gw2Sqv_ShHm5Lpvt7SyA5R41t2mGTrx0wTPVry9UW0r-6sHFbcbhzj9xhCO9nvxsgaH6tflUWGve9of3RUwQGYnhFhpIRvF8gaauVhHkRm0Eu1vpRZeIGFAZCwkMnYeZwNrM55Sq8ssC64pVq_oSw";

    public void getPrices(){
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        for(int i=0;i<200;i++) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.allegro.pl/sale/products?phrase="+i+"&limit=1"))
                    .timeout(Duration.ofMinutes(1))
                    .headers("Authorization", "Bearer " + token, "Accept", "application/vnd.allegro.public.v1+json")
                    .GET()
                    .build();

            try {
                HttpResponse<String> response =
                        client.send(request, HttpResponse.BodyHandlers.ofString());
                JSONObject jsontoken = new JSONObject(response.body());
                JSONArray products = jsontoken.getJSONArray("products");
                System.out.println(products.getJSONObject(0).getString("name"));
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

   /* private static final String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NzY0MDI0ODksInVzZXJfbmFtZSI6IjczODUzMjI2IiwianRpIjoiN2FmMTEzNDUtODk4Yi00YzdlLWEzMDEtNDUwMmIyZjA4MmEzIiwiY2xpZW50X2lkIjoiMGFkMWQ1ZjlmY2Y0NDgxYThiNjJjMWZlODA2NWZlYzkiLCJzY29wZSI6WyJhbGxlZ3JvX2FwaSJdfQ.5QmOYdg6r6gnc0kHYJ5Li-vBBm_E2KpUxzMFDt0I8tjyXv9xorgvaLC_r_Sz2wl78AcJAzKrn6NSqWta_dBbn5z36IxxefOnBhZeyxTCEhpmmMom5x7tDEMXRqFm3n0_DwddsBP8774dAgabVRi11UuGoCww0cgjiLTkfR_4LW00bmOCgF8Ye2kPNAbKzVQZ4gw2Sqv_ShHm5Lpvt7SyA5R41t2mGTrx0wTPVry9UW0r-6sHFbcbhzj9xhCO9nvxsgaH6tflUWGve9of3RUwQGYnhFhpIRvF8gaauVhHkRm0Eu1vpRZeIGFAZCwkMnYeZwNrM55Sq8ssC64pVq_oSw";

    public void getPrice() throws IOException{
        for(int i=0;i<200;i++){
            connectionGET(i);
        }
    }

    public void connectionGET(int i) throws IOException{
        URL url = new URL("https://api.allegro.pl/sale/products?phrase="+i+"&limit=1");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + token);
        connection.setRequestProperty("Accept","application/vnd.allegro.public.v1+json");

        printResult(connection);
    }

    public void printResult(HttpURLConnection connection) throws IOException{
        InputStream content = connection.getInputStream();
        BufferedReader in =
                new BufferedReader(new InputStreamReader(content));
        StringBuilder product = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            product.append(line);
        }

        JSONObject jsontoken = new JSONObject(product.toString());
        JSONArray products = jsontoken.getJSONArray("products");
        System.out.println(products.getJSONObject(0).getString("name"));
    }*/
}
