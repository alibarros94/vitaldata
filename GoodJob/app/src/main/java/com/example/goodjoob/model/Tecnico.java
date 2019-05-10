package com.example.goodjoob.model;

public class Tecnico {
    private String Id_usuario;
    private String Nome;
    private String Email;
    private String Telefone;
    private double Avaliacao = 0;
    private int Qtd_avaliacao = 0;

    public Tecnico (String id_usuario, String nome, String email, String telefone, int qtd_avaliacao, double avaliacao){
        setId_usuario(id_usuario);
        setNome(nome);
        setEmail(email);
        setTelefone(telefone);
        setQtd_avaliacao(qtd_avaliacao);
        setAvaliacao(avaliacao);
    }

    public String getId_usuario() {
        return Id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        Id_usuario = id_usuario;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public int getQtd_avaliacao() {
        return Qtd_avaliacao;
    }

    public void setQtd_avaliacao(int qtd_avaliacao) {
        Qtd_avaliacao = qtd_avaliacao;
    }

    public double getAvaliacao() {
        return Avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        Avaliacao = avaliacao;
    }


}
