import java.sql.*;
import java.util.*;
import java.io.*;

/*
CSCE 315
9-27-2021 Lab
 */
public class ordersChange {

  //Commands to run this script
  //This will compile all java files in this directory
  //javac *.java
  //This command tells the file where to find the postgres jar which it needs to execute postgres commands, then executes the code
  //Windows: java -cp ".;postgresql-42.2.8.jar" jdbcpostgreSQL
  //Mac/Linux: java -cp ".:postgresql-42.2.8.jar" jdbcpostgreSQL

  //MAKE SURE YOU ARE ON VPN or TAMU WIFI TO ACCESS DATABASE
  public static void main(String args[]) throws Exception {

    //Building the connection with your credentials
    //TODO: update teamNumber, sectionNumber, and userPassword here
     Connection conn = null;
     String teamNumber = "9";
     String sectionNumber = "909";
     String dbName = "csce315" + sectionNumber + "_" + teamNumber + "db";
     String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
     String userName = "csce315" + sectionNumber + "_" + teamNumber + "user";
     String userPassword = "password";

    //Connecting to the database
    try {
        conn = DriverManager.getConnection(dbConnectionString,userName, userPassword);
     } catch (Exception e) {
        e.printStackTrace();
        System.err.println(e.getClass().getName()+": "+e.getMessage());
        System.exit(0);
     }

     System.out.println("Opened database successfully");

     try{
        //create a statement object
        Statement stmt = conn.createStatement();
        int counter = 0;
        File file = new File("FirstWeekSales.csv");
        Scanner s = new Scanner(file);
        s.useDelimiter(",");
        
        for(int i=0;i<6;i++){
          s.nextLine();
          s.nextLine();
          for(int j=0;j<19;j++){// go thru each day
            
            String order_id = s.next();
            String order_quantity = s.next();
            String order_id_real = order_id + i;
            s.next();
            s.next();
            //String day_id = i;
              //Running a query
              //TODO: update the sql command here
           //System.out.println(order_id_real + ", " + order_quantity + ", " + i);
            String sqlStatement = "INSERT INTO orders VALUES (" + order_id_real + ", " + order_quantity + ", " + i + ", " + order_id + ");";
            int result = stmt.executeUpdate(sqlStatement);
          }
        }
        s.nextLine();
        s.nextLine();
        for(int j=0;j<18;j++){// go thru each day    
            String order_id = s.next();
            String order_quantity = s.next();
            String order_id_real = order_id + "6";
            s.next();
            s.next();
              //Running a query
              //TODO: update the sql command here
           //System.out.println(order_id_real + ", " + order_quantity + ", " + i);
            String sqlStatement = "INSERT INTO orders VALUES (" + order_id_real + ", " + order_quantity + ", " + 6 + ", " + order_id + ");";
            int result = stmt.executeUpdate(sqlStatement);
        }
        String order_id = s.next();
        String order_quantity = s.next();
        String order_id_real = order_id + "6";
        String sqlStatement = "INSERT INTO orders VALUES (" + order_id_real + ", " + order_quantity + ", " + 6 + ", " + order_id + ");";
        int result = stmt.executeUpdate(sqlStatement);

        s.close();


      //Second week input
        File file1 = new File("FirstWeekSales.csv");
        s = new Scanner(file1);
        s.useDelimiter(",");
        for(int i=7;i<13;i++){
          s.nextLine();
          s.nextLine();
          for(int j=0;j<19;j++){// go thru each day
            order_id = s.next();
            order_quantity = s.next();
            order_id_real = order_id + i;
            s.next();
            s.next();
            //String day_id = i;
              //Running a query
              //TODO: update the sql command here
           //System.out.println(order_id_real + ", " + order_quantity + ", " + i);
            sqlStatement = "INSERT INTO orders VALUES (" + order_id_real + ", " + order_quantity + ", " + i + ", " + order_id + ");";
            result = stmt.executeUpdate(sqlStatement);
          }
        }
        s.nextLine();
        s.nextLine();
        for(int j=0;j<18;j++){// go thru each day    
            order_id = s.next();
            order_quantity = s.next();
            order_id_real = order_id + "13";
            s.next();
            s.next();
              //Running a query
              //TODO: update the sql command here
           //System.out.println(order_id_real + ", " + order_quantity + ", " + i);
            sqlStatement = "INSERT INTO orders VALUES (" + order_id_real + ", " + order_quantity + ", " + 13 + ", " + order_id + ");";
            result = stmt.executeUpdate(sqlStatement);
        }
        order_id = s.next();
        order_quantity = s.next();
        order_id_real = order_id + "13";
        sqlStatement = "INSERT INTO orders VALUES (" + order_id_real + ", " + order_quantity + ", " + 13 + ", " + order_id + ");";
        result = stmt.executeUpdate(sqlStatement);

        s.close();

        //third week input
        File file2 = new File("ThirdWeekSales.csv");
        s = new Scanner(file2);
        s.useDelimiter(",");
        for(int i=14;i<20;i++){
          s.nextLine();
          s.nextLine();
          for(int j=0;j<19;j++){// go thru each day
            order_id = s.next();
            order_quantity = s.next();
            order_id_real = order_id + i;
            s.next();
            s.next();
            //String day_id = i;
              //Running a query
              //TODO: update the sql command here
           //System.out.println(order_id_real + ", " + order_quantity + ", " + i);
            sqlStatement = "INSERT INTO orders VALUES (" + order_id_real + ", " + order_quantity + ", " + i + ", " + order_id + ");";
            result = stmt.executeUpdate(sqlStatement);
          }
        }
        s.nextLine();
        s.nextLine();
        for(int j=0;j<18;j++){// go thru each day    
            order_id = s.next();
            order_quantity = s.next();
            order_id_real = order_id + "20";
            s.next();
            s.next();
              //Running a query
              //TODO: update the sql command here
           //System.out.println(order_id_real + ", " + order_quantity + ", " + i);
            sqlStatement = "INSERT INTO orders VALUES (" + order_id_real + ", " + order_quantity + ", " + 20 + ", " + order_id + ");";
            result = stmt.executeUpdate(sqlStatement);
        }
        order_id = s.next();
        order_quantity = s.next();
        order_id_real = order_id + "20";
        sqlStatement = "INSERT INTO orders VALUES (" + order_id_real + ", " + order_quantity + ", " + 20 + ", " + order_id + ");";
        result = stmt.executeUpdate(sqlStatement);

        s.close();
        
        //input fourth week
        File file3 = new File("FourthWeekSales.csv");
        s = new Scanner(file3);
        s.useDelimiter(",");
        for(int i=21;i<27;i++){
          s.nextLine();
          s.nextLine();
          for(int j=0;j<19;j++){// go thru each day
            order_id = s.next();
            order_quantity = s.next();
            order_id_real = order_id + i;
            s.next();
            s.next();
            //String day_id = i;
              //Running a query
              //TODO: update the sql command here
           //System.out.println(order_id_real + ", " + order_quantity + ", " + i);
            sqlStatement = "INSERT INTO orders VALUES (" + order_id_real + ", " + order_quantity + ", " + i + ", " + order_id + ");";
            result = stmt.executeUpdate(sqlStatement);
          }
        }
        s.nextLine();
        s.nextLine();
        for(int j=0;j<18;j++){// go thru each day    
            order_id = s.next();
            order_quantity = s.next();
            order_id_real = order_id + "27";
            s.next();
            s.next();
              //Running a query
              //TODO: update the sql command here
           //System.out.println(order_id_real + ", " + order_quantity + ", " + i);
            sqlStatement = "INSERT INTO orders VALUES (" + order_id_real + ", " + order_quantity + ", " + 27 + ", " + order_id + ");";
            result = stmt.executeUpdate(sqlStatement);
        }
        order_id = s.next();
        order_quantity = s.next();
        order_id_real = order_id + "27";
        sqlStatement = "INSERT INTO orders VALUES (" + order_id_real + ", " + order_quantity + ", " + 27 + ", " + order_id + ");";
        result = stmt.executeUpdate(sqlStatement);

        s.close();

      }
        //send statement to DBMS
        //This executeQuery command is useful for data retrieval
        //ResultSet result = stmt.executeQuery(sqlStatement);
        //OR
        //This executeUpdate command is useful for updating data
        //int result = stmt.executeUpdate(sqlStatement);

        //OUTPUT
        //You will need to output the results differently depeninding on which function you use
        // System.out.println("--------------------Query Results--------------------");
        //while (result.next()) {
        //System.out.println(result.getString("column_name"));
        //}
        //OR
        //System.out.println(result);
      catch (Exception e){
       e.printStackTrace();
       System.err.println(e.getClass().getName()+": "+e.getMessage());
       System.exit(0);
   }

    //closing the connection
    try {
      conn.close();
      System.out.println("Connection Closed.");
    } catch(Exception e) {
      System.out.println("Connection NOT Closed.");
    }//end try catch
  }//end main
}//end Class