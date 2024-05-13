package AirlineMangementSystem;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class FlightController {
    public static void AddNewFlight(Database database, Scanner s) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.out.println("Enter Plane id (int) \n(-1 to show all planes)");
        int planeId = s.nextInt();
        if (planeId == -1) {
            AirPlaneController.printAllPlanes(database);
            System.out.println("Enter Plane id: ");
            planeId = s.nextInt();
        }
       AirPlane plane =  AirPlaneController.getAirPlaneById(database,planeId);
        System.out.println("Enter origin airport id (int) \n(-1 to show all airports)");
        int originAirportId = s.nextInt();
        if (originAirportId == -1) {
            AirPortController.PrintAllAirports(database);
            System.out.println("Enter origin  airport id (int) \n(-1 to show all airports)");
            originAirportId = s.nextInt();
        }
        AirPort origin = AirPortController.getAirPortById(database);


        System.out.println("Enter destination airport id (int) \n(-1 to show all airports)");
        int destinationAirportId = s.nextInt();
        if (destinationAirportId == -1) {
            AirPortController.PrintAllAirports(database);
            System.out.println("Enter Destination id (int) \n(-1 to show all flights)");
            destinationAirportId = s.nextInt();
        }
        AirPort destination = AirPortController.getAirPortById(database);
        System.out.println("Enter Departure tİME (yyyy-MM-dd HH:mm:ss): ");
        String dTime = s.next();
        LocalDateTime departureTime = LocalDateTime.parse(dTime,formatter);

        System.out.println("Enter Arrival tİME (yyyy-MM-dd HH:mm:ss): ");
        String aTime = s.next();
        LocalDateTime arrivalTime = LocalDateTime.parse(dTime,formatter);
        Flight flight = new Flight();
        flight.setAirPlane(plane);
        flight.setOrigin(origin);
        flight.setDestination(destination);
        flight.setDepartureTime(departureTime);
        flight.setArrivalTime(arrivalTime);

        ArrayList<Flight> flights = getAllFlight(database);
        int id = 0;
        if(flights.size()!=0) id = flights.size();

        flight.setId(id);

        String insert = "INSERT INTO flights(id, airplane, origin, destination, departureTime,arrivalTime,isDelayed,bookedEconomy,bookedBusiness,stuff,passangers) VALUES ('" + flight.getId() + "','" + flight.getAirPlane()+ "','" + flight.getOrigin()+ "','" +flight.getDestination() + "','" + flight.getDepartureTime() +"','" + flight.getArrivalTime()+"','"+flight.isDelayed() +"','"+flight.getBookedBusiness()+"','"+flight.getBookedBusiness()+"','"+flight.getStuff()+"','"+flight.getPassengers() +"');";
        database.getStatement().executeQuery(insert);
        System.out.println("Flight added Succsefully");

    }
    public static ArrayList<Flight> getAllFlight(Database database) throws SQLException {
        String get = "SELECT * FROM flights";
        ResultSet rs = database.getStatement().executeQuery(get);
        ArrayList<Flight> flights = new ArrayList<>();
        while (rs.next()) {
            Flight flight = new Flight();
            flight.setId(rs.getInt("id"));
            int planeId = rs.getInt("airplane");
            AirPlane plane = AirPlaneController.getAirPlaneById(database,planeId);
            flight.setAirPlane(AirPlaneController.getAirPlaneById(database,planeId));
            int originId = rs.getInt("origin");
           // flight.setOrigin(AirPortController.getAirports(database));

            int destinationId = rs.getInt("destination");
         // flight.setDestination(AirPortController.getAirports(database,originId));
            String depTime = rs.getString("departureTime");
            LocalDateTime departureTime = LocalDateTime.parse(depTime);
            flight.setDepartureTime(departureTime);
            String arrTime = rs.getString("arrivalTime");
            LocalDateTime arrivalTime = LocalDateTime.parse(arrTime);
            flight.setArrivalTime(arrivalTime);

            boolean delayed = rs.getBoolean("delayed");
            if(delayed) flight.delay();
            flight.setBookedEconomy(rs.getInt("bookedEconomy"));
            flight.setBookedBusiness(rs.getInt("bookedBusiness"));
            String st = rs.getString("stuff");
            String[] stuffID = st.split("<%%/>");
            Employee[] stuff = new Employee[stuffID.length];
            for(int i = 0; i < stuffID.length; i++){
                int id = Integer.parseInt(stuffID[i]);
                stuff[i] = EmployeesController.getEmployeesByIdName(database,null);
            }
            flight.setStuff(stuff);
            String pas = rs.getString("passenger");
            String[] passengerID = pas.split("<%%/>");
            int totalCapacity = plane.getBusinessCapacity()+plane.getEconomyCapacity();
            Passenger[] passenger = new Passenger[totalCapacity];
            for(int i = 0; i < passengerID.length; i++){
                int id = Integer.parseInt(passengerID[i]);
                passenger[i] = PassangerController.getPassengerById(database,id);
            }
            flight.setPassengers(passenger);
            flights.add(flight);
        }

        return flights;
    }
    public static void showAllFlights(Database database) throws SQLException {
        ArrayList<Flight> flights = getAllFlight(database);
        System.out.println("Airplane\tOrigin Airport\tDestination Airport\tDeparture Time" +
                "\tArrival Time\tstatus\tBooked Economy Seats\tBooked Business Seats");
        for (Flight flight:flights){
            flight.print();
        }

    }
        public static void delayflights(Database database,Scanner s) throws SQLException {
            System.out.println("Enter Flight id(int): \n(-1 to show all flights)");
            int id = s.nextInt();
            Flight flight;
            if(id==-1){
                showAllFlights(database);
                System.out.println("Enter Flight id(int): ");
                id = s.nextInt();
            }
            String update = "UPDATE 'flights' SET 'isDelayed' = 'true' WHERE 'id' ="+id+ ";";
            database.getStatement().executeQuery(update);
            System.out.println("Flight delayed succsesfully");

    }
    public static void bookFlight(Database database,Scanner s) throws SQLException {
        System.out.println("Enter flight id(int): \n(-1 to show all Flights ");
        int id = s.nextInt();
        if(id==-1){
            showAllFlights(database);
            System.out.println("Enter flight id(int): ");
            id = s.nextInt();
        }
        Passenger passenger;
        System.out.println("Enter passanger id (int): \n(-1 to get passanger by Name)");
        int passangerId = s.nextInt();
        if (passangerId==-1){
            passenger = PassangerController.getPassengerIdByName(database,s);
        }else{
            passenger  = PassangerController.getPassengerById(database,passangerId);
        }
        System.out.println("1.Economy seat");
        System.out.println("2.Business Seat");
        int n = s.nextInt();
        Flight flight = null;
        System.out.println("Enter Number of Seats (int): ");
        int num = s.nextInt();
        if(n==1){
            flight.setBookedEconomy(flight.getBookedEconomy()-num);
        }else{
            flight.setBookedBusiness(flight.getBookedBusiness()-num);
        }
            Passenger[] passengers = flight.getPassengers();
        for(int i =0;i<passengers.length;i++){
            if(passengers[i] ==null){
                passengers[i] = passenger;
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Passenger p :flight.getPassengers()){
          if(p!=null)  sb.append(p.getId()).append("<%%/>");
        }

        String update = "UPDATE INTO flights SET ('bookedEconomy,bookedBusiness') VALUES ('" + flight.getBookedBusiness() + "','"+flight.getBookedEconomy() +"');";
        database.getStatement().execute(update);
        System.out.println("Booked Sucsessfully");
    }
    public static Flight getFlight(Database database, int id) throws SQLException {
        Flight flight = new Flight();
        String get = "SELECT id, airplane,origin,destination,departureTime,arrivalTime,isDelayed,bookedEconomy,bookedBusiness,stuff,passangers FROM flights WHERE id = '" + id + "'";
        ResultSet rs = database.getStatement().executeQuery(get);
         id = rs.getInt("id");
         int planeId = rs.getInt("origin");
         int destId = rs.getInt("destination");
         String deepTime = rs.getString("destination");
         String arrTime = rs.getString("ArrivalTime");
         String del  = rs.getString("isDelayed");
         int bookedEconomy = rs.getInt("bookedEconomy");
         String st = rs.getString("stuff");
         String pass = rs.getString("passangers");
         Boolean delayed = Boolean.parseBoolean(del);
         AirPlane plane = AirPlaneController.getAirPlaneById(database,planeId);
         flight.setAirPlane(plane);

        return flight;
    }
    public static void setFlightStuff(Database database,Scanner s) throws SQLException {
        System.out.println("Enter Flight id(int): \n(-1 to show all flights");
        int id = s.nextInt();
        if(id ==-1){
            showAllFlights(database);
            System.out.println("Enter Flight id(int): ");
            id = s.nextInt();
        }
        Flight flight = getFlight(database,id);
        System.out.println("1.Show all employees");
        System.out.println("2.Continue");
        int j = s.nextInt();
        if(j==1){
            EmployeesController.printAllEmployees(database);
            System.out.println("Enter Employees id(int): ");
            int[] ids = new int[10];
            for (int i =0;i<10;i++){
                System.out.println("id "+(i+1)+"/10");
                ids[i] = s.nextInt();
            }
            Employee[] employees = new Employee[10];
            for(int i = 0;i<10;i++){
                employees[i] = EmployeesController.getEmployeesById(database, s);
            }
            Flight.setStuff(employees);
        }
        StringBuilder bd = new StringBuilder();
        for(Employee e :flight.getStuff()){
            if(e!=null) bd.append(e.getId()).append("<%%/>");

        }
        String update = "UPDATE INTO flights SET ('stuff,id') VALUES ('" + bd.toString() +"','"+flight.getId() +"',');";
        database.getStatement().execute(update);
        System.out.println("Stuff updated Succesfully");
     }
     public static void cancelFlight(Database database,Scanner s) throws SQLException {
         System.out.println("Enter Flight id(int): \n(-1 to show all flights");
         int id = s.nextInt();
         if(id ==-1){
             showAllFlights(database);
             System.out.println("Enter Flight id(int): ");
             id = s.nextInt();
         }
         Flight flight = getFlight(database,id);
         String delete ="DELETE FROM flights WHERE id ="+id+"; ";
         database.getStatement().execute(delete);
         System.out.println("Flight cancelled Sucssefully");
     }
     public static void printFlightStuff(Database database,Scanner s) throws SQLException {
         System.out.println("Enter Flight id(int): \n(-1 to show all flights");
         int id = s.nextInt();
         if(id ==-1){
             showAllFlights(database);
             System.out.println("Enter Flight id(int): ");
             id = s.nextInt();
         }
         Flight f = getFlight(database,id);
         System.out.println("id\tFirst Name\tLast Name\tEmail\tTel\tPosition");
         for(Employee e:f.getStuff()) {
             if (e != null) {
                 System.out.print(e.getId() + "\t");
                 System.out.print(e.getFirstname() + "\t");
                 System.out.print(e.getLastname() + "\t");
                 System.out.print(e.getEmail() + "\t");
                 System.out.print(e.getTel() + "\t");
                 System.out.print(e.getPosition() + "\t");
             }
         }
     }
     public static void printFlightPassangers(Database database,Scanner s) throws SQLException {
         System.out.println("Enter Flight id(int): \n(-1 to show all flights");
         int id = s.nextInt();
         if(id ==-1){
             showAllFlights(database);
             System.out.println("Enter Flight id(int): ");
             id = s.nextInt();
         }
         Flight f = getFlight(database,id);
         System.out.println("id\tFirst Name\tLast Name\tEmail\tTel");
         for(Passenger p:f.getPassengers()) {
             if (p != null) {
                 System.out.print(p.getId() + "\n");
                 System.out.print(p.getFirstName() + "\n");
                 System.out.print(p.getLastName() + "\n");
                 System.out.print(p.getEmail() + "\n");
                 System.out.print(p.getTel() + "\n");
             }
         }
     }

}
