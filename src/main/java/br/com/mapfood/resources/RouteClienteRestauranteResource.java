package br.com.mapfood.resources;

import br.com.mapfood.APIMaps.FindRotasAndTimeAPI;
import br.com.mapfood.Service.ClienteService;
import br.com.mapfood.Service.EstabelecimentoService;
import br.com.mapfood.domain.Cliente;
import br.com.mapfood.domain.Estabelecimento;
import br.com.mapfood.domain.Rotas;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Rotas", description = "Busca rota entre cliente e restaurante")
@RequestMapping(value = "/rotaclienterestaurante")
public class RouteClienteRestauranteResource {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @Autowired
    private FindRotasAndTimeAPI findRoutesAndTimeService;

    @ApiOperation(value = "Retorna tempo e distancia entre cliente e restaurante")
    @GetMapping
    public ResponseEntity<Rotas> buscaRotaClienteRestaurante(@RequestHeader Long idCliente, @RequestHeader Long idEstabelecimento ){
        //Busca coordenadas do cliente
        Cliente cliente1 =  clienteService.findById(idCliente);
        String CORDENADAS_ORIGEM = cliente1.getLongitude()+","+ cliente1.getLatitude();

        //Buscando no repositorio das coordenadas do estabelecimento
        Estabelecimento estabelecimento =  estabelecimentoService.findById(idEstabelecimento);
        String CORDENADAS_DESTINO = estabelecimento.getLongitude() +","+ estabelecimento.getLatitude() ;

        Rotas obJectRotas = findRoutesAndTimeService.ApiBuscaRota(CORDENADAS_ORIGEM, CORDENADAS_DESTINO);
        System.out.println("Rota de entrega entre cliente e restaurente: " + obJectRotas);

        return ResponseEntity.ok(obJectRotas);
    }
}
