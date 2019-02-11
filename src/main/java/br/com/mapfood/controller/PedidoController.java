package br.com.mapfood.controller;

import java.util.List;

import com.google.maps.model.DirectionsStep;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mapfood.service.PedidoService;
import br.com.mapfood.domain.Pedido;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;

    @ApiOperation("Retorna todos os pedidos")
    @GetMapping
    public ResponseEntity<List<Pedido>> listar(){
        List<Pedido> pedidos = pedidoService.findAll();
        return ResponseEntity.ok().body(pedidos);
    }

    @ApiOperation("Retorna um pedido")
    @GetMapping(value="/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable Long id){
        Pedido pedido = pedidoService.findById(id);
        return ResponseEntity.ok().body(pedido);
    }

    @ApiOperation("Retorna o pedido com o motoboy que irá fazer a entrega")
    @GetMapping(value="/selecionarmotoboy/{idPedido}")
    public ResponseEntity<Pedido> selecionarMotoBoy(@PathVariable Long idPedido){
    	return ResponseEntity.ok().body(pedidoService.selecionarMotoBoy(idPedido));
    }

    @ApiOperation("Retorna a rota para entrega")
    @GetMapping(value="/rota/{idPedido}")
    public ResponseEntity<List<DirectionsStep>> selecionarRota(@PathVariable Long idPedido){
    	return ResponseEntity.ok().body(pedidoService.gerarRota(idPedido));
    }

    @ApiOperation("Retorna a previsão da entrega em minutos")
    @GetMapping(value="/previsaoDeEntrega/{idPedido}")
    public ResponseEntity<Long> previsaoDeEntrega(@PathVariable Long idPedido){
        return ResponseEntity.ok().body(pedidoService.previsaoDeEntrega(idPedido));
    }

    @ApiOperation("Organizar Entregas")
    @GetMapping(value="/montarRoteiro")
    public ResponseEntity<List<Pedido>> montarRoteiro(@RequestBody List<Long> idsPedido){
        return ResponseEntity.ok().body(pedidoService.montarRoteiro(idsPedido));
    }
}
