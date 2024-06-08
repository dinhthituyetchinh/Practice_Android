package com.example.bai2_de1;

import java.io.Serializable;
import java.util.Map;

public class Name implements Serializable {
    private String common;
    private String official;
    private Map<String, NativeName> nativeName;

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public String getOfficial() {
        return official;
    }

    public void setOfficial(String official) {
        this.official = official;
    }

    public Map<String, NativeName> getNativeName() {
        return nativeName;
    }

    public void setNativeName(Map<String, NativeName> nativeName) {
        this.nativeName = nativeName;
    }
}
