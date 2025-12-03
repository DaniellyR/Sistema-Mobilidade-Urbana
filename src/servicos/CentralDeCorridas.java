package servicos;

import java.util.ArrayList;			

import java.util.List;

import entidades.Corrida;
import entidades.Motorista;
import entidades.Passageiro;
import entidades.StatusMotorista;
import excecoes.PassageiroPendenteException;
import excecoes.NenhumMotoristaDisponivelException;
import entidades.MetodoPagamento;
import entidades.TipoVeiculo;
import entidades.VeiculoComum;
import entidades.Dinheiro;
public class CentralDeCorridas {

    // atributos
    private List<Motorista> motoristasCadastrados;
    private List<Passageiro> passageirosCadastrados;

    // Construtor
    public CentralDeCorridas() {
        this.motoristasCadastrados = new ArrayList<>();
        this.passageirosCadastrados = new ArrayList<>();
    }

    // METODOS DE CADASTRO
    public void cadastrarMotorista(Motorista m) {
        if (m == null) {
            throw new IllegalArgumentException("Motorista não pode ser nulo");
        }
        if (motoristasCadastrados.contains(m)) {
            throw new IllegalArgumentException("Motorista já cadastrado: " + m.getNome());
        }
        motoristasCadastrados.add(m);
        System.out.println("Motorista cadastrado: " + m.getNome());
    }

    public void cadastrarPassageiro(Passageiro p) {
        if (p == null) {
            throw new IllegalArgumentException("Passageiro não pode ser nulo");
        }
        if (passageirosCadastrados.contains(p)) {
            throw new IllegalArgumentException("Passageiro já cadastrado: " + p.getNome());
        }
        passageirosCadastrados.add(p);
        System.out.println("Passageiro cadastrado: " + p.getNome());
    }
    
    public Corrida solicitarCorrida(Passageiro p, String origem, String destino) 
            throws PassageiroPendenteException, NenhumMotoristaDisponivelException {
        
        // veiculo comum vai ser o default do sistema
        TipoVeiculo tipoPadrao = new VeiculoComum();
        
        // dinheiro ou cartao será o default do sistema
        MetodoPagamento pagPadrao = (p.getMeusPagamentos().isEmpty()) 
                                    ? new Dinheiro() 
                                    : p.getMeusPagamentos().get(0);
        return this.solicitarCorrida(p, origem, destino, pagPadrao, tipoPadrao);
    }
    
    public Corrida solicitarCorrida(Passageiro p, String origem, String destino,MetodoPagamento mp, TipoVeiculo tv) 
    		throws PassageiroPendenteException, NenhumMotoristaDisponivelException {

        if (p == null || origem == null || destino == null) {
            throw new IllegalArgumentException("Dados inválidos.");
        }
        if (!passageirosCadastrados.contains(p)) {
            throw new IllegalArgumentException("Passageiro não cadastrado.");
        }
        if (p.getPossuiPendencia()) { 
            throw new PassageiroPendenteException("Passageiro " + p.getNome() + " possui pendências!");
        }

        //se passar por todas as exceções, tenta procurar um motorista
        Motorista motoristaEncontrado = buscarMotoristaDisponivel(tv);

        //cria a corrida
        Corrida corrida = new Corrida(p, origem, destino);
        corrida.setMetodoPagamento(mp);
        corrida.setTipoVeiculo(tv);
        
        //atribui a corrida
        corrida.atribuirMotorista(motoristaEncontrado);
        p.setCorridaAtual(corrida);
        p.setPossuiPendencia(true);
        corrida.calcularPreco();
        
        System.out.println("\nCorrida solicitada com sucesso!");
        System.out.println("Passageiro: " + p.getNome());
        System.out.println("Motorista: " + motoristaEncontrado.getNome());
        System.out.println("Veículo: " + motoristaEncontrado.getVeiculo().getModelo());
        
        
        return corrida;
    }
    private Motorista buscarMotoristaDisponivel(TipoVeiculo tipoDesejado) 
            throws NenhumMotoristaDisponivelException {

        System.out.println("Buscando motorista para categoria: " + tipoDesejado.getClass().getSimpleName() + "...");

        for (Motorista m : motoristasCadastrados) {
            // verifica se o motorista esta livre
            if (m.getStatus() == StatusMotorista.DISPONIVEL) {
                
                // verifica se o tipo de veiculo é o desejado
                if (m.getVeiculo().getTipo().getClass().equals(tipoDesejado.getClass())) {
                    return m;
                }
            }
        }

        // se não achou nenhum motorista
        throw new NenhumMotoristaDisponivelException("Não há motoristas disponíveis para essa categoria no momento.");
    }
        
    // getters e setters
    public List<Motorista> getMotoristasCadastrados() {
        return new ArrayList<>(motoristasCadastrados);
    }

    public List<Passageiro> getPassageirosCadastrados() {
        return new ArrayList<>(passageirosCadastrados);
    }

    public int getTotalMotoristas() {
        return motoristasCadastrados.size();
    }

    public int getTotalPassageiros() {
        return passageirosCadastrados.size();
    }
}
