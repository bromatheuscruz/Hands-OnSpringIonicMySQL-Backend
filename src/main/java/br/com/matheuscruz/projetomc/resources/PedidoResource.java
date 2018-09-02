package br.com.matheuscruz.projetomc.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody @Valid Pedido pedido) {

		pedido = pedidoService.insert(pedido);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@GetMapping("")
	public ResponseEntity<Page<Pedido>> findPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "linesPerPage", defaultValue = "4") Integer linesPerPage,
			@RequestParam(name = "orderBy", defaultValue = "instante") String orderBy,
			@RequestParam(name = "direction", defaultValue = "DESC") String direction) {

		Page<Pedido> pagesPedido = pedidoService.findPage(page, linesPerPage, orderBy, direction);

		return ResponseEntity.ok().body(pagesPedido);

	}

}
