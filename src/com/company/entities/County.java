package com.company.entities;

public enum County {
    ALBA("ALBA"), BUCURESTI("BUCURESTI"), IASI("IASI"), VASLUI("VASLUI");
    private String value;

    County(String value){
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return value;
    }
}
