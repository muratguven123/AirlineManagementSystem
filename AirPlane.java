package AirlineMangementSystem;

public class AirPlane {
    private int id;
    private int economyCapacity;
    private int businessCapacity;
    private String model;

    public AirPlane(){}

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setEconomyCapacity(int economyCapacity) {
        this.economyCapacity = economyCapacity;
    }
    public int getEconomyCapacity() {
        return economyCapacity;
    }
    public void setBusinessCapacity(int businessCapacity) {
        this.businessCapacity = businessCapacity;
    }
    public int getBusinessCapacity() {
        return businessCapacity;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getModel() {
        return model;
    }
    public void print(){
        System.out.println("id"+ getId());
        System.out.println("economyCapacity"+ getEconomyCapacity());
        System.out.println("businessCapacity"+ getBusinessCapacity());
        System.out.println("model"+ getModel());

    }
}
