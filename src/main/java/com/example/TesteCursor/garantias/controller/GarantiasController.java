package com.example.TesteCursor.garantias.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TesteCursor.garantias.dto.CalculoSuficienciaRequest;
import com.example.TesteCursor.garantias.service.CalculadoraSuficienciaGarantiasService;
import com.example.TesteCursor.garantias.service.CalculadoraSuficienciaGarantiasService.ResultadoCalculo;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/garantias")
@Validated
public class GarantiasController {

	private final CalculadoraSuficienciaGarantiasService calculadora;

	public GarantiasController(CalculadoraSuficienciaGarantiasService calculadora) {
		this.calculadora = calculadora;
	}

	@PostMapping("/calcular")
	public ResponseEntity<ResultadoCalculo> calcular(@Valid @RequestBody CalculoSuficienciaRequest request) {
		ResultadoCalculo resultado = calculadora.calcularSuficiencia(request);
		return ResponseEntity.ok(resultado);
	}
}


