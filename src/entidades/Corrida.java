package entidades;

public class Corrida {
    private Passageiro passageiro;
    private Motorista motorista;
    private String origem;
    private String destino;
    private double distanciakm;
    private StatusCorrida status;
    private double precoEstimado;
    private double precoFinal;
    private MetodoPagamento metodoPagamento;
    private TipoVeiculo tipoVeiculo;
    private long id;
    private static long contador;

    public Corrida (Passageiro passageiro, String origem, String destino){
        this.passageiro = passageiro;
        this.origem = origem;
        this.destino = destino;
        this.status = StatusCorrida.SOLICITADA;
        this.motorista = null;
        contador++;
        this.id = contador;
        this.distanciakm = 0.0;
    }

    //getter(ver), setter(mudar)

    public StatusCorrida getStatus(){
        return status;
    }
    public void setStatus(StatusCorrida s){
        this.status = s;
    }

    public Motorista getMotorista(){
        return motorista;
    }
    public void setMotorista( Motorista motorista){
        this.motorista = motorista;
    }

    public Passageiro getPassageiro(){
        return passageiro;
    }
    public void setPassageiro(Passageiro passageiro){
        this.passageiro = passageiro;
    }
    
}
