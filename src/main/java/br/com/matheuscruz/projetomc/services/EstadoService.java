package br.com.matheuscruz.projetomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.matheuscruz.projetomc.domain.Estado;
import br.com.matheuscruz.projetomc.repositories.EstadoRepository;

@Service
public class EstadoService {
	
	@Autowired
	EstadoRepository estadoRepository;
	
	public List<Estado> findAll(){
		
		return estadoRepository.findAllByOrderByNome();
	}
	
}
