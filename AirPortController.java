package AirlineMangementSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class AirPortController {
    public static void AddNewAirPort(Database database, Scanner s) throws SQLException {
        System.out.println("Enter City: ");
        String city = s.next();
        ArrayList<AirPort> airports = getAirports(database);
        int id;
        if (!(airports.isEmpty())) {
            id = airports.get(airports.size() - 1).getId() + 1;
        } else {
            id = 0;
        }
        AirPort airPort = new AirPort();
        airPort.setId(id);
        airPort.setCity(city);

        String insert = "INSERT INTO airports(id, city) VALUES ('" + airPort.getId() + "','" + airPort.getCity() + "');";
        int rowsAffected = database.getStatement().executeUpdate(insert);
        if (rowsAffected > 0) {
            System.out.println("airport added successfully");
        } else {
            System.out.println("Failed to add plane");
        }
    }

    public static ArrayList<AirPort> getAirports(Database database) throws SQLException {
        ArrayList<AirPort> airports = new ArrayList<>();
        String select = "SELECT * FROM airports";
        ResultSet rs = database.getStatement().executeQuery(select);
        while (rs.next()) {
            AirPort airport = new AirPort();
            airport.setId(rs.getInt("id"));
            airport.setCity(rs.getString("city"));
            airports.add(airport);
        }
        return airports;
    }


    public static void PrintAllAirports(Database database) throws SQLException {
        System.out.println("-------------------------");
        ArrayList<AirPort> airports = getAirports(database);
        for (AirPort airport : airports) {
            airport.print();
        }
        System.out.println("-------------------------");
    }

    public static void deleteAirport(Database database, Scanner s) throws SQLException {
        System.out.println("Enter Passenger ID: \n(-1 to get passenger by name)");
        int id = s.nextInt();
        AirPort airPort;
        if (id != -1) {
            airPort = getAirPortById(database);
        } else {
            String get = "SELECT 'id','city' FROM airports WHERE id = 'airports.id'";
            ResultSet rs = database.getStatement().executeQuery(get);
            AirPort plane = new AirPort();
            rs.next();
            try {
                plane.setId(Integer.parseInt(rs.getString("id")));
            } catch (SQLException exception) {
                throw new SQLException("ID not found");
            }
            plane.setCity(rs.getString("city"));

        }
        String delete = "DELETE FROM airports WHERE id =  +'airPort.getId()'";
        database.getStatement().execute(delete);
        System.out.println("Airport Deleted successfuly");
    }

    public static AirPort getAirPortById(Database database) throws SQLException {
        AirPort a = new AirPort();
        String get = "SELECT id , city FROM airports WHERE id =+'id'";
        ResultSet rs = database.getStatement().executeQuery(get);
        rs.next();
        a.setId(rs.getInt("id"));
        a.setCity(rs.getString("city"));
        return a;
    }

    public static void editAirPort(Database database, Scanner s) throws SQLException {
        System.out.println("Enter employee ID(int): \n(-1 to get employee by name)");
        int id = s.nextInt();
        ArrayList<AirPort> airPorts;
        if (id != -1) {
//                printAllPassengers(database);
//                System.out.println("Enter Passenger ID: ");
//                id = s.nextInt();
            airPorts = Database.getAllAirports(database);
        } else {
            try {
                String get = "SELECT id, city FROM airports WHERE id =+'id'";


                ResultSet rs = database.getStatement().executeQuery(get);
                rs.next();
                AirPort port = new AirPort();
                rs.next();
                try {
                    port.setId(Integer.parseInt(rs.getString("id")));
                } catch (SQLException exception) {
                    throw new SQLException("ID not found");
                }
                port.setCity(rs.getString("city"));

                System.out.println("Enter city: \n(-1 to get employee by name)");
                String city = s.next();
                if (port.getCity() == null) {
                    city = port.getCity();
                }
                port.setId(id);
                port.setCity(city);

                String update = "UPDATE airports SET id = '" + port.getId() + "', city = '" + port.getCity() + "'";
                database.getStatement().execute(update);
                System.out.println("airport Edit successfuly");

            } catch (Exception e) {
                System.out.println("Hayır buradaymış Hocam");
            }
        }
    }


}