/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.io.PrintWriter;
import model.planeta;
/**
 *
 * @author macbookair
 */
public class cPlaneta extends CRUD{
    //atributo
    recursivo rec = new recursivo();
    //métodos
    //constructor

    public cPlaneta() {
        this.read("planeta.dat");
        rec.llamaFibo();
    }
    
    //CRUD
    //Create
    private planeta create(String nom, String cod, float tam, float mas, float dist, int id){
        return new planeta(nom, cod, tam, mas, dist, id);
    }
    
    public boolean insertF(String nom, String cod, float tam, float mas, float dist, int id){
        if(this.insertFIFO(this.create(nom, cod, tam, mas, dist, id))!= null){
            this.save("planeta.dat");
            return true;
        }else{
            return false;
        }
    }
    
    public boolean insertL(String nom, String cod, float tam, float mas, float dist, int id){
        if(this.insertLIFO(this.create(nom, cod, tam, mas, dist, id))!= null){
            this.save("planeta.dat");
            return true;
        }else{
            return false;
        }
    }
    //READ
    public String showAll(){
        String resp = "";
        if(this.cab != null){
            planeta p = (planeta)this.cab;
            while(p != null){
                double yearlight = p.getDistancia()/0.010570008340246;
                resp = resp + p.getId()+","+p.getNombre()+","+p.getCodigo()+","+p.getTam()+","+p.getMasa()+","+p.getDistancia()+","+yearlight+"\n";
                System.out.println(p.getId()+","+p.getNombre()+","+p.getCodigo()+","+p.getTam()+","+p.getMasa()+","+p.getDistancia()+","+yearlight);
                p = (planeta)p.getSig();
            }
            return resp;
        }else{
            return null;
        }
    }
    
    public String select(int id){
        planeta p = (planeta)this.search(id);
        if(p != null){//encontró al nodo
            return p.getId()+","+p.getNombre()+","+p.getCodigo()+","+p.getTam()+","+p.getMasa()+","+p.getDistancia();
        }else{// no encontró el nodo
            return null;
        }
    }
    //UPDATE
    private planeta setInfo(planeta nn, String nom, String cod, float tam, float mas, float dist){
        nn.setNombre(nom);
        nn.setCodigo(cod);
        nn.setTam(tam);
        nn.setMasa(mas);
        nn.setDistancia(dist);
        return nn;
    }
    public boolean update(String nom, String cod, float tam, float mas, float dist, int id){
        planeta p  =  (planeta)this.search(id);
        if(p != null){
            if(this.setInfo(p, nom, cod, tam, mas, dist)!= null){
                this.save("planeta.dat");
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    protected void writting(PrintWriter wr){
        planeta p = (planeta)this.cab;
        while(p!=null){
            //raíz
            wr.println(p.getId()+","+p.getNombre()+","+p.getCodigo()+","+p.getTam()+","+p.getMasa()+","+p.getDistancia());
            p = (planeta)p.getSig();
        }
    }
    protected void reading (String cadena){
        this.insertL(cadena.split(",")[1], cadena.split(",")[2], Float.parseFloat(cadena.split(",")[3]), Float.parseFloat(cadena.split(",")[4]), Float.parseFloat(cadena.split(",")[5]), Integer.parseInt(cadena.split(",")[0]));
    }
    
    public void bubbleDist(){
        boolean flag = false;//valor centinela
        planeta i = (planeta)this.cab, j, lim = (planeta)this.cab;
        while(lim.getSig() != null){//mueve el lim al final
            lim = (planeta)lim.getSig();
        }
        while(i.getSig() != null && flag == false){
            /*  rompe la condición al hacer el número de
                repeticiones o cuando la bandera no cumpla 
                la condición */
            flag = true;//niega el estado inicial
            for(j = (planeta)this.cab ; j != lim ; j = (planeta)j.getSig()){
                planeta jsig = (planeta)j.getSig();
                if(j.getDistancia() > jsig.getDistancia()){
                    if(i == j){//si se intercambia donde va i
                        i = (planeta)j.getSig();
                    }
                    if(lim == j.getSig()){
                        lim = j;//si intercambia el límite
                    }
                    j = (planeta)exchange(j, j.getSig());//intercambio
                    flag = false;//afirma la bandera
                }
            }
            i = (planeta)i.getSig();//incrementa a i
            lim = (planeta)lim.getAnt();//decrementa el límite
        }
    }
    public float mediaMasa(){
        
        int cont = 0;
        float acum = 0;
        planeta p  =  (planeta)this.cab;
        while(p != null){
            acum = acum + p.getMasa();
            cont++;
            p = (planeta)p.getSig();
        }
        if(this.cab != null){
            return acum/cont;
        }else{
            return 0;
        }
    }
}
