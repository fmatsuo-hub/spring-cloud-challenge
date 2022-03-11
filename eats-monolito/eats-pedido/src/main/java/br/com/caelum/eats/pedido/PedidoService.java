package br.com.caelum.eats.pedido;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.caelum.eats.pedido.Pedido.Status;

@Service
public class PedidoService {
	
	private static final Logger LOG = LoggerFactory.getLogger(PedidoService.class);

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
		
		String cep = pedido.getEntrega().getCep();
		Long id = pedido.getRestaurante().getId();
		
		LOG.info("buscando distancia do restaurante");
		RestauranteComDistanciaDto distancia = distanciaClient.comDistanciaPorId(cep, id);
		System.out.println("Distancia do restaurante: " + distancia.getDistancia());
	}
	
	public void confirmaPagamento() {
		
		LOG.info("confirmando pagamento id {}", 1L);
		PagamentoDto pagamento = pagamentoClient.confirma(1L);
		System.out.println("Status Pagamento: " + pagamento.getStatus());
	}

}
