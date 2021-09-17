package br.com.cd2tec.javatest.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Cotacao {

	@Id
	@SequenceGenerator(name = "cotacao_seq", sequenceName = "cotacao_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cotacao_seq")
	private Long id;
	private BigDecimal peso;
	private String cepOrigem;
	private String cepDestino;
	private String nomeDestinatario;
	private BigDecimal vlTotalFrete;
	private LocalDate dataPrevistaEntrega;
	private LocalDate dataConsulta;

	public Cotacao() {

	}

	public Cotacao(BigDecimal peso, String cepOrigem, String cepDestino, String nomeDestinatario,
			BigDecimal vlTotalFrete, LocalDate dataPrevistaEntrega, LocalDate dataConsulta) {
		this.peso = peso;
		this.cepOrigem = cepOrigem;
		this.cepDestino = cepDestino;
		this.nomeDestinatario = nomeDestinatario;
		this.vlTotalFrete = vlTotalFrete;
		this.dataPrevistaEntrega = dataPrevistaEntrega;
		this.dataConsulta = dataConsulta;
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public void setCepOrigem(String cepOrigem) {
		this.cepOrigem = cepOrigem;
	}

	public void setCepDestino(String cepDestino) {
		this.cepDestino = cepDestino;
	}

	public void setNomeDestinatario(String nomeDestinatario) {
		this.nomeDestinatario = nomeDestinatario;
	}

	public void setVlTotalFrete(BigDecimal vlTotalFrete) {
		this.vlTotalFrete = vlTotalFrete;
	}

	public void setDataPrevistaEntrega(LocalDate dataPrevistaEntrega) {
		this.dataPrevistaEntrega = dataPrevistaEntrega;
	}

	public void setDataConsulta(LocalDate dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

}
