package br.com.caelum.eats.pedido;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient("eats-pagamento")
public interface PagamentoClient {
	
	@PutMapping("/pagamentos/{id}")
	PagamentoDto confirma(@PathVariable("id") Long id);

}
