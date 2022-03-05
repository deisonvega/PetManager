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
public class clsReporteEstadoSalud {
    
    private String estadosalud;
    private int contadordeestados;

    public clsReporteEstadoSalud() {
       this.contadordeestados=0;
       this.estadosalud="";
    }
    
    

    
    public String getEstadosalud() {
        return estadosalud;
    }

    
    public void setEstadosalud(String estadosalud) {
        this.estadosalud = estadosalud;
    }

    
    public int getContadordeestados() {
        return contadordeestados;
    }

  
    public void setContadordeestados(int contadordeestados) {
        this.contadordeestados = contadordeestados;
    }

    
}
