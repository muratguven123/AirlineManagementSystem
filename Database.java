package AirlineMangementSystem;

import java.sql.*;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Database {
    Connection connection;
    private String url = "jdbc:mysql://localhost:3306/airlinemanagementsystem";
    private String user = "root";
    private String pass = "guven123.";
    private static Statement statement;
    public Database() throws SQLException {
      Connection  connection = DriverManager.getConnection(url,user,pass);
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet  = statement.executeQuery("select * from airlinemanagementsystem.passanger");
    }
    public Statement getStatement(){
        return statement;
    }
    public void AddPassenger(Passenger p) throws SQLException {
//        String insert = "INSERT INTO passanger(id, firstName, lastName, tel, email) VALUES ('" + p.getId() + "','" + p.getFirstName() + "','" + p.getLastName() + "','" + p.getTel() + "','" + p.getEmail() + "');";
//        statement.executeUpdate(insert);
    }
//    public Passenger getPassenger(int id) throws SQLException {
//        String get = "SELECT 'id','firstName','lastName','Tel','email';";
//        ResultSet rs = statement.executeQuery(get);
//        Passenger p = new Passenger();
//        rs.next();
//        try {
//            p.setId(Integer.parseInt(rs.getString("id")));
//        }catch (SQLException e) {
//            throw new SQLException("ID not found");
//        }
//        p.setFirstName(rs.getString("firstName"));
//        p.setLastName(rs.getString("lastName"));
//        p.setEmail(rs.getString("email"));
//        return p;
//    }
//    public Passenger getPassenger(String firstName,String lastName) throws SQLException {
//        try {
//            String get = "SELECT id, firstName, lastName, Tel, email FROM passanger WHERE firstName = '" + firstName + "'";
//
//            ResultSet rs = statement.executeQuery(get);
//        }catch (SQLException e){
//            throw new SQLException("Knk syntax da hata var");
//        }
//        String get = "SELECT id, firstName, lastName, Tel, email FROM passanger WHERE firstName = '" + firstName + "'";
//        ResultSet rs = statement.executeQuery(get);
//        Passenger passenger = new Passenger();
//
//        while (rs.next()) {
//            Passenger p = new Passenger();
//            rs.next();
//            try {
//                if (rs.next()) { // Eğer ResultSet'te sonraki bir satır varsa devam eder.
//                    p.setId(Integer.parseInt(rs.getString("id"))); // "id" sütunundaki değeri alıp Integer'a dönüştürerek setId() metoduna atıyoruz.
//                } else {
//                    // Eğer ResultSet boşsa, yani hiçbir satır dönmediyse, "ID not found" hatasını veriyoruz.
//                    System.out.println("ID not found");
//                }
//            }catch (SQLException e) {
//                throw new SQLException("ID not found");
//            }
//            try {
//                // ResultSet'te bir sonraki satır varsa, işlemleri gerçekleştir
//                if (rs.next()) {
//                    // ResultSet'te bir sonraki satır varsa, sütunları okuyarak işlem yapabiliriz.
//                    p.setFirstName(rs.getString("firstName"));
//                    p.setFirstName(rs.getString("firstName"));
//                    p.setLastName(rs.getString("lastName"));
//                    p.setEmail(rs.getString("email"));
//                } else {
//                    // Eğer ResultSet'te bir sonraki satır yoksa, "After end of result set" hatası almamak için herhangi bir işlem yapmamalıyız.
//                    // Opsiyonel olarak, bir mesaj yazdırabiliriz veya başka bir işlem yapabiliriz.
//                    System.out.println("ResultSet is empty.");
//                }
//            } catch (SQLException e) {
//                // SQLException durumunda hata mesajını yazdırıyoruz.
//                e.printStackTrace();
//            }
//
//            try {
//                while (rs.next()) {
//                    firstName = rs.getString("firstName");
//                     lastName = rs.getString("lastName");
//
//                    // Null kontrolü burada yapılabilir
//                    if (firstName != null && lastName != null) {
//                        // Değerler null değilse, işlemleri gerçekleştir
//                        System.out.println("First Name: " + firstName + ", Last Name: " + lastName);
//                    } else {
//                        // Değerlerden biri veya her ikisi null ise, uygun bir işlem yap
//                        System.out.println("One or both values are null");
//                    }
//                }
//            } catch (SQLException e) {
//                // SQLException durumunda hata mesajını yazdırıyoruz.
//                e.printStackTrace();
//            }
//
//
//// Eğer passengerFirstName veya passengerLastName null değilse ve aranan değerlerle eşleşiyorsa, karşılaştırma yapabiliriz
//            String passengerFirstName =firstName;
//            String passengerLastName =lastName;
//            if (passengerFirstName != null && passengerLastName != null &&
//                    passengerFirstName.equals(firstName) && passengerLastName.equals(lastName)) {
//                passenger = p;
//            }
//
//        }
////        Passenger p = new Passenger();
////        rs.next();
////        try {
////            p.setId(Integer.parseInt(rs.getString("id")));
////        }catch (SQLException e) {
////            throw new SQLException("ID not found");
////        }
////        p.setFirstName(rs.getString("firstName"));
////        p.setLastName(rs.getString("lastName"));
////        p.setEmail(rs.getString("email"));
////        return p;
//        return passenger;
//    }

//    public static void editPassenger(Passenger p) throws SQLException {
//        String update  ="UPDATE INTO passanger SET ('id','firstName','lastName',"+"''Tel','email') VALUES ('"+p.getId()+"''"+p.getFirstName()+"''"+p.getLastName()+"'"+p.getTel()+"''"+p.getEmail()+"');";
//        statement.executeUpdate(update);
//    }
    public ArrayList<Passenger> getAllPassengers() throws SQLException {
        String get = "SELECT*FROM passanger;";
        try {
            ResultSet rs = statement.executeQuery(get);
        }catch (Exception e){
            throw new SQLException(e.getMessage());
        }
        ResultSet rs = statement.executeQuery(get);
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
    public static ArrayList<Employee> getAllEmployees(Database database) throws SQLException {
        String get = "SELECT * FROM employees";
        ResultSet rs = database.getStatement().executeQuery(get);
        ArrayList<Employee> employees = new ArrayList<>();
        while (rs.next()) {
            Employee employee = new Employee();
            employee.setId(Integer.parseInt(rs.getString("id")));
            employee.setFirstname(rs.getString("firstname"));
            employee.setLastname(rs.getString("lastname"));
            employee.setTel(rs.getString("tel"));
            employee.setEmail(rs.getString("email"));
            employee.setSalary(Double.parseDouble(rs.getString("salary")));
            employee.setPosition(rs.getString("position"));
            employees.add(employee);

        }
        return employees;
    }
    public static ArrayList<AirPlane> getAllPlane(Database database) throws SQLException {
        String get = "SELECT * FROM airplane";
        ResultSet rs = database.getStatement().executeQuery(get);
        ArrayList<AirPlane> airPlanes = new ArrayList<>();
        while (rs.next()) {
           AirPlane plane = new AirPlane();
           plane.setId(Integer.parseInt(rs.getString("id")));
           plane.setEconomyCapacity(rs.getInt("economyCapacity"));
           plane.setEconomyCapacity(rs.getInt("economyCapacity"));
           plane.setModel(rs.getString("model"));
           airPlanes.add(plane);
        }

        return airPlanes;
    }
    public static ArrayList<AirPort> getAllAirports(Database database) throws SQLException {
        String get = "SELECT * FROM airports";
        ResultSet rs = database.getStatement().executeQuery(get);
        ArrayList<AirPort> airPorts = new ArrayList<>();
        while (rs.next()) {
            AirPort port = new AirPort();
            port.setId(Integer.parseInt(rs.getString("id")));
            port.setCity(rs.getString("city"));
            airPorts.add(port);
        }

        return airPorts;
    }




//        public  void deletePassenger(Passenger p) throws SQLException {
//        String delete = "DELETE FROM passanger WHERE id = " + p.getId();
//        statement.executeUpdate(delete);
//        }

}
