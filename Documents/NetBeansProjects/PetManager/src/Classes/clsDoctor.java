/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Classes;

/**
 *
 * @author Aprendiz
 */
public class clsDoctor {
    
    private int id_doctor;
    private String name;
    private String nit;
    private String address;
    private String Phone;
    private int id_hospital;

    public clsDoctor( String name, String nit, String address, String Phone, int id_hospital) {
    
        this.name = name;
        this.nit = nit;
        this.address = address;
        this.Phone = Phone;
        this.id_hospital = id_hospital;
    }
    
    // eliminar doctores
    public clsDoctor(int id_doctor, String name, String nit, String address, String Phone, int id_hospital) {
        this.id_doctor = id_doctor;
        this.name = name;
        this.nit = nit;
        this.address = address;
        this.Phone = Phone;
        this.id_hospital = id_hospital;
    }
    
   
     
    public int getId_doctor() {
        return id_doctor;
    }

    /**
     * @param id_doctor the id_doctor to set
     */
    public void setId_doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the nit
     */
    public String getNit() {
        return nit;
    }

    /**
     * @param nit the nit to set
     */
    public void setNit(String nit) {
        this.nit = nit;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the Phone
     */
    public String getPhone() {
        return Phone;
    }

    /**
     * @param Phone the Phone to set
     */
    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    /**
     * @return the id_hospital
     */
    public int getId_hospital() {
        return id_hospital;
    }

    /**
     * @param id_hospital the id_hospital to set
     */
    public void setId_hospital(int id_hospital) {
        this.id_hospital = id_hospital;
    }
    
   



    
}

