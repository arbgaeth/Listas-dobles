/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.io.PrintWriter;
import model.vivienda;
/**
 *
 * @author macbookair
 */
public class cVivienda extends CRUD{
    //atributo
 
    //métodos
    //constructor

    public cVivienda() {
        this.read("vivienda.dat");
    }
    
    //CRUD
    //Create
    private vivienda create(String dir, String tipo, float a, String zona, int est, int id){
        return new vivienda(dir, tipo, a, zona, est, id);
    }
    
    public boolean insertF(String dir, String tipo, float a, String zona, int est, int id){
        if(this.insertFIFO(this.create(dir, tipo, a, zona, est, id))!= null){
            this.save("vivienda.dat");
            return true;
        }else{
            return false;
        }
    }
    
    public boolean insertL(String dir, String tipo, float a, String zona, int est, int id){
        if(this.insertLIFO(this.create(dir, tipo, a, zona, est, id))!= null){
            this.save("vivienda.dat");
            return true;
        }else{
            return false;
        }
    }
    //READ
    public String showAll(){
        String resp = "";
        if(this.cab != null){
            vivienda p = (vivienda)this.cab;
            while(p != null){
                resp = resp + p.getId()+","+p.getDireccion()+","+p.getTipo()+","+p.getArea()+","+p.getZona()+","+p.getEstrato()+"\n";
                System.out.println(p.getId()+","+p.getDireccion()+","+p.getTipo()+","+p.getArea()+","+p.getZona()+","+p.getEstrato());
                p = (vivienda)p.getSig();
            }
            return resp;
        }else{
            return null;
        }
    }
    
    public String select(int id){
        vivienda p = (vivienda)this.search(id);
        if(p != null){//encontró al nodo
            return p.getId()+","+p.getDireccion()+","+p.getTipo()+","+p.getArea()+","+p.getZona()+","+p.getEstrato();
        }else{// no encontró el nodo
            return null;
        }
    }
    //UPDATE
    private vivienda setInfo(vivienda nn, String dir, String tipo, float a, String zona, int est){
        nn.setDireccion(dir);
        nn.setTipo(tipo);
        nn.setArea(a);
        nn.setZona(zona);
        nn.setEstrato(est);
        return nn;
    }
    public boolean update(String dir, String tipo, float a, String zona, int est, int id){
        vivienda p  =  (vivienda)this.search(id);
        if(p != null){
            if(this.setInfo(p, dir, tipo, a, zona, est)!= null){
                this.save("vivienda.dat");
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    protected void writting(PrintWriter wr){
        vivienda p = (vivienda)this.cab;
        while(p!=null){
            //raíz
            wr.println(p.getId()+","+p.getDireccion()+","+p.getTipo()+","+p.getArea()+","+p.getZona()+","+p.getEstrato());
            p = (vivienda)p.getSig();
        }
    }
    protected void reading (String cadena){
        this.insertL(cadena.split(",")[1], cadena.split(",")[2], Float.parseFloat(cadena.split(",")[3]), cadena.split(",")[4], Integer.parseInt(cadena.split(",")[5]), Integer.parseInt(cadena.split(",")[0]));
    }

}
