package com.example.TesteCursor.garantias.config;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.example.TesteCursor.garantias.model.TipoGarantia;

@ConfigurationProperties(prefix = "garantias")
public class HaircutProperties {

	private Map<String, BigDecimal> haircuts = new HashMap<>();

	public Map<String, BigDecimal> getHaircuts() {
		return haircuts;
	}

	public void setHaircuts(Map<String, BigDecimal> haircuts) {
		this.haircuts = haircuts;
	}

	public BigDecimal obterHaircutPercentual(TipoGarantia tipoGarantia) {
		if (tipoGarantia == null) {
			return BigDecimal.valueOf(100);
		}
		String chave = tipoGarantia.name().toLowerCase();
		BigDecimal percentual = haircuts.getOrDefault(chave, BigDecimal.valueOf(100));
		return percentual.setScale(2, RoundingMode.HALF_UP);
	}
}


