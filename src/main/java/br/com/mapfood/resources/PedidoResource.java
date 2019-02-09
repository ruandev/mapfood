package br.com.mapfood.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mapfood.Service.PedidoService;
import br.com.mapfood.domain.Pedido;

@RestController
@RequestMapping(value = "/Pedidos")
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
    
    @PostMapping(value="/selecionarmotoboy")
    public ResponseEntity<Pedido> selecionarMotoBoy(@RequestBody Pedido p){
    	Pedido pedido = pedidoService.selecionarMotoBoy(p);
    	return ResponseEntity.ok().body(pedido);
    }
   
    @PostMapping(value="/rota")
    public ResponseEntity<Pedido> selecionarRota(@RequestBody Pedido p){
    	Pedido pedido = pedidoService.gerarRota(p);
    	return ResponseEntity.ok().body(pedido);
    }

}
