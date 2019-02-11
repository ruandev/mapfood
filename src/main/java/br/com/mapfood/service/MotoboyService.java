package br.com.mapfood.service;

import br.com.mapfood.apimaps.GMapsMatrixAPI;
import br.com.mapfood.domain.Estabelecimento;
import br.com.mapfood.domain.Motoboy;
import br.com.mapfood.domain.Pedido;
import br.com.mapfood.domain.Rotas;
import br.com.mapfood.processors.MotoboyProcessor;
import br.com.mapfood.repository.MotoboyRepository;
import br.com.mapfood.util.DistanciaEmKm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MotoboyService {

    @Autowired
    private MotoboyRepository motoboyRepository;

    @Autowired
    private MotoboyProcessor motoboyProcessor;

    public void processarFileMotoboy(){
        List<Motoboy> listMotoboy = motoboyProcessor.lerArquivo();

        motoboyRepository.saveAll(listMotoboy);
    }

    public List<Motoboy> findAll(){
        return motoboyRepository.findAll();
    }

    public Motoboy findById(Long id) {
        Optional<Motoboy> obj = motoboyRepository.findById(id);
        return obj.orElse(null);
    }

    public Motoboy definirMotoboyPedido(Pedido pedido) {
        List<Motoboy> listaTodosMotoBoy = motoboyRepository.findMotoboyListDisponivel();
        List<Motoboy> motoboysComDistancia = new ArrayList();
        List<Motoboy> motoboyRotasGoogle = new ArrayList();

        String cordenadasOrigem;
        String cordenadasDestino;

        Estabelecimento estabelecimento = pedido.getEstabelecimento();

        for (Motoboy motoboy : listaTodosMotoBoy) {

            Double distanciaMotoBoy = DistanciaEmKm.calcularDistancia(
                    Double.parseDouble(estabelecimento.getLatitude()),
                    Double.parseDouble(estabelecimento.getLongitude()),
                    Double.parseDouble(motoboy.getLatitude()),
                    Double.parseDouble(motoboy.getLongitude()));

            motoboysComDistancia.add(new Motoboy().builder()
                    .id(motoboy.getId())
                    .distanciaParaEstabelecimento(distanciaMotoBoy)
                    .latitude(motoboy.getLatitude())
                    .longitude(motoboy.getLongitude()).build());
        }

        List<Motoboy> motoboysProximos = motoboysComDistancia.stream()
                .sorted(Comparator.comparing(Motoboy::getDistanciaParaEstabelecimento))
                .collect(Collectors.toList())
                .subList(0, 5);

        cordenadasOrigem = estabelecimento.getLongitude() + ", " + estabelecimento.getLatitude();

        for (Motoboy motoboy : motoboysProximos) {
            cordenadasDestino = motoboy.getLongitude() + "," + motoboy.getLatitude();
            Rotas rota = GMapsMatrixAPI.buscarDistanciaTempo(cordenadasOrigem, cordenadasDestino);
            motoboy.setDistanciaParaEstabelecimento(rota.getDistanciaMetros());
            motoboyRotasGoogle.add(motoboy);
        }

        //ordena a lista pela distancia
        return motoboyRotasGoogle.stream()
                .min(Comparator.comparing(Motoboy::getDistanciaParaEstabelecimento))
                .get();
    }
}
