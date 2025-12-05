package entidades;

import excecoes.EstadoInvalidoDaCorridaException;
import excecoes.PagamentoRecusadoException;
import excecoes.SaldoInsuficienteException;

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
    public void atribuirMotorista(Motorista m) {
        this.motorista = m;
        this.status = StatusCorrida.ACEITA; //altera o status para aceita
        System.out.println("Motorista " + m.getNome() + " atribuído à corrida " + this.id);
    }

    public double calcularPreco(){
        this.precoEstimado = this.tipoVeiculo.getValorBase() +(this.distanciakm * this.tipoVeiculo.getValorPorKm());
        // ao escolher o tipo de veiculo (comum ou luxo), pelo passageiro, tem que a multiplicação da distancia dada multiplicada
        // pelo valor do tipo de carro e somada a base fixa
        return precoEstimado;        
    }

    public void iniciarViagem() throws EstadoInvalidoDaCorridaException {
        if (this.status != StatusCorrida.ACEITA) {
            throw new EstadoInvalidoDaCorridaException("Não pode iniciar uma corrida que não foi aceita.");
        }
        this.status = StatusCorrida.EM_ANDAMENTO;
    }

   //finalizando a viagem, temos o valor final, o pagamento, eo feedback
    public void finalizarViagem() throws EstadoInvalidoDaCorridaException{
        if (this.status != StatusCorrida.EM_ANDAMENTO){//verifica se ele não vai cometer erros
            throw new EstadoInvalidoDaCorridaException("Não é possível finalizar uma corrida que não está em andamento");
        }

        this.precoFinal = this.tipoVeiculo.getValorBase() +(this.distanciakm * this.tipoVeiculo.getValorPorKm());
        //mesmo calculo
        System.out.println("Valor final calculado: R$ " + this.precoFinal);
        //verificar se o passageiro pagou, senão fica pendente
        try {
            this.metodoPagamento.processarPagamento(this.precoFinal);
            this.status = StatusCorrida.FINALIZADA;
            System.out.println("Corrida finalizada com sucesso!");
            this.passageiro.setPossuiPendencia(false);
            
        } catch (PagamentoRecusadoException | SaldoInsuficienteException e) {
            this.passageiro.setPossuiPendencia(true);
            this.status = StatusCorrida.PENDENTE_PAGAMENTO;
            System.out.println("Ops! Falha no pagamento: " + e.getMessage());
            
        }

        
    }

    public void cancelarPeloPassageiro() throws EstadoInvalidoDaCorridaException{
       if (this.status == StatusCorrida.EM_ANDAMENTO) {
            throw new EstadoInvalidoDaCorridaException("Nãe é possivel cancelar! Já está em andamento.");
        }
        else if (this.status == StatusCorrida.FINALIZADA) {
            throw new EstadoInvalidoDaCorridaException("Nãe é possivel cancelar! Corrida já finalizada.");
            
        }
        else{
            this.status = StatusCorrida.CANCELADA;
        }
    }

    //feedback
    public void coletarFeedback(int notaPassageiro, int notaMotorista) {
    	if (this.passageiro != null) {
    		this.passageiro.receberAvaliacao(notaPassageiro);
    	}
    	if (this.motorista != null) {
    		this.motorista.receberAvaliacao(notaMotorista);
    	}

    }

    
}
