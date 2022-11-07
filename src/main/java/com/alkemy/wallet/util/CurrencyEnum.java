package com.alkemy.wallet.util;


public enum CurrencyEnum {
    USD("USD"), ARS("ARS");
    private String valor;

    private CurrencyEnum(String valor) {
        this.valor = valor;
    }
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}
