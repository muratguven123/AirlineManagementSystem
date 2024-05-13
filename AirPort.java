package AirlineMangementSystem;

public class AirPort {
    private int id;
    private String city;

    public AirPort(){}

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public void setCity(String city){
        this.city = city;
    }
    public String getCity(){
        return city;
    }
    public void print(){
        System.out.println("id"+ getId());
        System.out.println("city"+ getCity());

    }

}
