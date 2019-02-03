package br.com.mapfood.APIMaps;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class FindRotasAndTimeAPI {

        ObJectRotas obJectRotas = new ObJectRotas();
        String API_KEY="AIzaSyDHQu5G007T8-ANcovnWyGIZrQ6r9DCssk";
        OkHttpClient client = new OkHttpClient();
        JSONParser parser = new JSONParser();

        public ObJectRotas ApiBuscaRota(String cordenadasOrigem,String coordenadasDestino) {
            try {

                String url="https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&origins="+cordenadasOrigem+"&destinations="+coordenadasDestino+"&mode=driving&key="+ API_KEY;
                Request request = new Request.Builder().url(url).build();

                Response response = client.newCall(request).execute();
                String responseFinal =  response.body().string();
//                System.out.println(responseFinal);

                try {
                    Object obj = parser.parse(responseFinal);
                    JSONObject jsonobj=(JSONObject)obj;
                    JSONArray dist=(JSONArray)jsonobj.get("rows");
                    JSONObject obj2 = (JSONObject)dist.get(0);
                    JSONArray disting=(JSONArray)obj2.get("elements");
                    JSONObject obj3 = (JSONObject)disting.get(0);
                    JSONObject obj4=(JSONObject)obj3.get("distance");
                    JSONObject obj5=(JSONObject)obj3.get("duration");

                    obJectRotas.setDistancia(obj4.get("text").toString());
                    obJectRotas.setDistanciaMetros(Integer.valueOf(obj4.get("value").toString()));
                    obJectRotas.setTempoHorasMinutos(obj5.get("text").toString());
                    obJectRotas.setTempoSegundos(Integer.valueOf(obj5.get("value").toString()));
//                    System.out.println(obJectRotas.getDistancia());
//                    System.out.println(obJectRotas.getDistanciaMetros());
//                    System.out.println(obJectRotas.getTempoHorasMinutos());
//                    System.out.println(obJectRotas.getTempoSegundos());
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
            catch(Exception e) {
                System.out.println("Exception Occurred");
            }
            return obJectRotas;
    }

}
