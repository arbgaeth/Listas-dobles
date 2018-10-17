/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author macbookair
 */
public class recursivo {
    
    public void llamaFibo(){
        //this.fibonacci(0, 1, 10, 0);
    }
    
    
    
    
    public int fibonacci(int vi, int vf, int lim, int cont){
        if(cont<lim){
            System.out.print(vi+", ");
            return fibonacci(vf, vi+vf, lim, cont++);//recursividad
        }else{
            System.out.print(vi+", "+vf);
            return vf;
        }
    }
    
    
    public double serie1(int lim, int cont){
        if(lim > cont){
            System.out.print(Math.pow(cont, 2)-2);
            return this.serie1(lim, cont++);
        }else{
            return Math.pow(cont, 2)-2;
        }
    }
    
    public double serie2(int lim, int cont){
        if(lim > cont){
            System.out.print(Math.pow(-3, cont)-3);
            return this.serie2(lim, cont++);
        }else{
            return Math.pow(-3, cont)-3;
        }
    }
}
