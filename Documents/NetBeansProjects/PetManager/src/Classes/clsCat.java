/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Classes;


public class clsCat extends clsPet  {
    private int catId;
    private String breed;

    public clsCat(int catId, String breed, int petId, String name, String code, int born_year, String color, String health_status) {
        super(petId, name, code, born_year, color, health_status);
        this.catId = catId;
        this.breed = breed;
    }

   
   
    
    public void Climb(){
        System.out.println("EL GATO "+super.getName()+" TREPA");
        System.out.println("");
    }
    
    /**
     *
     */
    @Override
    public void Sound (){
        System.out.println("el gato "+super.getName()+"hace miaaauuu");
    
    
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
        return "Gato";
    }

    @Override
    public int getNumberofBones() {
        return 230;
    }

    /**
     * @return the catId
     */
    public int getCatId() {
        return catId;
    }

    
    
}
