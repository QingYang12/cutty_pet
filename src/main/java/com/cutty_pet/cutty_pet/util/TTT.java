package com.cutty_pet.cutty_pet.util;

import java.io.Serializable;

public class TTT implements Serializable {

    private static final long serialVersionUID = 7581334233026964739L;
    String id;
    String name;
    String kkk;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKkk() {
        return kkk;
    }

    public void setKkk(String kkk) {
        this.kkk = kkk;
    }
}
