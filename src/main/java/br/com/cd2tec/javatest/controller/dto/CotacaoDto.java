package br.com.cd2tec.javatest.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.cd2tec.javatest.model.Cotacao;

public class CotacaoDto {

	private Long id;	
	private BigDecimal peso;
	private String cepOrigem;
	private String cepDestino;
	private String nomeDestinatario;
	private BigDecimal vlTotalFrete;
	private LocalDate dataPrevistaEntrega;
	private LocalDate dataConsulta;
	
	
	public CotacaoDto(BigDecimal vlTotalFrete, LocalDate dataPrevistaEntrega, LocalDate dataConsulta) {
		this.vlTotalFrete = vlTotalFrete;
		this.dataPrevistaEntrega = dataPrevistaEntrega;
		this.dataConsulta = dataConsulta;
	}

	public CotacaoDto(Cotacao cotacao) {
		this.id = cotacao.getId();
		this.peso = cotacao.getPeso();
		this.cepOrigem = cotacao.getCepOrigem();
		this.cepDestino = cotacao.getCepDestino();
		this.nomeDestinatario = cotacao.getNomeDestinatario();
		this.vlTotalFrete = cotacao.getVlTotalFrete();
		this.dataPrevistaEntrega = cotacao.getDataPrevistaEntrega();
		this.dataConsulta = cotacao.getDataConsulta();
	}

	public Long getId() {
		return id;
	}

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

	public BigDecimal getVlTotalFrete() {
		return vlTotalFrete;
	}

	public LocalDate getDataPrevistaEntrega() {
		return dataPrevistaEntrega;
	}

	public LocalDate getDataConsulta() {
		return dataConsulta;
	}
	
	public static List<CotacaoDto> converter (List<Cotacao> cotacoes){
		return cotacoes.stream().map(CotacaoDto::new).collect(Collectors.toList());
	}
	
}
