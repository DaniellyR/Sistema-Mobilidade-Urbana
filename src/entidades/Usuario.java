package entidades;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private String CPF;
    private String email;
    private String senha;
    private String telefone;
    private double mediaNota;
    private List<Integer> avaliacoes;

    public Usuario (String nome, String CPF, String email, String senha, String telefone){
        this.nome = nome;
        this.telefone = telefone;
        this.CPF = CPF;
        this.email = email;
        this.senha = senha;
        this.mediaNota = 5.0;
        this.avaliacoes = new ArrayList<>();
    }
}
