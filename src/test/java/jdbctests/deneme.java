package jdbctests;
import java.sql.*;
public class deneme {
                                            //getconnection hata verdigi icin buraya exception ekledik
    public static void main(String[] args) throws SQLException {
        //database nasil baglaniyoruz
        //once connection string yaziyoruz
        String dbUrl = "jdbc:oracle:thin:@34.227.143.172:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";

        String q1="Select * from departments";
        String q2 ="select first_name,last_name,salary from employees";
        String q3 ="select * from regions";
        String q4="";

        /**             JAVA — — JDBC — Databases
                       JDBC is like middle man, translator
                         JDBC = Java Database Connectivity

         jdbc interface i database ile java arasindaki baglantiyi kurmak icin kullaniyoruz
         Connection ->java projelerinin database baglanmasini saglar
         Statement -> SQL query lerinin yazilip calistirilmasini saglar
         ResultSet-> DataStructure dir. DataBase den gelen verileri store eder
         DataStructure->(veri yapıları) aslında veriyi Memory'de nasıl tuttuğumuzun(sakladığımızın) yöntemidir.
         Array, Linked List, Stack, Queue, Hash Table, Binary Tree, Binary Heap vb gibi yapıları içeririr. */

        //connection create ettik, icine connection stringi koyduk
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);

        //statement objecti create ettik
        Statement statement = connection.createStatement();

        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery(q1);

       /** //default pointer ilk satiri gosteriyor index o olani, ilk row a ulasmak icin bir alt satira inmeliyiz
        resultSet.next();
        //europe yazdirdik
        System.out.println(resultSet.getString(2));
        System.out.println(resultSet.getString("region_name"));

        //get pointer to next row
        resultSet.next();

        System.out.println(resultSet.getString(2));
        //column informationlarini almak, iki secenek var; columnName as a parameter ya da index of the column
        System.out.println(resultSet.getString("region_name"));

        //iki columni birlikte aldik, (region_id, region_name)
        System.out.println(resultSet.getInt(1)+" - " + resultSet.getString("region_name")); */

        // resultSet.next() pratik yol olmadigi icin, while kulalndik
        while(resultSet.next()) //-> loop degil .next() metodunun avantaji, cunku-> .next() return to boolean value
            System.out.println(resultSet.getString(1)+" " + resultSet.getString(2) +"-"+resultSet.getString(3)+ "-" + resultSet.getString(4));

        /**
         * column index starts from 1
         * row index starts from 0
         */

        /**
         * ResultSet Methods
         * •next()
         * •getString(ColumName)
         * •getString(Index)
         * •getInt(ColumName)
         * •getInt(Index)
         * •getDouble(ColumName)
         * •getDouble(Index)
         * •getDate(ColumName)
         * •getDate(Index)
         */

        resultSet.close();
        statement.close();
        connection.close();


    }
}
