package entidades;

import excecoes.EstadoInvalidoDaCorridaException;
import excecoes.MotoristaInvalidoException;

public class Motorista extends Usuario {
    private CNH cnh;
    private Veiculo veiculo;
    private StatusMotorista status;
    private Corrida corridaAtual;

    public Motorista(String nome, String CPF, String email, String senha, String telefone, CNH cnh, Veiculo veiculo) {
        super(nome, CPF, email, senha, telefone); //classe filho chamando pela classe pai
        this.cnh = cnh;
        this.veiculo = veiculo;
        this.status = StatusMotorista.OFFLINE;
        this.corridaAtual = null;
    }
    //dados pessoais
    public CNH getCnh(){
        return cnh;
    }
    public void setCNH (CNH cnh){
        this.cnh = cnh;
    } 

    public Veiculo getVeiculo(){
        return veiculo;
    }
    public void setVeiculo(Veiculo veiculo){
        this.veiculo = veiculo;
    }
    
    public StatusMotorista getStatus(){
        return status;
    }
    public void setStatus(StatusMotorista status){
        this.status = status;
    }
    public Corrida getCorridaAtual() {
        return corridaAtual;
    }
    public void setCorridaAtual(Corrida corridaAtual){
        this.corridaAtual = corridaAtual;
    }

    //corrida e disponibilidade

    public void ficarOnline() throws MotoristaInvalidoException {
    	//validar se a cnh é nula ou inválida
        if (this.cnh == null || !this.cnh.isValida()) {
            throw new MotoristaInvalidoException("Motorista " + getNome() + " tem a CNH inválida ou vencida.");
        }
        //validar se o veiculo nulo ou inválido
        if (this.veiculo == null || !this.veiculo.validarVeiculo()) {
            throw new MotoristaInvalidoException("Motorista " + getNome() + " não possui veículo válido.");
        }
        this.status = StatusMotorista.DISPONIVEL;
        System.out.println("Motorista " + getNome() + " agora está DISPONIVEL.");
    }
    public void aceitarCorrida(Corrida c){
    	//aceita se o motorista estiver disponivel e atualiza o motorista em corrida
        if (this.status == StatusMotorista.DISPONIVEL){
            this.corridaAtual = c;
            this.status = StatusMotorista.EM_CORRIDA;
            System.out.println("Motorista aceitou a corrida ID: " + c.getId());
        } 
        else {
        	System.out.println("Motorista não pode aceitar: está oucpado ou offline.");
        }
    }

    public void finalizarCorrida() throws EstadoInvalidoDaCorridaException {
        if (this.corridaAtual == null){
            throw new EstadoInvalidoDaCorridaException("Não há corrida em andamento para finalizar.");
        }

        this.corridaAtual.finalizarViagem();

        System.out.println("Corrida finalizada pelo motorista.");
        this.corridaAtual = null;
        this.status = StatusMotorista.DISPONIVEL;
    }
}
