/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import model.nodo;
/**
 *
 * @author macbookair
 */
public abstract class CRUD {
    //Atributos
    nodo cab;
    //métodos
    protected nodo insertLIFO(nodo nn){
        if(this.cab != null){
            nn.setSig(this.cab);
            this.cab.setAnt(nn);

        }
        this.cab = nn;
        return this.cab;
    }
    protected nodo insertFIFO(nodo nn){
        if(this.cab != null){
            nodo p = this.cab;
            while(p.getSig() != null){
                p = p.getSig();
            }
            p.setSig(nn);
            nn.setAnt(p);
        }else{
            this.cab = nn;
        }
        return nn;
    }
    
    protected nodo search(int id){
        if(this.cab != null){
            nodo p  = this.cab;
            while(p != null){
                if(p.getId() == id){
                    return p;
                }
                p = p.getSig();
            }
            return null;
        }else{
            return null;// lista está vacía
        }
    }
    
    //DELETE
    public boolean delete(int id){
        nodo p = this.search(id);
        if(p != null){
            if(p.getSig() != null){
                p.getSig().setAnt(p.getAnt());
            }
            if(p != this.cab){
                p.getAnt().setSig(p.getSig());
            }else{
                this.cab = p.getSig();
            }
            return true;
        }else{
            return false;
        }
    }
    
    public void bubble(){
        boolean flag = false;//valor centinela
        nodo i = this.cab, j, lim = this.cab;
        while(lim.getSig() != null){//mueve el lim al final
            lim = lim.getSig();
        }
        while(i.getSig() != null && flag == false){
            /*  rompe la condición al hacer el número de
                repeticiones o cuando la bandera no cumpla 
                la condición */
            flag = true;//niega el estado inicial
            for(j = this.cab ; j != lim ; j = j.getSig()){
                if(j.getId() > j.getSig().getId()){
                    if(i == j){//si se intercambia donde va i
                        i = j.getSig();
                    }
                    if(lim == j.getSig()){
                        lim = j;//si intercambia el límite
                    }
                    j = exchange(j, j.getSig());//intercambio
                    flag = false;//afirma la bandera
                }
            }
            i = i.getSig();//incrementa a i
            lim = lim.getAnt();//decrementa el límite
        }
    }
    
    protected nodo exchange(nodo p, nodo q){
        nodo r = null, s = null;
        if(p.getSig() != q){//si los nodos no están juntos
            r = p.getSig();
            s = q.getAnt();
        }
        p.setSig(q.getSig());
        q.setAnt(p.getAnt());
        if(q.getSig() != null){//si q no es el último nodo
            q.getSig().setAnt(p);
        }
        if(p.getAnt() != null){//si p no es el primer nodo
            p.getAnt().setSig(q);
        }else{//p está en la cabeza de la lista
            this.cab = q;//reposiciona la cabeza
        }
        if(r != null && s != null){//intercambio largo
            r.setAnt(q);
            q.setSig(r);
            s.setSig(p);
            p.setAnt(s);
        }else{//intercamnio corto
            q.setSig(p);
            p.setAnt(q);
        }
        return q;
    }
    
    public void save(String nom){
        this.writeFile(nom);
    }
    protected void writeFile(String file){
        File f;
        f = new File(file);
        //Escritura
        try{
            FileWriter w = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);
            this.writting(wr);
            wr.close();
            bw.close();
        }catch(IOException e){};
    }
    protected abstract void writting(PrintWriter wr);
    //lectura
    public void read(String nom){
        this.readFile(nom);
    }
    protected void readFile(String file){
        FileReader fr = null;
        try {
            fr = new FileReader(file);
            BufferedReader entrada = new BufferedReader(fr);
            String cadena = entrada.readLine();
            while (cadena != null) {
		      reading(cadena);
		      cadena = entrada.readLine();
            }   
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
		if (fr != null) {
			fr.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    protected abstract void reading (String cadena);
}



