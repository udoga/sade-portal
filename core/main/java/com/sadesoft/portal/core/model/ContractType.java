package com.sadesoft.portal.core.model;

public enum ContractType {
    Definite, Indefinite;

    public static ContractType from(String s) {
        return s.isEmpty()? null : ContractType.valueOf(s);
    }
}
