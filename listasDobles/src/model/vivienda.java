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
public class vivienda extends nodo{
    
    //atributos propios de vivienda
    
    String direccion;
    String tipo;
    float area;
    String zona;
    int estrato;
    
    //métodos 
    //constructor

    public vivienda() {
    }

    public vivienda(String direccion, String tipo, float area, String zona, int estrato, int id) {
        super(id);
        this.direccion = direccion;
        this.tipo = tipo;
        this.area = area;
        this.zona = zona;
        this.estrato = estrato;
    }
    
    //métodos get y set

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public int getEstrato() {
        return estrato;
    }

    public void setEstrato(int estrato) {
        this.estrato = estrato;
    }

    
    
    
}
