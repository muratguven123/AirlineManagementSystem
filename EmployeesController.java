package AirlineMangementSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeesController {
    public static void AddNewEmployee(Database database, Scanner s) throws SQLException {
        System.out.println("Enter employee first name: ");
        String firstName = s.next();
        System.out.println("Enter employee last name: ");
        String lastName = s.next();
        System.out.println("Enter employee email: ");
        String email = s.next();
        System.out.println("Enter employee Tel: ");
        String tel = s.next();
        System.out.println("Enter employee salary(double): ");
        double salary = s.nextDouble();
        System.out.println("Enter position: ");
        String position = s.next();

        Employee employee = new Employee();
        employee.setFirstname(firstName);
        employee.setLastname(lastName);
        employee.setPassword(lastName);
        employee.setEmail(email);
        employee.setTel(tel);
        employee.setSalary(salary);
        employee.setPosition(position);

        ArrayList<Employee> employees = getAllEmployees(database);
        int id;
        if (!(employees.isEmpty())) {
            try {
                id = employees.get(employees.size() - 1).getId();
            } catch (Exception e) {
                System.out.println("knk sıkıntı var");
            }
            id = employees.get(employees.size() - 1).getId();

        } else {
            id = 0;
        }
        employee.setId(id);
        try {
            String insert = "INSERT INTO employees (id, firstName, lastName, tel, email, salary, position) VALUES ('" + employee.getId() + "','" + employee.getFirstname() + "','" + employee.getLastname() + "','" + employee.getTel() + "','" + employee.getEmail() + "','" + employee.getSalary() + "','" + employee.getPosition() + "')";
            int rowsAffected = database.getStatement().executeUpdate(insert);
            if (rowsAffected > 0) {
                System.out.println("Employee added successfully");
            } else {
                System.out.println("Failed to add employee");
            }

        } catch (Exception e) {
            System.out.println("sızıntı burada hocam");
        }

//        int rowsAffected = database.getStatement().executeUpdate(insert);
//        if (rowsAffected > 0) {
//            System.out.println("Employee added successfully");
//        } else {
//            System.out.println("Failed to add employee");
//        }

    }

    public static void editEmployee(Database database, Scanner s) throws SQLException {
        System.out.println("Enter employee ID(int): \n(-1 to get employee by name)");
        int id = s.nextInt();
        Employee employee;
        if (id != -1) {
//                printAllPassengers(database);
//                System.out.println("Enter Passenger ID: ");
//                id = s.nextInt();
            employee = getEmployeesByIdName(database, s);
        } else {
            try {
                String get = "SELECT id, firstName, lastName, tel, email, salary, position FROM employees WHERE id = ?";


                ResultSet rs = database.getStatement().executeQuery(get);
                rs.next();
                Employee e = new Employee();
                rs.next();
                try {
                    e.setId(Integer.parseInt(rs.getString("id")));
                } catch (SQLException exception) {
                    throw new SQLException("ID not found");
                }
                e.setFirstname(rs.getString("firstName"));
                e.setLastname(rs.getString("lastName"));
                e.setEmail(rs.getString("email"));
                e.setTel(rs.getString("tel"));
                e.setSalary(rs.getDouble("salary"));
                e.setPosition(rs.getString("position"));
                employee = e;
                System.out.println("Enter First Name: \n(-1 to keep old value)");
                String firstName = s.next();
                if (firstName.equals("-1")) firstName = employee.getFirstname();
                System.out.println("Enter Last Name: \n(-1 to keep old value)");
                String lastName = s.next();
                if (lastName.equals(-1)) lastName = employee.getLastname();
                System.out.println("Enter tel: \n(-1 to keep old value)");
                String tel = s.next();
                if (tel.equals(-1)) tel = employee.getTel();
                System.out.println("Enter Email: \n(-1 to keep old value)");
                String email = s.next();
                if (email.equals(-1)) email = employee.getEmail();
                System.out.println("Enter Salary: \n(-1 to keep old value)");
                double salary = s.nextDouble();
                if (salary == -1) salary = employee.getSalary();
                System.out.println("Enter Position: \n(-1 to keep old value)");
                String position = s.next();
                if (position.equals(-1)) position = employee.getPosition();
                employee.setFirstname(firstName);
                employee.setLastname(lastName);
                employee.setTel(tel);
                employee.setEmail(email);
                employee.setSalary(salary);
                employee.setPosition(position);
                String update = "UPDATE employees SET id = '" + employee.getId() + "', firstName = '" + employee.getFirstname() + "', lastName = '" + employee.getLastname() + "', tel = '" + employee.getTel() + "', email = '" + employee.getEmail() + "', salary = '" + employee.getSalary() + "', position = '" + employee.getPosition() + "'";

                database.getStatement().execute(update);
                System.out.println("Passenger Edit successfuly");

            } catch (Exception e) {
                System.out.println("Hayır buradaymış Hocam");
            }
//            String get = "SELECT id, firstName, lastName, Tel, email, salary,position FROM employees WHERE id =employees.id ";
//
////            ResultSet rs = database.getStatement().executeQuery(get);
//            Employee e = new Employee();
//            rs.next();
//            try {
//                e.setId(Integer.parseInt(rs.getString("id")));
//            } catch (SQLException exception) {
//                throw new SQLException("ID not found");
//            }
//            e.setFirstname(rs.getString("firstName"));
//            e.setLastname(rs.getString("lastName"));
//            e.setEmail(rs.getString("email"));
//            e.setTel(rs.getString("tel"));
//            e.setSalary(rs.getDouble("salary"));
//            e.setPosition(rs.getString("position"));
//            employee = e;
        }
//           try {
//                Passenger passenger = database.getPassenger(id);
//            }catch (Exception e){
//                System.out.println("Passenger not found");            }
////            Passenger passenger = database.getPassenger(id);
//        System.out.println("Enter First Name: \n(-1 to keep old value)");
//        String firstName = s.next();
//        if (firstName.equals("-1")) firstName = employee.getFirstname();
//        System.out.println("Enter Last Name: \n(-1 to keep old value)");
//        String lastName = s.next();
//        if (lastName.equals(-1)) lastName = employee.getLastname();
//        System.out.println("Enter tel: \n(-1 to keep old value)");
//        String tel = s.next();
//        if (tel.equals(-1)) tel = employee.getTel();
//        System.out.println("Enter Email: \n(-1 to keep old value)");
//        String email = s.next();
//        if (email.equals(-1)) email = employee.getEmail();
//        System.out.println("Enter Salary: \n(-1 to keep old value)");
//        double salary = s.nextDouble();
//        if(salary == -1) salary = employee.getSalary();
//        System.out.println("Enter Position: \n(-1 to keep old value)");
//        String position = s.next();
//        if (position.equals(-1)) position = employee.getPosition();
//        employee.setFirstname(firstName);
//        employee.setLastname(lastName);
//        employee.setTel(tel);
//        employee.setEmail(email);
//        employee.setSalary(salary);
//        employee.setPosition(position);
//        String update = "UPDATE INTO employees SET ('id','firstName','lastName'," + "''Tel','email','salary','position') VALUES ('" + employee.getId() + "''" + employee.getFirstname() + "''" + employee.getLastname() + "'" + employee.getTel() + "''" + employee.getEmail() +"','" +employee.getSalary()+"''"+employee.getPosition()+"');";
//        database.getStatement().execute(update);
//        System.out.println("Passenger Edit successfuly");
    }

    public static Employee getEmployeesById(Database database, Scanner s) throws SQLException {
        Employee employee = new Employee();
        String get = "SELECT id, firstName, lastName, Tel, email, salary, position FROM employees WHERE id = " + employee.getId() + ";";

        ResultSet rs = database.getStatement().executeQuery(get);


            Employee e = new Employee();
            e.setId(Integer.parseInt(rs.getString("id")));
            e.setFirstname(rs.getString("firstName"));
            e.setLastname(rs.getString("lastName"));
            e.setTel(rs.getString("Tel"));
            e.setEmail(rs.getString("email"));
            e.setSalary(rs.getDouble("salary"));
            e.setPosition(rs.getString("position"));
            return e;



        //employee.print();

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

    public static void findEmployeByName(Database database, Scanner s) throws SQLException {
        System.out.println("Enter First Name: ");
        String firstName = s.next();
        System.out.println("Enter Last Name: ");
        String lastName = s.next();
        String get = "SELECT id, firstName, lastName, Tel, email FROM employees WHERE firstName = '" + firstName + "'";
        ResultSet rs = database.getStatement().executeQuery(get);
        Employee employee = new Employee();
        while (rs.next()) {
            Employee e = new Employee();
            e.setId(Integer.parseInt(rs.getString("id")));
            e.setFirstname(rs.getString("firstName"));
            e.setLastname(rs.getString("lastName"));
            e.setTel(rs.getString("Tel"));
            e.setEmail(rs.getString("email"));
            if (e.getLastname().equals(lastName)) employee = e;
            break;
        }
        employee.print();
    }

    public static void printAllEmployees(Database database) throws SQLException {
        ArrayList<Employee> employees = Database.getAllEmployees(database);
        System.out.println("------------------------\n");
        for (Employee employee : employees) {
            System.out.println("id: " + employee.getId());
            System.out.println("firstName: " + employee.getFirstname());
            System.out.println("lastName: " + employee.getLastname());
            System.out.println("tel: " + employee.getTel());
            System.out.println("email: " + employee.getEmail());
        }
        System.out.println("-------------------------\n");
    }

    public static void deleteEmployees(Database database, Scanner s) throws SQLException {
        System.out.println("Enter Passenger ID: \n(-1 to get passenger by name)");
        int id = s.nextInt();
        Employee employee;
        if (id != -1) {
            employee = getEmployeesByIdName(database, s);
        } else {
            String get = "SELECT 'id','firstName','lastName','Tel','email','salary','position';";
            ResultSet rs = database.getStatement().executeQuery(get);
            Employee e = new Employee();
            rs.next();
            try {
                e.setId(Integer.parseInt(rs.getString("id")));
            } catch (SQLException exception) {
                throw new SQLException("ID not found");
            }
            e.setFirstname(rs.getString("firstName"));
            e.setLastname(rs.getString("lastName"));
            e.setEmail(rs.getString("email"));
            employee = e;
        }
        String delete = "DELETE FROM passanger WHERE id = " + employee.getId();
        database.getStatement().execute(delete);
        System.out.println("Passenger Deleted successfuly");
    }

    public static Employee getEmployeesByIdName(Database database, Scanner s) throws SQLException {
        System.out.println("Enter First Name: ");
        String firstName = s.next();
        System.out.println("Enter Last Name: ");
        String lastName = s.next();
        String get = "SELECT id, firstName, lastName, Tel, email,salary,position FROM employees WHERE firstName = '" + firstName + "'";
        ResultSet rs = database.getStatement().executeQuery(get);
        Employee employee = new Employee();
        while (rs.next()) {
            Employee e = new Employee();
            e.setId(Integer.parseInt(rs.getString("id")));
            e.setFirstname(rs.getString("firstName"));
            e.setLastname(rs.getString("lastName"));
            e.setTel(rs.getString("Tel"));
            e.setEmail(rs.getString("email"));
            e.setSalary(rs.getDouble("salary"));
            e.setPosition(rs.getString("position"));
            if (e.getLastname().equals(lastName)) employee = e;
            break;
        }
        return employee;
    }
}
