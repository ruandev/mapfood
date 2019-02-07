package br.com.mapfood.domain;

<<<<<<< HEAD
=======
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

>>>>>>> master
import br.com.mapfood.domain.enums.EstadoDoPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< HEAD
import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
=======
>>>>>>> master

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
<<<<<<< HEAD
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;
    private Long idCliente;
    private Long idEstabelecimento;
    private Long idMotoboy;

    //private Long distanciaKm;
    //private Time tempo;

    private EstadoDoPedido statusPedido;

    @OneToMany(mappedBy="pedido", cascade=CascadeType.ALL)
    private List<ItemDoPedido> itens;
=======
public class Pedido implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long idPedido;
	private Long idCliente;
	private Long idEstabelecimento;    
	private Long idMotoboy;
	
	private EstadoDoPedido statusPedido;	
	
	@OneToMany(mappedBy="pedido", cascade=CascadeType.ALL)
	private List<ItemDoPedido> itens = new ArrayList();
		

	 
	 
>>>>>>> master
}
