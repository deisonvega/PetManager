package Model;

import Classes.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class ModelDoctor {

    DbData dbdata;

    public ModelDoctor() {
        this.dbdata = new DbData();
    }

    //METODO CREAR DE DOCTOR DE CLASE MODEL
    public boolean CreateDoctor(clsDoctor doct) {

        try (Connection connection = DriverManager.getConnection(dbdata.getUrl(), dbdata.getUser(), dbdata.getPass())) {
            String query = "insert into tb_doctor (nombre,nit,address,phone,id_hospital) values (?,?,?,?,?)";
            PreparedStatement statementDoc = connection.prepareStatement(query);
            statementDoc.setString(1, doct.getName());
            statementDoc.setString(2, doct.getNit());
            statementDoc.setString(3, doct.getAddress());
            statementDoc.setString(4, doct.getPhone());
            statementDoc.setInt(5, doct.getId_hospital());

            int numfilas = statementDoc.executeUpdate();

            if (numfilas > 0) {

                return true;
            }

        } catch (SQLException e) {
            return false;
        }
        return false;
    }

//METODO EDITAR DE DOCTOR DE CLASE MODEL
    public boolean EditDoctor(clsDoctor doct) {

        try (Connection connection = DriverManager.getConnection(dbdata.getUrl(), dbdata.getUser(), dbdata.getPass())) {

            String querydoct = "update tb_doctor set nombre = ?, id_hospital=?, address = ?, phone = ? where nit = ?";
            PreparedStatement statementdoc = connection.prepareStatement(querydoct);

            statementdoc.setString(1, doct.getName());
            statementdoc.setString(5, doct.getNit());
            statementdoc.setString(3, doct.getAddress());
            statementdoc.setString(4, doct.getPhone());
            statementdoc.setInt(2, doct.getId_hospital());

            int rowUpdatedDoctor = statementdoc.executeUpdate();
            System.out.println("entro editar modelo");
            return (rowUpdatedDoctor > 0);
        } catch (SQLException e) {
            return false;
        }
    }

    //METODO ELIMINAR DE DOCTOR DE CLASE MODEL
    public boolean DeleteDoctor(clsDoctor doct) {

        try (Connection connection = DriverManager.getConnection(dbdata.getUrl(), dbdata.getUser(), dbdata.getPass())) {
            String queryDoctor = "delete from tb_doctor where id_doctor = " + doct.getId_doctor();
            PreparedStatement statementDoct = connection.prepareStatement(queryDoctor);
            int rowUpdatedDoctor = statementDoct.executeUpdate();

            return (rowUpdatedDoctor > 0);

        } catch (SQLException e) {
            return false;
        }
    }

    //METODO ELIMINAR DE DOCTOR DE CLASE MODEL 
    public clsBusquedaDoctHospital BusquedaDochos(String nit) {

        clsBusquedaDoctHospital doct = null;
        System.out.println("entre modelo");
        try (Connection connection = DriverManager.getConnection(dbdata.getUrl(), dbdata.getUser(), dbdata.getPass())) {
            String query = "SELECT *FROM tb_doctor AS d  INNER JOIN tb_hospital AS h on d.id_hospital = h.id_hospital WHERE d.nit = ?;";
            PreparedStatement statementDoct = connection.prepareStatement(query);
            statementDoct.setString(1, nit);
            ResultSet result = statementDoct.executeQuery();
            while (result.next()) {

                int id_doc = result.getInt(1);
                int id_hos = result.getInt(6);
                String named = result.getString(2);
                String address = result.getString(4);
                String Phone = result.getString(5);
                String numero = result.getString(9);
                String salah = result.getString(8);
                doct = new clsBusquedaDoctHospital(id_doc, named, address, nit, Phone, numero, salah, id_hos);
            }

            return doct;

        } catch (Exception e) {
            return doct;
        }
    }

    public LinkedList<clsBusquedaDoctHospital> BusquedaDoctor() {
        LinkedList<clsBusquedaDoctHospital> doctlist = new LinkedList<>();
        try (Connection connection = DriverManager.getConnection(dbdata.getUrl(), dbdata.getUser(), dbdata.getPass())) {
            String query = "SELECT * FROM tb_doctor;";
            PreparedStatement statementPet = connection.prepareStatement(query);
            ResultSet result = statementPet.executeQuery();

            while (result.next()) {
                int id = result.getInt(1);
                String named = result.getString(2);
                String nit = result.getString(3);
                String address = result.getString(4);
                String Phone = result.getString(5);
                int id_hospital = result.getInt(6);

                clsBusquedaDoctHospital doct = new clsBusquedaDoctHospital(id,named, address, nit, Phone, named, named, id_hospital);
                doctlist.add(doct);
            }
            return doctlist;

        } catch (Exception e) {
            return doctlist;
        }
    }

}
