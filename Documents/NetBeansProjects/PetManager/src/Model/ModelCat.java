/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import Classes.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class ModelCat {
    
    DbData dbdata;
    
    //CONSTRUCTOR VACIO
    public ModelCat() {
        
     this.dbdata =new DbData();
        
    }
    
    
        //METODO CREAR DE CAT DE CLASE MODEL
    public boolean CreatePet(clsCat cat){
        
        try (Connection connection = DriverManager.getConnection(dbdata.getUrl(), dbdata.getUser(), dbdata.getPass())) {
            System.out.println("entre a gato");
            String query = "insert into tb_pet (namen,born_year,color,health_status,codes)values (?, ?, ?, ?, ?)";
            PreparedStatement statementPet = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statementPet.setString(1, cat.getName());
            statementPet.setInt(2, cat.getBorn_year());
            statementPet.setString(3, cat.getColor());
            statementPet.setString(4, cat.getHealth_status());
            statementPet.setString(5, cat.getCode());

            int numfilas = statementPet.executeUpdate();

            if (numfilas > 0) {
                ResultSet keys = statementPet.getGeneratedKeys();

                if (keys.next()) {
                    int id_Ped = keys.getInt(1);
                    query = "insert into tb_cat(breed,id_pet) values (?, ?)";
                    PreparedStatement statementCat = connection.prepareStatement(query);
                    statementCat.setString(1, cat.getBreed());
                    statementCat.setInt(2, id_Ped);
                    numfilas = statementCat.executeUpdate();
                    if (numfilas > 0) {
                        return true;
                    }
                }

            }
            return false;
        } catch (SQLException e) {
            return false;
        }
        
        
        /*try {
             return true;
            
        } catch (Exception e) {
            return false;
        }*/
        
        }
    
    //METODO EDITAR DE CAT DE CLASE MODEL
      public boolean EditPet(clsCat cat){
          
          try (Connection connection = DriverManager.getConnection(dbdata.getUrl(), dbdata.getUser(), dbdata.getPass())) {
            String queryPet ="update  tb_pet set codes = ?, namen = ?, born_year = ?, color = ?, health_status = ? where ID = ?";
          //  String queryPet = "update  tb_pet set  namen = ?, born_year = ?, color = ?, health_status = ?,codes = ? where ID = ?";
            PreparedStatement statementPet = connection.prepareStatement(queryPet);
            
            statementPet.setString(1, cat.getCode());
            statementPet.setString(2, cat.getName());
            statementPet.setInt(3, cat.getBorn_year());
            statementPet.setString(4, cat.getColor());
            statementPet.setString(5, cat.getHealth_status());
            statementPet.setInt(6, cat.getPetId());
            int rowUpdatedPet = statementPet.executeUpdate();
            
            String queryCat = "update tb_cat set Breed = ?, where id_pet = ?";
            PreparedStatement statementCat = connection.prepareStatement(queryCat);
            System.out.println("paso PreparedStatement");
            statementCat.setString(1, cat.getBreed());
            statementCat.setInt(3, cat.getPetId());
            
            int rowUpdatedCat = statementCat.executeUpdate();
            
            System.out.println(rowUpdatedPet + "  " + rowUpdatedCat);
            return (rowUpdatedPet > 0) && (rowUpdatedCat > 0);
        } catch (SQLException e) {
            return false;
        }
 }    
    //METODO ELIMINAR DE CAT DE CLASE MODEL
      public boolean DeletePet(clsCat cat){
          
          
          try (Connection connection = DriverManager.getConnection(dbdata.getUrl(), dbdata.getUser(), dbdata.getPass())) {

              String queryPet = "delete from tb_pet where id = ?;";
              PreparedStatement statementPet = connection.prepareStatement(queryPet);
              statementPet.setInt(1, cat.getPetId());
              int rowUpdatedPet = statementPet.executeUpdate();

              String queryCat = "delete from tb_cat where id_pet = ?;";
              PreparedStatement statementCat = connection.prepareStatement(queryCat);
              System.out.println("paso PreparedStatement");
              statementCat.setInt(1, cat.getCatId());
              int rowUpdatedCat = statementCat.executeUpdate();

              return (rowUpdatedPet > 0) && (rowUpdatedCat > 0);

          } catch (SQLException e) {
              return false;
          }
        
 }
    //METODO BUSCAR DE CAT DE CLASE MODEL
    public clsCat BusquedaPet(String code) {
        clsCat cat = null;
        try (Connection connection = DriverManager.getConnection(dbdata.getUrl(), dbdata.getUser(), dbdata.getPass())) {
            String query = "select * from tb_pet inner join tb_cat on tb_pet.id = tb_cat.id_pet where tb_pet.codes = ?";
            PreparedStatement statementPet = connection.prepareStatement(query);
            statementPet.setString(1, code);
            ResultSet result = statementPet.executeQuery();
            while (result.next()) {
                int petId = result.getInt(1);
                String petName = result.getString(2);
                int petBornYear = result.getInt(3);
                String petColor = result.getString(4);
                String petHealthStatus = result.getString(5);

                String petCode = result.getString(6);

                int catId = result.getInt(7);
                String petBreed = result.getString(8);

                cat = new clsCat(catId, petBreed, petId, petName, petCode, petBornYear, petColor, petHealthStatus);
            }
            return cat;
        } catch (Exception e) {
            return cat;
        }

    }

    public LinkedList<clsPet> BusquedaPet1() {
        LinkedList<clsPet> catList = new LinkedList<>();
        try (Connection connection = DriverManager.getConnection(dbdata.getUrl(), dbdata.getUser(), dbdata.getPass())) {
            String query = "select * from tb_pet inner join tb_cat on tb_pet.id = tb_cat.id_pet;";
            PreparedStatement statementPet = connection.prepareStatement(query);
            ResultSet result = statementPet.executeQuery();

            while (result.next()) {
                int petId = result.getInt(1);
                String petName = result.getString(2);
                int petBornYear = result.getInt(3);
                String petColor = result.getString(4);
                String petHealthStatus = result.getString(5);

                String petCode = result.getString(6);

                int catId = result.getInt(7);
                String petBreed = result.getString(8);

                clsCat cat = new clsCat(catId, petBreed, petId, petName, petCode, petBornYear, petColor, petHealthStatus);
                catList.add(cat);
            }

            return catList;

        } catch (Exception e) {

            return catList;
        }

    }
  
    public LinkedList<clsReporteEstadoSalud> BusquedaPetEstadoSaludCat(){
        LinkedList<clsReporteEstadoSalud> reporte = new LinkedList<>();
        try (Connection connection = DriverManager.getConnection(dbdata.getUrl(), dbdata.getUser(), dbdata.getPass())) {
            String query = "select  health_status,count(health_status)as Cantidad from tb_pet  inner join tb_cat on tb_pet.id = tb_cat.id_pet\n" +
"                    group by health_status;";
            PreparedStatement statementPet = connection.prepareStatement(query);
            ResultSet result = statementPet.executeQuery();

            while (result.next()) {

                clsReporteEstadoSalud registro = new clsReporteEstadoSalud();

                String estadosalud = result.getString(1);
                int conteo = result.getInt(2);
                registro.setContadordeestados(conteo);
                registro.setEstadosalud(estadosalud);
                reporte.add(registro);

            }

            return reporte;

        } catch (Exception e) {

            return reporte;
        }
     }
    
    
    public LinkedList<clsReporteDeRaza> BusquedaPetReporteRazaCat() {
        LinkedList<clsReporteDeRaza> reporte = new LinkedList<>();
        try (Connection connection = DriverManager.getConnection(dbdata.getUrl(), dbdata.getUser(), dbdata.getPass())) {
            String query = "select breed,count(breed) as Cantidad from tb_pet inner join tb_cat on tb_pet.id = tb_cat.id_pet group by breed;";
            PreparedStatement statementPet = connection.prepareStatement(query);
            ResultSet result = statementPet.executeQuery();

            while (result.next()) {

                clsReporteDeRaza registro = new clsReporteDeRaza();

                String raza = result.getString(1);
                int conteo = result.getInt(2);
                registro.setContarraza(conteo);
                registro.setRaza(raza);
                reporte.add(registro);

            }

            return reporte;

        } catch (Exception e) {

            return reporte;//To change body of generated methods, choose Tools | Templates.
    }
    
}
}