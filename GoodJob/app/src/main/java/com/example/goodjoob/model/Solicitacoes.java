package com.example.goodjoob.model;

public class Solicitacoes {
    private String Id_orcamento ;
    private String Id_tecnicoServico ;
    private String Id_nomeServico ;
    private String Id_usuario ;
    private String Data_orcamento ;
    private String Valor_orcamento;
    private String Status ;
    private String Descricao ;
    private String NomeTecnicoSoli;

    public Solicitacoes (String id_orcamento, String id_tecnicoServico, String id_usuario, String data_orcamento, String valor_orcamento, String status, String descricao, String nome, String telefone, String email, String preco, String tipo){
        setId_orcamento(id_orcamento);
        setId_tecnicoServico(id_tecnicoServico);
        setId_usuario(id_usuario);
        setData_orcamento(data_orcamento);
        setValor_orcamento(valor_orcamento);
        setStatus(status);
        setDescricao(descricao);
        setNomeTecnicoSoli(nome);
        setId_nomeServico(tipo);
    }

    public String getId_orcamento() {
        return Id_orcamento;
    }

    public void setId_orcamento(String id_orcamento) {
        Id_orcamento = id_orcamento;
    }

    public String getId_tecnicoServico() {
        return Id_tecnicoServico;
    }

    public void setId_tecnicoServico(String id_tecnicoServico) {
        Id_tecnicoServico = id_tecnicoServico;
    }

    public String getId_nomeServico() {
        return Id_nomeServico;
    }

    public void setId_nomeServico(String id_nomeServico) {
        Id_nomeServico = id_nomeServico;
    }

    public String getId_usuario() {
        return Id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        Id_usuario = id_usuario;
    }

    public String getData_orcamento() {
        return Data_orcamento;
    }

    public void setData_orcamento(String data_orcamento) {
        Data_orcamento = data_orcamento;
    }

    public String getValor_orcamento() {
        return Valor_orcamento;
    }

    public void setValor_orcamento(String valor_orcamento){
        Valor_orcamento = valor_orcamento;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getDescricao() { return Descricao; }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public String getNomeTecnicoSoli() {
        return NomeTecnicoSoli;
    }

    public void setNomeTecnicoSoli(String nome) {
        NomeTecnicoSoli = nome;
    }


}
