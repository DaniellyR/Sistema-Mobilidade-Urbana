package entidades;

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

    //corrida e disponibilidade

    public void ficarOnline(){
        this.status = StatusMotorista.DISPONIVEL;
    }
    public Corrida getCorridaAtual(){
        return corridaAtual;
    }
    public void setCorridaAtual(Corrida corridaAtual){
        this.corridaAtual = corridaAtual;
    }

    public void aceitarCorrida(Corrida c){
        if (corridaAtual == null){
            this.corridaAtual = c;
            this.status = StatusMotorista.EM_CORRIDA;

        }
    }

    public void finalizarCorrida(){
        if (corridaAtual != null){
            this.corridaAtual = null;
            this.status = StatusMotorista.DISPONIVEL;
        }
    }
}
