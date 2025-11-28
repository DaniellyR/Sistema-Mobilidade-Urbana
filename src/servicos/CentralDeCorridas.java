package servicos;

import java.util.ArrayList;
import java.util.List;

class CentralDeCorridas {
    
    // Atributos (apenas os do UML)
    private List<Motorista> motoristasCadastrados;
    private List<Passageiro> passageirosCadastrados;
    
    // Construtor
    public CentralDeCorridas() {
        this.motoristasCadastrados = new ArrayList<>();
        this.passageirosCadastrados = new ArrayList<>();
    }
    
    // ========================================
    // MÉTODOS DE CADASTRO (COM VALIDAÇÃO)
    // ========================================
    
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
    
    // ========================================
    // MÉTODO: SOLICITAR CORRIDA (MELHORADO)
    // ========================================
    
    public Corrida solicitarCorrida(Passageiro p, String origem, String destino) 
            throws PassageiroPendenteException {
        
        // Validação de parâmetros
        if (p == null || origem == null || destino == null) {
            throw new IllegalArgumentException("Parâmetros não podem ser nulos");
        }
        
        // Verifica se passageiro está cadastrado
        if (!passageirosCadastrados.contains(p)) {
            throw new IllegalArgumentException("Passageiro não cadastrado: " + p.getNome());
        }
        
        // Verifica se o passageiro já tem uma corrida pendente
        if (p.isCorridaPendente()) {
            throw new PassageiroPendenteException(
                "Passageiro " + p.getNome() + " já possui uma corrida pendente!"
            );
        }
        
        // Cria a nova corrida (sem contador externo)
        Corrida corrida = new Corrida(p, origem, destino);
        
        // Marca o passageiro como tendo corrida pendente
        p.iniciarCorrida(corrida);  // Melhor: passageiro gerencia seu estado
        
        System.out.println("\n Nova corrida solicitada:");
        System.out.println("ID: " + corrida.getId());
        System.out.println("Passageiro: " + p.getNome());
        System.out.println("Trajeto: " + origem + " → " + destino);
        
        return corrida;
    }
    
    // ========================================
    // MÉTODO: BUSCAR MOTORISTA DISPONÍVEL (PRIVATE)
    // ========================================
    
    private Motorista buscarMotoristaDisponivel(TipoVeiculo tipo) 
            throws NenhumMotoristaDisponivelException {
        
        if (tipo == null) {
            throw new IllegalArgumentException("Tipo de veículo não pode ser nulo");
        }
        
        System.out.println("\n Buscando motorista disponível (tipo: " + tipo + ")...");
        
        // Procura por motoristas disponíveis do tipo solicitado
        for (Motorista m : motoristasCadastrados) {
            if (m.isDisponivel() && m.getTipoVeiculo() == tipo) {
                System.out.println("Motorista encontrado: " + m.getNome());
                return m;
            }
        }
        
        // Se não encontrou, lança exceção
        throw new NenhumMotoristaDisponivelException(
            "Nenhum motorista disponível com veículo do tipo " + tipo
        );
    }
    
    // ========================================
    // MÉTODOS AUXILIARES (OPCIONAIS)
    // ========================================
    
    public List<Motorista> getMotoristasCadastrados() {
        return new ArrayList<>(motoristasCadastrados); // Retorna cópia para encapsulamento
    }
    
    public List<Passageiro> getPassageirosCadastrados() {
        return new ArrayList<>(passageirosCadastrados); // Retorna cópia
    }
    
    public int getTotalMotoristas() {
        return motoristasCadastrados.size();
    }
    
    public int getTotalPassageiros() {
        return passageirosCadastrados.size();
    }
}
