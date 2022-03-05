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
public class clsBusquedaDoctHospital {
    
   // private String name;
    private int id;
    private String named;
    private String address;
    private String nit;
    private String phone;
    private String numero;
    private String salah;
    private int id_hos;

    public clsBusquedaDoctHospital(String named, String address, String nit, String phone, String numero, String salah) {
        this.named = named;
        this.address = address;
        this.nit = nit;
        this.phone = phone;
        this.numero = numero;
        this.salah = salah;
    }
    
      public clsBusquedaDoctHospital(String named, String address, String nit, String phone, String numero, String salah, int id_hos) {
        this.named = named;
        this.address = address;
        this.nit = nit;
        this.phone = phone;
        this.numero = numero;
        this.salah = salah;
        this.id_hos = id_hos;
    }
 
    // PARA EXPORTAR EL DOCTOR
      public clsBusquedaDoctHospital(int id, String named, String address, String nit, String phone, String numero, String salah, int id_hos) {
        this.id = id;
        this.named = named;
        this.address = address;
        this.nit = nit;
        this.phone = phone;
        this.numero = numero;
        this.salah = salah;
        this.id_hos = id_hos;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the named
     */
    public String getNamed() {
        return named;
    }

    /**
     * @param named the named to set
     */
    public void setNamed(String named) {
        this.named = named;
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
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the salah
     */
    public String getSalah() {
        return salah;
    }

    /**
     * @param salah the salah to set
     */
    public void setSalah(String salah) {
        this.salah = salah;
    }

    /**
     * @return the id_hos
     */
    public int getId_hos() {
        return id_hos;
    }

    /**
     * @param id_hos the id_hos to set
     */
    public void setId_hos(int id_hos) {
        this.id_hos = id_hos;
    }

    
   
}
