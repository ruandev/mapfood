package br.com.mapfood.ApiMatrixGoogleMaps;

import org.json.simple.JSONObject;

public class FindRotasAndTimeAPITest {

      public static JSONObject distancia = new JSONObject();
      public static JSONObject tempo = new JSONObject();

      static final String CORDENADAS_TESTE_ORIGEM = "-30.03742831,-51.228496";
      static final String CORDENADAS_TESTE_DESTINO = "-23.603658,-46.640973";

    public static void main(String[] args) {

        FindRotasAndTimeAPI  findRotasAndTimeAPI = new FindRotasAndTimeAPI();
//        ID Cliente,Longitude,Latitude
//                 1,-51.228496,-30.03742831

//        restaurant ,longitude,latitude,
//               -46.640973,-23.603658


//        Liste as coordenadas de latitude antes das coordenadas de longitude.

        //Implementar a primeiro a busca por id e retornar a latitude e longitude e passar elas como parametro
        ObJectRotas testeRotaUM = new ObJectRotas();
        testeRotaUM = findRotasAndTimeAPI.Api(CORDENADAS_TESTE_ORIGEM, CORDENADAS_TESTE_DESTINO);

        System.out.println(testeRotaUM);
    }

}
