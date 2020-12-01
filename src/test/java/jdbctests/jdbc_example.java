package jdbctests;

import org.testng.annotations.Test;

import javax.lang.model.SourceVersion;
import java.sql.*;

public class jdbc_example {

    String dbUrl = "jdbc:oracle:thin:@34.227.143.172:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test1() throws SQLException {
        //create connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);

        /** TYPE_SCROLL_INSENSITIVE -> allow us to navigate up and down in query result.
            CONCUR_READ_ONLY-> read only, don’t update the database */
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from departments");

        /**how to find how many rows we have for query
         //go to last row
         resultSet.last();
         //get the row count
         int rowCount = resultSet.getRow();
         System.out.println("rowCount = " + rowCount); */

         //last ile sona geldigimiz icin tekrar firs row a gitmemiz gerekti
        //resultSet.beforeFirst();
        while(resultSet.next()){
            System.out.println(resultSet.getString(2));
        }
        //2 different resultSet
        //========================================
        //we can run different query in the same object
        resultSet = statement.executeQuery("select * from regions");

        while(resultSet.next()){
            System.out.println(resultSet.getString(2));
        }



        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }
    @Test
    public void CountNavigate() throws SQLException {
        //create connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from departments");

        //kac tane row oldugunu bulma
        //son row a gitmek
        resultSet.last();//we have no idea which row
        //toplam row sayisi
        int rowCount = resultSet.getRow();//we get the current row
        System.out.println("rowCount = " + rowCount);//27
        System.out.println(" ====================================================== ");

        //we need to move before first row to get full list since we are at the last row right now.
        //while loop un calismasi icin tekrar basa donmekmiz gerekiyordu o yuzden beforefirst i yazdik
        resultSet.beforeFirst();

        while(resultSet.next()){
            System.out.println(resultSet.getString(2));
        }

        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void MetaDataExample() throws SQLException {
        //create connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from regions");

        //get the database related data inside the dbMetadata object
        //
        /**MetaData(Meta Veri Programlama), temeldeki veritabanı yazılımının ve kaynaklarının yeteneklerini,
          sınırlamalarını ve olanaklarını bilmek için kullanışlıdır.

         JDBC metadata programming Supports :
         •DatabaseMetadata
         •ResultSetMetaData */
        DatabaseMetaData dbMetadata = connection.getMetaData();

        System.out.println("User =" + dbMetadata.getUserName()); //-> username for db
        System.out.println("Database Product Name = " + dbMetadata.getDatabaseProductName());//->db product name
        System.out.println("Database Product Version = " + dbMetadata.getDatabaseProductVersion());
        System.out.println("Driver Name = " + dbMetadata.getDriverName());
        System.out.println("Driver Version = " + dbMetadata.getDriverVersion());

        //get the resultset object metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //how many columns we have? -> based on which query we executed
        //we get column count dynamicly
        int colCount = rsMetadata.getColumnCount();
        System.out.println(colCount);

        //column names
//        System.out.println(rsMetadata.getColumnName(1));
//        System.out.println(rsMetadata.getColumnName(2));

        //print all the column names dynamically
        for (int i = 1; i <= colCount; i++) {
            System.out.println(rsMetadata.getColumnName(i));

        }
        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }
}
