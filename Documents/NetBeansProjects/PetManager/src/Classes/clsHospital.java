/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;


public class clsHospital {
    
    private int id_hospital;
    private String nombre;
    private String numero;

    public clsHospital(int id_hospital, String nombre, String numero) {
        this.id_hospital = id_hospital;
        this.nombre = nombre;
        this.numero = numero;
    }

    
    
    public int getId_hospital() {
        return id_hospital;
    }

    
    public void setId_hospital(int id_hospital) {
        this.id_hospital = id_hospital;
    }

    
    public String getNombre() {
        return nombre;
    }

    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    public String getNumero() {
        return numero;
    }

    
    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    
    
}
