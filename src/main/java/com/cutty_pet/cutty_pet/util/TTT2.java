package com.cutty_pet.cutty_pet.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
@Component
public class TTT2 implements Serializable {

    private static final long serialVersionUID = 7581334233026964739L;
    String id;
    String name;
    String kkk;
    @Value("${testvalue}")
    boolean yn;
    @Value("${testvalue}")
    Boolean yn2;

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

    public boolean isYn() {
        return yn;
    }

    public void setYn(boolean yn) {
        this.yn = yn;
    }

    public Boolean getYn2() {
        return yn2;
    }

    public void setYn2(Boolean yn2) {
        this.yn2 = yn2;
    }
}
