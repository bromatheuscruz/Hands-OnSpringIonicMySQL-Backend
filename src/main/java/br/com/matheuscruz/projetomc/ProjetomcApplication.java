package br.com.matheuscruz.projetomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.matheuscruz.projetomc.domain.Categoria;
import br.com.matheuscruz.projetomc.domain.Cidade;
import br.com.matheuscruz.projetomc.domain.Cliente;
import br.com.matheuscruz.projetomc.domain.Endereco;
import br.com.matheuscruz.projetomc.domain.Estado;
import br.com.matheuscruz.projetomc.domain.Pagamento;
import br.com.matheuscruz.projetomc.domain.PagamentoComBoleto;
import br.com.matheuscruz.projetomc.domain.PagamentoComCartao;
import br.com.matheuscruz.projetomc.domain.Pedido;
import br.com.matheuscruz.projetomc.domain.Produto;
import br.com.matheuscruz.projetomc.domain.enums.EstadoPagamento;
import br.com.matheuscruz.projetomc.domain.enums.TipoCliente;
import br.com.matheuscruz.projetomc.repositories.CategoriaRepository;
import br.com.matheuscruz.projetomc.repositories.CidadeRepository;
import br.com.matheuscruz.projetomc.repositories.ClienteRepository;
import br.com.matheuscruz.projetomc.repositories.EnderecoRepository;
import br.com.matheuscruz.projetomc.repositories.EstadoRepository;
import br.com.matheuscruz.projetomc.repositories.PagamentoRepository;
import br.com.matheuscruz.projetomc.repositories.PedidoRepository;
import br.com.matheuscruz.projetomc.repositories.ProdutoRepository;

@SpringBootApplication
public class ProjetomcApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProjetomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//Cansei dessa bagunça ... rs

	}
}
