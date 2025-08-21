package com.example.TesteCursor.garantias.dto;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CalculoSuficienciaRequest {

	@NotNull
	@Positive
	private BigDecimal valorExposicao;

	@NotEmpty
	@Valid
	private List<GarantiaInput> garantias;

	public BigDecimal getValorExposicao() {
		return valorExposicao;
	}

	public void setValorExposicao(BigDecimal valorExposicao) {
		this.valorExposicao = valorExposicao;
	}

	public List<GarantiaInput> getGarantias() {
		return garantias;
	}

	public void setGarantias(List<GarantiaInput> garantias) {
		this.garantias = garantias;
	}
}


