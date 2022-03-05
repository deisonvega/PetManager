/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Classes;


public class clsDog extends clsPet {
    private int dogId;
    private boolean pedigree;
    private String breed;

    public clsDog(int dogId, boolean pedigree, String breed, int petId, String name, String code, int born_year, String color, String health_status) {
        super(petId, name, code, born_year, color, health_status);
        this.dogId = dogId;
        this.pedigree = pedigree;
        this.breed = breed;
    }

   

    

    public void WalkAround(){
    
        System.out.println("EL PERRO "+super.getName()+" ESTA CAMINANDO");
            System.out.println("");
    }
    
    public void Assist(){
    
        System.out.println("EL PERRO "+super.getName()+" AYUDA A SU AMO");
            System.out.println("");
    }
    
    
    @Override
    public void Sound(){
        System.out.println("EL PERRO"+super.getName()+"HACE GUUUAAAUUUU");
    
    
    }
   
    /**
     * @return the pedigree
     */
    public boolean isPedigree() {
        return pedigree;
    }

    /**
     * @param pedigree the pedigree to set
     */
    public void setPedigree(boolean pedigree) {
        this.pedigree = pedigree;
    }

    /**
     * @return the breed
     */
    public String getBreed() {
        return breed;
    }

    /**
     * @param breed the breed to set
     */
    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public String getAnimalType() {
        return "Perro";
    }

    @Override
    public int getNumberofBones() {
        return 320;
    }

    /**
     * @return the dogId
     */
    public int getDogId() {
        return dogId;
    }

    /**
     * @param dogId the dogId to set
     */
    public void setDogId(int dogId) {
        this.dogId = dogId;
    }
    
    
    
}




