package br.com.mapfood.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.mapfood.domain.enums.EstadoDoPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pedido implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long idPedido;
	private Long idCliente;
	private Long idEstabelecimento;    
	private Long idMotoboy;
	private Long tempoExpectativaEntrega;
	
	private EstadoDoPedido statusPedido;	
	
	@OneToMany(mappedBy="pedido", cascade=CascadeType.ALL)
	private List<ItemDoPedido> itens = new ArrayList();

	public Pedido(Long idPedido, Long idCliente, Long idEstabelecimento, EstadoDoPedido statusPedido, List<ItemDoPedido> itens) {
		this.idPedido = idPedido;
		this.idCliente = idCliente;
		this.idEstabelecimento = idEstabelecimento;
		this.statusPedido = statusPedido;
		this.itens = itens;
	}
}
