package br.uem.geolocalizacao.rabbit;

import jakarta.validation.constraints.NotNull;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ImovelCreatedListener {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final OkHttpClient httpClient = new OkHttpClient();
    @RabbitListener(queues = "imoveis.v1.imovel-created")
    public void onImovelCreated(ImovelCreateEvent event) throws JSONException {
        String routingKey = "geolocalizacao.v1.geolocalizacao-created";

        GeolocalizacaoCreateEvent eventGeolocalizacao = new GeolocalizacaoCreateEvent(event.getId(), null, null);
        JSONObject retorno;
        try {
            retorno = this.searchGeo(event.getQuery().replace(" ", "+"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(retorno != null) {
            eventGeolocalizacao.setLat(retorno.getString("lat"));
            eventGeolocalizacao.setLon(retorno.getString("lon"));

            System.out.println(eventGeolocalizacao.toString());

            rabbitTemplate.convertAndSend(routingKey, eventGeolocalizacao);
        }

    }

    @NotNull
    public JSONObject searchGeo(String query) throws IOException {
        Request request = new Request.Builder()
                .url("https://nominatim.openstreetmap.org/?q=" + query + "&format=json&limit=1")
                .build();

        System.out.println(request);

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            JSONArray jsonArray = new JSONArray(response.body().string());

            if(jsonArray.length() > 0) {
                return jsonArray.getJSONObject(0);
            }

            return null;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
