package br.com.caelum.eats.pedido;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class RestauranteComDistanciaDto {

	private Long restauranteId;

	private BigDecimal distancia;

}

