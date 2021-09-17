package br.com.cd2tec.javatest.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.cd2tec.javatest.model.Cotacao;
import br.com.cd2tec.javatest.repository.CotacaoRepository;

public class CotacaoFormDto {

	@NotNull
	private BigDecimal peso;

	@NotBlank
	@Length(max = 8)
	private String cepOrigem;

	@NotBlank
	@Length(max = 8)
	private String cepDestino;

	@NotBlank
	@Length(max = 50)
	private String nomeDestinatario;

	public BigDecimal getPeso() {
		return peso;
	}

	public String getCepOrigem() {
		return cepOrigem;
	}

	public String getCepDestino() {
		return cepDestino;
	}

	public String getNomeDestinatario() {
		return nomeDestinatario;
	}

	public Cotacao converter(CotacaoDto dto) {
		return new Cotacao(peso, cepOrigem, cepDestino, nomeDestinatario, dto.getVlTotalFrete(), dto.getDataPrevistaEntrega(), dto.getDataConsulta());
	}

	public Cotacao atualizar(Long id, CotacaoRepository cotacaoRepository) {
		Cotacao cotacao = cotacaoRepository.getById(id);
		cotacao.setPeso(this.peso);
		cotacao.setCepOrigem(this.cepOrigem);
		cotacao.setCepDestino(this.cepDestino);
		cotacao.setNomeDestinatario(this.nomeDestinatario);
		return cotacao;
	}

}
