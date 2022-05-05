package com.example.doitfast.model;

public class Card {

    private int card_num;
    private String full_name;
    private String exp;
    private int cvc;

    public Card() {
    }

    public Card(int card_num, String full_name, String exp, int cvc) {
        this.card_num = card_num;
        this.full_name = full_name;
        this.exp = exp;
        this.cvc = cvc;
    }

    public int getCard_num() {
        return card_num;
    }

    public void setCard_num(int card_num) {
        this.card_num = card_num;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }


    @Override
    public String toString() {
        return "Card{" +
                "card_num=" + card_num +
                ", full_name='" + full_name + '\'' +
                ", exp='" + exp + '\'' +
                ", cvc=" + cvc +
                '}';
    }
}
