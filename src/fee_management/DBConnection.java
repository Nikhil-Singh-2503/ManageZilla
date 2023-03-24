/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fee_management;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author nikhi
 */
public class DBConnection {
    public static Connection getConnection(){
        Connection con=null;
        
        try{
            Class.forName("org.netbeans.modules.db.drivers/modules/ext/mysql-connector-java-5.1.23-bin.jar!/");
            con =DriverManager.getConnection("jdbc:mysql://localhost:3306/ManageZilla","root","");
        }catch(Exception e){
            e.printStackTrace();
        }
        return con;
    }
}
