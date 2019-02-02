package br.com.mapfood.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ItemDoPedido {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;	
	private Long idProduto;
	private Long quantidade;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="pedido_id")
	private Pedido pedido;
	
}
