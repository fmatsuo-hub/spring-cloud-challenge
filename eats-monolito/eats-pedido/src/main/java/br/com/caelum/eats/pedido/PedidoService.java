package br.com.caelum.eats.pedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.caelum.eats.pedido.Pedido.Status;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PedidoService {

	private PedidoRepository repo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public Pedido porIdComItens(Long pedidoId) {
		return repo.porIdComItens(pedidoId).orElseThrow(ResourceNotFoundException::new);
	}

	public void atualizaStatus(Status status, Pedido pedido) {
		repo.atualizaStatus(status, pedido);
	}
	
	public void buscaDistanciaRestaurante(Pedido pedido) {
		ResponseEntity<RestauranteComDistanciaDto> distancia = restTemplate.exchange("http://eats-distancia/restaurantes/"+pedido.getEntrega().getCep()+
				"/restaurante/"+pedido.getRestaurante().getId(), HttpMethod.GET, null, RestauranteComDistanciaDto.class);
		
		System.out.println("Distancia do restaurante: " + distancia.getBody().getDistancia());
	}
	
	public void confirmaPagamento() {
		ResponseEntity<PagamentoDto> pagamento = restTemplate.exchange("http://eats-pagamento/pagamentos/1", HttpMethod.PUT, null, PagamentoDto.class);
		
		System.out.println("Status Pagamento: " + pagamento.getBody().getStatus());
	}

}
