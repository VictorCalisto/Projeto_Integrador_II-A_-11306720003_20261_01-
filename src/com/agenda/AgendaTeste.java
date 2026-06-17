package com.agenda;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

// Classe principal que exibe o menu interativo da agenda
public class AgendaTeste {
    private static AgendaTelefonica agenda = new AgendaTelefonica();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║     BEM-VINDO À AGENDA TELEFÔNICA     ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        int opcao = 0;

        while (opcao != 6) {
            exibirMenu();
            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar quebra de linha

                switch (opcao) {
                    case 1:
                        adicionarContato();
                        break;
                    case 2:
                        removerContato();
                        break;
                    case 3:
                        buscarContato();
                        break;
                    case 4:
                        listarContatos();
                        break;
                    case 5:
                        atualizarContato();
                        break;
                    case 6:
                        System.out.println("Até logo!");
                        break;
                    default:
                        System.out.println("✗ Opção inválida! Tente novamente.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("✗ Entrada inválida! Digite um número de 1 a 6.");
                scanner.nextLine(); // Limpar a entrada inválida
            } catch (SQLException e) {
                System.out.println("✗ Erro ao conectar ao banco de dados: " + e.getMessage());
            }

            System.out.println();
        }

        scanner.close();
    }

    // Exibe o menu principal
    private static void exibirMenu() {
        System.out.println("┌────────────────────────────────────────┐");
        System.out.println("│           MENU PRINCIPAL               │");
        System.out.println("├────────────────────────────────────────┤");
        System.out.println("│ 1. Adicionar novo contato              │");
        System.out.println("│ 2. Remover contato existente           │");
        System.out.println("│ 3. Buscar contato pelo nome            │");
        System.out.println("│ 4. Listar todos os contatos            │");
        System.out.println("│ 5. Atualizar contato                   │");
        System.out.println("│ 6. Sair                                │");
        System.out.println("└────────────────────────────────────────┘");
        System.out.print("Escolha uma opção: ");
    }

    // Opção 1: Adicionar contato
    private static void adicionarContato() throws SQLException {
        System.out.println("\n--- ADICIONAR NOVO CONTATO ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();

        if (nome.isEmpty()) {
            System.out.println("✗ Nome não pode ser vazio!");
            return;
        }

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine().trim();

        if (telefone.isEmpty()) {
            System.out.println("✗ Telefone não pode ser vazio!");
            return;
        }

        System.out.print("Email: ");
        String email = scanner.nextLine().trim();

        if (email.isEmpty()) {
            System.out.println("✗ Email não pode ser vazio!");
            return;
        }

        Contato contato = new Contato(nome, telefone, email);
        agenda.adicionarContato(contato);
    }

    // Opção 2: Remover contato
    private static void removerContato() throws SQLException {
        System.out.println("\n--- REMOVER CONTATO ---");
        System.out.print("Digite o nome do contato a remover: ");
        String nome = scanner.nextLine().trim();

        if (nome.isEmpty()) {
            System.out.println("✗ Nome não pode ser vazio!");
            return;
        }

        agenda.removerContato(nome);
    }

    // Opção 3: Buscar contato
    private static void buscarContato() throws SQLException {
        System.out.println("\n--- BUSCAR CONTATO ---");
        System.out.print("Digite o nome do contato: ");
        String nome = scanner.nextLine().trim();

        if (nome.isEmpty()) {
            System.out.println("✗ Nome não pode ser vazio!");
            return;
        }

        Contato contato = agenda.buscarContato(nome);

        if (contato != null) {
            System.out.println("\n✓ Contato encontrado:");
            System.out.println(contato);
        } else {
            System.out.println("✗ Contato não encontrado!");
        }
    }

    // Opção 4: Listar contatos
    private static void listarContatos() throws SQLException {
        System.out.println("\n--- LISTAR TODOS OS CONTATOS ---");
        List<Contato> contatos = agenda.listarContatos();

        if (contatos.isEmpty()) {
            System.out.println("Nenhum contato na agenda!");
        } else {
            System.out.println("\nTotal de contatos: " + contatos.size() + "\n");
            for (Contato c : contatos) {
                System.out.println(c);
            }
        }
    }

    // Opção 5: Atualizar contato
    private static void atualizarContato() throws SQLException {
        System.out.println("\n--- ATUALIZAR CONTATO ---");
        System.out.print("Digite o nome atual do contato: ");
        String nomeAntigo = scanner.nextLine().trim();

        if (nomeAntigo.isEmpty()) {
            System.out.println("✗ Nome não pode ser vazio!");
            return;
        }

        Contato contatoExistente = agenda.buscarContato(nomeAntigo);

        if (contatoExistente == null) {
            System.out.println("✗ Contato não encontrado!");
            return;
        }

        System.out.println("\nContato atual: " + contatoExistente);
        System.out.println("\nDigite os novos dados (deixe em branco para manter o atual):");

        System.out.print("Novo nome [" + contatoExistente.getNome() + "]: ");
        String novoNome = scanner.nextLine().trim();
        if (novoNome.isEmpty()) {
            novoNome = contatoExistente.getNome();
        }

        System.out.print("Novo telefone [" + contatoExistente.getTelefone() + "]: ");
        String novoTelefone = scanner.nextLine().trim();
        if (novoTelefone.isEmpty()) {
            novoTelefone = contatoExistente.getTelefone();
        }

        System.out.print("Novo email [" + contatoExistente.getEmail() + "]: ");
        String novoEmail = scanner.nextLine().trim();
        if (novoEmail.isEmpty()) {
            novoEmail = contatoExistente.getEmail();
        }

        Contato contatoAtualizado = new Contato(novoNome, novoTelefone, novoEmail);
        agenda.atualizarContato(nomeAntigo, contatoAtualizado);
    }
}
