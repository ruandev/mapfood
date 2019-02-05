package br.com.mapfood.resources;

import br.com.mapfood.APIMaps.ObJectRotas;
import br.com.mapfood.service.EstabelecimentoService;
import br.com.mapfood.service.FindRoutesAndTimeService;
import br.com.mapfood.service.MotoboyService;
import br.com.mapfood.domain.Estabelecimento;
import br.com.mapfood.domain.Motoboy;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rotarestaurantemotoboy")
public class RouteRestauranteMotoboyResource {

    @Autowired
    private MotoboyService motoboyService;

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @Autowired
    private FindRoutesAndTimeService findRoutesAndTimeService;

    @ApiOperation(value = "Retorna tempo e distancia entre restaurante e motoboy")
    @GetMapping
    public ResponseEntity<ObJectRotas> buscaRotaRestauranteMotoboy(@RequestHeader Long idEstabelecimento, @RequestHeader Long idMotoboy ){
        //Buscando no repositorio das coordenadas do motoboy
        Estabelecimento estabelecimento =  estabelecimentoService.findById(idEstabelecimento);
        String CORDENADAS_ORIGEM = estabelecimento.getLongitude() +","+ estabelecimento.getLatitude() ;

        //Busca coordenadas do motoboy
        Motoboy motoboy =  motoboyService.findById(idMotoboy);
        String CORDENADAS_DESTINO = motoboy.getLongitude()+","+ motoboy.getLatitude();

        ObJectRotas obJectRotas = findRoutesAndTimeService.ApiBuscaRota(CORDENADAS_ORIGEM, CORDENADAS_DESTINO);
        System.out.println("Rota restaurente ao motoboy: " + obJectRotas);

        return ResponseEntity.ok(obJectRotas);
    }














}
