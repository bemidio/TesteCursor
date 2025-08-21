package com.example.TesteCursor.garantias.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import com.example.TesteCursor.garantias.config.HaircutProperties;
import com.example.TesteCursor.garantias.dto.CalculoSuficienciaRequest;
import com.example.TesteCursor.garantias.dto.GarantiaInput;

@Service
public class CalculadoraSuficienciaGarantiasService {

	private final HaircutProperties haircutProperties;

	public CalculadoraSuficienciaGarantiasService(HaircutProperties haircutProperties) {
		this.haircutProperties = haircutProperties;
	}

	public ResultadoCalculo calcularSuficiencia(CalculoSuficienciaRequest request) {
		BigDecimal valorExposicao = request.getValorExposicao();
		BigDecimal valorTotalGarantiasAjustado = BigDecimal.ZERO;

		// possivel extensão para injetar novas garantias.
		for (GarantiaInput garantia : request.getGarantias()) {
			BigDecimal haircut = haircutProperties.obterHaircutPercentual(garantia.getTipo());
			BigDecimal fatorManutencao = BigDecimal.ONE.subtract(haircut.divide(BigDecimal.valueOf(100), new MathContext(10, RoundingMode.HALF_UP)));
			BigDecimal valorAjustado = garantia.getValor().multiply(fatorManutencao);
			valorTotalGarantiasAjustado = valorTotalGarantiasAjustado.add(valorAjustado);
		}

		boolean suficiente = valorTotalGarantiasAjustado.compareTo(valorExposicao) >= 0;
		BigDecimal sobraOuDeficit = valorTotalGarantiasAjustado.subtract(valorExposicao).setScale(2, RoundingMode.HALF_UP);

		return new ResultadoCalculo(valorTotalGarantiasAjustado.setScale(2, RoundingMode.HALF_UP), suficiente, sobraOuDeficit);
	}

	public static class ResultadoCalculo {
		private final BigDecimal valorGarantiasAjustado;
		private final boolean suficiente;
		private final BigDecimal sobraOuDeficit;

		public ResultadoCalculo(BigDecimal valorGarantiasAjustado, boolean suficiente, BigDecimal sobraOuDeficit) {
			this.valorGarantiasAjustado = valorGarantiasAjustado;
			this.suficiente = suficiente;
			this.sobraOuDeficit = sobraOuDeficit;
		}

		public BigDecimal getValorGarantiasAjustado() {
			return valorGarantiasAjustado;
		}

		public boolean isSuficiente() {
			return suficiente;
		}

		public BigDecimal getSobraOuDeficit() {
			return sobraOuDeficit;
		}
	}
}


