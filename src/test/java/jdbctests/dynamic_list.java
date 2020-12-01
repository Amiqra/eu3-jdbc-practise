package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dynamic_list {



        String dbUrl = "jdbc:oracle:thin:@34.227.143.172:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";

        @Test
        public void dynamic_list() throws SQLException {
            //create connection
            Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
            //create statement object
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            //run query and get the result in resultset object
            ResultSet resultSet = statement.executeQuery("select * from countries");

            //get the resultset object metadata
            ResultSetMetaData rsMetadata = resultSet.getMetaData();

            //list for keeping all rows a map
            List<Map<String,Object>> queryData = new ArrayList<>();

            //number of columns
            int colCount = rsMetadata.getColumnCount();

            //loop through each one
            while(resultSet.next()) {
                Map<String,Object> row = new HashMap<>();//row infolarini tutacak

                //loop starting from 1 till completed to column
                for (int i = 1; i <colCount; i++) {

                             //get column name,         get column value
                    row.put(rsMetadata.getColumnName(i),resultSet.getObject(i));

                }
                //while working with next, itar working for column count


                //add your map to your list
                queryData.add(row);

            }

            for(Map<String, Object> row:queryData) {
                System.out.println(row.toString());
            }




            //close all connections
            resultSet.close();
            statement.close();
            connection.close();
        }



    }
