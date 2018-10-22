package br.com.matheuscruz.projetomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheuscruz.projetomc.domain.Cidade;
import br.com.matheuscruz.projetomc.domain.Estado;
import br.com.matheuscruz.projetomc.dto.CidadeDTO;
import br.com.matheuscruz.projetomc.dto.EstadoDTO;
import br.com.matheuscruz.projetomc.services.CidadeService;
import br.com.matheuscruz.projetomc.services.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoResource {

	@Autowired
	EstadoService estadoService;

	@Autowired
	CidadeService cidadeService;

	@GetMapping
	public ResponseEntity<List<EstadoDTO>> findAll() {

		List<Estado> estados = estadoService.findAll();

		List<EstadoDTO> listDTO = estados.stream().map(estado -> new EstadoDTO(estado)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listDTO);

	}

	@GetMapping("/{estadoId}/cidades")
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId) {

		List<Cidade> list = cidadeService.findByEstado(estadoId);

		List<CidadeDTO> listDto = list.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listDto);
	}

}
