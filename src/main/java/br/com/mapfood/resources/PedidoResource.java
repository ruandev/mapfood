package br.com.mapfood.resources;

import java.util.List;

import com.google.maps.model.DirectionsStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mapfood.service.PedidoService;
import br.com.mapfood.domain.Pedido;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService pedidoService;
		
    @GetMapping
    public ResponseEntity<List<Pedido>> listar(){
        List<Pedido> pedidos = pedidoService.findAll();
        return ResponseEntity.ok().body(pedidos);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable Long id){
        Pedido pedido = pedidoService.findById(id);
        return ResponseEntity.ok().body(pedido);
    }
    
    @GetMapping(value="/selecionarmotoboy/{idPedido}")
    public ResponseEntity<Pedido> selecionarMotoBoy(@PathVariable Long idPedido){
    	return ResponseEntity.ok().body(pedidoService.selecionarMotoBoy(idPedido));
    }
   
    @GetMapping(value="/rota/{idPedido}")
    public ResponseEntity<List<DirectionsStep>> selecionarRota(@PathVariable Long idPedido){
    	return ResponseEntity.ok().body(pedidoService.gerarRota(idPedido));
    }

    @GetMapping(value="/expectativaentrega/{idPedido}")
    public ResponseEntity<Long> expectativaEntrega(@PathVariable Long idPedido){
        return ResponseEntity.ok().body(pedidoService.expectativaEntrega(idPedido));
    }
}
