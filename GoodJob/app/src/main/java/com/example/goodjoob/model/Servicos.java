package com.example.goodjoob.model;

public class Servicos {
    private String Id_tecnicoServico;
    private String Id_tecnico;
    private String Id_servico;
    private String Preco;
    private String Tipo;

    public Servicos(String id_tecnicoServico, String id_tecnico, String id_servico, String preco, String tipo){
        setId_tecnicoServico(id_tecnicoServico);
        setId_tecnico(id_tecnico);
        setId_servico(id_servico);
        setPreco(preco);
        setTipo(tipo);
    }

    public String getId_tecnicoServico() {
        return Id_tecnicoServico;
    }

    public void setId_tecnicoServico(String id_tecnicoServico) {
        Id_tecnicoServico = id_tecnicoServico;
    }

    public String getId_tecnico() {
        return Id_tecnico;
    }

    public void setId_tecnico(String id_tecnico) {
        Id_tecnico = id_tecnico;
    }

    public String getId_servico() {
        return Id_servico;
    }

    public void setId_servico(String id_servico) {
        Id_servico = id_servico;
    }

    public String getPreco() {
        return Preco;
    }

    public void setPreco(String preco) {
        Preco = preco;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }
}
