package com.moonlabcoding.calculatriceappwithhistorique;

public class Operation {
    private String operation;
    private String resultat;

    public Operation(String operation, String resultat) {
        this.operation = operation;
        this.resultat = resultat;
    }

    public String getOperation() {
        return operation;
    }

    public String getResultat() {
        return resultat;
    }
}
