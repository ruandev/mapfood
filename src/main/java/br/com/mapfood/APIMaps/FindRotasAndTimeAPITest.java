package br.com.mapfood.APIMaps;

import org.json.simple.JSONObject;

public class FindRotasAndTimeAPITest {

      static final String COORDENADAS_ESTABELECIMENTO = "-30.01196,-51.199383";  //codigo  59
      static final String COORDENADAS_CLIENTE = "-30.03742831,-51.228496"; //codigo 1

    public static void main(String[] args) {

        FindRotasAndTimeAPI  findRotasAndTimeAPI = new FindRotasAndTimeAPI();
        //Liste as coordenadas de latitude antes das coordenadas de longitude.
        //Implementar a primeiro a busca por id e retornar a latitude e longitude e passar elas como parametro

        ObJectRotas testeRotaUM = findRotasAndTimeAPI.ApiBuscaRota(COORDENADAS_ESTABELECIMENTO, COORDENADAS_CLIENTE);
        System.out.println(testeRotaUM);
    }

}
