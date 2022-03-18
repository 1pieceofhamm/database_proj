import java.sql.*;
import java.util.*;
import java.io.*;

/*
CSCE 315
9-27-2021 Lab
 */
public class menuChange {

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
      File file = new File("MenuKey.csv");
      Scanner s = new Scanner(file);
      s.useDelimiter(",");
      s.nextLine();
      s.nextLine();
      for (int i = 0; i < 4; i++) {
          String item_id = s.next();
          String item_name = s.next();
            String desc = s.next();
            if (desc.charAt(0) == '\"') {
                desc = desc.substring(1);
                while (!(desc.contains("\""))) {
                    desc += s.next();
                }
            }
          String price = s.next();
          price = price.substring(1);
          System.out.println(item_id + ", " + item_name +", " + price);
          String sqlStatement = "INSERT INTO menu VALUES (" + item_id + ", \'" + item_name +"\', " + price + ");";
          int result = stmt.executeUpdate(sqlStatement);
      }
      
        String item_id = s.next();
        String item_name = s.next();
        s.next();
        String price = s.next();
        price = price.substring(1);
          System.out.println(item_id + ", " + item_name +", " + price);
        String sqlStatement = "INSERT INTO menu VALUES (" + item_id + ", \'" + item_name +"\', " + price + ");";
        int result = stmt.executeUpdate(sqlStatement);

        for (int i = 0; i < 2; i++) {
           item_id = s.next();
           item_name = s.next();
            String desc = s.next();
            if (desc.charAt(0) == '\"') {
                desc = desc.substring(1);
                while (!(desc.contains("\""))) {
                    desc += s.next();
                }
            }
           price = s.next();
          price = price.substring(1);
          System.out.println(item_id + ", " + item_name +", " + price);
           sqlStatement = "INSERT INTO menu VALUES (" + item_id + ", \'" + item_name +"\', " + price + ");";
           result = stmt.executeUpdate(sqlStatement);
        }
      
         item_id = s.next();
         item_name = s.next();
         s.next();
         price = s.next();
        price = price.substring(1);
          System.out.println(item_id + ", " + item_name +", " + price);
         sqlStatement = "INSERT INTO menu VALUES (" + item_id + ", \'" + item_name +"\', " + price + ");";
         result = stmt.executeUpdate(sqlStatement);

        for (int i = 0; i < 1; i++) {
           item_id = s.next();
           item_name = s.next();
            String desc = s.next();
            if (desc.charAt(0) == '\"') {
                desc = desc.substring(1);
                while (!(desc.contains("\""))) {
                    desc += s.next();
                }
            }
           price = s.next();
          price = price.substring(1);
          System.out.println(item_id + ", " + item_name +", " + price);
           sqlStatement = "INSERT INTO menu VALUES (" + item_id + ", \'" + item_name +"\', " + price + ");";
           result = stmt.executeUpdate(sqlStatement);
        }

        item_id = s.next();
         item_name = s.next();
         s.next();
         price = s.next();
        price = price.substring(1);
          System.out.println(item_id + ", " + item_name +", " + price);
         sqlStatement = "INSERT INTO menu VALUES (" + item_id + ", \'" + item_name +"\', " + price + ");";
         result = stmt.executeUpdate(sqlStatement);

        for (int i = 0; i < 1; i++) {
           item_id = s.next();
           item_name = s.next();
            String desc = s.next();
            if (desc.charAt(0) == '\"') {
                desc = desc.substring(1);
                while (!(desc.contains("\""))) {
                    desc += s.next();
                }
            }
           price = s.next();
          price = price.substring(1);
          System.out.println(item_id + ", " + item_name +", " + price);
           sqlStatement = "INSERT INTO menu VALUES (" + item_id + ", \'" + item_name +"\', " + price + ");";
           result = stmt.executeUpdate(sqlStatement);
        }

        item_id = s.next();
         item_name = s.next();
         s.next();
         price = s.next();
        price = price.substring(1);
          System.out.println(item_id + ", " + item_name +", " + price);
         sqlStatement = "INSERT INTO menu VALUES (" + item_id + ", \'" + item_name +"\', " + price + ");";
         result = stmt.executeUpdate(sqlStatement);

         item_id = s.next();
           item_name = s.next();
           item_name = "Laynes sauce";
            String desc = s.next();
            if (desc.charAt(0) == '\"') {
                desc = desc.substring(1);
                while (!(desc.contains("\""))) {
                    desc += s.next();
                }
            }
           price = s.next();
          price = price.substring(1);
          System.out.println(item_id + ", " + item_name +", " + price);
           sqlStatement = "INSERT INTO menu VALUES (" + item_id + ", \'" + item_name +"\', " + price + ");";
           result = stmt.executeUpdate(sqlStatement);

        for (int i = 0; i < 5; i++) {
           item_id = s.next();
           item_name = s.next();
            desc = s.next();
            if (desc.charAt(0) == '\"') {
                desc = desc.substring(1);
                while (!(desc.contains("\""))) {
                    desc += s.next();
                }
            }
           price = s.next();
          price = price.substring(1);
          System.out.println(item_id + ", " + item_name +", " + price);
           sqlStatement = "INSERT INTO menu VALUES (" + item_id + ", \'" + item_name +"\', " + price + ");";
           result = stmt.executeUpdate(sqlStatement);
        }
      
        item_id = s.next();
        item_name = s.next();
         s.next();
        price = s.next();
        price = price.substring(1);
          System.out.println(item_id + ", " + item_name +", " + price);
        sqlStatement = "INSERT INTO menu VALUES (" + item_id + ", \'" + item_name +"\', " + price + ");";
        result = stmt.executeUpdate(sqlStatement);

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
    }
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