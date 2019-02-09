package br.com.mapfood.ApiMatrixGoogleMaps;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class FindRotasSteps {

        ObjectRotasSteps obJectRotas = new ObjectRotasSteps();
        String API_KEY="AIzaSyDHQu5G007T8-ANcovnWyGIZrQ6r9DCssk";
        OkHttpClient client = new OkHttpClient();
        JSONParser parser = new JSONParser();

        public ObjectRotasSteps Api(String cordenadasOrigem,String cordenadasDestino) {
            try {

                String url="https://maps.googleapis.com/maps/api/directions/json?origin="+cordenadasOrigem+"&destination="+cordenadasDestino+"&key=AIzaSyDHQu5G007T8-ANcovnWyGIZrQ6r9DCssk&language=pt-BR";
            	Request request = new Request.Builder().url(url).build();

                Response response = client.newCall(request).execute();
                String responseFinal =  response.body().string();

                System.out.println("Response final "+responseFinal);
                System.out.println(cordenadasOrigem+" "+cordenadasDestino);
                
            }
            catch(Exception e) {
                System.out.println("Exception Occurred");
            }
            return null;
    }

}
