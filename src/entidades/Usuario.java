package entidades;

import java.util.ArrayList;	
import java.util.List;

public abstract class Usuario {
    private String nome;
    private String CPF;
    private String email;
    private String senha;
    private String telefone;
    private double mediaNota;
    private static long contador = 0;
    private long id;
    
    //List é um tipo de vetor sem tamanho definido que aumenta a cada elemento colocado

    private List<Integer> avaliacoes;

    public Usuario (String nome, String CPF, String email, String senha, String telefone){
    	contador++;
    	this.id = contador;
    	this.nome = nome;
        this.telefone = telefone;
        this.CPF = CPF;
        this.email = email;
        this.senha = senha;
        this.mediaNota = 5.0;
        this.avaliacoes = new ArrayList<>();
    }

    // avaliações e notas

    public void receberAvaliacao(int nota){
    	if(nota < 1 || nota > 5) {
    		System.out.println("Erro: A nota deve ser entre 1 e 5");
    		return;
    	}
        this.avaliacoes.add(nota);
        double soma = 0.0;
        for (Integer n : this.avaliacoes) { //calculo da media de avaliações
            soma = soma + n;
        }
        this.mediaNota = soma / this.avaliacoes.size(); 
    }
    //dados pessoais
    public String getNome(){ //apenas retornar o nome (get), serve para todos os outros dados
        return nome;
    }
    public void setNome(String nome){ //para alterar um novo nome (set), serve para todos os outros dados
        this.nome = nome;
    }

    public String getCPF(){
        return CPF;
    }
    public void setCPF( String CPF){
        this.CPF = CPF;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail( String email){
        this.email = email;
    }

    public String getTelefone(){
        return telefone;
    }
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public String getSenha(){
        return senha;
    }
    public void setSenha( String senha){
        this.senha = senha;
    }
    public double getMediaNota(){
        return mediaNota;
    }

	public long getId() {
		return id;
	}
	public List<Integer> getAvaliacoes() {
		return avaliacoes; 
	}
}
