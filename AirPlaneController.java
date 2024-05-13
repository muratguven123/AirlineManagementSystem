package AirlineMangementSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import static AirlineMangementSystem.Database.getAllPlane;

public class AirPlaneController {
    public static void AddNewAirPlane(Database database, Scanner s) throws SQLException {
        System.out.println("Enter Plane id (int): ");
        int id = s.nextInt();
        System.out.println("Enter Economy capacity (int): ");
        int economycapacity = s.nextInt();
        System.out.println("Enter Business capacity (int): ");
        int businesscapacity = s.nextInt();
        System.out.println("Enter Model: ");
        String model = s.next();
        AirPlane airPlane = new AirPlane();
        airPlane.setEconomyCapacity(economycapacity);
        airPlane.setBusinessCapacity(businesscapacity);
        airPlane.setModel(model);
        ArrayList<AirPlane> airPlanes = getAllPlane(database);

        if (!(airPlanes.isEmpty())) {
            id = airPlanes.get(airPlanes.size()-1).getId()+1;
        } else {
            id = 0;
        }
        airPlane.setId(id);
        String insert = "INSERT INTO airplane (id, economyCapacity, businessCapacity, model) VALUES ('" + airPlane.getId() + "','" + airPlane.getEconomyCapacity() + "','" + airPlane.getBusinessCapacity() + "','" + airPlane.getModel() + "')";
        int rowsAffected = database.getStatement().executeUpdate(insert);
        if (rowsAffected > 0) {
            System.out.println("Plane added successfully");
        } else {
            System.out.println("Failed to add plane");
        }
    }
    public static void printAllPlanes(Database database) throws SQLException {
        System.out.println("------------------------\n");
        for (AirPlane airPlane : getAllPlane(database)) {
            airPlane.print();
        }
        System.out.println("-------------------------\n");
    }
    public static void editPlane(Database database, Scanner s) throws SQLException {
        System.out.println("Enter employee ID(int): \n(-1 to get employee by name)");
        int id = s.nextInt();
        ArrayList<AirPlane> airPlane;
        if (id != -1) {
//                printAllPassengers(database);
//                System.out.println("Enter Passenger ID: ");
//                id = s.nextInt();
            airPlane = database.getAllPlane(database);
        } else {
            try {
                String get = "SELECT id, economyCapacity, businessCapacity, model FROM airplane WHERE id =+'id'";


                ResultSet rs = database.getStatement().executeQuery(get);
                rs.next();
                AirPlane plane = new AirPlane();
                rs.next();
                try {
                    plane.setId(Integer.parseInt(rs.getString("id")));
                } catch (SQLException exception) {
                    throw new SQLException("ID not found");
                }
                plane.setEconomyCapacity(plane.getEconomyCapacity());
                plane.setBusinessCapacity(plane.getBusinessCapacity());
                plane.setModel(rs.getString("email"));
                System.out.println("Enter id(int): \n(-1 to get employee by name)");
                id = s.nextInt();
                System.out.println("Enter Economy capacity (int): \n(-1 to get employee by name)");
                int economycapacity = s.nextInt();
                if (plane.getEconomyCapacity()==-1) {
                    int economyCapacity = plane.getEconomyCapacity();
                }
                if (plane.getBusinessCapacity()==-1) {}
                System.out.println("Enter businessCapacity: \n(-1 to get employee by name)");
                int businessCapacity = s.nextInt();
                if (plane.getBusinessCapacity()==-1) {
                     businessCapacity = plane.getBusinessCapacity();
                }
                System.out.println("Enter model: \n(-1 to get employee by name)");
                String model = s.next();
                if (plane.getModel()==null) {
                    model = plane.getModel();
                }
                plane.setId(id);
                plane.setEconomyCapacity(economycapacity);
                plane.setBusinessCapacity(businessCapacity);
                plane.setModel(model);

                String update = "UPDATE airplane SET id = '" + plane.getId() + "', firstName = '" + plane.getEconomyCapacity() + "', lastName = '" + plane.getBusinessCapacity() + "', tel = '" + plane.getModel() + "'";
                database.getStatement().execute(update);
                System.out.println("Passenger Edit successfuly");

            } catch (Exception e) {
                System.out.println("Hayır buradaymış Hocam");
            }
        }

    }
    public static AirPlane getAirPlaneById(Database database,int id) throws SQLException {
        AirPlane a = new AirPlane();
        String get =  "SELECT id , economyCapacity, businessCapacity, model FROM airplane WHERE id =+'id'";
        ResultSet rs = database.getStatement().executeQuery(get);
        rs.next();
        a.setId(rs.getInt("id"));
        a.setEconomyCapacity(rs.getInt("economyCapacity"));
        a.setBusinessCapacity(rs.getInt("businessCapacity"));
        a.setModel(rs.getString("model"));
        return a;
    }
    public static void DeletePlane(Database database, Scanner s) throws SQLException {
        System.out.println("Enter Passenger ID: \n(-1 to get passenger by name)");
        int id = s.nextInt();
        AirPlane airPlane;
        if (id != -1) {
            airPlane = getAirPlaneById(database,id);
        } else {
            String get = "SELECT 'id','economyCapacity','businessCapacity','model'";
            ResultSet rs = database.getStatement().executeQuery(get);
            AirPlane plane = new AirPlane();
            rs.next();
            try {
                plane.setId(Integer.parseInt(rs.getString("id")));
            } catch (SQLException exception) {
                throw new SQLException("ID not found");
            }
            plane.setEconomyCapacity(plane.getEconomyCapacity());
            plane.setBusinessCapacity(plane.getBusinessCapacity());
            plane.setModel(plane.getModel());
            airPlane = plane;
        }
        String delete = "DELETE FROM airplane WHERE id = " + airPlane.getId();
        database.getStatement().execute(delete);
        System.out.println("Passenger Deleted successfuly");
    }
    }

