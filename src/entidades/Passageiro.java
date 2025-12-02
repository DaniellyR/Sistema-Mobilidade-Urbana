package entidades;

import java.util.ArrayList;
import java.util.List;

import excecoes.EstadoInvalidoDaCorridaException;

public class Passageiro extends Usuario {
    private List<MetodoPagamento> meusPagamentos;
    private Corrida corridaAtual;
    private boolean possuiPendencia;
    
    public Passageiro(String nome, String CPF, String email, String senha, String telefone) {
        super(nome, CPF, email, senha, telefone);
        this.meusPagamentos = new ArrayList<>();
        this.corridaAtual = null;
        this.possuiPendencia = false; //ele inicia sem
    }

    public List<MetodoPagamento> getMeusPagamentos(){
        return meusPagamentos;
    }
    public void setMeusPagamentos(List<MetodoPagamento> meusPagamentos){
        this.meusPagamentos = meusPagamentos;
    }

    public Corrida getCorridaAtual(){
        return corridaAtual;
    }
    public void setCorridaAtual(Corrida c){ 
        this.corridaAtual = c;
    }

    public boolean getPossuiPendencia(){
        return possuiPendencia; 
    }
    public void setPossuiPendencia( boolean status){ //pq status
        this.possuiPendencia = status; 

    }

    public void adicionarMetodoPagamento(MetodoPagamento mp){
        this.meusPagamentos.add(mp);
    }

    public void solicitarCorrida(String origem, String destino){
        this.corridaAtual = new Corrida(this, origem, destino);

    }
    public void cancelarCorrida() throws EstadoInvalidoDaCorridaException {
        if (this.corridaAtual != null) {
            this.corridaAtual.cancelarPeloPassageiro();

            this.corridaAtual = null;
            System.out.println("Corrida cancelada pelo passageiro.");
        } else {
            System.out.println("Você não tem corrida para cancelar.");
        }
    }
}
