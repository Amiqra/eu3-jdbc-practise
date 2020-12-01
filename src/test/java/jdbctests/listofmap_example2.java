package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class listofmap_example2 {
    String dbUrl = "jdbc:oracle:thin:@34.227.143.172:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void MetaDataExample() throws SQLException {
        //create connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from regions");

        //get the resultset object metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //List for keeping all rows a map
        //burda bos list map create ettik
        List<Map<String,Object>> mylist =new ArrayList<>();

        Map<String,Object> deneme = new HashMap<>();

        deneme.put("firstName","Lucy");
        deneme.put("lastName","Bob");
        deneme.put("Id",1234);

        System.out.println(deneme.toString());
        System.out.println(deneme.get("firstName"));

        Map<String,Object> deneme2 = new HashMap<>();

        deneme2.put("firstName","Ela");
        deneme2.put("lastName","White");
        deneme2.put("Id",123456);

        System.out.println(deneme2.toString());
        System.out.println(deneme2.get("lastName"));

        mylist.add(deneme);
        mylist.add(deneme2);

        //get thw steven last name directly from the last
        //birinci row dan sadece soyadini getirdik--index 0 dan basladigi icin get(0)
        System.out.println(mylist.get(1).get("lastName"));

        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void MetaDataExample2() throws SQLException {

        /** Metadata = retrieving those different information related your connection */
        //create connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select first_name,last_name,salary,job_id from employees\n" +
                "where rownum <6");

        //get the resultset object metadata
        /** ResultSetMetaData -> we need to it for column count, column name, column info..  */
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //list for keeping all rows a map


        //list for keeping all rows a map
        List<Map<String,Object>> queryData = new ArrayList<>();
        //move to first row
        resultSet.next();
        Map<String,Object> row1 = new HashMap<>();
        row1.put(rsMetadata.getColumnName(1),resultSet.getString(1));
        row1.put(rsMetadata.getColumnName(2),resultSet.getString(2));
        row1.put(rsMetadata.getColumnName(3),resultSet.getString(3));
        row1.put(rsMetadata.getColumnName(4),resultSet.getString(4));


        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }
}
