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

    public String getOrigem(){
        return origem;
    }
    public void setOrigem(String origem){
        this.origem = origem;
    }

    public String getDestino(){
        return destino;
    }
    public void setDestino( String destino){
        this.destino = destino;
    }

    public double getDistancia(){
        return distanciakm;
    }
    public void setDistancia( double d){
        this.distanciakm = d;
    }

    public double getPrecoEstimado(){
        return precoEstimado;
    }
    public void setPrecoEstimado (double pe){
        this.precoEstimado = pe;
    }

    public double getPrecoFinal(){
        return precoFinal;
    }
    public void setPrecoFinal (double pf){
        this.precoFinal = pf;
    }

    public MetodoPagamento getMetodoPagamento(){
        return metodoPagamento;
    }
    public void setMetodoPagamento(MetodoPagamento mp){
        this.metodoPagamento = mp;
    }

    public TipoVeiculo getTipoVeiculo(){
        return tipoVeiculo;
    }
    public void setTipoVeiculo(TipoVeiculo tp){
        this.tipoVeiculo = tp;
    }

    public long getId(){
        return id; 
        // apenas visualizar o numero de corridas
        // e nunca modificar, por isso, nada de setters
    }

    public double calcularPreco(){
        this.precoEstimado = this.tipoVeiculo.getValorBase() +(this.distanciakm * this.tipoVeiculo.getValorPorKm());
        // ao escolher o tipo de veiculo (comum ou luxo), pelo passageiro, tem que a multiplicação da distancia dada multiplicada
        // pelo valor do tipo de carro e somada a base fixa
        return precoEstimado;        
    }
    
    
}
