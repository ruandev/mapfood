package br.com.mapfood.domain;

import br.com.mapfood.domain.enums.EstadoDoPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


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

	@OneToOne(fetch = FetchType.EAGER)
	private Cliente cliente;

	@OneToOne(fetch = FetchType.EAGER)
	private Estabelecimento estabelecimento;

	@OneToOne(fetch = FetchType.EAGER)
	private Motoboy motoboy;

	private Long tempoExpectativaEntrega;
	
	private EstadoDoPedido statusPedido;	
	
	@OneToMany(mappedBy="pedido", cascade=CascadeType.ALL)
	private List<ItemDoPedido> itens = new ArrayList();

	@Transient
	private Long tempoEstabelecimentoCliente;

	public Pedido(Long idPedido, Cliente cliente, Estabelecimento estabelecimento, EstadoDoPedido statusPedido, List<ItemDoPedido> itens) {
		this.idPedido = idPedido;
		this.cliente = cliente;
		this.estabelecimento = estabelecimento;
		this.statusPedido = statusPedido;
		this.itens = itens;
	}
}
