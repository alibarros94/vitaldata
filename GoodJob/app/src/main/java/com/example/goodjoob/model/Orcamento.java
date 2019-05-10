package com.example.goodjoob.model;

import java.util.Date;

public class Orcamento {
    private static String id_tecnico ;
    private static String id_tecnicoServico ;
    private static String id_usuario ;
    private static String data_orcamento ;
    private static String valor_orcamento ;
    private static String status ;
    private static String descricao;

    public Orcamento(){

        id_tecnicoServico = "1";
        id_usuario = "5";
        data_orcamento = "2019-09-04 10:10:10";
        valor_orcamento = "29";
        descricao = "Tecnico de Ar condicionado";

    }

    public static String getId_tecnicoServico() {
        return id_tecnicoServico;
    }

    public void setId_tecnicoServico(String id_tecnicoServico) {
        this.id_tecnicoServico = id_tecnicoServico;
    }

    public static String getId_usuario() {
        return id_usuario;
    }

    public  void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public static String getData_orcamento() {
        return data_orcamento;
    }

    public  void setData_orcamento(String data_orcamento) {
        this.data_orcamento = data_orcamento;
    }

    public static String getValor_orcamento() {
        return valor_orcamento;
    }

    public  void setValor_orcamento(String valor_orcamento) {
        this.valor_orcamento = valor_orcamento;
    }

    public static String getStatus() {
        return status;
    }

    public  void setStatus(String status) {
        this.status = status;
    }

    public static String getDesc() {
        return descricao;
    }

    public  void setDesc(String desc) {
        this.descricao = desc;
    }
}
