# Agenda Telefônica - CRUD em Java

Aplicação acadêmica de Agenda Telefônica desenvolvida em Java com operações CRUD (Create, Read, Update, Delete) completas, utilizando PostgreSQL como banco de dados.

## 📋 Características

- ✅ **CRUD Completo**: Adicionar, buscar, listar, atualizar e remover contatos
- ✅ **Banco de Dados**: PostgreSQL com persistência em volume Docker
- ✅ **Interface Console**: Menu interativo e amigável
- ✅ **Tratamento de Erros**: Validação de entrada e exceções
- ✅ **Containerização**: Docker e Docker Compose para execução simplificada

## 📁 Estrutura do Projeto

```
.
├── src/
│   └── com/
│       └── agenda/
│           ├── Contato.java              # Classe modelo do contato
│           ├── AgendaTelefonica.java     # Lógica de CRUD
│           └── AgendaTeste.java          # Menu principal e interface
├── db/
│   ├── init.sql                          # Script de criação da tabela
│   └── dump.sql                          # Dados iniciais de exemplo
├── pom.xml                               # Configuração Maven
├── Dockerfile                            # Imagem Docker da aplicação
├── docker-compose.yml                    # Orquestração dos containers
└── README.md                             # Este arquivo
```

## 🛠️ Pré-requisitos

- **Docker** (v20.10+)
- **Docker Compose** (v1.29+)
- **Linux/Ubuntu** ou terminal compatível

Ou, se executar localmente (sem Docker):
- **Java 17+**
- **Maven 3.6+**
- **PostgreSQL 12+**

## 🚀 Como Executar com Docker

### 1. Clonar o repositório
```bash
git clone <url-do-repositorio>
cd Projeto_Integrador_II-A_-11306720003_20261_01-
```

### 2. Subir os containers
```bash
docker-compose up --build
```

**O que acontece:**
- Docker baixa as imagens necessárias
- Cria o volume para dados do PostgreSQL
- Inicia o banco de dados PostgreSQL
- Cria a tabela de contatos
- Popula com dados de exemplo
- Compila o projeto Java com Maven
- Inicia a aplicação com o menu interativo

### 3. Testar a aplicação

Quando a aplicação iniciar, você verá o menu:

```
╔════════════════════════════════════════╗
║     BEM-VINDO À AGENDA TELEFÔNICA     ║
╚════════════════════════════════════════╝

┌────────────────────────────────────────┐
│           MENU PRINCIPAL               │
├────────────────────────────────────────┤
│ 1. Adicionar novo contato              │
│ 2. Remover contato existente           │
│ 3. Buscar contato pelo nome            │
│ 4. Listar todos os contatos            │
│ 5. Atualizar contato                   │
│ 6. Sair                                │
└────────────────────────────────────────┘
Escolha uma opção:
```

## 🧪 Testando o CRUD

### Teste 1: Listar Contatos (Opção 4)
```
Escolha uma opção: 4

--- LISTAR TODOS OS CONTATOS ---
Total de contatos: 5

ID: 1 | Nome: João Silva | Telefone: 11987654321 | Email: joao.silva@email.com
ID: 2 | Nome: Maria Santos | Telefone: 21987654321 | Email: maria.santos@email.com
ID: 3 | Nome: Carlos Oliveira | Telefone: 31987654321 | Email: carlos.oliveira@email.com
ID: 4 | Nome: Ana Costa | Telefone: 41987654321 | Email: ana.costa@email.com
ID: 5 | Nome: Pedro Ferreira | Telefone: 51987654321 | Email: pedro.ferreira@email.com
```

### Teste 2: Buscar Contato (Opção 3)
```
Escolha uma opção: 3

--- BUSCAR CONTATO ---
Digite o nome do contato: João Silva

✓ Contato encontrado:
ID: 1 | Nome: João Silva | Telefone: 11987654321 | Email: joao.silva@email.com
```

### Teste 3: Adicionar Contato (Opção 1)
```
Escolha uma opção: 1

--- ADICIONAR NOVO CONTATO ---
Nome: Roberto Lima
Telefone: 61987654321
Email: roberto.lima@email.com

✓ Contato adicionado com sucesso!
```

### Teste 4: Atualizar Contato (Opção 5)
```
Escolha uma opção: 5

--- ATUALIZAR CONTATO ---
Digite o nome atual do contato: João Silva

Contato atual: ID: 1 | Nome: João Silva | Telefone: 11987654321 | Email: joao.silva@email.com

Digite os novos dados (deixe em branco para manter o atual):
Novo nome [João Silva]: João Pedro Silva
Novo telefone [11987654321]: 
Novo email [joao.silva@email.com]: joao.pedro@email.com

✓ Contato atualizado com sucesso!
```

