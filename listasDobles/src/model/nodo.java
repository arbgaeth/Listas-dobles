/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author macbookair
 */
public class nodo {
    
    //atributos del nodo
    
    int id;
    nodo sig;
    nodo ant;
    
    //métodos
    //Constructor
    
    public nodo() {
    }

    public nodo(int id) {
        this.id = id;
    }
    
    //métodos get y set

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public nodo getSig() {
        return sig;
    }

    public void setSig(nodo sig) {
        this.sig = sig;
    }

    public nodo getAnt() {
        return ant;
    }

    public void setAnt(nodo ant) {
        this.ant = ant;
    }
    
    
}
