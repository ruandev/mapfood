package br.com.mapfood.APIMaps;

public class FindTest {


    public static void main(String[] args){
        FindRotasAndTimeAPI findRotasAndTimeAPI = new FindRotasAndTimeAPI();

        findRotasAndTimeAPI.ApiBuscaRota("-30.048995,-51.19373482", "-23.541937,-46.576658");
    }
}
