package entidades;

public class Veiculo {
    
		private String placa;
		private String modelo;
		private String cor;
		private int ano;
		private TipoVeiculo tipo;
		
public Veiculo(String placa, String modelo, String cor, int ano, TipoVeiculo tipo) {
	
	this.setPlaca(placa);
	this.setModelo(modelo);
	this.setCor(cor);
	this.ano = ano;
	this.setTipo(tipo);
}

public boolean validarVeiculo() {
	return this.ano >= 2015;
}
//getters e setters
public String getPlaca() {
	return placa;
}

public void setPlaca(String placa) {
	this.placa = placa;
}

public String getModelo() {
	return modelo;
}

public void setModelo(String modelo) {
	this.modelo = modelo;
}

public String getCor() {
	return cor;
}

public void setCor(String cor) {
	this.cor = cor;
}

public int getAno() {
	return ano;
}
public void setAno(int ano) {
	this.ano = ano;
}

public TipoVeiculo getTipo() {
	return tipo;
}

public void setTipo(TipoVeiculo tipo) {
	this.tipo = tipo;
}

}
