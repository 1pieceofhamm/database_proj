import java.sql.*;
import java.awt.event.*;
import java.awt.*;   
import javax.swing.*;
import java.util.*;

/*
  TODO:
  1) Change credentials for your own team's database
  2) Change SQL command to a relevant query that retrieves a small amount of data
  3) Create a JTextArea object using the queried data
  4) Add the new object to the JPanel p
*/

public class GUI extends JFrame implements ActionListener {
    static GUI s;
    static JFrame f;
    static JFrame I;
    static JFrame login;
    static JFrame O;
    static JFrame S;
    static JFrame M;
    static JFrame A;
    static JFrame ser;
    static JFrame addItem;
    static JPanel menu;
    static Set<JButton> mButtons = new HashSet<>();
    static ArrayList<String> order = new ArrayList<>();
    static ArrayList<String> items = new ArrayList<>();
    static ArrayList<String> ids = new ArrayList<>();
    static String itemsInOrder = "";
    static Connection conn = null;
    static String name = "";
    static String sqlStatement ="";
    static int day;
    static int chicken1, toast1, potato1, drink1, fries1, tea1, bread1, cheese1, bacon1, sauce1, impChick = 0;

    public static void main(String[] args)
    {
      //Building the connection
      
      //TODO STEP 1
      

      // create a new frame
      f = new JFrame("DB GUI");
      I = new JFrame("Inventory");
      login = new JFrame("Login");
      O = new JFrame("Orders");
      S = new JFrame("Sales");
      M = new JFrame("Menu");
      A = new JFrame("Analytics");



      // create a object
      s = new GUI();
      try {
        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315909_9db",
            "csce315909_9user", "password");
      } catch (Exception e109) { //
        e109.printStackTrace();
        System.err.println(e109.getClass().getName()+": "+e109.getMessage());
        System.exit(0);
      }
      try {
        day = 0;
        Statement stmt = conn.createStatement();
        sqlStatement = "SELECT day_id FROM orders;";
        ResultSet result = stmt.executeQuery(sqlStatement);
        while (result.next()) {
          String day_id = result.getString("day_id");
          int numDay = Integer.parseInt(day_id);
          if (numDay > day) {
            day = numDay;
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      try {
        conn.close();
        // JOptionPane.showMessageDialog(null, "Connection closed.");
      } catch (Exception e3) {
        JOptionPane.showMessageDialog(null, "Connection not closed.");
      }

      int ans = JOptionPane.showConfirmDialog(f, "New day?");
      if (ans == JOptionPane.YES_OPTION) {
        day++;
        newDay();
      }

      // Server GUI
      ser = new JFrame("Server GUI");
      JLabel header = new JLabel("New Order");
      header.setFont(header.getFont().deriveFont(48.0F));
      JPanel spanel = new JPanel();
      spanel.add(header);
      JTextArea text = new JTextArea(itemsInOrder);
      text.setBounds(300, 100, 350, 350);
      ser.add(text);
      JScrollPane scrollbar = new JScrollPane(text, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
      scrollbar.setBounds(300, 100, 600, 600);
      ser.add(scrollbar);
      JPanel orderButtons = new JPanel(new GridLayout(3, 1, 15, 20));
      orderButtons.setBounds(30, 100, 250, 600);
      JButton add = new JButton("Add Item");
      JButton cancel = new JButton("Cancel Order");
      JButton finish = new JButton("Finish And Pay");
      orderButtons.add(add);
      orderButtons.add(cancel);
      orderButtons.add(finish);
      ser.add(orderButtons);
      add.addActionListener(s);
      cancel.addActionListener(s);
      finish.addActionListener(s);

      //Add Item GUI
      createMenuGUI();

      // create a panel
      JPanel p = new JPanel(new GridLayout(2,3,20,25));
      JPanel p1 = new JPanel();
      JPanel p2 = new JPanel();
      JPanel p3 = new JPanel();


      //analytics buttons
      JPanel p4 = new JPanel(new GridLayout(3,1,20,25));
      JButton a1 = new JButton("Inventory Usage Chart");
      JButton a2 = new JButton("Restock Report");
      JButton a3 = new JButton("Ordering Popularity");
      p4.add(a1);
      p4.add(a2);
      p4.add(a3);
      p4.setBounds(620,30,300,600);
      a1.addActionListener(s);
      a2.addActionListener(s);
      a3.addActionListener(s);
      A.add(p4);

      //inventory how to sort data
      JPanel inventorySelections = new JPanel(new GridLayout(5,2,20,25));
      JButton sort1 = new JButton("Sort by item_id ASC");    
      JButton sort2 = new JButton("Sort by item_id DESC");
      JButton sort3 = new JButton("Sort by item_name ASC");    
      JButton sort4 = new JButton("Sort by item_name DESC");
      JButton sort5 = new JButton("Sort by quantity ASC");    
      JButton sort6 = new JButton("Sort by quantity DESC");
      JButton sort7 = new JButton("EDIT item_quantity");    
      JButton sort8 = new JButton("ADD new item");
      JButton sort9 = new JButton("REMOVE item");    
      JButton sort10 = new JButton("Sort by price DESC");    
      inventorySelections.add(sort1);
      inventorySelections.add(sort2);
      inventorySelections.add(sort3);
      inventorySelections.add(sort4);
      inventorySelections.add(sort5);
      inventorySelections.add(sort6);
      inventorySelections.add(sort10);
      inventorySelections.add(sort9);
      inventorySelections.add(sort7);
      inventorySelections.add(sort8);
      inventorySelections.setBounds(620,30,350,600);
      I.add(inventorySelections);
      sort1.addActionListener(s);
      sort2.addActionListener(s);
      sort3.addActionListener(s);
      sort4.addActionListener(s);
      sort5.addActionListener(s);
      sort6.addActionListener(s);
      sort7.addActionListener(s);
      sort8.addActionListener(s);
      sort9.addActionListener(s);
      sort10.addActionListener(s);

      //menu panels
      JPanel menuSelections = new JPanel(new GridLayout(3,1,20,25));
      JButton menu1 = new JButton("Edit menu item price");
      JButton menu2 = new JButton("Add menu item");
      JButton menu3 = new JButton("Remove menu item");
      menuSelections.add(menu1);
      menuSelections.add(menu2);
      menuSelections.add(menu3);
      menu1.addActionListener(s);
      menu2.addActionListener(s);
      menu3.addActionListener(s);
      menuSelections.setBounds(620,30,300,600);
      M.add(menuSelections);

      JPanel salesSelections = new JPanel(new GridLayout(2,1,20,25));
      JButton sales1 = new JButton("Sort by total_sales ASC");    
      JButton sales2 = new JButton("Sort by total_sales DESC");
      salesSelections.add(sales1);
      salesSelections.add(sales2);
      salesSelections.setBounds(620,30,350,600);
      S.add(salesSelections);
      sales1.addActionListener(s);
      sales2.addActionListener(s);


      JPanel loginP = new JPanel(new GridLayout(1,2,20,25));

      JButton manager = new JButton("Manager");
      JButton server = new JButton("Server");
      JButton close = new JButton("Close");

      login.add(manager,BorderLayout.WEST);
      login.add(server,BorderLayout.EAST);
      login.add(close,BorderLayout.SOUTH);

      JButton btn1 = new JButton("Analytics");    
      JButton btn2 = new JButton("Sales");    
      JButton btn3 = new JButton("Orders");    
      JButton btn4 = new JButton("Inventory");    
      JButton btn5 = new JButton("Menu");    
      JButton btn6 = new JButton("Log Out");
      JButton btn7 = new JButton("Go back to main menu");
      JButton btn8 = new JButton("Go back to main menu");
      JButton btn9 = new JButton("Go back to main menu");
      JButton btn10 = new JButton("Go back to main menu");
      JButton btn11 = new JButton("Go back to main menu");

      // add actionlistener to button
      btn1.addActionListener(s);
      btn4.addActionListener(s);
      btn7.addActionListener(s);
      btn8.addActionListener(s);
      btn9.addActionListener(s);
      btn10.addActionListener(s);
      btn11.addActionListener(s);

      manager.addActionListener(s);
      server.addActionListener(s);
      close.addActionListener(s);
      btn5.addActionListener(s);
      btn2.addActionListener(s);
      btn3.addActionListener(s);
      btn6.addActionListener(s);

      //TODO Step 3 

      //TODO Step 4

      // add button to panel
      loginP.add(manager);
      loginP.add(server);


      p.add(btn1);
      p.add(btn2);
      p.add(btn3);
      p.add(btn4);
      p.add(btn5);
      p.add(btn6);


      p1.add(btn7);
      p2.add(btn8);
      p3.add(btn9);
      JPanel panel4 = new JPanel();
      JPanel panel5 = new JPanel();
      panel4.add(btn10);
      panel5.add(btn11);
      // add panel to frame
      login.add(loginP);
      f.add(p);
      I.add(p3);
      O.add(p2);
      S.add(p1);
      M.add(panel4);
      A.add(panel5);
      ser.add(spanel);

      // set the size of frame

      login.setSize(1000, 800);
      login.setVisible(true);
      login.show();
      
      
      f.setSize(1000, 800);
      I.setSize(1000, 800);
      O.setSize(1000, 800);
      S.setSize(1000, 800);
      M.setSize(1000,800);
      A.setSize(1000,800);
      ser.setSize(1000, 800);
      addItem.setSize(1000, 800);
    }

    // if button is pressed
    public void actionPerformed(ActionEvent e)
    {
        String s = e.getActionCommand();
        if (s.equals("Analytics")) {
            f.setVisible(false);
            O.setVisible(false);
            S.setVisible(false);
            M.setVisible(false);
            I.setVisible(false);
            A.setVisible(true);
        }
        else if(s.equals("Inventory Usage Chart")){
          int q = -1;
          int q2 = -2;
          String startDate = "";
          String endDate = "";

          JPanel p90 = new JPanel(new GridLayout(3,1,20,25));
          p90.setBounds(10,30,350,350);
          A.add(p90);

          while(q<=0){
            startDate = JOptionPane.showInputDialog("Please enter the start date: ");
            if(startDate.length() > 0){
              q = q + 3;
            }
          }
          while(q2<=0){
            endDate = JOptionPane.showInputDialog("Please enter the end date: ");
            if(endDate.length() > 0){
              q2 = q2 + 3;
            }
          }
          name = "";
          try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315909_9db",
               "csce315909_9user", "password");
          } catch (Exception e999) {
            e999.printStackTrace();
            System.err.println(e999.getClass().getName()+": "+e999.getMessage());
            System.exit(0);
          }
    
          try{
            Statement stmt = conn.createStatement();
            sqlStatement = "SELECT SUM(order_quantity) AS \"sumquant\", order_type FROM orders WHERE day_id >= " + startDate + " AND day_id < " + endDate + " GROUP BY order_type;";
            ResultSet result = stmt.executeQuery(sqlStatement);
            name +="item amount used, item name" + "\n";
            name +="-------------------------------------------------------------------------------------" + "\n";
            ///int chicken1, toast1, potato1, sauce1, drink1, fries1, tea1, bread1, cheese1, bacon1, sauce1, impChick = 0;
            while (result.next()) {
              //name += result.getString("sumquant") + ", ";
              String oTypes = result.getString("order_type");
              //String menu_name ="";
              
              switch(oTypes){
                case "501":
                chicken1 = chicken1 + 5;
                toast1 = toast1 + 1;
                potato1 = potato1 + 1;
                sauce1 = sauce1 + 1;
                drink1 = drink1 + 1;
                fries1 = fries1 + 1; 
                break;
              case "502":
                chicken1 = chicken1 + 4;
                toast1 = toast1 + 1;
                potato1 = potato1 + 1;
                sauce1 = sauce1 + 1;
                drink1 = drink1 + 1;
                fries1 = fries1 + 1;
                break;
              case "503":
                chicken1 = chicken1 + 3;
                toast1 = toast1 + 1;
                potato1 = potato1 + 1;
                sauce1 = sauce1 + 1;
                drink1 = drink1 + 1;
                fries1 = fries1 + 1;
                break;
              case "504":
                chicken1 = chicken1 + 1;
                toast1 = toast1 + 1;
                potato1 = potato1 + 1;
                sauce1 = sauce1 + 1;
                drink1 = drink1 + 1;
                fries1 = fries1 + 1;
                break;
              case "505": 
                tea1 = tea1 + 1;
                break;
              case "506":
                chicken1 = chicken1 + 20;
                toast1 = toast1 + 4;
                sauce1 = sauce1 + 8;
                fries1 = fries1 + 4;
                break;
              case "507":
                bread1 = bread1 + 2;
                cheese1 = cheese1 + 1;
                chicken1 = chicken1 + 1;
                bacon1 = bacon1 + 2;
                break;
              case "508":
                bread1 = bread1 + 2;
                cheese1 = cheese1 + 1;
                chicken1 = chicken1 + 1;
                bacon1 = bacon1 + 2;
                break;
              case "509": 
                bread1 = bread1 + 2;
                cheese1 = cheese1 + 1;
                chicken1 = chicken1 + 1;
                break;
              case "510":
                bread1 = bread1 + 2;
                cheese1 = cheese1 + 1;
                chicken1 = chicken1 + 1;
                break;
              case "511":
                bread1 = bread1 + 2;
                cheese1 = cheese1 + 1;
                break;
              case "512":
                bread1 = bread1 + 2;
                cheese1 = cheese1 + 1;
                break;
              case "513": 
                sauce1 = sauce1 + 1;
                break;
              case "514":
                chicken1 = chicken1 + 1;
                break;
              case "515":
                toast1 = toast1 + 1;
                break;
              case "516":
                potato1 = potato1 + 1;
                break;
              case "517": 
                fries1 = fries1 + 1;
                break;
              case "518":
                chicken1 = chicken1 + 4;
                toast1 = toast1 + 1;
                potato1 = potato1 + 1;
                sauce1 = sauce1 + 1;
                drink1 = drink1 + 1;
                fries1 = fries1 + 1;
                break;
              case "519":
                drink1 = drink1 + 1;
                break;
              case "520":
                impChick = impChick + 1;
                break;
              }
          }
        }
          catch (Exception e998){
            e998.printStackTrace();
          }
          if (chicken1 > 0){
            //c1.setText(chicken1 + " chicken fingers used");
            //p90.add(c1);
            name += "" + chicken1 + " chicken fingers used" + '\n';
          }
          if (toast1 > 0){
            //c2.setText(toast1 + " toast used");
            //p90.add(c2);
            name += "" + toast1 + " toast used" + '\n';
          }
          if (potato1 > 0){
            //c3.setText(potato1 + " potato salad used");
            //p90.add(c3);
            name += "" + potato1 + " potato salad used" + '\n';
          }
          if (sauce1 > 0){
            //c4.setText(sauce1 + " sauce used");
            //p90.add(c4);
            name += "" + sauce1 + " sauce used" + '\n';
          }
          if (drink1 > 0){
            //c5.setText(drink1 + " drinks used");
            //p90.add(c5);
            name += "" + drink1 + " drinks used" + '\n';
          }
          if (fries1 > 0){
            //c6.setText(fries1 + " fries used");
            //p90.add(c6);
            name += "" + fries1 + " fries used" + '\n';
          }
          if (tea1 > 0){
            //c7.setText(tea1 + " tea used");
            //p90.add(c7);
            name += "" + tea1 + " tea used" + '\n';
          }
          if (bread1 > 0){
            //c8.setText(bread1 + " bread used");
            //p90.add(c8);
            name += "" + bread1 + " bread used" + '\n';
          }
          if (cheese1 > 0){
            //c9.setText(cheese1 + " cheese used");
            //p90.add(c9);
            name += "" + cheese1 + " cheese used" + '\n';
          }
          if (bacon1 > 0){
            //c10.setText(bacon1 + " bacon used");
            //p90.add(c10);
            name += "" + bacon1 + " bacon used" + '\n';
          }
          if (sauce1 > 0){
            //c11.setText(sauce1 + " sauce used");
            //p90.add(c11);
            name += "" + sauce1 + " sauce used" + '\n';
          }
          if (impChick > 0){
            //c12.setText(impChick + " impossible chicken fingers used");
            //p90.add(c12);
            name += "" + impChick + " impossible chicken fingers used" + '\n';
          }

          try {
            conn.close();
          } catch(Exception e997) {
          }

          JTextArea area = new JTextArea(name);
          area.setBounds(10,30,350,350);
          A.add(area);
          JScrollPane scroll = new JScrollPane(area,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
          scroll.setBounds(10,30,600,600);
          A.add(scroll);
        }
        else if(s.equals("Restock Report")){
          //variables to set to refill
          int chicken_breast_restock = 0;
          int flour_restock = 0;
          int salt_restock = 0;
          int pepper_restock = 0;
          int fries_restock = 0;
          int bread_restock = 0;
          int potato_salad_restock = 0;
          int margarine_restock = 0;
          int garlic_restock = 0;
          int ranch_restock = 0;
          int ketchup_container_restock = 0;
          int ketchup_packet_restock = 0;
          int mayo_restock = 0;
          int tea_bags_restock = 0;
          int sugar_restock = 0;
          int worchester_restock = 0;
          int cheese_restock = 0;
          int bacon_restock = 0;
          int oil_restock = 0;
          //bib
          int dr_jones_restock = 0;
          int jones_orangecream_restock = 0;
          int jones_rootbeer_restock = 0;
          int jones_cola_restock = 0;
          int jones_lemon_restock = 0;
          int jones_sugarfree_restock = 0;
          //bottles
          int bottled_rootbeer_restock = 0;
          int bottled_cream_restock = 0;
          int bottled_orangecream_restock = 0;
          int bottled_berry_restock = 0;
          //food fill numbers
          int chicken_breast_fill = 89600;
          int flour_fill = 5;
          int salt_fill = 1;
          int pepper_fill = 2;
          int fries_fill = 14400;
          int bread_fill = 1530;
          int potato_salad_fill = 146;
          int margarine_fill = 4;
          int garlic_fill = 2;
          int ranch_fill = 12;
          int ketchup_container_fill = 3;
          int ketchup_packet_fill = 3;
          int mayo_fill = 6;
          int tea_bags_fill = 96;
          int sugar_fill = 4;
          int worchester_fill = 256;
          int cheese_fill = 960;
          int bacon_fill = 300;
          int oil_fill = 6;
          //bib
          int dr_jones_fill = 48;
          int jones_orangecream_fill = 48;
          int jones_rootbeer_fill = 48;
          int jones_cola_fill = 48;
          int jones_lemon_fill = 48;
          int jones_sugarfree_fill = 48;
          //bottles
          int bottled_rootbeer_fill = 96;
          int bottled_cream_fill = 96;
          int bottled_orangecream_fill = 96;
          int bottled_berry_fill = 96;
          //serving
          int straw_fill = 3;
          int drink_lid_fill = 2;
          int ten_oz_fill = 1;
          int sixteen_oz_fill = 2;
          int napkin_fill = 1;
          int cutlery_fill = 4;
          int basket_fill = 1;
          int two_clearcup_fill = 1;
          int two_lid_fill = 1;
          int five_clearcup_fill = 1;
          int five_lid_fill = 1;
          int hinge_small_fill = 3;
          int hinge_large_fill = 5;
          //janitorial
          int hand_soap_fill = 3;
          int hand_sanitizer_fill = 2;
          int dish_soap_fill = 1;
          int paper_towels_fill = 2;
          int gloves_fill = 4;
          int trash_small_fill = 1;
          int trash_large_fill = 1;
          int toilet_paper_fill = 1;
          int surface_fill = 1;
          int floor_fill = 2;
          name = "";
            try {
              Class.forName("org.postgresql.Driver");
              conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315909_9db",
                 "csce315909_9user", "password");
            } catch (Exception e999) {
              e999.printStackTrace();
              System.err.println(e999.getClass().getName()+": "+e999.getMessage());
              System.exit(0);
            }
            JOptionPane.showMessageDialog(null,"Opened database successfully");
      
            
            try{
              //create a statement object
              Statement stmt = conn.createStatement();
              //create an SQL statement
              //TODO Step 2
              sqlStatement = "SELECT item_name, item_quantity FROM inventory;";
              //send statement to DBMS
              ResultSet result = stmt.executeQuery(sqlStatement);
              name +="item_name, how much is needed to reach fill" + "\n";
              name +="-------------------------------------------------------------------------------------" + "\n";
              while (result.next()) {
                String it_name = result.getString("item_name");
                String strQuantity = result.getString("item_quantity");
                int intQuantity = Integer.parseInt(strQuantity);
                int difference;
                switch(it_name){
                  case "Chicken breast":
                    difference = chicken_breast_fill-intQuantity;
                    chicken_breast_restock=difference;
                    break;
                  case "flour":
                    difference = flour_fill-intQuantity;
                    flour_restock=difference;
                    break;
                  case "salt":
                    difference = salt_fill-intQuantity;
                    salt_restock=difference;
                    break;
                  case "black pepper":
                    difference = pepper_fill-intQuantity;
                    pepper_restock=difference;
                    break;
                  case "Fries":
                    difference = fries_fill-intQuantity;
                    fries_restock=difference;
                    break;
                  case "Thick bread":
                    difference = bread_fill-intQuantity;
                    bread_restock=difference;
                    break;
                  case "Potato Salad":
                    difference = potato_salad_fill-intQuantity;
                    potato_salad_restock=difference;
                    break;
                  case "Liquid margarine":
                    difference = margarine_fill-intQuantity;
                    margarine_restock=difference;
                    break;
                  case "Garlic powder":
                    difference = garlic_fill-intQuantity;
                    garlic_restock=difference;
                    break;
                  case "Ranch":
                    difference = ranch_fill-intQuantity;
                    ranch_restock=difference;
                    break;
                  case "Ketchup-Lg container":
                    difference = ketchup_container_fill-intQuantity;
                    ketchup_container_restock=difference;
                    break;
                  case "Ketchup-packets":
                    difference = ketchup_packet_fill-intQuantity;
                    ketchup_packet_restock=difference;
                    break;
                  case "Mayo":
                    difference = mayo_fill-intQuantity;
                    mayo_restock=difference;
                    break;
                  case "Tea bags":
                    difference = tea_bags_fill-intQuantity;
                    tea_bags_restock=difference;
                    break;
                  case "Sugar for tea":
                    difference = sugar_fill-intQuantity;
                    sugar_restock=difference;
                    break;
                  case "Worcestershire Sauce":
                    difference = worchester_fill-intQuantity;
                    worchester_restock=difference;
                    break;
                  case "Sliced cheese":
                    difference = cheese_fill-intQuantity;
                    cheese_restock=difference;
                    break;
                  case "Bacon slices":
                    difference = bacon_fill-intQuantity;
                    bacon_restock=difference;
                    break;
                  case "Fryer oil":
                    difference = oil_fill-intQuantity;
                    oil_restock=difference;
                    break;
                  //Bib
                  case "Jones-Dr Jones ":
                    difference = dr_jones_fill-intQuantity;
                    dr_jones_restock=difference;
                    break;
                  case "Jones-Orange&Cream":
                    difference = jones_orangecream_fill-intQuantity;
                    jones_orangecream_restock=difference;
                    break;
                  case "Jones-Root beer":
                    difference = jones_rootbeer_fill-intQuantity;
                    jones_rootbeer_restock=difference;
                    break;
                  case "Jones-Cola":
                    difference = jones_cola_fill-intQuantity;
                    jones_cola_restock=difference;
                    break;
                  case "Jones-Lemon Lime":
                    difference = jones_lemon_fill-intQuantity;
                    jones_lemon_restock=difference;
                    break;
                  case "Jones-Sugar Free Cola":
                    difference = jones_sugarfree_fill-intQuantity;
                    jones_sugarfree_restock=difference;
                    break;
                  //Bottles
                  case "Bottled Root Beer":
                    difference = bottled_rootbeer_fill-intQuantity;
                    bottled_rootbeer_restock=difference;
                    break;
                  case "Bottled Cream Soda":
                    difference = bottled_cream_fill-intQuantity;
                    bottled_cream_restock=difference;
                    break;
                  case "Bottled Orange & Cream":
                    difference = bottled_orangecream_fill-intQuantity;
                    bottled_orangecream_restock=difference;
                    break;
                  case "Bottled Berry Lemonade":
                    difference = bottled_berry_fill-intQuantity;
                    bottled_berry_restock=difference;
                    break;
                  //serving
                  case "Straw":
                    difference = straw_fill-intQuantity;
                    break;
                  case "Drink lid":
                    difference = drink_lid_fill-intQuantity;
                    break;
                  case "10oz Cup":
                    difference = ten_oz_fill-intQuantity;
                    break;
                  case "16 oz Cup":
                    difference = sixteen_oz_fill-intQuantity;
                    break;
                  case "Napkin":
                    difference = napkin_fill-intQuantity;
                    break;
                  case "Cutlery pack":
                    difference = cutlery_fill-intQuantity;
                    break;
                  case "Basket liners":
                    difference = basket_fill-intQuantity;
                    break;
                  case "2oz clear cup":
                    difference = two_clearcup_fill-intQuantity;
                    break;
                  case "2oz lid":
                    difference = two_lid_fill-intQuantity;
                    break;
                  case "5.5 oz clear cup":
                    difference = five_clearcup_fill-intQuantity;
                    break;
                  case "5.5 oz lid":
                    difference = five_lid_fill-intQuantity;
                    break;
                  case "Hinged lid box sm":
                    difference = hinge_small_fill-intQuantity;
                    break;
                  case "Hinged lid box lg":
                    difference = hinge_large_fill-intQuantity;
                    break;
                  //janitorial
                  case "Hand soap":
                    difference = hand_soap_fill-intQuantity;
                    break;
                  case "Hand sanitizer":
                    difference = hand_sanitizer_fill-intQuantity;
                    break;
                  case "Dish soap":
                    difference = dish_soap_fill-intQuantity;
                    break;
                  case "Paper towels":
                    difference = paper_towels_fill-intQuantity;
                    break;
                  case "gloves":
                    difference = gloves_fill-intQuantity;
                    break;
                  case "trash can liners-small":
                    difference = trash_small_fill-intQuantity;
                    break;
                  case "trash can liners-lg":
                    difference = trash_large_fill-intQuantity;
                    break;
                  case "toilet paper":
                    difference = toilet_paper_fill-intQuantity;
                    break;
                  case "Surface cleaner":
                    difference = surface_fill-intQuantity;
                    break;
                  case "Floor cleaner": 
                    difference = floor_fill-intQuantity;
                    break;
                  default:
                    difference = 0;
                } 
                if(difference != 0){
                  name += it_name + ", ";
                  name += "" + difference + "\n";
                }
                
              }
            } catch (Exception e998){
              e998.printStackTrace();
              JOptionPane.showMessageDialog(null,"Error accessing Database.");
            }
            try {
              conn.close();
              //JOptionPane.showMessageDialog(null,"Connection Closed.");
            } catch(Exception e997) {
              JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
            }
            JTextArea area = new JTextArea(name);
            area.setBounds(10,30,350,350);
            A.add(area);

             //scroll area
            JScrollPane scroll = new JScrollPane(area,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            scroll.setBounds(10,30,600,600);
            A.add(scroll);
            int res = JOptionPane.showOptionDialog(new JFrame(), "Restock?", "restock",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
            new Object[] { "Yes", "No" }, JOptionPane.YES_OPTION);
            if (res == JOptionPane.YES_OPTION) {
              try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315909_9db",
                   "csce315909_9user", "password");
              } catch (Exception e6) {
                e6.printStackTrace();
                System.err.println(e6.getClass().getName()+": "+e6.getMessage());
                System.exit(0);
              }
              //JOptionPane.showMessageDialog(null,"Opened database successfully");
              name = "";
              try{
                //create a statement object
                Statement stmt = conn.createStatement();
                //create an SQL statement
                //TODO Step 2
                sqlStatement = "UPDATE inventory SET item_quantity = item_quantity +"+chicken_breast_restock+"WHERE item_id = 1001; ";
                sqlStatement+="UPDATE inventory SET item_quantity = item_quantity +"+flour_restock+"WHERE item_id = 2001; ";
                sqlStatement+="UPDATE inventory SET item_quantity = item_quantity +"+salt_restock+"WHERE item_id = 2002; ";
                sqlStatement+="UPDATE inventory SET item_quantity = item_quantity +"+pepper_restock+"WHERE item_id = 2003; ";
                sqlStatement+="UPDATE inventory SET item_quantity = item_quantity +"+fries_restock+"WHERE item_id = 3002; ";
                sqlStatement+="UPDATE inventory SET item_quantity = item_quantity +"+bread_restock+"WHERE item_id = 2004; ";
                sqlStatement+="UPDATE inventory SET item_quantity = item_quantity +"+potato_salad_restock+"WHERE item_id = 1002; ";
                sqlStatement+="UPDATE inventory SET item_quantity = item_quantity +"+margarine_restock+"WHERE item_id = 1003; ";
                sqlStatement+="UPDATE inventory SET item_quantity = item_quantity +"+garlic_restock+"WHERE item_id = 2005; ";
                sqlStatement+="UPDATE inventory SET item_quantity = item_quantity +"+ranch_restock+"WHERE item_id = 1004; ";
                sqlStatement+="UPDATE inventory SET item_quantity = item_quantity +"+ketchup_container_restock+"WHERE item_id = 2006; ";
                sqlStatement+="UPDATE inventory SET item_quantity = item_quantity +"+ketchup_packet_restock+"WHERE item_id = 2007; ";
                sqlStatement+="UPDATE inventory SET item_quantity = item_quantity +"+mayo_restock+"WHERE item_id = 2008; ";
                sqlStatement+="UPDATE inventory SET item_quantity = item_quantity +"+tea_bags_restock+"WHERE item_id = 2009; ";
                sqlStatement+="UPDATE inventory SET item_quantity = item_quantity +"+sugar_restock+"WHERE item_id = 2010; ";

                sqlStatement+="UPDATE inventory SET item_quantity = item_quantity +"+worchester_restock+"WHERE item_id = 2011; ";
                sqlStatement+="UPDATE inventory SET item_quantity = item_quantity +"+cheese_restock+"WHERE item_id = 1005; ";
                sqlStatement+="UPDATE inventory SET item_quantity = item_quantity +"+bacon_restock+"WHERE item_id = 1006; ";
                sqlStatement+="UPDATE inventory SET item_quantity = item_quantity +"+oil_restock+"WHERE item_id = 2012; ";


                sqlStatement+="UPDATE inventory SET item_quantity = item_quantity +"+dr_jones_restock+"WHERE item_id = 2013; ";
                sqlStatement+="UPDATE inventory SET item_quantity = item_quantity +"+jones_orangecream_restock+"WHERE item_id = 2014; ";
                sqlStatement+="UPDATE inventory SET item_quantity = item_quantity +"+jones_rootbeer_restock+"WHERE item_id = 2015; ";
                sqlStatement+="UPDATE inventory SET item_quantity = item_quantity +"+jones_cola_restock+"WHERE item_id = 2016; ";
                sqlStatement+="UPDATE inventory SET item_quantity = item_quantity +"+jones_lemon_restock+"WHERE item_id = 2017; ";
                sqlStatement+="UPDATE inventory SET item_quantity = item_quantity +"+jones_sugarfree_restock+"WHERE item_id = 2018; ";

                sqlStatement+="UPDATE inventory SET item_quantity = item_quantity +"+bottled_rootbeer_restock+"WHERE item_id = 2019; ";
                sqlStatement+="UPDATE inventory SET item_quantity = item_quantity +"+bottled_cream_restock+"WHERE item_id = 2020; ";
                sqlStatement+="UPDATE inventory SET item_quantity = item_quantity +"+bottled_orangecream_restock+"WHERE item_id = 2021; ";
                sqlStatement+="UPDATE inventory SET item_quantity = item_quantity +"+bottled_berry_restock+"WHERE item_id = 2022;";
                //send statement to DBMS
                stmt.executeUpdate(sqlStatement);
                
              } catch (Exception e7){
                e7.printStackTrace();
               // JOptionPane.showMessageDialog(null,"Error accessing Database.");
              }
              try {
                conn.close();
                //JOptionPane.showMessageDialog(null,"Connection Closed.");
              } catch(Exception e7) {
                //JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
              }
    
            }
        }
        else if(s.equals("Ordering Popularity")){
          int g =-1;
          int g1 = -1;
          String day1 ="";
          String day2 ="";
          while(g<0){
            day1 = JOptionPane.showInputDialog("Please enter the first day: ");
            if(day1.length() > 0){
              g++;
            }
          }
          while(g1<0){
            day2 = JOptionPane.showInputDialog("Please enter the last day: ");
            if(day2.length() > 0){
              g1++;
            }
          }
          //sql command
          //SELECT SUM(order_quantity), order_type FROM orders WHERE day_id >= day1 AND day_id < day2 GROUP BY order_type ORDER BY SUM(order_quantity) DESC;
          name = "";
            try {
              Class.forName("org.postgresql.Driver");
              conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315909_9db",
                 "csce315909_9user", "password");
            } catch (Exception e999) {
              e999.printStackTrace();
              System.err.println(e999.getClass().getName()+": "+e999.getMessage());
              System.exit(0);
            }
            //JOptionPane.showMessageDialog(null,"Opened database successfully");
      
            
            try{
              //create a statement object
              Statement stmt = conn.createStatement();
              //create an SQL statement
              //TODO Step 2
              sqlStatement = "SELECT SUM(order_quantity) AS \"sumquant\", order_type FROM orders WHERE day_id >= " + day1 + " AND day_id < " + day2 + " GROUP BY order_type ORDER BY SUM(order_quantity) DESC;";
              //send statement to DBMS
              ResultSet result = stmt.executeQuery(sqlStatement);
              name +="SUM(order_quantity), order_name" + "\n";
              name +="-------------------------------------------------------------------------------------" + "\n";
              //name += result;
              while (result.next()) {
                name += result.getString("sumquant") + ", ";
                String oType = result.getString("order_type");
                //name+= oType + ", "; //+ "\n";
                String menu_name ="";
                switch(oType){
                  case "501": 
                    menu_name = "5 finger original";
                    break;
                  case "502":
                    menu_name = "4 finger meal";
                    break;
                  case "503":
                    menu_name = "three finger meal";
                    break;
                  case "504":
                    menu_name = "kids meal";
                    break;
                  case "505": 
                    menu_name = "gallon of tea";
                    break;
                  case "506":
                    menu_name = "family pack";
                    break;
                  case "507":
                    menu_name = "Club Sandwich meal";
                    break;
                  case "508":
                    menu_name = "Club Sandwich only";
                    break;
                  case "509": 
                    menu_name = "Sandwich meal combo";
                    break;
                  case "510":
                    menu_name = "sandwich only";
                    break;
                  case "511":
                    menu_name = "Grill cheese meal combo";
                    break;
                  case "512":
                    menu_name = "grill cheese sandwich only";
                    break;
                  case "513": 
                    menu_name = "Laynes sauce";
                    break;
                  case "514":
                    menu_name = "Chicken finger";
                    break;
                  case "515":
                    menu_name = "texas toast";
                    break;
                  case "516":
                    menu_name = "potato Salad";
                    break;
                  case "517": 
                    menu_name = "Crinke cut fries";
                    break;
                  case "518":
                    menu_name = "Fountain Drink";
                    break;
                  case "519":
                    menu_name = "Bottle Drink";
                    break;
                  case "520":
                    menu_name = "impossible chicken fingers";
                    break;
                }
                name += menu_name + "\n";
                //name += result.getString("sumquant") + "\n";
              }
              
            } catch (Exception e998){
              e998.printStackTrace();
              //JOptionPane.showMessageDialog(null,"Error accessing Database.");
            }
            try {
              conn.close();
              //JOptionPane.showMessageDialog(null,"Connection Closed.");
            } catch(Exception e997) {
              //JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
            }
            JTextArea area = new JTextArea(name);
            area.setBounds(10,30,350,350);
            A.add(area);

             //scroll area
            JScrollPane scroll = new JScrollPane(area,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            scroll.setBounds(10,30,600,600);
            A.add(scroll);
        }
        else if(s.equals("Inventory")){
            f.setVisible(false);
            O.setVisible(false);
            S.setVisible(false);
            M.setVisible(false);
            A.setVisible(false);
            I.setVisible(true);
            name = "";
            try {
              Class.forName("org.postgresql.Driver");
              conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315909_9db",
                 "csce315909_9user", "password");
            } catch (Exception e999) {
              e999.printStackTrace();
              System.err.println(e999.getClass().getName()+": "+e999.getMessage());
              System.exit(0);
            }
            JOptionPane.showMessageDialog(null,"Opened database successfully");
      
            
            try{
              //create a statement object
              Statement stmt = conn.createStatement();
              //create an SQL statement
              //TODO Step 2
              sqlStatement = "SELECT * FROM inventory;";
              //send statement to DBMS
              ResultSet result = stmt.executeQuery(sqlStatement);
              name +="item_id, item_name, item_quantity, expiration_date, price, store_id" + "\n";
              name +="-------------------------------------------------------------------------------------" + "\n";
              while (result.next()) {
                name += result.getString("item_id") + ", "+ result.getString("item_name") + ", " + result.getString("item_quantity") + ", " + result.getString("expiration_date") + ", " + result.getString("price") + ", " + result.getString("store_id") + "\n";
              }
            } catch (Exception e998){
              e998.printStackTrace();
              JOptionPane.showMessageDialog(null,"Error accessing Database.");
            }
            try {
              conn.close();
              //JOptionPane.showMessageDialog(null,"Connection Closed.");
            } catch(Exception e997) {
              JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
            }
            JTextArea area = new JTextArea(name);
            area.setBounds(10,30,350,350);
            I.add(area);

             //scroll area
            JScrollPane scroll = new JScrollPane(area,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            scroll.setBounds(10,30,600,600);
            I.add(scroll);
        }
        else if(s.equals("Sort by item_id ASC")){
          try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315909_9db",
               "csce315909_9user", "password");
          } catch (Exception e1) {
            e1.printStackTrace();
            System.err.println(e1.getClass().getName()+": "+e1.getMessage());
            System.exit(0);
          }
          //JOptionPane.showMessageDialog(null,"Opened database successfully");
          name = "";
          try{
            //create a statement object
            Statement stmt = conn.createStatement();
            //create an SQL statement
            //TODO Step 2
            sqlStatement = "SELECT * FROM inventory ORDER BY item_id ASC;";
            //send statement to DBMS
            ResultSet result = stmt.executeQuery(sqlStatement);
            name +="item_id, item_name, item_quantity, expiration_date, price, store_id" + "\n";
            name +="-------------------------------------------------------------------------------------" + "\n";
            while (result.next()) {
              name += result.getString("item_id") + ", "+ result.getString("item_name") + ", " + result.getString("item_quantity") + ", " + result.getString("expiration_date") + ", " + result.getString("price") + ", " + result.getString("store_id") + "\n";
            }
          } catch (Exception e2){
            e2.printStackTrace();
           // JOptionPane.showMessageDialog(null,"Error accessing Database.");
          }
          try {
            conn.close();
            //JOptionPane.showMessageDialog(null,"Connection Closed.");
          } catch(Exception e3) {
            //JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
          }
          //I.remove(area);
          JTextArea area = new JTextArea(name);
          area.setBounds(10,30,350,350);
          I.add(area);
          JScrollPane scroll = new JScrollPane(area,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
          scroll.setBounds(10,30,600,600);
          I.add(scroll);

        }
        else if(s.equals("Sort by item_name ASC")){
          try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315909_9db",
               "csce315909_9user", "password");
          } catch (Exception e4) {
            e4.printStackTrace();
            System.err.println(e4.getClass().getName()+": "+e4.getMessage());
            System.exit(0);
          }
          //JOptionPane.showMessageDialog(null,"Opened database successfully");
          name = "";
          try{
            //create a statement object
            Statement stmt = conn.createStatement();
            //create an SQL statement
            //TODO Step 2
            sqlStatement = "SELECT * FROM inventory ORDER BY item_name ASC;";
            //send statement to DBMS
            ResultSet result = stmt.executeQuery(sqlStatement);
            name +="item_id, item_name, item_quantity, expiration_date, price, store_id" + "\n";
            name +="-------------------------------------------------------------------------------------" + "\n";
            while (result.next()) {
              name += result.getString("item_id") + ", "+ result.getString("item_name") + ", " + result.getString("item_quantity") + ", " + result.getString("expiration_date") + ", " + result.getString("price") + ", " + result.getString("store_id") + "\n";
            }
          } catch (Exception e5){
            e5.printStackTrace();
           // JOptionPane.showMessageDialog(null,"Error accessing Database.");
          }
          try {
            conn.close();
            //JOptionPane.showMessageDialog(null,"Connection Closed.");
          } catch(Exception e5) {
            //JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
          }
          //I.remove(area);
          JTextArea area = new JTextArea(name);
          area.setBounds(10,30,350,350);
          I.add(area);
          JScrollPane scroll = new JScrollPane(area,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
          scroll.setBounds(10,30,600,600);
          I.add(scroll);

        }
        else if(s.equals("Sort by quantity ASC")){
          try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315909_9db",
               "csce315909_9user", "password");
          } catch (Exception e6) {
            e6.printStackTrace();
            System.err.println(e6.getClass().getName()+": "+e6.getMessage());
            System.exit(0);
          }
          //JOptionPane.showMessageDialog(null,"Opened database successfully");
          name = "";
          try{
            //create a statement object
            Statement stmt = conn.createStatement();
            //create an SQL statement
            //TODO Step 2
            sqlStatement = "SELECT * FROM inventory ORDER BY item_quantity ASC;";
            //send statement to DBMS
            ResultSet result = stmt.executeQuery(sqlStatement);
            name +="item_id, item_name, item_quantity, expiration_date, price, store_id" + "\n";
            name +="-------------------------------------------------------------------------------------" + "\n";
            while (result.next()) {
              name += result.getString("item_id") + ", "+ result.getString("item_name") + ", " + result.getString("item_quantity") + ", " + result.getString("expiration_date") + ", " + result.getString("price") + ", " + result.getString("store_id") + "\n";
            }
          } catch (Exception e7){
            e7.printStackTrace();
           // JOptionPane.showMessageDialog(null,"Error accessing Database.");
          }
          try {
            conn.close();
            //JOptionPane.showMessageDialog(null,"Connection Closed.");
          } catch(Exception e7) {
            //JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
          }
          //I.remove(area);
          JTextArea area = new JTextArea(name);
          area.setBounds(10,30,350,350);
          I.add(area);
          JScrollPane scroll = new JScrollPane(area,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
          scroll.setBounds(10,30,600,600);
          I.add(scroll);

        }
        else if(s.equals("EDIT item_quantity")){
          int g =-1;
          int g1 = -1;
          String inputID ="";
          String newQuantity ="";
          while(g<0){
            inputID = JOptionPane.showInputDialog("Please enter the item_id to change the quantity of: ");
            if(inputID.length() > 0){
              g++;
            }
          }
          while(g1<0){
            newQuantity = JOptionPane.showInputDialog("Please enter the new quantity: ");
            if(newQuantity.length() > 0){
              g1++;
            }
          }
          try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315909_9db",
               "csce315909_9user", "password");
          } catch (Exception e8) {
            e8.printStackTrace();
            System.err.println(e8.getClass().getName()+": "+e8.getMessage());
            System.exit(0);
          }
          //JOptionPane.showMessageDialog(null,"Opened database successfully");
          name = "";
          try{
            //create a statement object
            Statement stmt = conn.createStatement();
            //create an SQL statement
            //TODO Step 2
            sqlStatement = "UPDATE inventory SET item_quantity = " + newQuantity + " WHERE item_id = " + inputID + ";";
              //send statement to DBMS
              stmt.executeUpdate(sqlStatement);
            sqlStatement = "SELECT * FROM inventory;";
            //send statement to DBMS
            ResultSet result = stmt.executeQuery(sqlStatement);
            name +="item_id, item_name, item_quantity, expiration_date, price, store_id" + "\n";
            name +="-------------------------------------------------------------------------------------" + "\n";
            while (result.next()) {
              name += result.getString("item_id") + ", "+ result.getString("item_name") + ", " + result.getString("item_quantity") + ", " + result.getString("expiration_date") + ", " + result.getString("price") + ", " + result.getString("store_id") + "\n";
            }
          } catch (Exception e9){
            e9.printStackTrace();
           // JOptionPane.showMessageDialog(null,"Error accessing Database.");
          }
          try {
            conn.close();
            //JOptionPane.showMessageDialog(null,"Connection Closed.");
          } catch(Exception e10) {
            //JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
          }
          //I.remove(area);
          JTextArea area = new JTextArea(name);
          area.setBounds(10,30,350,350);
          I.add(area);
          JScrollPane scroll = new JScrollPane(area,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
          scroll.setBounds(10,30,600,600);
          I.add(scroll);

        }
        else if(s.equals("REMOVE item")){
          int g =-1;
          String inputID ="";
          while(g<0){
            inputID = JOptionPane.showInputDialog("Please enter item_id to remove: ");
            if(inputID.length() > 0){
              g++;
            }
          }
          try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315909_9db",
               "csce315909_9user", "password");
          } catch (Exception e11) {
            e11.printStackTrace();
            System.err.println(e11.getClass().getName()+": "+e11.getMessage());
            System.exit(0);
          }
          //JOptionPane.showMessageDialog(null,"Opened database successfully");
          name = "";
          try{
            //create a statement object
            Statement stmt = conn.createStatement();
            //create an SQL statement
            //TODO Step 2
            sqlStatement = "DELETE FROM inventory WHERE item_id = " + inputID + ";";
              //send statement to DBMS
              stmt.executeUpdate(sqlStatement);
            sqlStatement = "SELECT * FROM inventory;";
            //send statement to DBMS
            ResultSet result = stmt.executeQuery(sqlStatement);
            name +="item_id, item_name, item_quantity, expiration_date, price, store_id" + "\n";
            name +="-------------------------------------------------------------------------------------" + "\n";
            while (result.next()) {
              name += result.getString("item_id") + ", "+ result.getString("item_name") + ", " + result.getString("item_quantity") + ", " + result.getString("expiration_date") + ", " + result.getString("price") + ", " + result.getString("store_id") + "\n";
            }
          } catch (Exception e12){
            e12.printStackTrace();
           // JOptionPane.showMessageDialog(null,"Error accessing Database.");
          }
          try {
            conn.close();
            //JOptionPane.showMessageDialog(null,"Connection Closed.");
          } catch(Exception e13) {
            //JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
          }
          //I.remove(area);
          JTextArea area = new JTextArea(name);
          area.setBounds(10,30,350,350);
          I.add(area);
          JScrollPane scroll = new JScrollPane(area,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
          scroll.setBounds(10,30,600,600);
          I.add(scroll);
          
        }
        else if(s.equals("Sort by item_id DESC")){ //
          try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315909_9db",
               "csce315909_9user", "password");
          } catch (Exception e100) { //
            e100.printStackTrace();
            System.err.println(e100.getClass().getName()+": "+e100.getMessage());
            System.exit(0);
          }
          //JOptionPane.showMessageDialog(null,"Opened database successfully");
          name = "";
          try{
            //create a statement object
            Statement stmt = conn.createStatement();
            //create an SQL statement
            //TODO Step 2
            sqlStatement = "SELECT * FROM inventory ORDER BY item_id DESC;"; //
            //send statement to DBMS
            ResultSet result = stmt.executeQuery(sqlStatement);
            name +="item_id, item_name, item_quantity, expiration_date, price, store_id" + "\n";
            name +="-------------------------------------------------------------------------------------" + "\n";
            while (result.next()) {
              name += result.getString("item_id") + ", "+ result.getString("item_name") + ", " + result.getString("item_quantity") + ", " + result.getString("expiration_date") + ", " + result.getString("price") + ", " + result.getString("store_id") + "\n";
            }
          } catch (Exception e101){ //
            e101.printStackTrace();
           // JOptionPane.showMessageDialog(null,"Error accessing Database.");
          }
          try {
            conn.close();
            //JOptionPane.showMessageDialog(null,"Connection Closed.");
          } catch(Exception e102) { //
            //JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
          }
          //I.remove(area);
          JTextArea area = new JTextArea(name);
          area.setBounds(10,30,350,350);
          I.add(area);
          JScrollPane scroll = new JScrollPane(area,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
          scroll.setBounds(10,30,600,600);
          I.add(scroll);

        }
        else if(s.equals("Sort by item_name DESC")){ //
          try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315909_9db",
               "csce315909_9user", "password");
          } catch (Exception e103) { //
            e103.printStackTrace();
            System.err.println(e103.getClass().getName()+": "+e103.getMessage());
            System.exit(0);
          }
          //JOptionPane.showMessageDialog(null,"Opened database successfully");
          name = "";
          try{
            //create a statement object
            Statement stmt = conn.createStatement();
            //create an SQL statement
            //TODO Step 2
            sqlStatement = "SELECT * FROM inventory ORDER BY item_name DESC;"; //
            //send statement to DBMS
            ResultSet result = stmt.executeQuery(sqlStatement);
            name +="item_id, item_name, item_quantity, expiration_date, price, store_id" + "\n";
            name +="-------------------------------------------------------------------------------------" + "\n";
            while (result.next()) {
              name += result.getString("item_id") + ", "+ result.getString("item_name") + ", " + result.getString("item_quantity") + ", " + result.getString("expiration_date") + ", " + result.getString("price") + ", " + result.getString("store_id") + "\n";
            }
          } catch (Exception e104){ //
            e104.printStackTrace();
           // JOptionPane.showMessageDialog(null,"Error accessing Database.");
          }
          try {
            conn.close();
            //JOptionPane.showMessageDialog(null,"Connection Closed.");
          } catch(Exception e105) { //
            //JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
          }
          //I.remove(area);
          JTextArea area = new JTextArea(name);
          area.setBounds(10,30,350,350);
          I.add(area);
          JScrollPane scroll = new JScrollPane(area,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
          scroll.setBounds(10,30,600,600);
          I.add(scroll);

        }

        else if(s.equals("Sort by quantity DESC")){ //
          try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315909_9db",
               "csce315909_9user", "password");
          } catch (Exception e106) { //
            e106.printStackTrace();
            System.err.println(e106.getClass().getName()+": "+e106.getMessage());
            System.exit(0);
          }
          //JOptionPane.showMessageDialog(null,"Opened database successfully");
          name = "";
          try{
            //create a statement object
            Statement stmt = conn.createStatement();
            //create an SQL statement
            //TODO Step 2
            sqlStatement = "SELECT * FROM inventory ORDER BY item_quantity DESC;"; //
            //send statement to DBMS
            ResultSet result = stmt.executeQuery(sqlStatement);
            name +="item_id, item_name, item_quantity, expiration_date, price, store_id" + "\n";
            name +="-------------------------------------------------------------------------------------" + "\n";
            while (result.next()) {
              name += result.getString("item_id") + ", "+ result.getString("item_name") + ", " + result.getString("item_quantity") + ", " + result.getString("expiration_date") + ", " + result.getString("price") + ", " + result.getString("store_id") + "\n";
            }
          } catch (Exception e107){ //
            e107.printStackTrace();
           // JOptionPane.showMessageDialog(null,"Error accessing Database.");
          }
          try {
            conn.close();
            //JOptionPane.showMessageDialog(null,"Connection Closed.");
          } catch(Exception e108) { //
            //JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
          }
          //I.remove(area);
          JTextArea area = new JTextArea(name);
          area.setBounds(10,30,350,350);
          I.add(area);
          JScrollPane scroll = new JScrollPane(area,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
          scroll.setBounds(10,30,600,600);
          I.add(scroll);

        }

        else if(s.equals("ADD new item")){ //
          int g =-1;
          int g1 = -1;
          int g2 = -1;
          int g3 = -1;
          int g4 = -1;
          String inputID ="";
          String newPrice ="";
          String newName ="";
          String newQuantity ="";
          String newExpDate ="";
          while(g<0){
            inputID = JOptionPane.showInputDialog("Please enter the item_id of new item: ");
            if(inputID.length() > 0){
              g++;
            }
          }
          while(g2<0){
            newName = JOptionPane.showInputDialog("Please enter the name of new item: ");
            if(newName.length() > 0){
              g2++;
            }
          }
          while(g1<0){
            newQuantity = JOptionPane.showInputDialog("Please enter the item_quantity: ");
            if(newQuantity.length() > 0){
              g1++;
            }
          }
          while(g3<0){
            newExpDate = JOptionPane.showInputDialog("Please enter the expiration date: ");
            if(newExpDate.length() > 0){
              g3++;
            }
          }
          while(g4<0){
            newPrice = JOptionPane.showInputDialog("Please enter the price: ");
            if(newPrice.length() > 0){
              g4++;
            }
          }
          try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315909_9db",
               "csce315909_9user", "password");
          } catch (Exception e109) { //
            e109.printStackTrace();
            System.err.println(e109.getClass().getName()+": "+e109.getMessage());
            System.exit(0);
          }
          //JOptionPane.showMessageDialog(null,"Opened database successfully");
          name = "";
          try{
            //create a statement object
            Statement stmt = conn.createStatement();
            //create an SQL statement
            //TODO Step 2
            sqlStatement = "INSERT INTO inventory VALUES (" + inputID + ", \'" + newName +"\', " + newQuantity + ", \'" + newExpDate +"\', " + newPrice + ", " + 0 + ");";
              //send statement to DBMS
            stmt.executeUpdate(sqlStatement);
            sqlStatement = "SELECT * FROM inventory;"; //
            //send statement to DBMS
            ResultSet result = stmt.executeQuery(sqlStatement);
            name +="item_id, item_name, item_quantity, expiration_date, price, store_id" + "\n";
            name +="-------------------------------------------------------------------------------------" + "\n";
            while (result.next()) {
              name += result.getString("item_id") + ", "+ result.getString("item_name") + ", " + result.getString("item_quantity") + ", " + result.getString("expiration_date") + ", " + result.getString("price") + ", " + result.getString("store_id") + "\n";
            }
          } catch (Exception e110){ //
            e110.printStackTrace();
           // JOptionPane.showMessageDialog(null,"Error accessing Database.");
          }
          try {
            conn.close();
            //JOptionPane.showMessageDialog(null,"Connection Closed.");
          } catch(Exception e111) { //
            //JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
          }
          //I.remove(area);
          JTextArea area = new JTextArea(name);
          area.setBounds(10,30,350,350);
          I.add(area);
          JScrollPane scroll = new JScrollPane(area,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
          scroll.setBounds(10,30,600,600);
          I.add(scroll);

        }

        else if(s.equals("Sort by price DESC")){ //
          try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315909_9db",
               "csce315909_9user", "password");
          } catch (Exception e112) { //
            e112.printStackTrace();
            System.err.println(e112.getClass().getName()+": "+e112.getMessage());
            System.exit(0);
          }
          //JOptionPane.showMessageDialog(null,"Opened database successfully");
          name = "";
          try{
            //create a statement object
            Statement stmt = conn.createStatement();
            //create an SQL statement
            //TODO Step 2
            sqlStatement = "SELECT * FROM inventory ORDER BY price DESC;"; //
            //send statement to DBMS
            ResultSet result = stmt.executeQuery(sqlStatement);
            name +="item_id, item_name, item_quantity, expiration_date, price, store_id" + "\n";
            name +="-------------------------------------------------------------------------------------" + "\n";
            while (result.next()) {
              name += result.getString("item_id") + ", "+ result.getString("item_name") + ", " + result.getString("item_quantity") + ", " + result.getString("expiration_date") + ", " + result.getString("price") + ", " + result.getString("store_id") + "\n";
            }
          } catch (Exception e113){ //
            e113.printStackTrace();
           // JOptionPane.showMessageDialog(null,"Error accessing Database.");
          }
          try {
            conn.close();
            //JOptionPane.showMessageDialog(null,"Connection Closed.");
          } catch(Exception e114) { //
            //JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
          }
          //I.remove(area);
          JTextArea area = new JTextArea(name);
          area.setBounds(10,30,350,350);
          I.add(area);
          JScrollPane scroll = new JScrollPane(area,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
          scroll.setBounds(10,30,600,600);
          I.add(scroll);

        }
        else if(s.equals("Sales")){
            f.setVisible(false);
            O.setVisible(false);
            I.setVisible(false);
            M.setVisible(false);
            A.setVisible(false);
            S.setVisible(true);
            name = "";
            try {
              Class.forName("org.postgresql.Driver");
              conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315909_9db",
                 "csce315909_9user", "password");
            } catch (Exception e999) {
              e999.printStackTrace();
              System.err.println(e999.getClass().getName()+": "+e999.getMessage());
              System.exit(0);
            }
            JOptionPane.showMessageDialog(null,"Opened database successfully");
      
            
            try{
              //create a statement object
              Statement stmt = conn.createStatement();
              //create an SQL statement
              //TODO Step 2
              sqlStatement = "SELECT * FROM sales;";
              //send statement to DBMS
              ResultSet result = stmt.executeQuery(sqlStatement);
              name +="day_id, total_sales, store_id" + "\n";
              name +="-------------------------------------------------------------------------------------" + "\n";
              while (result.next()) {
                name += result.getString("day_id") + ", "+ result.getString("total_sales") + ", " + result.getString("store_id") + "\n";
              }
            } catch (Exception e998){
              e998.printStackTrace();
              JOptionPane.showMessageDialog(null,"Error accessing Database.");
            }
            try {
              conn.close();
              //JOptionPane.showMessageDialog(null,"Connection Closed.");
            } catch(Exception e997) {
              JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
            }
            JTextArea area = new JTextArea(name);
            area.setBounds(10,30,350,350);
            S.add(area);

             //scroll area
            JScrollPane scroll = new JScrollPane(area,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            scroll.setBounds(10,30,600,600);
            S.add(scroll);
        }
        else if(s.equals("Manager")){
          login.setVisible(false);
          f.setVisible(true);
        }
        else if(s.equals("Server")){
          login.setVisible(false);
          ser.setVisible(true);
        }
        else if(s.equals("Close")){
          login.dispose();
        }
        else if(s.equals("Orders")){
          f.setVisible(false);
          I.setVisible(false);
          S.setVisible(false);
          M.setVisible(false);
          A.setVisible(false);
          O.setVisible(true);
          name = "";
          try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315909_9db",
               "csce315909_9user", "password");
          } catch (Exception e9999) {
            e9999.printStackTrace();
            System.err.println(e9999.getClass().getName()+": "+e9999.getMessage());
            System.exit(0);
          }
          JOptionPane.showMessageDialog(null,"Opened database successfully");
    
          
          try{
            //create a statement object
            Statement stmt = conn.createStatement();
            //create an SQL statement
            //TODO Step 2
            sqlStatement = "SELECT * FROM orders;";
            //send statement to DBMS
            ResultSet result = stmt.executeQuery(sqlStatement);
            name +="order_id, order_quantity, day_id, order_type" + "\n";
            name +="-------------------------------------------------------------------------------------" + "\n";
            while (result.next()) {
              name += result.getString("order_id") + ", "+ result.getString("order_quantity") + ", " + result.getString("day_id") + ", " + result.getString("order_type")  + "\n";
            }
          } catch (Exception e9989){
            e9989.printStackTrace();
            JOptionPane.showMessageDialog(null,"Error accessing Database.");
          }
          try {
            conn.close();
            //.");
          } catch(Exception e9979) {
            JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
          }
          JTextArea area1 = new JTextArea(name);
          area1.setBounds(10,30,350,350);
          O.add(area1);

           //scroll area
          JScrollPane scroll1 = new JScrollPane(area1,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
          scroll1.setBounds(10,30,800,600);
          O.add(scroll1);
        }
        else if(s.equals("Sort by total_sales ASC")){
          name = "";
            try {
              Class.forName("org.postgresql.Driver");
              conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315909_9db",
                 "csce315909_9user", "password");
            } catch (Exception e9990) {
              e9990.printStackTrace();
              System.err.println(e9990.getClass().getName()+": "+e9990.getMessage());
              System.exit(0);
            }
            JOptionPane.showMessageDialog(null,"Opened database successfully");
      
            
            try{
              //create a statement object
              Statement stmt = conn.createStatement();
              //create an SQL statement
              //TODO Step 2
              sqlStatement = "SELECT * FROM sales ORDER BY total_sales ASC;";
              //send statement to DBMS
              ResultSet result = stmt.executeQuery(sqlStatement);
              name +="day_id, total_sales, store_id" + "\n";
              name +="-------------------------------------------------------------------------------------" + "\n";
              while (result.next()) {
                name += result.getString("day_id") + ", "+ result.getString("total_sales") + ", " + result.getString("store_id") + "\n";
              }
            } catch (Exception e9980){
              e9980.printStackTrace();
              JOptionPane.showMessageDialog(null,"Error accessing Database.");
            }
            try {
              conn.close();
              //JOptionPane.showMessageDialog(null,"Connection Closed.");
            } catch(Exception e9970) {
              JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
            }
            JTextArea area = new JTextArea(name);
            area.setBounds(10,30,350,350);
            S.add(area);

             //scroll area
            JScrollPane scroll = new JScrollPane(area,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            scroll.setBounds(10,30,600,600);
            S.add(scroll);
        }
        else if(s.equals("Sort by total_sales DESC")){
          name = "";
            try {
              Class.forName("org.postgresql.Driver");
              conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315909_9db",
                 "csce315909_9user", "password");
            } catch (Exception e9991) {
              e9991.printStackTrace();
              System.err.println(e9991.getClass().getName()+": "+e9991.getMessage());
              System.exit(0);
            }
            JOptionPane.showMessageDialog(null,"Opened database successfully");
      
            
            try{
              //create a statement object
              Statement stmt = conn.createStatement();
              //create an SQL statement
              //TODO Step 2
              sqlStatement = "SELECT * FROM sales ORDER BY total_sales DESC;";
              //send statement to DBMS
              ResultSet result = stmt.executeQuery(sqlStatement);
              name +="day_id, total_sales, store_id" + "\n";
              name +="-------------------------------------------------------------------------------------" + "\n";
              while (result.next()) {
                name += result.getString("day_id") + ", "+ result.getString("total_sales") + ", " + result.getString("store_id") + "\n";
              }
            } catch (Exception e9981){
              e9981.printStackTrace();
              JOptionPane.showMessageDialog(null,"Error accessing Database.");
            }
            try {
              conn.close();
              //JOptionPane.showMessageDialog(null,"Connection Closed.");
            } catch(Exception e9971) {
              JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
            }
            JTextArea area = new JTextArea(name);
            area.setBounds(10,30,350,350);
            S.add(area);

             //scroll area
            JScrollPane scroll = new JScrollPane(area,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            scroll.setBounds(10,30,600,600);
            S.add(scroll);
        }
        else if(s.equals("Edit menu item price")){
          int g =-1;
          int g1 = -1;
          String inputID ="";
          String newPrice ="";
          while(g<0){
            inputID = JOptionPane.showInputDialog("Please enter the item_id to change the price of: ");
            if(inputID.length() > 0){
              g++;
            }
          }
          while(g1<0){
            newPrice = JOptionPane.showInputDialog("Please enter the new price: ");
            if(newPrice.length() > 0){
              g1++;
            }
          }
          name = "";
            try {
              Class.forName("org.postgresql.Driver");
              conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315909_9db",
                 "csce315909_9user", "password");
            } catch (Exception e9791) {
              e9791.printStackTrace();
              System.err.println(e9791.getClass().getName()+": "+e9791.getMessage());
              System.exit(0);
            }
            JOptionPane.showMessageDialog(null,"Opened database successfully");
      
            
            try{
              //create a statement object
              Statement stmt = conn.createStatement();
              //create an SQL statement
              //TODO Step 2
              sqlStatement = "UPDATE menu SET price = " + newPrice + " WHERE item_id = " + inputID + ";";
              //send statement to DBMS
              stmt.executeUpdate(sqlStatement);
              sqlStatement = "SELECT * FROM menu;";
              //send statement to DBMS
              ResultSet result = stmt.executeQuery(sqlStatement);
              name +="item_id, item_name, price" + "\n";
              name +="-------------------------------------------------------------------------------------" + "\n";
              while (result.next()) {
                name += result.getString("item_id") + ", "+ result.getString("name") + ", " + result.getString("price") + "\n";
              }
            } catch (Exception e9781){
              e9781.printStackTrace();
              JOptionPane.showMessageDialog(null,"Error accessing Database.");
            }
            try {
              conn.close();
              //JOptionPane.showMessageDialog(null,"Connection Closed.");
            } catch(Exception e9771) {
              JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
            }
            JTextArea area = new JTextArea(name);
            area.setBounds(10,30,350,350);
            M.add(area);

             //scroll area
            JScrollPane scroll = new JScrollPane(area,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            scroll.setBounds(10,30,600,600);
            M.add(scroll);
        }
        else if(s.equals("Add menu item")){
          int g =-1;
          int g1 = -1;
          int g2 = -1;
          String inputID ="";
          String newPrice ="";
          String newName ="";
          while(g<0){
            inputID = JOptionPane.showInputDialog("Please enter the item_id of new item: ");
            if(inputID.length() > 0){
              g++;
            }
          }
          while(g2<0){
            newName = JOptionPane.showInputDialog("Please enter the name of new item: ");
            if(newName.length() > 0){
              g2++;
            }
          }
          while(g1<0){
            newPrice = JOptionPane.showInputDialog("Please enter the price of new item: ");
            if(newPrice.length() > 0){
              g1++;
            }
          }
          name = "";
            try {
              Class.forName("org.postgresql.Driver");
              conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315909_9db",
                 "csce315909_9user", "password");
            } catch (Exception e9791) {
              e9791.printStackTrace();
              System.err.println(e9791.getClass().getName()+": "+e9791.getMessage());
              System.exit(0);
            }
            JOptionPane.showMessageDialog(null,"Opened database successfully");
      
            
            try{
              //create a statement object
              Statement stmt = conn.createStatement();
              //create an SQL statement
              //TODO Step 2
              //sqlStatement = "INSERT INTO menu VALUES ( " + inputID + ", " + "\'" + newName + "\'" + ", " + newPrice + ");";
              sqlStatement = "INSERT INTO menu VALUES (" + inputID + ", \'" + newName +"\', " + newPrice + ");";
              //send statement to DBMS
              stmt.executeUpdate(sqlStatement);
              sqlStatement = "SELECT * FROM menu;";
              //send statement to DBMS
              ResultSet result = stmt.executeQuery(sqlStatement);
              name +="item_id, item_name, price" + "\n";
              name +="-------------------------------------------------------------------------------------" + "\n";
              while (result.next()) {
                name += result.getString("item_id") + ", "+ result.getString("name") + ", " + result.getString("price") + "\n";
              }
            } catch (Exception e9781){
              e9781.printStackTrace();
              JOptionPane.showMessageDialog(null,"Error accessing Database.");
            }
            try {
              conn.close();
              //JOptionPane.showMessageDialog(null,"Connection Closed.");
            } catch(Exception e9771) {
              JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
            }
            JTextArea area = new JTextArea(name);
            area.setBounds(10,30,350,350);
            M.add(area);

             //scroll area
            JScrollPane scroll = new JScrollPane(area,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            scroll.setBounds(10,30,600,600);
            M.add(scroll);

            JButton tempButton = new JButton(newName);
            menu.add(tempButton);
            tempButton.addActionListener(GUI.s);
        }
        else if(s.equals("Remove menu item")){
          int g =-1;
          String inputID ="";
          while(g<0){
            inputID = JOptionPane.showInputDialog("Please enter the item_id of new item: ");
            if(inputID.length() > 0){
              g++;
            }
          }
          name = "";
            try {
              Class.forName("org.postgresql.Driver");
              conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315909_9db",
                 "csce315909_9user", "password");
            } catch (Exception e9791) {
              e9791.printStackTrace();
              System.err.println(e9791.getClass().getName()+": "+e9791.getMessage());
              System.exit(0);
            }
            JOptionPane.showMessageDialog(null,"Opened database successfully");
      
            
            try{
              //create a statement object
              Statement stmt = conn.createStatement();
              //create an SQL statement
              //TODO Step 2
              //sqlStatement = "INSERT INTO menu VALUES ( " + inputID + ", " + "\'" + newName + "\'" + ", " + newPrice + ");";
              sqlStatement = "DELETE FROM menu WHERE item_id = " + inputID + ";";
              //send statement to DBMS
              stmt.executeUpdate(sqlStatement);
              sqlStatement = "SELECT * FROM menu;";
              //send statement to DBMS
              ResultSet result = stmt.executeQuery(sqlStatement);
              name +="item_id, item_name, price" + "\n";
              name +="-------------------------------------------------------------------------------------" + "\n";
              while (result.next()) {
                name += result.getString("item_id") + ", "+ result.getString("name") + ", " + result.getString("price") + "\n";
              }
            } catch (Exception e9781){
              e9781.printStackTrace();
              JOptionPane.showMessageDialog(null,"Error accessing Database.");
            }
            try {
              conn.close();
              //JOptionPane.showMessageDialog(null,"Connection Closed.");
            } catch(Exception e9771) {
              JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
            }
            JTextArea area = new JTextArea(name);
            area.setBounds(10,30,350,350);
            M.add(area);

             //scroll area
            JScrollPane scroll = new JScrollPane(area,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            scroll.setBounds(10,30,600,600);
            M.add(scroll);
        }
        else if(s.equals("Log Out")){
          f.setVisible(false);
          login.setVisible(true);
        }
        else if(s.equals("Go back to main menu")){
          I.setVisible(false);
          O.setVisible(false);
          S.setVisible(false);
          M.setVisible(false);
          A.setVisible(false);
          f.setVisible(true);
        }
        else if(s.equals("Menu")){
          I.setVisible(false);
          O.setVisible(false);
          S.setVisible(false);
          f.setVisible(false);
          A.setVisible(false);
          M.setVisible(true);
          name = "";
          try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315909_9db",
                "csce315909_9user", "password");
          } catch (Exception e979) {
            e979.printStackTrace();
            System.err.println(e979.getClass().getName()+": "+e979.getMessage());
            System.exit(0);
          }
          JOptionPane.showMessageDialog(null,"Opened database successfully");
          try{
            //create a statement object
            Statement stmt = conn.createStatement();
            //create an SQL statement
            //TODO Step 2
            sqlStatement = "SELECT * FROM menu;";
            //send statement to DBMS
            ResultSet result = stmt.executeQuery(sqlStatement);
            name +="item_id, name, price" + "\n";
            name +="-------------------------------------------------------------------------------------" + "\n";
            while (result.next()) {
              name += result.getString("item_id") + ", "+ result.getString("name") + ", " + result.getString("price") + "\n";
            }
          } catch (Exception e978){
            e978.printStackTrace();
            //JOptionPane.showMessageDialog(null,"Error accessing Database.");
          }
          try {
            conn.close();
            //JOptionPane.showMessageDialog(null,"Connection Closed.");
          } catch(Exception e977) {
            //JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
          }
          JTextArea area = new JTextArea(name);
          area.setBounds(10,30,350,350);
          M.add(area);

          // //scroll area
          JScrollPane scroll = new JScrollPane(area,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
          scroll.setBounds(10,30,600,600);
          M.add(scroll);
        }
        else if(s.equals("Add Item")) {
          ser.setVisible(false);
          addItem.setVisible(true);
        }
        else if(s.equals("Cancel Order")) {
          order.clear();
          items.clear();
          ids.clear();
          ser.setVisible(false);
          login.setVisible(true);
        }
        else if(s.equals("Finish And Pay")) {
          //do sql query then rest order to ""
          try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315909_9db",
              "csce315909_9user", "password");
          } catch (Exception e1) {
            e1.printStackTrace();
            System.err.println(e1.getClass().getName()+": "+e1.getMessage());
            System.exit(0);
          }
          JOptionPane.showMessageDialog(null,"Opened database successfully");
          try {
            for (String i : order) {
              Statement stmt = conn.createStatement();
              sqlStatement = "UPDATE inventory SET item_quantity = item_quantity - 1 WHERE item_id = " + i + ";";
              stmt.executeUpdate(sqlStatement);
            }
            for (String i : ids) {
              Statement stmt = conn.createStatement();
              sqlStatement = "UPDATE orders SET order_quantity = order_quantity + 1 WHERE order_id = " + i + ";";
              stmt.executeUpdate(sqlStatement);
            }
          } catch (Exception e2) {
            e2.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error accessing database.");
          }
          try {
            conn.close();
            // JOptionPane.showMessageDialog(null, "Connection closed.");
          } catch (Exception e3) {
            JOptionPane.showMessageDialog(null, "Connection not closed.");
          }
          order.clear();
          items.clear();
          ids.clear();
          ser.setVisible(false);
          login.setVisible(true);
        }
        else if(s.equals("5 finger original")) {
          ids.add("501" + day);
          itemsInOrder = "";
          order.add("2004");
          order.add("1002");
          order.add("1001");
          order.add("1001");
          order.add("1001");
          order.add("1001");
          order.add("1001");
          order.add("2011");
          order.add("2013");
          order.add("3002");
          items.add("5 Finger Original");
          addItem.setVisible(false);
          ser.setVisible(true);
          for (String i : items) {
            itemsInOrder += i + "\n";
          }
          JTextArea text = new JTextArea(itemsInOrder);
          text.setBounds(300, 100, 350, 350);
          ser.add(text);
          JScrollPane scrollbar = new JScrollPane(text, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
          scrollbar.setBounds(300, 100, 600, 600);
          ser.add(scrollbar);
        }
        else if(s.equals("4 finger meal")) {
          ids.add("502" + day);
          itemsInOrder = "";
          order.add("2004");
          order.add("1002");
          order.add("1001");
          order.add("1001");
          order.add("1001");
          order.add("1001");
          order.add("2011");
          order.add("2013");
          order.add("3002");
          items.add("4 Finger Meal");
          addItem.setVisible(false);
          ser.setVisible(true);
          for (String i : items) {
            itemsInOrder += i + "\n";
          }
          JTextArea text = new JTextArea(itemsInOrder);
          text.setBounds(300, 100, 350, 350);
          ser.add(text);
          JScrollPane scrollbar = new JScrollPane(text, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
          scrollbar.setBounds(300, 100, 600, 600);
          ser.add(scrollbar);
        }
        else if(s.equals("three finger meal")) {
          ids.add("503" + day);
          itemsInOrder = "";
          order.add("2004");
          order.add("1002");
          order.add("1001");
          order.add("1001");
          order.add("1001");
          order.add("2011");
          order.add("2013");
          order.add("3002");
          items.add("3 Finger Meal");
          addItem.setVisible(false);
          ser.setVisible(true);
          for (String i : items) {
            itemsInOrder += i + "\n";
          }
          JTextArea text = new JTextArea(itemsInOrder);
          text.setBounds(300, 100, 350, 350);
          ser.add(text);
          JScrollPane scrollbar = new JScrollPane(text, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
          scrollbar.setBounds(300, 100, 600, 600);
          ser.add(scrollbar);
        }
        else if(s.equals("kids meal")) {
          ids.add("504" + day);
          itemsInOrder = "";
          order.add("2004");
          order.add("1002");
          order.add("1001");
          order.add("1001");
          order.add("2011");
          order.add("2013");
          order.add("3002");
          items.add("Kids Meal");
          addItem.setVisible(false);
          ser.setVisible(true);
          for (String i : items) {
            itemsInOrder += i + "\n";
          }
          JTextArea text = new JTextArea(itemsInOrder);
          text.setBounds(300, 100, 350, 350);
          ser.add(text);
          JScrollPane scrollbar = new JScrollPane(text, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
          scrollbar.setBounds(300, 100, 600, 600);
          ser.add(scrollbar);
        }
        else if(s.equals("gallon of tea")) {
          ids.add("505" + day);
          itemsInOrder = "";
          order.add("2009");
          items.add("Gallon of Tea");
          addItem.setVisible(false);
          ser.setVisible(true);
          for (String i : items) {
            itemsInOrder += i + "\n";
          }
          JTextArea text = new JTextArea(itemsInOrder);
          text.setBounds(300, 100, 350, 350);
          ser.add(text);
          JScrollPane scrollbar = new JScrollPane(text, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
          scrollbar.setBounds(300, 100, 600, 600);
          ser.add(scrollbar);
        }
        else if(s.equals("family pack")) {
          ids.add("506" + day);
          itemsInOrder = "";
          for (int i = 0; i < 20; i++) {
            order.add("1001");
          }
          order.add("2004");
          order.add("3002");
          order.add("2011");
          items.add("Family Pack");
          addItem.setVisible(false);
          ser.setVisible(true);
          for (String i : items) {
            itemsInOrder += i + "\n";
          }
          JTextArea text = new JTextArea(itemsInOrder);
          text.setBounds(300, 100, 350, 350);
          ser.add(text);
          JScrollPane scrollbar = new JScrollPane(text, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
          scrollbar.setBounds(300, 100, 600, 600);
          ser.add(scrollbar);
        }
        else if(s.equals("Club Sandwich meal")) {
          ids.add("507" + day);
          itemsInOrder = "";
          order.add("2004");
          order.add("2004");
          order.add("1005");
          order.add("1001");
          order.add("1006");
          order.add("1006");
          order.add("2004");
          order.add("3002");
          order.add("1002");
          order.add("2013");
          items.add("Club Sandwich Meal");
          addItem.setVisible(false);
          ser.setVisible(true);
          for (String i : items) {
            itemsInOrder += i + "\n";
          }
          JTextArea text = new JTextArea(itemsInOrder);
          text.setBounds(300, 100, 350, 350);
          ser.add(text);
          JScrollPane scrollbar = new JScrollPane(text, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
          scrollbar.setBounds(300, 100, 600, 600);
          ser.add(scrollbar);
        }
        else if(s.equals("Club Sandwich only")) {
          ids.add("508" + day);
          itemsInOrder = "";
          order.add("2004");
          order.add("2004");
          order.add("1005");
          order.add("1001");
          order.add("1006");
          order.add("1006");
          items.add("Club Sandwich");
          addItem.setVisible(false);
          ser.setVisible(true);
          for (String i : items) {
            itemsInOrder += i + "\n";
          }
          JTextArea text = new JTextArea(itemsInOrder);
          text.setBounds(300, 100, 350, 350);
          ser.add(text);
          JScrollPane scrollbar = new JScrollPane(text, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
          scrollbar.setBounds(300, 100, 600, 600);
          ser.add(scrollbar);
        }
        else if(s.equals("Sandwich meal combo")) {
          ids.add("509" + day);
          itemsInOrder = "";
          order.add("2004");
          order.add("2004");
          order.add("1005");
          order.add("1001");
          order.add("2004");
          order.add("3002");
          order.add("1002");
          order.add("2013");
          items.add("Sandwich Meal");
          addItem.setVisible(false);
          ser.setVisible(true);
          for (String i : items) {
            itemsInOrder += i + "\n";
          }
          JTextArea text = new JTextArea(itemsInOrder);
          text.setBounds(300, 100, 350, 350);
          ser.add(text);
          JScrollPane scrollbar = new JScrollPane(text, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
          scrollbar.setBounds(300, 100, 600, 600);
          ser.add(scrollbar);
        }
        else if(s.equals("sandwich only")) {
          ids.add("510" + day);
          itemsInOrder = "";
          order.add("2004");
          order.add("2004");
          order.add("1005");
          order.add("1001");
          items.add("Sandwich");
          addItem.setVisible(false);
          ser.setVisible(true);
          for (String i : items) {
            itemsInOrder += i + "\n";
          }
          JTextArea text = new JTextArea(itemsInOrder);
          text.setBounds(300, 100, 350, 350);
          ser.add(text);
          JScrollPane scrollbar = new JScrollPane(text, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
          scrollbar.setBounds(300, 100, 600, 600);
          ser.add(scrollbar);
        }
        else if(s.equals("Grill cheese meal combo")) {
          ids.add("511" + day);
          itemsInOrder = "";
          order.add("2004");
          order.add("2004");
          order.add("1005");
          order.add("2004");
          order.add("3002");
          order.add("1002");
          order.add("2013");
          items.add("Grilled Cheese Meal");
          addItem.setVisible(false);
          ser.setVisible(true);
          for (String i : items) {
            itemsInOrder += i + "\n";
          }
          JTextArea text = new JTextArea(itemsInOrder);
          text.setBounds(300, 100, 350, 350);
          ser.add(text);
          JScrollPane scrollbar = new JScrollPane(text, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
          scrollbar.setBounds(300, 100, 600, 600);
          ser.add(scrollbar);
        }
        else if(s.equals("grill cheese sandwich only")) {
          ids.add("512" + day);
          itemsInOrder = "";
          order.add("2004");
          order.add("2004");
          order.add("1005");
          items.add("Grilled Cheese");
          addItem.setVisible(false);
          ser.setVisible(true);
          for (String i : items) {
            itemsInOrder += i + "\n";
          }
          JTextArea text = new JTextArea(itemsInOrder);
          text.setBounds(300, 100, 350, 350);
          ser.add(text);
          JScrollPane scrollbar = new JScrollPane(text, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
          scrollbar.setBounds(300, 100, 600, 600);
          ser.add(scrollbar);
        }
        else if(s.equals("Laynes sauce")) {
          ids.add("513" + day);
          itemsInOrder = "";
          order.add("2011");
          items.add("Layne's Sauce");
          addItem.setVisible(false);
          ser.setVisible(true);
          for (String i : items) {
            itemsInOrder += i + "\n";
          }
          JTextArea text = new JTextArea(itemsInOrder);
          text.setBounds(300, 100, 350, 350);
          ser.add(text);
          JScrollPane scrollbar = new JScrollPane(text, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
          scrollbar.setBounds(300, 100, 600, 600);
          ser.add(scrollbar);
        }
        else if(s.equals("Chicken finger")) {
          ids.add("514" + day);
          itemsInOrder = "";
          order.add("1001");
          items.add("Chicken Finger");
          addItem.setVisible(false);
          ser.setVisible(true);
          for (String i : items) {
            itemsInOrder += i + "\n";
          }
          JTextArea text = new JTextArea(itemsInOrder);
          text.setBounds(300, 100, 350, 350);
          ser.add(text);
          JScrollPane scrollbar = new JScrollPane(text, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
          scrollbar.setBounds(300, 100, 600, 600);
          ser.add(scrollbar);
        }
        else if(s.equals("texas toast")) {
          ids.add("515" + day);
          itemsInOrder = "";
          order.add("2004");
          items.add("Texas Toast");
          addItem.setVisible(false);
          ser.setVisible(true);
          for (String i : items) {
            itemsInOrder += i + "\n";
          }
          JTextArea text = new JTextArea(itemsInOrder);
          text.setBounds(300, 100, 350, 350);
          ser.add(text);
          JScrollPane scrollbar = new JScrollPane(text, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
          scrollbar.setBounds(300, 100, 600, 600);
          ser.add(scrollbar);
        }
        else if(s.equals("potato Salad")) {
          ids.add("516" + day);
          itemsInOrder = "";
          order.add("1002");
          items.add("Potato Salad");
          addItem.setVisible(false);
          ser.setVisible(true);
          for (String i : items) {
            itemsInOrder += i + "\n";
          }
          JTextArea text = new JTextArea(itemsInOrder);
          text.setBounds(300, 100, 350, 350);
          ser.add(text);
          JScrollPane scrollbar = new JScrollPane(text, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
          scrollbar.setBounds(300, 100, 600, 600);
          ser.add(scrollbar);
        }
        else if(s.equals("Crinke cut fries")) {
          ids.add("517" + day);
          itemsInOrder = "";
          order.add("3002");
          items.add("Crinkle Cut Fries");
          addItem.setVisible(false);
          ser.setVisible(true);
          for (String i : items) {
            itemsInOrder += i + "\n";
          }
          JTextArea text = new JTextArea(itemsInOrder);
          text.setBounds(300, 100, 350, 350);
          ser.add(text);
          JScrollPane scrollbar = new JScrollPane(text, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
          scrollbar.setBounds(300, 100, 600, 600);
          ser.add(scrollbar);
        }
        else if(s.equals("Fountain Drink")) {
          ids.add("518" + day);
          itemsInOrder = "";
          order.add("2013");
          items.add("Fountain Drink");
          addItem.setVisible(false);
          ser.setVisible(true);
          for (String i : items) {
            itemsInOrder += i + "\n";
          }
          JTextArea text = new JTextArea(itemsInOrder);
          text.setBounds(300, 100, 350, 350);
          ser.add(text);
          JScrollPane scrollbar = new JScrollPane(text, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
          scrollbar.setBounds(300, 100, 600, 600);
          ser.add(scrollbar);
        }
        else if(s.equals("Bottle drink")) {
          ids.add("519" + day);
          itemsInOrder = "";
          order.add("2019");
          items.add("Bottled Drink");
          addItem.setVisible(false);
          ser.setVisible(true);
          for (String i : items) {
            itemsInOrder += i + "\n";
          }
          JTextArea text = new JTextArea(itemsInOrder);
          text.setBounds(300, 100, 350, 350);
          ser.add(text);
          JScrollPane scrollbar = new JScrollPane(text, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
          scrollbar.setBounds(300, 100, 600, 600);
          ser.add(scrollbar);
        }
        else if (s.equals("impossible chicken fingers")) {
          ids.add("520" + day);
          itemsInOrder = "";
          items.add("Impossible Chicken Fingers");
          addItem.setVisible(false);
          ser.setVisible(true);
          for (String i : items) {
            itemsInOrder += i + "\n";
          }
          JTextArea text = new JTextArea(itemsInOrder);
          text.setBounds(300, 100, 350, 350);
          ser.add(text);
          JScrollPane scrollbar = new JScrollPane(text, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
          scrollbar.setBounds(300, 100, 600, 600);
          ser.add(scrollbar);
        }
        else if(s.equals("Cancel")) {
          itemsInOrder = "";
          addItem.setVisible(false);
          ser.setVisible(true);
          for (String i : items) {
            itemsInOrder += i + "\n";
          }
          JTextArea text = new JTextArea(itemsInOrder);
          text.setBounds(300, 100, 350, 350);
          ser.add(text);
          JScrollPane scrollbar = new JScrollPane(text, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
          scrollbar.setBounds(300, 100, 600, 600);
          ser.add(scrollbar);
        }
    }

    public static void createMenuGUI() {
      try {
        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315909_9db",
            "csce315909_9user", "password");
      } catch (Exception e109) { //
        e109.printStackTrace();
        System.err.println(e109.getClass().getName()+": "+e109.getMessage());
        System.exit(0);
      }

      try {
        Statement stmt = conn.createStatement();
        sqlStatement = "SELECT name FROM menu;";
        ResultSet result = stmt.executeQuery(sqlStatement);
        addItem = new JFrame("Add Item");
        menu = new JPanel(new GridLayout(5, 5, 20, 25));
        menu.setBounds(30, 100, 100, 100);
        while (result.next()) {
          mButtons.add(new JButton(result.getString("name")));
        }
        JButton addCancel = new JButton("Cancel");
        menu.add(addCancel);
        addCancel.addActionListener(s);
        for (JButton i : mButtons) {
          menu.add(i);
          i.addActionListener(s);
        }
        addItem.add(menu);
      } catch (Exception e) {
        e.printStackTrace();
      }

      try {
        conn.close();
        //JOptionPane.showMessageDialog(null, "Connection closed.");
      } catch (Exception e3) {
        JOptionPane.showMessageDialog(null, "Connection not closed.");
      }
    }
    
    public static void newDay() {
      try {
        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/csce315909_9db",
            "csce315909_9user", "password");
      } catch (Exception e109) { //
        e109.printStackTrace();
        System.err.println(e109.getClass().getName()+": "+e109.getMessage());
        System.exit(0);
      }

      try {
        Statement stmt = conn.createStatement();
        for (int i = 501; i < 521; i++) {
          String id = "" + i + day;
          sqlStatement = "INSERT into orders VALUES(" + id + ", 0, " + day + ", " + i + ");";
          stmt.executeUpdate(sqlStatement);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }

      try {
        conn.close();
        //JOptionPane.showMessageDialog(null, "Connection closed.");
      } catch (Exception e3) {
        JOptionPane.showMessageDialog(null, "Connection not closed.");
      }
    }
}