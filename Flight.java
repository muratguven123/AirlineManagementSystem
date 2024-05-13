package AirlineMangementSystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Flight {
    private int id;
    private AirPlane airPlane;
    private AirPort origin;
    private AirPort destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private boolean delayed;
    private int bookedEconomy;
    private int bookedBusiness;
    private static Employee[] stuff;
    private Passenger[] passengers;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public Flight() {
        delayed = false;
        stuff = new Employee[10];
        bookedEconomy = 0;
        bookedBusiness = 0;
        int totalCapacity = airPlane.getBusinessCapacity()+airPlane.getEconomyCapacity();
        passengers = new Passenger[totalCapacity];

    }
    public AirPlane getAirPlane() {
        return airPlane;
    }
    public void setAirPlane(AirPlane airPlane) {
        this.airPlane = airPlane;
        int totalCapacity = airPlane.getBusinessCapacity()+airPlane.getEconomyCapacity();
        passengers = new Passenger[totalCapacity];
    }
    public AirPort getOrigin() {
        return origin;
    }
    public void setOrigin(AirPort origin) {
        this.origin = origin;
    }
    public LocalDateTime getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }
    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public boolean isDelayed() {
        return delayed;
    }
    public void setDelayed(boolean delayed) {
        this.delayed = delayed;
    }
    public int getBookedEconomy() {
        return bookedEconomy;
    }
    public void setBookedEconomy(int bookedEconomy) {
        this.bookedEconomy = bookedEconomy;
    }
    public int getBookedBusiness() {
        return bookedBusiness;
    }
    public void setBookedBusiness(int bookedBusiness) {
        this.bookedBusiness = bookedBusiness;
    }
    public Employee[] getStuff() {
        return stuff;
    }
    public static void setStuff(Employee[] stuff) {
        stuff = stuff;
    }
    public Passenger[] getPassengers() {
        return passengers;
    }
    public void setPassengers(Passenger[] passengers) {
        this.passengers = passengers;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void delay()
    {
        delayed = true;
    }

    public AirPort getDestination() {
        return destination;
    }

    public void setDestination(AirPort destination) {
        this.destination = destination;
    }
    public void print(){
        System.out.print(airPlane.getId()+""+airPlane.getModel()+"\t");
        System.out.print(origin.getId()+""+origin.getCity()+"\t");
        System.out.print(destination.getId()+""+destination.getCity()+"\t");
        System.out.print(formatter.format(departureTime)+"\t");
        System.out.print(formatter.format(arrivalTime)+"\t");
        if(delayed){
            System.out.println("delayed\t");
        }else {
            System.out.println("Estimated\t");
        }
        System.out.println(bookedEconomy+"\t\t");
        System.out.println(bookedBusiness);

    }
}
