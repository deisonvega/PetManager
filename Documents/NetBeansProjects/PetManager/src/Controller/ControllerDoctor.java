
package Controller;

import Classes.*;

//import Classes.clsVeterinary;
import Model.ModelDoctor;
import java.util.LinkedList;



public class ControllerDoctor {
    
     private final ModelDoctor modelDoct;

    
     public ControllerDoctor() {
        this.modelDoct = new ModelDoctor();

    }
      //METODO CREAR DE DOCTOR PARA DOG EN EL CONTROLADOR
    public boolean CreateDoctor(clsDoctor doct) {
      
        try {
        boolean res= modelDoct.CreateDoctor(doct);

            return res;
        } catch (Exception e) {
            return false;
        }
    }
    //METODO EDITAR DE PET PARA DOG EN EL CONTROLADOR
    public boolean EditDoctor(clsDoctor doct) {
        try {

            //this.modelDoct.EditDoctor((clsDoctor)doct);
            this.modelDoct.EditDoctor(doct);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
 
    //METODO ELIMINAR DE DOCTOR PARA DOG EN EL CONTROLADOR   
    public boolean DeleteDoctor(clsDoctor doct) {
        try {

            this.modelDoct.DeleteDoctor(doct);

            return true;
        } catch (Exception e) {
            return false;
        }

    }
    

    public clsBusquedaDoctHospital BusquedaDochos(String nit)  {
        clsBusquedaDoctHospital bdoct = null;
        try {

            bdoct = this.modelDoct.BusquedaDochos(nit);

            return bdoct;
        } catch (Exception e) {
            return bdoct;
        }

    }

    public  LinkedList <clsBusquedaDoctHospital> ListDoc() {
         
        LinkedList <clsBusquedaDoctHospital> docList = null;
        try {
             docList = this.modelDoct.BusquedaDoctor();

            return docList;

        } catch (Exception e) {
            return null;
        }
     }
    
}
