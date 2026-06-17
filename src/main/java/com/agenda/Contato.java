package com.agenda;

// Classe simples que representa um contato na agenda
public class Contato {
    private int id;
    private String nome;
    private String telefone;
    private String email;

    // Construtor padrão (sem argumentos)
    public Contato() {
    }

    // Construtor com argumentos (sem id, pois é gerado pelo banco)
    public Contato(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    // Construtor com todos os argumentos
    public Contato(int id, String nome, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Método toString para exibição
    @Override
    public String toString() {
        return "ID: " + id +
               " | Nome: " + nome +
               " | Telefone: " + telefone +
               " | Email: " + email;
    }
}
