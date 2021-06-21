package com.example.databaseprogrammingtest;

import java.sql.*;

/**
 * Short database connection and query demo
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/supermarket", "root", "D8abase!");
            if (args.length >= 3) {
                String articleName = args[0];
                int price = 0;
                int vat = 0;
                try {
                    price = Integer.parseInt(args[1]);
                    vat = Integer.parseInt(args[2]);
                } catch(NumberFormatException e) {
                    System.out.println("Preis und Mehrwertsteuersatz müssen Zahlen sein");
                    System.exit(1);
                }
                PreparedStatement insertStatement = conn.prepareStatement(
                    "INSERT INTO articles (artname, price, vat) VALUES (?, ?, ?)"
                );
                insertStatement.setString(1, articleName);
                insertStatement.setInt(2, price);
                insertStatement.setInt(3, vat);
                insertStatement.execute();
                insertStatement.close();
            }
            Statement queryStatement = conn.createStatement();
            ResultSet result = queryStatement.executeQuery("SELECT * FROM articles");
            while (result.next()) {
                System.out.print("Artikel: " + result.getString("artname") + "\t");
                System.out.print("Preis: " + result.getInt("price") + "\t");
                System.out.println("MWSt-Satz: " + result.getString("vat"));
            }
            result.close();
            queryStatement.close();
            conn.close();
        } catch(SQLException e) {
            System.out.println("Fehler bei der Datenbankabfrage: " + e.getMessage());
            System.exit(4);
        }
    }
}
