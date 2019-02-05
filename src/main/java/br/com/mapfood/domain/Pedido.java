package br.com.mapfood.domain;

import br.com.mapfood.domain.enums.EstadoDoPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;
    private Long idCliente;
    private Long idEstabelecimento;
    private Long idMotoboy;

    private EstadoDoPedido statusPedido;

    @OneToMany(mappedBy="pedido", cascade=CascadeType.ALL)
    private List<ItemDoPedido> itens;
}
