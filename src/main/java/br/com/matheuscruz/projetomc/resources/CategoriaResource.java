package br.com.matheuscruz.projetomc.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.matheuscruz.projetomc.domain.Categoria;
import br.com.matheuscruz.projetomc.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {

		Categoria categoria = categoriaService.find(id);

		if (categoria == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(categoria);

	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Categoria categoria) {

		categoria = categoriaService.insert(categoria);

		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(categoria.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();

	}

}
