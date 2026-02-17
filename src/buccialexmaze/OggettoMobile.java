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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
}
