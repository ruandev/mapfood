package br.com.mapfood.resources;

<<<<<<< HEAD
import br.com.mapfood.service.PedidoService;
import br.com.mapfood.domain.Pedido;
import io.swagger.annotations.ApiOperation;
=======
import java.util.List;

>>>>>>> master
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    @Autowired
    PedidoService pedidoService;

    @ApiOperation(value = "Listar todos os pedidos")
=======
import br.com.mapfood.Service.PedidoService;
import br.com.mapfood.domain.Pedido;

@RestController
@RequestMapping(value = "/Pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService pedidoService;
		
>>>>>>> master
    @GetMapping
    public ResponseEntity<List<Pedido>> listar(){
        List<Pedido> pedidos = pedidoService.findAll();
        return ResponseEntity.ok().body(pedidos);
    }

<<<<<<< HEAD
    @ApiOperation(value = "Buscar pedido pelo id")
=======
>>>>>>> master
    @GetMapping(value="/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable Long id){
        Pedido pedido = pedidoService.findById(id);
        return ResponseEntity.ok().body(pedido);
    }
<<<<<<< HEAD
=======
    
   

>>>>>>> master
}
