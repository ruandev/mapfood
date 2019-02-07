package br.com.mapfood.domain;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnore;
=======
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


>>>>>>> master
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< HEAD
import javax.persistence.*;

=======
>>>>>>> master
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ItemDoPedido {

<<<<<<< HEAD
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idProduto;
    private Long quantidade;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="pedido_id")
    private Pedido pedido;
=======
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;	
	private Long idProduto;
	private Long quantidade;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="pedido_id")
	private Pedido pedido;
	
>>>>>>> master
}
