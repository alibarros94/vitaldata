package com.example.goodjoob.model;

public class AddServicos {
    private String Id_servico;
    private String Tipo;

    public AddServicos (String id_servico, String tipo){
        setId_servico(id_servico);
        setTipo(tipo);
    }

    public String getId_servico() {
        return Id_servico;
    }

    public void setId_servico(String id_servico) {
        Id_servico = id_servico;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }
}
