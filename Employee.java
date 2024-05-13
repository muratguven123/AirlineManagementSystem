package AirlineMangementSystem;

public class Employee {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String tel;
    private double salary;
    private String position;

    public Employee(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    public void print(){
        System.out.println("id"+ getId());
        System.out.println("firstName"+ getFirstname());
        System.out.println("lastName"+ getLastname());
        System.out.println("tel"+ getTel());
        System.out.println("email"+ getEmail());
    }
}
