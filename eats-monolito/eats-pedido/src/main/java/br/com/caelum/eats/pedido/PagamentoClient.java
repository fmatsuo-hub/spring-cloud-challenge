package br.com.caelum.eats.pedido;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("eats-pagamento")
public interface PagamentoClient {
	
	@RequestMapping("/pagamentos/{id}")
	PagamentoDto confirma(@PathVariable("id") Long id);

}
