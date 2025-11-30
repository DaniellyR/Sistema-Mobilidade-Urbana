package servicos;

import java.util.ArrayList;
import java.util.List;

import entidades.Corrida;
import entidades.Motorista;
import entidades.Passageiro;
import entidades.StatusMotorista;
import entidades.Veiculo;
import excecoes.PassageiroPendenteException;
import excecoes.NenhumMotoristaDisponivelException;

class CentralDeCorridas {

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

    // MÉTODO: SOLICITAR CORRIDA (CORRIGIDO)
    public Corrida solicitarCorrida(Passageiro p, String origem, String destino)
            throws PassageiroPendenteException {

        // validação
        if (p == null || origem == null || destino == null) {
            throw new IllegalArgumentException("Parâmetros não podem ser nulos");
        }

        // verifica se passageiro está cadastrado
        if (!passageirosCadastrados.contains(p)) {
            throw new IllegalArgumentException("Passageiro não cadastrado: " + p.getNome());
        }

        if (p.getCorridaAtual() != null || p.getPossuiPendencia()) {
            throw new PassageiroPendenteException(
                    "Passageiro " + p.getNome() + " já possui uma corrida pendente!");
        }

        // cria a nova corrida
        Corrida corrida = new Corrida(p, origem, destino);

        p.setCorridaAtual(corrida);
        p.setPossuiPendencia(true);

        System.out.println("\nNova corrida solicitada:");
        System.out.println("ID: " + corrida.getId());
        System.out.println("Passageiro: " + p.getNome());
        System.out.println("Trajeto: " + origem + " → " + destino);

        return corrida;
    }

    // METODO: BUSCAR MOTORISTA DISPONIVEL
    private Motorista buscarMotoristaDisponivel(Veiculo tipo)
            throws NenhumMotoristaDisponivelException {

        if (tipo == null) {
            throw new IllegalArgumentException("Tipo de veículo não pode ser nulo");
        }

        System.out.println("\nBuscando motorista disponível (tipo: " + tipo + ")...");

        // procura por motoristas disponíveis do tipo solicitado
        for (Motorista m : motoristasCadastrados) {

            if (m.getStatus() == StatusMotorista.DISPONIVEL && m.getVeiculo() == tipo) {
                System.out.println("Motorista encontrado: " + m.getNome());
                return m;
            }
        }

        // se não encontrou, lança exceção
        throw new NenhumMotoristaDisponivelException(
                "Nenhum motorista disponível com veículo do tipo " + tipo);
    }

    // METODOS AUXILIARES
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
