package br.com.mapfood.processors;

import java.util.ArrayList;
import java.util.List;

import br.com.mapfood.domain.Cliente;
import br.com.mapfood.domain.Estabelecimento;
import org.springframework.stereotype.Service;
import br.com.mapfood.domain.ItemDoPedido;
import br.com.mapfood.domain.Pedido;
import br.com.mapfood.domain.enums.EstadoDoPedido;

@Service
public class PedidoProcessor {

	
	public List<Pedido> criarPedidos(){
		
		List<ItemDoPedido> itens1 = new ArrayList<>();
		List<ItemDoPedido> itens2 = new ArrayList<>();
		List<ItemDoPedido> itens3 = new ArrayList<>();
		List<ItemDoPedido> itens4 = new ArrayList<>();

		Pedido p1=new Pedido(
				1l,
				new Cliente(1001l),
				new Estabelecimento(1001l),
				EstadoDoPedido.PREPARANDO,
				null
				);
			
		Pedido p2=new Pedido(
				2l,
				new Cliente(1002l),
				new Estabelecimento(1001l),
				EstadoDoPedido.PREPARANDO,
				null
				);
		
		Pedido p3=new Pedido(
				3l,
				new Cliente(1003l),
				new Estabelecimento(1001l),
				EstadoDoPedido.PREPARANDO,
				null
				);
				
		Pedido p4=new Pedido(
				4l,
				new Cliente(1004l),
				new Estabelecimento(1001l),
				EstadoDoPedido.EM_DESLOCAMENTO,
				null
				);

		Pedido p5=new Pedido(
				5l,
				new Cliente(1005l),
				new Estabelecimento(1001l),
				EstadoDoPedido.EM_DESLOCAMENTO,
				null
		);

		ItemDoPedido item1 = new ItemDoPedido();
		ItemDoPedido item2 = new ItemDoPedido();
		ItemDoPedido item3 = new ItemDoPedido();
		ItemDoPedido item4 = new ItemDoPedido();
		ItemDoPedido item5 = new ItemDoPedido();
		ItemDoPedido item6 = new ItemDoPedido();
		ItemDoPedido item7 = new ItemDoPedido();
		
		item1.setId((long)1);
		item1.setIdProduto((long)5);
		item1.setQuantidade((long)2);
		item1.setPedido(p1);
		
		item2.setId((long)2);
		item2.setIdProduto((long)17);
		item2.setQuantidade((long)3);
		item2.setPedido(p2);
		
		item3.setId((long)3);
		item3.setIdProduto((long)353);
		item3.setQuantidade((long)4);
		item3.setPedido(p2);
		
		item4.setId((long)4);
		item4.setIdProduto((long)18);
		item4.setQuantidade((long)1);
		item4.setPedido(p3);
		
		item5.setId((long)5);
		item5.setIdProduto((long)560);
		item5.setQuantidade((long)1);
		item5.setPedido(p3);
		
		item6.setId((long)6);
		item6.setIdProduto((long)31);
		item6.setQuantidade((long)1);
		item6.setPedido(p4);
		
		item7.setId((long)7);
		item7.setIdProduto((long)594);
		item7.setQuantidade((long)1);
		item7.setPedido(p4);
		
			
		itens1.add(item1);
		itens2.add(item2);
		itens2.add(item3);
		itens3.add(item4);
		itens3.add(item5);
		itens4.add(item6);
		itens4.add(item7);
		
		p1.setItens(itens1);
		p2.setItens(itens2);
		p3.setItens(itens3);
		p4.setItens(itens4);
		p5.setItens(itens4);
		
	    List<Pedido> pedidos = new ArrayList<Pedido>();
	    pedidos.add(p1);
	    pedidos.add(p2);
	    pedidos.add(p3);
	    pedidos.add(p4);
		pedidos.add(p5);
	   
	    return pedidos;
	}

}
