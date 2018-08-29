package br.com.matheuscruz.projetomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheuscruz.projetomc.domain.Pedido;
import br.com.matheuscruz.projetomc.services.PedidoService;

@RequestMapping("/pedidos")
@RestController
public class PedidoResource {

	@Autowired
	PedidoService pedidoService;

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> find(@PathVariable Integer id) {

		Pedido pedido = pedidoService.find(id);

		if (pedido == null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok().body(pedido);
	}
}
