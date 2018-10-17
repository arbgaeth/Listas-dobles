/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.io.PrintWriter;
import model.propietario;
/**
 *
 * @author macbookair
 */
public class cPropietario extends CRUD{
    //CREATE
    public cPropietario(){
        this.read("propietario.dat");
    }
    private propietario create(String nombre, String direccion, int telefono, String email, int id){
        return new propietario(nombre, direccion, telefono, email, id);
    }
    public boolean insert(String nombre, String direccion, int telefono, String email, int id){
        if(this.insertLIFO(this.create(nombre, direccion, telefono, email, id))!= null){
            this.save("propietario.dat");
            return true;
        }else{
            return false;
        }
    }
    //READ
    public String showAll(){
        String resp = "";
        if(this.cab != null){
            propietario p = (propietario)this.cab;
            while(p != null){
                resp = resp + p.getId()+","+p.getNombre()+","+p.getDireccion()+","+p.getTelefono()+","+p.getEmail()+"\n";
                System.out.println(p.getId()+","+p.getNombre()+","+p.getDireccion()+","+p.getTelefono()+","+p.getEmail());
                p = (propietario)p.getSig();
            }
            return resp;
        }else{
            return null;
        }
    }
    
    public String select(int id){
        propietario p = (propietario)this.search(id);
        if(p != null){//encontró al nodo
            return p.getId()+","+p.getNombre()+","+p.getDireccion()+","+p.getTelefono()+","+p.getEmail();
        }else{// no encontró el nodo
            return null;
        }
    }
    //UPDATE
    private propietario setInfo(propietario nn, String nombre, String direccion, int telefono, String email){
        nn.setNombre(nombre);
        nn.setDireccion(direccion);
        nn.setTelefono(telefono);
        nn.setEmail(email);
        return nn;
    }
    public boolean update(String nom, String dir, int tel, String email, int id){
        propietario p  =  (propietario)this.search(id);
        if(p != null){
            if(this.setInfo(p, nom, dir, tel, email)!= null){
                this.save("propietario.dat");
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    protected void writting(PrintWriter wr){
        propietario p = (propietario)this.cab;
        while(p!=null){
            //raíz
            wr.println(p.getId()+","+p.getNombre()+","+p.getDireccion()+","+p.getTelefono()+","+p.getEmail());
            p = (propietario)p.getSig();
        }
    }
    protected void reading (String cadena){
        this.insert(cadena.split(",")[1], cadena.split(",")[2], Integer.parseInt(cadena.split(",")[3]), cadena.split(",")[4], Integer.parseInt(cadena.split(",")[0]));
    }
}
