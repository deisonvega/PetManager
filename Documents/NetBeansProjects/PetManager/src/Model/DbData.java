/*

//CONEXION REMOTA

package Model;

public class DbData {
    
    private final    String drive="com.mysql.jdbc.Drive";
    private final    String url="jdbc:mysql://b4mocypahht44t0kwkur-mysql.services.clever-cloud.com:3306/b4mocypahht44t0kwkur";
    private final    String user="ujm1xyivdz7mzld3";
    private final    String pass="g8j2xJvOsRAXPSz8nHq1";

    public String getDrive() {
        return drive;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }
}*/

// CONEXION LOCAL

package Model;

public class DbData {
    
    private final    String drive="com.mysql.jdbc.Drive";
    private final    String url="jdbc:mysql://localhost:3306/bd_pet";
    private final    String user="root";
    private final    String pass="";

    public String getDrive() {
        return drive;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }
}