### Teste 5: Remover Contato (Opção 2)
```
Escolha uma opção: 2

--- REMOVER CONTATO ---
Digite o nome do contato a remover: Roberto Lima

✓ Contato removido com sucesso!
```

## 🛑 Parar os Containers

```bash
docker-compose down
```

**Para parar e remover os volumes (apagar dados):**
```bash
docker-compose down -v
```

## 🔄 Verificar Persistência

Os dados são persistidos em um volume Docker nomeado `agenda_db_data`. Mesmo parando e reiniciando os containers, os dados serão mantidos.

Para verificar os dados direto no banco PostgreSQL:
```bash
docker exec -it agenda_db psql -U postgres -d agenda -c "SELECT * FROM contatos;"
```

## 💻 Executar Localmente (sem Docker)

Se preferir executar sem Docker:

### 1. Preparar o banco PostgreSQL
```bash
# Criar banco de dados
createdb agenda

# Executar scripts de inicialização
psql -U postgres -d agenda -f db/init.sql
psql -U postgres -d agenda -f db/dump.sql
```

### 2. Compilar o projeto
```bash
mvn clean compile
```

### 3. Executar a aplicação
```bash
mvn exec:java -Dexec.mainClass="com.agenda.AgendaTeste"
```

Ou compilar JAR:
```bash
mvn clean package
java -cp target/classes:target/lib/* com.agenda.AgendaTeste
```

## 📝 Dados de Exemplo

A base de dados já vem com 5 contatos pré-cadastrados (veja `db/dump.sql`):

| Nome | Telefone | Email |
|------|----------|-------|
| João Silva | 11987654321 | joao.silva@email.com |
| Maria Santos | 21987654321 | maria.santos@email.com |
| Carlos Oliveira | 31987654321 | carlos.oliveira@email.com |
| Ana Costa | 41987654321 | ana.costa@email.com |
| Pedro Ferreira | 51987654321 | pedro.ferreira@email.com |

## 🔐 Credenciais Banco de Dados

- **Host**: db (via Docker) ou localhost (local)
- **Porta**: 5432
- **Database**: agenda
- **Usuário**: postgres
- **Senha**: postgres

## 📚 Estrutura das Classes

### Contato
- Atributos: `id`, `nome`, `telefone`, `email`
- Construtores simples e parametrizados
- Getters e setters
- toString() para exibição

### AgendaTelefonica
- `adicionarContato(Contato contato)`: Adiciona novo contato
- `removerContato(String nome)`: Remove contato pelo nome
- `buscarContato(String nome)`: Busca contato por nome
- `listarContatos()`: Retorna lista de todos os contatos
- `atualizarContato(String nomeAntigo, Contato contatoAtualizado)`: Atualiza dados

### AgendaTeste
- `main()`: Ponto de entrada da aplicação
- Menu interativo com 6 opções
- Tratamento de exceções
- Validação de entrada

## ⚙️ Tecnologias Utilizadas

- **Java 17**: Linguagem de programação
- **PostgreSQL 15**: Banco de dados relacional
- **Maven**: Gerenciador de dependências
- **JDBC**: API de acesso ao banco
- **Docker**: Containerização
- **Docker Compose**: Orquestração

## 📖 Referências

- [PostgreSQL Documentation](https://www.postgresql.org/docs/)
- [Java JDBC Tutorial](https://docs.oracle.com/javase/tutorial/jdbc/)
- [Docker Documentation](https://docs.docker.com/)

## 👨‍🎓 Contexto Acadêmico

Este projeto foi desenvolvido como atividade acadêmica sobre CRUD em Java com banco de dados, demonstrando:
- Operações completas de leitura, criação, atualização e exclusão
- Integração com banco de dados relacional
- Interface de console amigável
- Tratamento robusto de erros
- Containerização com Docker

## 📞 Suporte

Para dúvidas ou problemas, verifique:
1. Se Docker e Docker Compose estão instalados: `docker --version` e `docker-compose --version`
2. Se a porta 5432 está disponível
3. Se o volume Docker foi criado: `docker volume ls`
4. Logs dos containers: `docker-compose logs`

---

**Desenvolvido por**: Aluno do Projeto Integrador II-A  
**Data**: 2026