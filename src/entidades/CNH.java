package entidades;

import java.time.LocalDate;

public class CNH {

	private String numero;
	private String categoria;
	private LocalDate dataValidade;
	
	public CNH(String numero, String categoria, LocalDate dataValidade) {
		this.setNumero(numero);
		this.setCategoria(categoria);
		this.dataValidade = dataValidade;	
	}
	
	public boolean estaVencida() {
		LocalDate hoje = LocalDate.now();
		return dataValidade.isBefore(hoje);
	}
	public boolean isValida() {
		return !estaVencida();
		
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public LocalDate getDataValidade() {
		return dataValidade;
	}
	public void setDataValidade(LocalDate dataValidade) {
		this.dataValidade = dataValidade;
	}
	
}
