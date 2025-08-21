package com.example.TesteCursor.garantias.dto;

import java.math.BigDecimal;

import com.example.TesteCursor.garantias.model.TipoGarantia;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class GarantiaInput {

	@NotNull
	private TipoGarantia tipo;

	@NotNull
	@Positive
	private BigDecimal valor;

	public TipoGarantia getTipo() {
		return tipo;
	}

	public void setTipo(TipoGarantia tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
}


