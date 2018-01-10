package com.codegene.femicodes.cscproject;

/**
 * Created by femicodes on 1/8/2018.
 */

class Drug {

    String name;
    String regno;
    String header;

    public Drug() {
    }

    public Drug(String name, String regno, String header) {
        this.name = name;
        this.regno = regno;
        this.header = header;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
