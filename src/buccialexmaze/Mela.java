/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buccialexmaze;
import java.util.*;
/**
 *
 * @author bucci.alex
 */
public class Mela {
    /**
     * attributi:
     * bonus = il valore da sommare alla velocità (può essere positivo o negativo)
     */
    private int bonus;
    
    /**
     * costruttore di mela
     */
    public Mela(){
        Random rnd = new Random();
        this.bonus = rnd.nextInt(-20, 21);
    }

    /**
     * getter del bonus
     * @return il valore del bonus
     */
    public int getBonus() {
        return bonus;
    }

   
    
    
}
