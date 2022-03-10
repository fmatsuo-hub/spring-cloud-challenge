package br.com.caelum.eats.pedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.caelum.eats.pedido.Pedido.Status;

@Service
public class PedidoService {

	private PedidoRepository repo;
	
	@Autowired
	private DistanciaClient distanciaClient;
	
	@Autowired
	private PagamentoClient pagamentoClient;
	
	public Pedido porIdComItens(Long pedidoId) {
		return repo.porIdComItens(pedidoId).orElseThrow(ResourceNotFoundException::new);
	}

	public void atualizaStatus(Status status, Pedido pedido) {
		repo.atualizaStatus(status, pedido);
	}
	
	public void buscaDistanciaRestaurante(Pedido pedido) {
		
		RestauranteComDistanciaDto distancia = distanciaClient.comDistanciaPorId(pedido.getEntrega().getCep(), pedido.getRestaurante().getId());
		System.out.println("Distancia do restaurante: " + distancia.getDistancia());
	}
	
	public void confirmaPagamento() {
		
		PagamentoDto pagamento = pagamentoClient.confirma(1L);
		System.out.println("Status Pagamento: " + pagamento.getStatus());
	}

}
