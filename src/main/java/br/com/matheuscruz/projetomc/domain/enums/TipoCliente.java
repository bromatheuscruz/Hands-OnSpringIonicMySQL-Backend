package br.com.matheuscruz.projetomc.domain.enums;

public enum TipoCliente {

	PESSOA_FISICA(1, "Pessoa Jurídica"), PESSOA_JURIDICA(2, "Pessoa Jurídica");

	private int codigo;
	private String descricao;

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	private TipoCliente(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public static TipoCliente toEnum(Integer codigo) {

		if (codigo == null) {
			return null;
		}

		for (TipoCliente tipoCliente : TipoCliente.values()) {
			if (codigo.equals(tipoCliente.getCodigo()))
				return tipoCliente;
		}

		throw new IllegalArgumentException("Id inválido: " + codigo);
	}
}
