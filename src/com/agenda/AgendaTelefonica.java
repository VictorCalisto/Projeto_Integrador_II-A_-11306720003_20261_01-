package com.agenda;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Classe responsável por gerenciar os contatos no banco de dados
public class AgendaTelefonica {
    private static final String URL = "jdbc:postgresql://db:5432/agenda";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    // Carrega o driver PostgreSQL
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Erro ao carregar driver PostgreSQL!");
            e.printStackTrace();
        }
    }

    // Obtém conexão com o banco de dados
    private Connection obterConexao() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Adicionar um novo contato no banco de dados
    public void adicionarContato(Contato contato) throws SQLException {
        String sql = "INSERT INTO contatos (nome, telefone, email) VALUES (?, ?, ?)";

        try (Connection conn = obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getTelefone());
            stmt.setString(3, contato.getEmail());

            stmt.executeUpdate();
            System.out.println("✓ Contato adicionado com sucesso!");
        }
    }

    // Remover um contato pelo nome
    public void removerContato(String nome) throws SQLException {
        String sql = "DELETE FROM contatos WHERE nome = ?";

        try (Connection conn = obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("✓ Contato removido com sucesso!");
            } else {
                System.out.println("✗ Contato não encontrado!");
            }
        }
    }

    // Buscar um contato pelo nome
    public Contato buscarContato(String nome) throws SQLException {
        String sql = "SELECT id, nome, telefone, email FROM contatos WHERE nome = ?";

        try (Connection conn = obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Contato(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("telefone"),
                    rs.getString("email")
                );
            } else {
                return null;
            }
        }
    }

    // Listar todos os contatos
    public List<Contato> listarContatos() throws SQLException {
        List<Contato> contatos = new ArrayList<>();
        String sql = "SELECT id, nome, telefone, email FROM contatos ORDER BY nome";

        try (Connection conn = obterConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                contatos.add(new Contato(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("telefone"),
                    rs.getString("email")
                ));
            }
        }

        return contatos;
    }

    // Atualizar um contato existente
    public void atualizarContato(String nomeAntigo, Contato contatoAtualizado) throws SQLException {
        String sql = "UPDATE contatos SET nome = ?, telefone = ?, email = ? WHERE nome = ?";

        try (Connection conn = obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, contatoAtualizado.getNome());
            stmt.setString(2, contatoAtualizado.getTelefone());
            stmt.setString(3, contatoAtualizado.getEmail());
            stmt.setString(4, nomeAntigo);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("✓ Contato atualizado com sucesso!");
            } else {
                System.out.println("✗ Contato não encontrado!");
            }
        }
    }
}
