package br.com.mapfood.resources;

import br.com.mapfood.service.PedidoService;
import br.com.mapfood.domain.Pedido;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    @Autowired
    PedidoService pedidoService;

    @ApiOperation(value = "Listar todos os pedidos")
    @GetMapping
    public ResponseEntity<List<Pedido>> listar(){
        List<Pedido> pedidos = pedidoService.findAll();
        return ResponseEntity.ok().body(pedidos);
    }

    @ApiOperation(value = "Buscar pedido pelo id")
    @GetMapping(value="/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable Long id){
        Pedido pedido = pedidoService.findById(id);
        return ResponseEntity.ok().body(pedido);
    }
}