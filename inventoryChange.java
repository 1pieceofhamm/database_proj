import java.sql.*;
import java.util.*;
import java.io.*;

/*
CSCE 315
9-27-2021 Lab
 */
public class inventoryChange {

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
        File file = new File("First day order.csv");
        Scanner s = new Scanner(file);
        s.useDelimiter(",");
        for (int i = 0; i < 3; i++) {
          s.nextLine();
        }
        for(int i=0;i<19;i++){//food
        //while (s.hasNext()) {
          String item_name = s.next();
          String item_id_string = s.next();
          String without_letter = item_id_string.substring(1);
          int item_ID = Integer.parseInt(without_letter);
          String item_quantity_str = s.next();
          double item_quantity = Double.parseDouble(item_quantity_str);
          s.next(); //skip
          s.next(); //skip
          s.next(); //skip
          String multiplier = s.next();
          Double double_multiplier = Double.parseDouble(multiplier);
          item_quantity = item_quantity * double_multiplier;
          String with_dollar_sign = s.next();
          String without_dollar_sign = with_dollar_sign.substring(1);
          double price = Double.parseDouble(without_dollar_sign);
          for (int j = 0; j < 4; j++) {
            String str = s.next();
            if(str.charAt(0)=='\"'){
              str = str.substring(1);
              while(!(str.contains("\""))){
                str+=s.next();
              }
            }
          }
          switch(item_ID){
            case 1001: 
              item_quantity = item_quantity * 20 * 16 * 4 / 6; //for individual strips
              break;
            case 2004:
              item_quantity = item_quantity * 170;
              break;
            case 3002:
              item_quantity = item_quantity * 30;
              break;
            case 1002:
              item_quantity = item_quantity * 5 * 16 / 5.5; //trying to divide int by double
              break;
            case 2011:
              item_quantity = item_quantity * 4 * 128 / 2;
              break;
            case 2013: //Bib
              item_quantity = item_quantity * 3 * 128 / 16;
              break;
            case 2014:
              item_quantity = item_quantity * 3 * 128 / 16;
              break;
            case 2015:
              item_quantity = item_quantity * 3 * 128 / 16;
              break;
            case 2016:
              item_quantity = item_quantity * 3 * 128 / 16;
              break;
            case 2017:
              item_quantity = item_quantity * 3 * 128 / 16;
              break;
            case 2018:
              item_quantity = item_quantity * 3 * 128 / 16;
              break;
            case 2019: //Bottles
              item_quantity = item_quantity * 24;
              break;
            case 2020: //Bottles
              item_quantity = item_quantity * 24;
              break;
            case 2021: //Bottles
              item_quantity = item_quantity * 24;
              break;
            case 2022: //Bottles
              item_quantity = item_quantity * 24;
              break;
            case 1005: //cheese
              item_quantity = item_quantity * 6 * 160;
              break;
            case 1006: //bacon
              item_quantity = item_quantity * 300;
              break;
            case 2009:
              item_quantity = item_quantity * 24;
              break;
            default:
              break;
          }
            //Running a query
            //TODO: update the sql command here
          //System.out.println(item_ID + ", " + item_name + ", " + item_quantity + ", " + "\'2022-02-20\'" + ", " + price);
          String sqlStatement = "INSERT INTO inventory VALUES (" + item_ID + ", " + "\'"+ item_name+"\'" + ", " + item_quantity + ", " + "\'2022-02-20\'" + ", " + price + ", "+ 0 + ");";
          int result = stmt.executeUpdate(sqlStatement);
        }
        s.nextLine(); //skip
        for(int i=0;i<6;i++){ //bib
            String item_name = s.next();
            String item_id_string = s.next();
            String without_letter = item_id_string.substring(1);
            int item_ID = Integer.parseInt(without_letter);
            String item_quantity_str = s.next();
            double item_quantity = Double.parseDouble(item_quantity_str);
            s.next(); //skip
            s.next(); //skip
            s.next(); //skip
            String multiplier = s.next();
            Double double_multiplier = Double.parseDouble(multiplier);
            item_quantity = item_quantity * double_multiplier;
            String with_dollar_sign = s.next();
            String without_dollar_sign = with_dollar_sign.substring(1);
            double price = Double.parseDouble(without_dollar_sign);
            for (int j = 0; j < 4; j++) {
              String str = s.next();
              if(str.charAt(0)=='\"'){
                str = str.substring(1);
                while(!(str.contains("\""))){
                  str+=s.next();
                }
              }
            }
            switch(item_ID){
              case 1001: 
                item_quantity = item_quantity * 20 * 16 * 4 / 6; //for individual strips
                break;
              case 2004:
                item_quantity = item_quantity * 170;
                break;
              case 3002:
                item_quantity = item_quantity * 30;
                break;
              case 1002:
                item_quantity = item_quantity * 5 * 16 / 5.5; //trying to divide int by double
                break;
              case 2011:
                item_quantity = item_quantity * 4 * 128 / 2;
                break;
              case 2013: //Bib
                item_quantity = item_quantity * 3 * 128 / 16;
                break;
              case 2014:
                item_quantity = item_quantity * 3 * 128 / 16;
                break;
              case 2015:
                item_quantity = item_quantity * 3 * 128 / 16;
                break;
              case 2016:
                item_quantity = item_quantity * 3 * 128 / 16;
                break;
              case 2017:
                item_quantity = item_quantity * 3 * 128 / 16;
                break;
              case 2018:
                item_quantity = item_quantity * 3 * 128 / 16;
                break;
              case 2019: //Bottles
                item_quantity = item_quantity * 24;
                break;
              case 2020: //Bottles
                item_quantity = item_quantity * 24;
                break;
              case 2021: //Bottles
                item_quantity = item_quantity * 24;
                break;
              case 2022: //Bottles
                item_quantity = item_quantity * 24;
                break;
              case 1005: //cheese
                item_quantity = item_quantity * 6 * 160;
                break;
              case 1006: //bacon
                item_quantity = item_quantity * 300;
                break;
              case 2009:
                item_quantity = item_quantity * 24;
                break;
              default:
                break;
            }
              //Running a query
              //TODO: update the sql command here
            String sqlStatement = "INSERT INTO inventory VALUES (" + item_ID + ", " + "\'" + item_name + "\'" + ", " + item_quantity + ", " + "\'2022-02-20\'" + ", " + price + ", "+ 0 + ");";
            int result = stmt.executeUpdate(sqlStatement);
        }
        s.nextLine();
        for(int i=0;i<4;i++){ //bottles
          //while (s.hasNext()) {
            String item_name = s.next();
            String item_id_string = s.next();
            String without_letter = item_id_string.substring(1);
            int item_ID = Integer.parseInt(without_letter);
            String item_quantity_str = s.next();
            double item_quantity = Double.parseDouble(item_quantity_str);
            s.next(); //skip
            s.next(); //skip
            s.next(); //skip
            String multiplier = s.next();
            Double double_multiplier = Double.parseDouble(multiplier);
            item_quantity = item_quantity * double_multiplier;
            String with_dollar_sign = s.next();
            String without_dollar_sign = with_dollar_sign.substring(1);
            double price = Double.parseDouble(without_dollar_sign);
            for (int j = 0; j < 4; j++) {
              String str = s.next();
              if(str.charAt(0)=='\"'){
                str = str.substring(1);
                while(!(str.contains("\""))){
                  str+=s.next();
                }
              }
            }
            switch(item_ID){
              case 1001: 
                item_quantity = item_quantity * 20 * 16 * 4 / 6; //for individual strips
                break;
              case 2004:
                item_quantity = item_quantity * 170;
                break;
              case 3002:
                item_quantity = item_quantity * 30;
                break;
              case 1002:
                item_quantity = item_quantity * 5 * 16 / 5.5; //trying to divide int by double
                break;
              case 2011:
                item_quantity = item_quantity * 4 * 128 / 2;
                break;
              case 2013: //Bib
                item_quantity = item_quantity * 3 * 128 / 16;
                break;
              case 2014:
                item_quantity = item_quantity * 3 * 128 / 16;
                break;
              case 2015:
                item_quantity = item_quantity * 3 * 128 / 16;
                break;
              case 2016:
                item_quantity = item_quantity * 3 * 128 / 16;
                break;
              case 2017:
                item_quantity = item_quantity * 3 * 128 / 16;
                break;
              case 2018:
                item_quantity = item_quantity * 3 * 128 / 16;
                break;
              case 2019: //Bottles
                item_quantity = item_quantity * 24;
                break;
              case 2020: //Bottles
                item_quantity = item_quantity * 24;
                break;
              case 2021: //Bottles
                item_quantity = item_quantity * 24;
                break;
              case 2022: //Bottles
                item_quantity = item_quantity * 24;
                break;
              case 1005: //cheese
                item_quantity = item_quantity * 6 * 160;
                break;
              case 1006: //bacon
                item_quantity = item_quantity * 300;
                break;
              case 2009:
                item_quantity = item_quantity * 24;
                break;
              default:
                break;
            }
              //Running a query
              //TODO: update the sql command here
            String sqlStatement = "INSERT INTO inventory VALUES (" + item_ID + ", " + "\'" + item_name + "\'" + ", " + item_quantity + ", " + "\'2022-02-20\'"+ ", " + price + ", "+ 0 + ");";
            int result = stmt.executeUpdate(sqlStatement);
          }
        s.nextLine();
        for(int i=0;i<13;i++){ //serving
          //while (s.hasNext()) {
            String item_name = s.next();
            String item_id_string = s.next();
            String without_letter = item_id_string.substring(1);
            int item_ID = Integer.parseInt(without_letter);
            String item_quantity_str = s.next();
            double item_quantity = Double.parseDouble(item_quantity_str);
            s.next(); //skip
            s.next(); //skip
            s.next(); //skip
            String multiplier = s.next();
            Double double_multiplier = Double.parseDouble(multiplier);
            item_quantity = item_quantity * double_multiplier;
            String with_dollar_sign = s.next();
            String without_dollar_sign = with_dollar_sign.substring(1);
            double price = Double.parseDouble(without_dollar_sign);
            for (int j = 0; j < 4; j++) {
              String str = s.next();
              if(str.charAt(0)=='\"'){
                str = str.substring(1);
                while(!(str.contains("\""))){
                  str+=s.next();
                }
              }
            }
            switch(item_ID){
              case 1001: 
                item_quantity = item_quantity * 20 * 16 * 4 / 6; //for individual strips
                break;
              case 2004:
                item_quantity = item_quantity * 170;
                break;
              case 3002:
                item_quantity = item_quantity * 30;
                break;
              case 1002:
                item_quantity = item_quantity * 5 * 16 / 5.5; //trying to divide int by double
                break;
              case 2011:
                item_quantity = item_quantity * 4 * 128 / 2;
                break;
              case 2013: //Bib
                item_quantity = item_quantity * 3 * 128 / 16;
                break;
              case 2014:
                item_quantity = item_quantity * 3 * 128 / 16;
                break;
              case 2015:
                item_quantity = item_quantity * 3 * 128 / 16;
                break;
              case 2016:
                item_quantity = item_quantity * 3 * 128 / 16;
                break;
              case 2017:
                item_quantity = item_quantity * 3 * 128 / 16;
                break;
              case 2018:
                item_quantity = item_quantity * 3 * 128 / 16;
                break;
              case 2019: //Bottles
                item_quantity = item_quantity * 24;
                break;
              case 2020: //Bottles
                item_quantity = item_quantity * 24;
                break;
              case 2021: //Bottles
                item_quantity = item_quantity * 24;
                break;
              case 2022: //Bottles
                item_quantity = item_quantity * 24;
                break;
              case 1005: //cheese
                item_quantity = item_quantity * 6 * 160;
                break;
              case 1006: //bacon
                item_quantity = item_quantity * 300;
                break;
              case 2009:
                item_quantity = item_quantity * 24;
                break;
              default:
                break;
            }
              //Running a query
              //TODO: update the sql command here
            String sqlStatement = "INSERT INTO inventory VALUES (" + item_ID + ", " + "\'" + item_name + "\'" + ", " + item_quantity + ", " + "\'2022-02-20\'" + ", " + price + ", "+ 0 + ");";
            int result = stmt.executeUpdate(sqlStatement);
          }
        s.nextLine();
        for(int i=0;i<10;i++){
          //while (s.hasNext()) {
            String item_name = s.next();
            String item_id_string = s.next();
            String without_letter = item_id_string.substring(1);
            int item_ID = Integer.parseInt(without_letter);
            String item_quantity_str = s.next();
            double item_quantity = Double.parseDouble(item_quantity_str);
            s.next(); //skip
            s.next(); //skip
            s.next(); //skip
            String multiplier = s.next();
            Double double_multiplier = Double.parseDouble(multiplier);
            item_quantity = item_quantity * double_multiplier;
            String with_dollar_sign = s.next();
            String without_dollar_sign = with_dollar_sign.substring(1);
            double price = Double.parseDouble(without_dollar_sign);
            for (int j = 0; j < 4; j++) {
              String str = s.next();
              if(str.charAt(0)=='\"'){
                str = str.substring(1);
                while(!(str.contains("\""))){
                  str+=s.next();
                }
              }
            }
            switch(item_ID){
              case 1001: 
                item_quantity = item_quantity * 20 * 16 * 4 / 6; //for individual strips
                break;
              case 2004:
                item_quantity = item_quantity * 170;
                break;
              case 3002:
                item_quantity = item_quantity * 30;
                break;
              case 1002:
                item_quantity = item_quantity * 5 * 16 / 5.5; //trying to divide int by double
                break;
              case 2011:
                item_quantity = item_quantity * 4 * 128 / 2;
                break;
              case 2013: //Bib
                item_quantity = item_quantity * 3 * 128 / 16;
                break;
              case 2014:
                item_quantity = item_quantity * 3 * 128 / 16;
                break;
              case 2015:
                item_quantity = item_quantity * 3 * 128 / 16;
                break;
              case 2016:
                item_quantity = item_quantity * 3 * 128 / 16;
                break;
              case 2017:
                item_quantity = item_quantity * 3 * 128 / 16;
                break;
              case 2018:
                item_quantity = item_quantity * 3 * 128 / 16;
                break;
              case 2019: //Bottles
                item_quantity = item_quantity * 24;
                break;
              case 2020: //Bottles
                item_quantity = item_quantity * 24;
                break;
              case 2021: //Bottles
                item_quantity = item_quantity * 24;
                break;
              case 2022: //Bottles
                item_quantity = item_quantity * 24;
                break;
              case 1005: //cheese
                item_quantity = item_quantity * 6 * 160;
                break;
              case 1006: //bacon
                item_quantity = item_quantity * 300;
                break;
              case 2009:
                item_quantity = item_quantity * 24;
                break;
              default:
                break;
            }
              //Running a query
              //TODO: update the sql command here
            String sqlStatement = "INSERT INTO inventory VALUES (" + item_ID + ", " + "\'" + item_name + "\'" + ", " + item_quantity + ", " + "\'2022-02-20\'" + ", " + price + ", "+ 0 + ");";
            int result = stmt.executeUpdate(sqlStatement);
          }
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