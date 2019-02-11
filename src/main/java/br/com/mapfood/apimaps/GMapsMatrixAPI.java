package br.com.mapfood.apimaps;

import br.com.mapfood.domain.Rotas;
import com.google.maps.DirectionsApi;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.*;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Service
public class GMapsMatrixAPI {

    private final static String API_KEY = "AIzaSyDHQu5G007T8-ANcovnWyGIZrQ6r9DCssk";


    public static Rotas buscarDistanciaTempo(String longitudeOrigem, String latitudeOrigem, String longitudeDestino, String latitudoDestino){
        return buscarDistanciaTempo(longitudeOrigem.concat(",").concat(latitudeOrigem), longitudeDestino.concat(",").concat(latitudoDestino));
    }

    public static Rotas buscarDistanciaTempo(String coordenadasOrigem, String coordenadasDestino) {

        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(API_KEY)
                .build();

        String[] origem = coordenadasOrigem.split(",");
        LatLng localicazaoOrigem = new LatLng(Double.valueOf(origem[0]), Double.valueOf(origem[1]));

        String[] destino = coordenadasDestino.split(",");
        LatLng localicazaoDestino = new LatLng(Double.valueOf(destino[0]), Double.valueOf(destino[1]));

        DistanceMatrix matrix = DistanceMatrixApi.newRequest(context)
                .origins(localicazaoOrigem)
                .destinations(localicazaoDestino)
                .awaitIgnoreError();

        DistanceMatrixElement rotaMatrix = matrix.rows[0].elements[0];

        return new Rotas().builder()
                    .distancia(rotaMatrix.distance.humanReadable)
                    .distanciaMetros(rotaMatrix.distance.inMeters)
                    .tempoHorasMinutos(rotaMatrix.duration.humanReadable)
                    .tempoSegundos(rotaMatrix.duration.inSeconds)
                    .build();
    }

    public static List<DirectionsStep> buscarRotas(String coordenadasOrigem, String coordenadasDestino) {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(API_KEY)
                .build();

        String[] origem = coordenadasOrigem.split(",");
        LatLng localicazaoOrigem = new LatLng(Double.valueOf(origem[0]), Double.valueOf(origem[1]));

        String[] destino = coordenadasDestino.split(",");
        LatLng localicazaoDestino = new LatLng(Double.valueOf(destino[0]), Double.valueOf(destino[1]));

        DirectionsResult direcao = DirectionsApi.newRequest(context)
                .units(Unit.METRIC)
                .language("pt_br")
                .origin(localicazaoOrigem)
                .destination(localicazaoDestino)
                .awaitIgnoreError();

        List<DirectionsStep> listSteps = Arrays.asList(direcao.routes[0].legs[0].steps);

        return listSteps;
    }
}
