# Sistema de Mobilidade Urbana 🚗

Projeto desenvolvido para a disciplina de Orientação a Objetos na Faculdade do Gama.

## Contexto
Desenvolver um protótipo de sistema para um aplicativo de compartilhamento de corridas.

## Objetivo
Desenvolver um sistema em Java que aplique todos os conceitos de orientação a objetos vistos em sala de aula ao longo da disciplina, garantindo que modularidade, encapsulamento, herança, polimorfismo e tratamento de exceções personalizadas sejam explicitamente consideradas na elaboração do trabalho.

## 📦 Instalação e Configuração

Siga estes passos para executar o projeto em sua máquina local.

### Pré-requisitos

- **Java JDK 8 ou superior** - [Download aqui](https://www.oracle.com/java/technologies/downloads/)
- **Git** - [Download aqui](https://git-scm.com/downloads)
- **IDE** (opcional) - [Eclipse](https://www.eclipse.org/downloads/), [VSCode](https://code.visualstudio.com/), [IntelliJ](https://www.jetbrains.com/idea/download/)

### Passo 1: Clonar o Repositório
```bash
# Clone o projeto
git clone https://github.com/DaniellyR/Sistema-Mobilidade-Urbana.git

# Acesse a pasta do projeto
cd Sistema-Mobilidade-Urbana
```

### Passo 2: Configurar Ambiente de Desenvolvimento

**Opção A - Usando VSCode:**
```bash
# Abra o projeto no VSCode
code .
```

**Opção B - Usando Eclipse:**

1. Abra o Eclipse
2. **File** → **Open Projects from File System**
3. Selecione a pasta `Sistema-Mobilidade-Urbana`
4. Clique em **Finish**

### Passo 3: Compilar o Projeto

**Via Terminal:**
```bash
# Compilar todos os arquivos
javac -d bin src/**/*.java

# Ou compilar a classe principal específica
javac -d bin src/Main.java
```

**Via IDE:**
- **Eclipse:** Automaticamente ao salvar (se configurado)
- **VSCode:** `Ctrl+Shift+B` (Build Task)

### Passo 4: Executar o Projeto

**Via Terminal:**
```bash
# Executar a classe principal
java -cp bin Main

# Ou executar classe específica
java -cp bin com.exemplo.MainClass
```

**Via IDE:**
- **VSCode:** Clique em "Run" acima do método `main()`
- **Eclipse:** Botão direito → **Run As** → **Java Application**

---

## 💻 Como Usar o Sistema

### Funcionalidades Principais:

1. **Cadastro de Usuários**
   - Motoristas e passageiros
   - Validação de dados

2. **Solicitação de Corridas**
   - Busca por motoristas disponíveis
   - Definição de origem e destino

3. **Gerenciamento**
   - Status de corridas
   - Sistema de avaliações

### Exemplo Básico:
```java
// Criação de usuário
Passageiro passageiro = new Passageiro("Maria", "123.456.789-00", "maria@email.com", "123456", "61999999999");

// Solicitação de corrida
Corrida corrida = sistema.solicitarCorrida(passageiro, "Casa", "Faculdade");
```

## 🤝 Como Contribuir

Quer ajudar a melhorar o projeto? Siga estes passos:

1. **Fork** o repositório

2. Crie uma branch para sua feature:
```bash
   git checkout -b feature/nova-funcionalidade
```

3. Commit suas mudanças:
```bash
   git commit -m 'feat: adiciona nova funcionalidade'
```

4. Push para a branch:
```bash
   git push origin feature/nova-funcionalidade
```

5. Abra um **Pull Request**

### Padrões de Código:

- Use nomes descritivos em português
- Comente métodos complexos
- Adicione testes para novas funcionalidades
- Mantenha a coesão e baixo acoplamento



## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.



**Desenvolvido para a disciplina de Orientação a Objetos**

*Faculdade de Ciências e Tecnologia em Engenharia - Universidade de Brasília*



## ⚡ Dica Rápida

Para começar rapidamente:
```bash
git clone https://github.com/DaniellyR/Sistema-Mobilidade-Urbana.git
cd Sistema-Mobilidade-Urbana
# Abra na sua IDE e execute!
```
