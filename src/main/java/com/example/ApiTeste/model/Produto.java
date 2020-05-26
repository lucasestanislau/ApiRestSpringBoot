package com.example.ApiTeste.model;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Produto extends AbstractEntity{

	@javax.validation.constraints.NotEmpty
	private String descricao;
	private double valor;
	private String marca;


	public Produto() {
	}


	public Produto(int id, String descricao, double valor, String marca) {
		this.descricao = descricao;
		this.valor = valor;
		this.marca = marca;
	}

	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	@Override
	public String toString() {
		return "Produto [descricao=" + descricao + ", valor=" + valor + ", marca=" + marca + "]";
	}


}
