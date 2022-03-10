package br.com.caelum.eats.pedido;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("eats-distancia")
public interface DistanciaClient {
	
	@GetMapping("/restaurantes/{cep}/restaurante/{restauranteId}")
	RestauranteComDistanciaDto comDistanciaPorId(@PathVariable("cep") String cep,
			@PathVariable("restauranteId") Long restauranteId);

}
