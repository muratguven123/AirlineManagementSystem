package AirlineMangementSystem;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class PassangerController {
    public static void AddNewPassenger(Database database, Scanner s) throws SQLException {
        System.out.println("Enter First Name: ");
        String firstName = s.next();
        System.out.println("Enter Last Name: ");
        String lastName = s.next();
        System.out.println("Enter tel: ");
        String tel = s.next();
        System.out.println("Enter Email: ");
        String email = s.next();

        Passenger p = new Passenger();
        p.setFirstName(firstName);
        p.setLastName(lastName);
        p.setTel(tel);
        p.setEmail(email);
        try {
            ArrayList<Passenger> passengers = getAllPassengers(database);
        } catch (Exception e) {
            System.out.println("Error in addPassenger");
        }
        ArrayList<Passenger> passengers = database.getAllPassengers();
        int id;

        if (passengers.size() != 0) {
            id = passengers.get(passengers.size() - 1).getId() + 1;
        } else {
            id = 0;
        }
        p.setId(id);
        String insert = "INSERT INTO passanger(id, firstName, lastName, tel, email) VALUES ('" + p.getId() + "','" + p.getFirstName() + "','" + p.getLastName() + "','" + p.getTel() + "','" + p.getEmail() + "');";
        database.getStatement().execute(insert);

        System.out.println("Pasanger Added successfuly");
    }

    public static void EditPassenger(Database database, Scanner s) throws SQLException {
        System.out.println("Enter Passenger ID(int): \n(-1 to get passanger by name)");
        int id = s.nextInt();
        Passenger passenger;
        if (id != -1) {
//                printAllPassengers(database);
//                System.out.println("Enter Passenger ID: ");
//                id = s.nextInt();
            passenger = getPassengerIdByName(database, s);
        } else {
            String get = "SELECT 'id','firstName','lastName','Tel','email';";
            ResultSet rs = database.getStatement().executeQuery(get);
            Passenger p = new Passenger();
            rs.next();
            try {
                p.setId(Integer.parseInt(rs.getString("id")));
            } catch (SQLException e) {
                throw new SQLException("ID not found");
            }
            p.setFirstName(rs.getString("firstName"));
            p.setLastName(rs.getString("lastName"));
            p.setEmail(rs.getString("email"));
            passenger = p;
        }
//           try {
//                Passenger passenger = database.getPassenger(id);
//            }catch (Exception e){
//                System.out.println("Passenger not found");            }
//            Passenger passenger = database.getPassenger(id);
        System.out.println("Enter First Name: \n(-1 to keep old value)");
        String firstName = s.next();
        if (firstName.equals("-1")) firstName = passenger.getFirstName();
        System.out.println("Enter Last Name: \n(-1 to keep old value)");
        String lastName = s.next();
        if (lastName.equals(-1)) lastName = passenger.getLastName();
        System.out.println("Enter tel: \n(-1 to keep old value)");
        String tel = s.next();
        if (tel.equals(-1)) tel = passenger.getTel();
        System.out.println("Enter Email: \n(-1 to keep old value)");
        String email = s.next();
        if (email.equals(-1)) email = passenger.getEmail();
        passenger.setFirstName(firstName);
        passenger.setLastName(lastName);
        passenger.setTel(tel);
        passenger.setEmail(email);
        String update = "UPDATE INTO passanger SET ('id','firstName','lastName'," + "''Tel','email') VALUES ('" + passenger.getId() + "''" + passenger.getFirstName() + "''" + passenger.getLastName() + "'" + passenger.getTel() + "''" + passenger.getEmail() + "');";
        database.getStatement().execute(update);
        System.out.println("Passenger Edit successfuly");
    }

    public static Passenger getPassengerIdByName(Database database, Scanner s) throws SQLException {
        System.out.println("Enter First Name: ");
        String firstName = s.next();
        System.out.println("Enter Last Name: ");
        String lastName = s.next();
        String get = "SELECT id, firstName, lastName, Tel, email FROM passanger WHERE firstName = '" + firstName + "'";
        ResultSet rs = database.getStatement().executeQuery(get);
        Passenger passenger = new Passenger();
        while (rs.next()) {
            Passenger p = new Passenger();
            p.setId(Integer.parseInt(rs.getString("id")));
            p.setFirstName(rs.getString("firstName"));
            p.setLastName(rs.getString("lastName"));
            p.setTel(rs.getString("Tel"));
            p.setEmail(rs.getString("email"));
            if (p.getLastName().equals(lastName)) passenger = p;
            break;
        }

        passenger.print();
        return passenger;
    }

    public static void findPassenger(Database database, Scanner s) throws SQLException {
        System.out.println("Enter First Name: ");
        String firstName = s.next();
        System.out.println("Enter Last Name: ");
        String lastName = s.next();
        String get = "SELECT id, firstName, lastName, Tel, email FROM passanger WHERE firstName = '" + firstName + "'";
        ResultSet rs = database.getStatement().executeQuery(get);
        Passenger passenger = new Passenger();
        while (rs.next()) {
            Passenger p = new Passenger();
            p.setId(Integer.parseInt(rs.getString("id")));
            p.setFirstName(rs.getString("firstName"));
            p.setLastName(rs.getString("lastName"));
            p.setTel(rs.getString("Tel"));
            p.setEmail(rs.getString("email"));
            if (p.getLastName().equals(lastName)) passenger = p;
            break;
        }

        passenger.print();

    }

    public static void printAllPassengers(Database database) throws SQLException {
        ArrayList<Passenger> passengers = database.getAllPassengers();
        System.out.println("------------------------\n");
        for (Passenger passenger : passengers) {
            System.out.println("id: " + passenger.getId());
            System.out.println("firstName: " + passenger.getFirstName());
            System.out.println("lastName: " + passenger.getLastName());
            System.out.println("tel: " + passenger.getTel());
            System.out.println("email: " + passenger.getEmail());
        }
        System.out.println("-------------------------\n");
    }

    public static void deletePassenger(Database database, Scanner s) throws SQLException {
        System.out.println("Enter Passenger ID: \n(-1 to get passenger by name)");
        int id = s.nextInt();
        Passenger passenger;
        if (id != -1) {
            passenger = getPassengerIdByName(database, s);
        } else {
            String get = "SELECT 'id','firstName','lastName','Tel','email';";
            ResultSet rs = database.getStatement().executeQuery(get);
            Passenger p = new Passenger();
            rs.next();
            try {
                p.setId(Integer.parseInt(rs.getString("id")));
            } catch (SQLException e) {
                throw new SQLException("ID not found");
            }
            p.setFirstName(rs.getString("firstName"));
            p.setLastName(rs.getString("lastName"));
            p.setEmail(rs.getString("email"));
            passenger = p;
        }
        String delete = "DELETE FROM passanger WHERE id = " + passenger.getId();
       database.getStatement().execute(delete);
        System.out.println("Passenger Deleted successfuly");
    }
    public static ArrayList<Passenger> getAllPassengers(Database database) throws SQLException {
        String get = "SELECT*FROM passanger;";
        try {
            ResultSet rs = database.getStatement().executeQuery(get);
        }catch (Exception e){
            throw new SQLException(e.getMessage());
        }
        ResultSet rs = database.getStatement().executeQuery(get);
        ArrayList<Passenger> passengers = new ArrayList<>();
        while(rs.next()){
            Passenger p = new Passenger();
            p.setId(Integer.parseInt(rs.getString("id")));;
            p.setFirstName(rs.getString("firstName"));
            p.setLastName(rs.getString("lastName"));
            p.setTel(rs.getString("tel"));
            p.setEmail(rs.getString("email"));
            passengers.add(p);
        }
        return passengers;
    }
    public static Passenger getPassengerById(Database database,int id) throws SQLException {
        String get = "SELECT id, firstName, lastName, Tel, email FROM passanger WHERE id = '" + id + "'";
        ResultSet rs = database.getStatement().executeQuery(get);
        Passenger passenger = new Passenger();
        rs.next();
            Passenger p = new Passenger();
            p.setId(Integer.parseInt(rs.getString("id")));
            p.setFirstName(rs.getString("firstName"));
            p.setLastName(rs.getString("lastName"));
            p.setTel(rs.getString("Tel"));
            p.setEmail(rs.getString("email"));
            return p;

    }

}

