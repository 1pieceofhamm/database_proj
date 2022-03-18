import java.sql.*;
import java.util.*;
import java.io.*;

/*
CSCE 315
9-27-2021 Lab
 */
public class salesChange {

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
      for (int j = 0; j < 4; j++) {
        String week = "";
        if (j == 0) {
          week = "First";
        } else if (j == 1) {
          week = "Second";
        } else if (j == 2) {
          week = "Third";
        } else {
          week = "Fourth";
        }
        File file = new File(week + "WeekSales.csv");
        Scanner s = new Scanner(file);
        s.useDelimiter(",");
        for (int i = 0; i < 4; i++) {
            s.next();
        }
          while (s.hasNext()) {
            for (int i = 0; i < 4; i++) {
              s.next();
            }
            String sales = s.next() + s.next();
            sales = sales.substring(2, 9);
            
            //Running a query
            //TODO: update the sql command here
            String sqlStatement = "INSERT INTO sales VALUES (" + counter + ", " + sales + ", 0);";
            int result = stmt.executeUpdate(sqlStatement);
            for (int i = 0; i < 19; i++) {
              String item = s.next();
              int quantity = s.nextInt();
              if (counter % 7 == 6 && i == 18) {
                sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + quantity + " WHERE item_id = 2019;";
                result = stmt.executeUpdate(sqlStatement);
                break;
              } else {
                s.next();
                s.next();
                switch (i) {
                  case 0:
                    int chicken = 5 * quantity;
                    int toast = quantity;
                    int potato = quantity;
                    int sauce = quantity;
                    int drink = quantity;
                    int fries = quantity;
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + toast + " WHERE item_id = 2004;";
                    result = stmt.executeUpdate(sqlStatement);
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + potato + " WHERE item_id = 1002;";
                    result = stmt.executeUpdate(sqlStatement);
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + chicken + " WHERE item_id = 1001;";
                    result = stmt.executeUpdate(sqlStatement);
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + sauce + " WHERE item_id = 2011;";
                    result = stmt.executeUpdate(sqlStatement);
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + drink + " WHERE item_id = 2013;";
                    result = stmt.executeUpdate(sqlStatement);
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + fries + " WHERE item_id = 3002;";
                    result = stmt.executeUpdate(sqlStatement);
                    break;
                  case 1:
                    chicken = 4 * quantity;
                    toast = quantity;
                    potato = quantity;
                    sauce = quantity;
                    drink = quantity;
                    fries = quantity;
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + toast + " WHERE item_id = 2004;";
                    result = stmt.executeUpdate(sqlStatement);
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + potato + " WHERE item_id = 1002;";
                    result = stmt.executeUpdate(sqlStatement);
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + chicken + " WHERE item_id = 1001;";
                    result = stmt.executeUpdate(sqlStatement);
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + sauce + " WHERE item_id = 2011;";
                    result = stmt.executeUpdate(sqlStatement);
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + drink + " WHERE item_id = 2013;";
                    result = stmt.executeUpdate(sqlStatement);
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + fries + " WHERE item_id = 3002;";
                    result = stmt.executeUpdate(sqlStatement);
                    break;
                  case 2:
                    chicken = 3 * quantity;
                    toast = quantity;
                    potato = quantity;
                    sauce = quantity;
                    drink = quantity;
                    fries = quantity;
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + toast + " WHERE item_id = 2004;";
                    result = stmt.executeUpdate(sqlStatement);
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + potato + " WHERE item_id = 1002;";
                    result = stmt.executeUpdate(sqlStatement);
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + chicken + " WHERE item_id = 1001;";
                    result = stmt.executeUpdate(sqlStatement);
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + sauce + " WHERE item_id = 2011;";
                    result = stmt.executeUpdate(sqlStatement);
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + drink + " WHERE item_id = 2013;";
                    result = stmt.executeUpdate(sqlStatement);
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + fries + " WHERE item_id = 3002;";
                    result = stmt.executeUpdate(sqlStatement);
                    break;
                  case 3:
                    chicken = quantity;
                    toast = quantity;
                    potato = quantity;
                    sauce = quantity;
                    drink = quantity;
                    fries = quantity;
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + toast + " WHERE item_id = 2004;";
                    result = stmt.executeUpdate(sqlStatement);
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + potato + " WHERE item_id = 1002;";
                    result = stmt.executeUpdate(sqlStatement);
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + chicken + " WHERE item_id = 1001;";
                    result = stmt.executeUpdate(sqlStatement);
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + sauce + " WHERE item_id = 2011;";
                    result = stmt.executeUpdate(sqlStatement);
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + drink + " WHERE item_id = 2013;";
                    result = stmt.executeUpdate(sqlStatement);
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + fries + " WHERE item_id = 3002;";
                    result = stmt.executeUpdate(sqlStatement);
                    break;
                  case 4:
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + quantity + " WHERE item_id = 2009;";
                    result = stmt.executeUpdate(sqlStatement);
                    break;
                  case 5:
                    chicken = 20 * quantity;
                    toast = 4 * quantity;
                    fries = 4 * quantity;
                    sauce = 8 * quantity;
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + toast + " WHERE item_id = 2004;";
                    result = stmt.executeUpdate(sqlStatement);
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + fries + " WHERE item_id = 3002;";
                    result = stmt.executeUpdate(sqlStatement);
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + chicken + " WHERE item_id = 1001;";
                    result = stmt.executeUpdate(sqlStatement);
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + sauce + " WHERE item_id = 2011;";
                    result = stmt.executeUpdate(sqlStatement);
                    break;
                  case 6:
                    int bread = 2 * quantity;
                    int cheese = quantity;
                    chicken = quantity;
                    int bacon = 2 * quantity;
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + bread + " WHERE item_id = 2004;";
                    result = stmt.executeUpdate(sqlStatement);
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + cheese + " WHERE item_id = 1005;";
                    result = stmt.executeUpdate(sqlStatement);
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + chicken + " WHERE item_id = 1001;";
                    result = stmt.executeUpdate(sqlStatement);
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + bacon + " WHERE item_id = 1006;";
                    result = stmt.executeUpdate(sqlStatement);
                    //toast
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + quantity + " WHERE item_id = 2004;";
                    result = stmt.executeUpdate(sqlStatement);
                    //fries
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + quantity + " WHERE item_id = 3002;";
                    result = stmt.executeUpdate(sqlStatement);
                    //potato salad
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + quantity + " WHERE item_id = 1002;";
                    result = stmt.executeUpdate(sqlStatement);
                    //drink
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + quantity + " WHERE item_id = 2013;";
                    result = stmt.executeUpdate(sqlStatement);
                    break;
                  case 7:
                    bread = 2 * quantity;
                    cheese = quantity;
                    chicken = quantity;
                    bacon = 2 * quantity;
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + bread + " WHERE item_id = 2004;";
                    result = stmt.executeUpdate(sqlStatement);
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + cheese + " WHERE item_id = 1005;";
                    result = stmt.executeUpdate(sqlStatement);
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + chicken + " WHERE item_id = 1001;";
                    result = stmt.executeUpdate(sqlStatement);
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + bacon + " WHERE item_id = 1006;";
                    result = stmt.executeUpdate(sqlStatement);
                    break;
                  case 8:
                    bread = 2 * quantity;
                    cheese = quantity;
                    chicken = quantity;
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + bread + " WHERE item_id = 2004;";
                    result = stmt.executeUpdate(sqlStatement);
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + cheese + " WHERE item_id = 1005;";
                    result = stmt.executeUpdate(sqlStatement);
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + chicken + " WHERE item_id = 1001;";
                    result = stmt.executeUpdate(sqlStatement);
                    //toast
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + quantity + " WHERE item_id = 2004;";
                    result = stmt.executeUpdate(sqlStatement);
                    //fries
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + quantity + " WHERE item_id = 3002;";
                    result = stmt.executeUpdate(sqlStatement);
                    //potato salad
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + quantity + " WHERE item_id = 1002;";
                    result = stmt.executeUpdate(sqlStatement);
                    //drink
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + quantity + " WHERE item_id = 2013;";
                    result = stmt.executeUpdate(sqlStatement);
                    break;
                  case 9:
                    bread = 2 * quantity;
                    cheese = quantity;
                    chicken = quantity;
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + bread + " WHERE item_id = 2004;";
                    result = stmt.executeUpdate(sqlStatement);
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + cheese + " WHERE item_id = 1005;";
                    result = stmt.executeUpdate(sqlStatement);
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + chicken + " WHERE item_id = 1001;";
                    result = stmt.executeUpdate(sqlStatement);
                    break;
                  case 10:
                    bread = 2 * quantity;
                    cheese = quantity;
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + bread + " WHERE item_id = 2004;";
                    result = stmt.executeUpdate(sqlStatement);
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + cheese + " WHERE item_id = 1005;";
                    result = stmt.executeUpdate(sqlStatement);
                    //toast
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + quantity + " WHERE item_id = 2004;";
                    result = stmt.executeUpdate(sqlStatement);
                    //fries
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + quantity + " WHERE item_id = 3002;";
                    result = stmt.executeUpdate(sqlStatement);
                    //potato salad
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + quantity + " WHERE item_id = 1002;";
                    result = stmt.executeUpdate(sqlStatement);
                    //drink
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + quantity + " WHERE item_id = 2013;";
                    result = stmt.executeUpdate(sqlStatement);
                    break;
                  case 11:
                    bread = 2 * quantity;
                    cheese = quantity;
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + bread + " WHERE item_id = 2004;";
                    result = stmt.executeUpdate(sqlStatement);
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + cheese + " WHERE item_id = 1005;";
                    result = stmt.executeUpdate(sqlStatement);
                    break;
                  case 12:
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + quantity + " WHERE item_id = 2011;";
                    result = stmt.executeUpdate(sqlStatement);
                    break;
                  case 13:
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + quantity + " WHERE item_id = 1001;";
                    result = stmt.executeUpdate(sqlStatement);
                    break;
                  case 14:
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + quantity + " WHERE item_id = 2004;";
                    result = stmt.executeUpdate(sqlStatement);
                    break;
                  case 15:
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + quantity + " WHERE item_id = 1002;";
                    result = stmt.executeUpdate(sqlStatement);
                    break;
                  case 16:
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + quantity + " WHERE item_id = 3002;";
                    result = stmt.executeUpdate(sqlStatement);
                    break;
                  case 17:
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + quantity + " WHERE item_id = 2013;";
                    result = stmt.executeUpdate(sqlStatement);
                    break;
                  case 18:
                    sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - " + quantity + " WHERE item_id = 2019;";
                    result = stmt.executeUpdate(sqlStatement);
                    break;
                  default:
                    System.out.println("Invalid order");
                    break;
                }
              }
            }
            if (counter % 7 != 6) {
              s.next();
              s.next();
              s.next();
            } else {
              counter++;
              break;
            }
            counter++;
          }
          s.close();
        }
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