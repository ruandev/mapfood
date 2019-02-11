package br.com.mapfood.controller;

import br.com.mapfood.apimaps.GMapsMatrixAPI;
import br.com.mapfood.service.EstabelecimentoService;
import br.com.mapfood.service.MotoboyService;
import br.com.mapfood.domain.Estabelecimento;
import br.com.mapfood.domain.Motoboy;
import br.com.mapfood.domain.Rotas;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rotarestaurantemotoboy")
public class RouteRestauranteMotoboyController {

    @Autowired
    private MotoboyService motoboyService;

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @Autowired
    private GMapsMatrixAPI findRoutesAndTimeService;

    @ApiOperation(value = "Retorna tempo e distancia entre restaurante e motoboy")
    @GetMapping
    public ResponseEntity<Rotas> buscaRotaRestauranteMotoboy(@RequestHeader Long idEstabelecimento, @RequestHeader Long idMotoboy ){
        //Buscando no repositorio das coordenadas do motoboy
        Estabelecimento estabelecimento =  estabelecimentoService.findById(idEstabelecimento);
        String coordenadasOrigem = estabelecimento.getLongitude() +","+ estabelecimento.getLatitude() ;

        //Busca coordenadas do motoboy
        Motoboy motoboy =  motoboyService.findById(idMotoboy);
        String cordenadasDestino = motoboy.getLongitude()+","+ motoboy.getLatitude();

        Rotas obJectRotas = findRoutesAndTimeService.buscarDistanciaTempo(coordenadasOrigem, cordenadasDestino);
        System.out.println("Rota restaurente ao motoboy: " + obJectRotas);

        return ResponseEntity.ok(obJectRotas);
    }














}
