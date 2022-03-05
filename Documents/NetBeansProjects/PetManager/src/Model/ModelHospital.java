/* deison*/
package Model;

import Classes.clsHospital;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;


public class ModelHospital {
    
    DbData dbdata;
    
    public ModelHospital() {

        this.dbdata = new DbData();
    }
    
    
    public boolean CreateHospital(clsHospital hospit) {

        try (Connection connection = DriverManager.getConnection(dbdata.getUrl(), dbdata.getUser(), dbdata.getPass())) {
           String query = "insert into tb_hospital (nombre,numero) values (?,?)";
           PreparedStatement statementHos = connection.prepareStatement(query);
            statementHos.setString(1, hospit.getNombre());
            statementHos.setString(2, hospit.getNumero());
            
            int numfilas = statementHos.executeUpdate();

            if (numfilas > 0) {

                return true;
            }

        } catch (SQLException e) {
            return false;
        }
        return false;
    }
    
    // METODO BUSCAR HOSPITAL ///
      public clsHospital BuscarHospital(String num){
      
       clsHospital hospit = null;
        try (Connection connection = DriverManager.getConnection(dbdata.getUrl(), dbdata.getUser(), dbdata.getPass())) {
            String query = "SELECT *FROM tb_hospital WHERE  numero = ?;"; 
            PreparedStatement statementhosp = connection.prepareStatement(query);
            statementhosp.setString(1, num);
            ResultSet result = statementhosp.executeQuery();
            while (result.next()) {
                int id = result.getInt(1);
                String name = result.getString(2);
                hospit = new clsHospital(id, name, num);
            }
         
            return hospit;
           
        } catch (Exception e) {
            return hospit;
        }

      }
      
      
      //METODO EDITAR DE HOSPITAL DE CLASE MODEL
      public boolean EditHospital(clsHospital hospit){
      
        try (Connection connection = DriverManager.getConnection(dbdata.getUrl(), dbdata.getUser(), dbdata.getPass())) {
           String queryHospital= "update  tb_hospital set nombre = ?, numero = ? where id_hospital = ?";
            //String queryHospital ="update  tb_hospital set nombre = ?, numero = ? where id = ?;";
            PreparedStatement statementdoc = connection.prepareStatement(queryHospital);

            statementdoc.setString(1, hospit.getNombre());
            statementdoc.setString(2, hospit.getNumero());
            statementdoc.setInt(3, hospit.getId_hospital());
            int rowUpdatedDoctor = statementdoc.executeUpdate();

            return (rowUpdatedDoctor > 0);
        } catch (SQLException e) {
            return false;
        }
      }
      
      //METODO ELIMINAR DE VETERINARIA DE CLASE MODEL
      public boolean DeleteHospital(int hospit){
  
        try (Connection connection = DriverManager.getConnection(dbdata.getUrl(), dbdata.getUser(), dbdata.getPass())) {
            String queryHospital = "delete from tb_hospital where id_hospital = ?";
            //String queryHospital = "delete from tb_hospital where id = ?;";
            PreparedStatement statementDoct = connection.prepareStatement(queryHospital);
            
            statementDoct.setInt(1, hospit);
            int rowUpdatedDoctor = statementDoct.executeUpdate();

            return (rowUpdatedDoctor > 0);

        } catch (SQLException e) {
            return false;
        }
      }

      
        //METODO ELIMINAR DE HOSPITAL DE CLASE MODEL 
      public LinkedList<clsHospital> BusquedaDHospital() {
        LinkedList<clsHospital> hospitalList = new LinkedList<>();
        try (Connection connection = DriverManager.getConnection(dbdata.getUrl(), dbdata.getUser(), dbdata.getPass())) {
            
            String query = "select *from tb_hospital";
            PreparedStatement statementHospital = connection.prepareStatement(query);
            ResultSet result = statementHospital.executeQuery();
            
            while (result.next()) {
                int hosId = result.getInt(1);
                String hosName = result.getString(2);
                String hosNumero = result.getString(3);
                clsHospital hospital = new clsHospital(hosId, hosName, hosNumero);
                hospitalList.add(hospital);
            }

            return hospitalList;

        } catch (Exception e) {

            return hospitalList;
        }

    }
    
  
     }  
  

