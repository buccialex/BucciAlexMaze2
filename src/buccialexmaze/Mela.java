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
    private int bonus;
    
    public Mela(){
        Random rnd = new Random();
        this.bonus = rnd.nextInt(-20, 21);
    }
}
