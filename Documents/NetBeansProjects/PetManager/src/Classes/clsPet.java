/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Classes;

import Interface.*;

/**
 *
 * @author Aprendiz
 */
public class clsPet implements IAnimal,IVertebrate {
    
    private int petId;
    private String name;
    private String code;
    private int born_year;
    private String color;
    private String health_status;
    
    
    // constructor completo
    public clsPet(int petId, String name, String code, int born_year, String color, String health_status){
        this.petId = petId;
        this.name = name;
        this.code = code;
        this.born_year = born_year;
        this.color = color;
        this.health_status = health_status;
    }

    // creacion de metodos propios de la clase
    public void Eat() {
        System.out.println("La Mascota "+this.name+" Codigo "+this.code+" Esta Comiendo");
        System.out.println(" ");
    }
    
    public void Move(){
    System.out.println("La Mascota "+this.name+" Codigo "+this.code+" Se esta Moviendo");
        System.out.println(" ");
    }
        
    public void Play(){
    System.out.println("La Mascota "+this.name+" Codigo "+this.code+" Esta Jugando");
        System.out.println(" ");
    }
    
    public void Sound(){
    System.out.println("La Mascota "+this.name+" Codigo "+this.code+" Hace un Sonido");
        System.out.println(" ");
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
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the born_year
     */
    public int getBorn_year() {
        return born_year;
    }

    /**
     * @param born_year the born_year to set
     */
    public void setBorn_year(int born_year) {
        this.born_year = born_year;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the health_status
     */
    public String getHealth_status() {
        return health_status;
    }

    /**
     * @param health_status the health_status to set
     */
    public void setHealth_status(String health_status) {
        this.health_status = health_status;
    }

    @Override
    public String getAnimalType() {
        return "Domestico";
    }

    @Override
    public int getNumberofBones() {
        return 0;
    }

    /**
     * @return the petId
     */
    public int getPetId() {
        return petId;
    }

    /**
     * @param petId the petId to set
     */
    public void setPetId(int petId) {
        this.petId = petId;
    }

}

   
