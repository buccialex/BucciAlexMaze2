/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buccialexmaze;

/**
 *
 * @author Bux
 */
public abstract class OggettoMobile {
    /**
     * attributi:
     * nome = nome dell'oggetto
     * x = coordinata x
     * y = coordinata y
     */
    protected String nome;
    protected int x;
    protected int y;
    /**
     * costruttore di oggetti mobili
     * @param nome nome dell'oggetto mobile
     */
    public OggettoMobile(String nome){
        this.nome = nome;
    }
    /**
     * metodo per muovere l'oggetto (da implementare nelle sottoclassi)
     * @param l labirinto
     */
    public abstract void muovi(Labirinto l);

    /**
     * getter nome
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * setter nome
     * @param nome nuovo nome 
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * getter x
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * setter x
     * @param x nuova x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * getter y
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * setter y
     * @param y nuova y 
     */
    public void setY(int y) {
        this.y = y;
    }
    
    
}
