
package Controller;

import Classes.*;
import Model.ModelHospital;
import java.util.LinkedList;


public class ControllerHospital {
    
    private ModelHospital modelhospital;
    
    public ControllerHospital(){
        
    this.modelhospital = new ModelHospital();
    
    }
     
       public boolean CreateHospital(clsHospital hospit) {
      
        try {
        boolean res= modelhospital.CreateHospital(hospit);

            return res;
        } catch (Exception e) {
            return false;
        }
    }
    
   public clsHospital BuscarHospitaL(String num){
     clsHospital hostpit = null;
        try {

            hostpit = this.modelhospital.BuscarHospital(num);

            return hostpit;
        } catch (Exception e) {
            return hostpit;
        }

   }
    
    //METODO EDITAR DE PET PARA DOG EN EL CONTROLADOR
    public boolean EditHospital(clsHospital hospit) {
        try {

            this.modelhospital.EditHospital(hospit);

            return true;
        } catch (Exception e) {
            return false;
        }

    }
     
  
    //METODO ELIMINAR DE DOCTOR PARA DOG EN EL CONTROLADOR   
    public boolean DeleteHospital(int hospit) {
        try {

            this.modelhospital.DeleteHospital(hospit);

            return true;
        } catch (Exception e) {
            return false;
        }

    }
    

    public LinkedList<clsHospital> ListHospital() {
        LinkedList<clsHospital> hospitalList = null;

        try {
           hospitalList = this.modelhospital.BusquedaDHospital();
            return hospitalList;

        } catch (Exception e) {
            return null;
        }
    } 
}
