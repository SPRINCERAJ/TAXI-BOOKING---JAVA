package PACKAGE;
import java.util.*;
class Taxi
{
	UUID uuid=UUID.randomUUID();
	private String id=uuid.toString();
	private char Cur_pos;
	private int avail_time;
	private int salary;
	public Taxi(char cur_pos, int avail_time) {
		Cur_pos = cur_pos;
		this.avail_time = avail_time;
		this.salary=0;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "TAXI ID:" + id + "    Current Postition:" + Cur_pos + "    Available Time:" + avail_time+ "   Salary:" + salary;
	}
	public char getCur_pos() {
		return Cur_pos;
	}
	public void setCur_pos(char cur_pos) {
		Cur_pos = cur_pos;
	}
	public int getAvail_time() {
		return avail_time;
	}
	public void setAvail_time(int avail_time) {
		this.avail_time = avail_time;
	}
}
public class TaxiBooking {
	public static void getTaxiDetails(HashMap<String,Taxi> taxi )
	{
	int i=1;
	for(Map.Entry t:taxi.entrySet())
	{
		System.out.println("************  TAXI"+i+" ***********");
		System.out.println(t.getValue());
		i++;
	}
	}
	public static int calculateSalary(char source,char destination)
	{
		if((int)source>(int)destination)
		{
			char temp=source;
			source=destination;
			destination=temp;
		}
		int temp=Math.abs((int)source-(int)destination);
		temp*=15;
		temp-=5;
		temp*=10;
		temp+=5*500;
		return temp;
	}
	public static void bookTaxi(HashMap<String,Taxi> taxi,Scanner sc )
	{
	System.out.println("Enter your name:");
	sc.nextLine();
	String pass_name=sc.nextLine();
	System.out.println("Enter your ID:");
	String pass_id=sc.nextLine();
	System.out.println("Enter your Start point:");
	char startpoint=sc.next().charAt(0);
	System.out.println("Enter your Destination:");
	char destination=sc.next().charAt(0);
	System.out.println("Enter your Pickup time:");
	int pickuptime=sc.nextInt();
	boolean booked=false;
	int min=Integer.MAX_VALUE;
	for(Map.Entry t:taxi.entrySet())
	{
		Taxi cab=(Taxi) t.getValue();
		for(Map.Entry t1:taxi.entrySet())
		{
			Taxi cab1=(Taxi) t1.getValue();
		if(cab1.getAvail_time() <= pickuptime)
		{
			int temp=calculateSalary(cab1.getCur_pos(),startpoint);
			if(temp<min)
			{
				cab=cab1;
			}
		}
	    }
			cab.setSalary(cab.getSalary()+calculateSalary(startpoint,destination));
			char startpoint_temp=startpoint;
			char destination_temp=destination;
			if((int)startpoint>(int)destination)
			{
				char temp=startpoint;
				startpoint=destination;
				destination=temp;
			}	
			if(Math.abs((int)startpoint-(int)destination)+pickuptime>24)	
				cab.setAvail_time((Math.abs((int)startpoint-(int)destination)+pickuptime)-24);
			else
				cab.setAvail_time(Math.abs((int)startpoint-(int)destination)+pickuptime);
			cab.setCur_pos(destination_temp);
			System.out.println("Ticket Booked!!!");
			System.out.println("TAXI ID:"+""+cab.getId());
			System.out.println("COST:"+""+calculateSalary(startpoint,destination));
			booked=!booked;
			break;
		}
	if(booked==false)
		System.out.println("No Taxi available");
	}
	public static void main(String[] args)
	{
	    Scanner sc=new Scanner(System.in);
	    HashMap<String,Taxi> taxi=new HashMap<>();
	    Taxi obj1=new Taxi('A',6);
	    taxi.put(obj1.getId(), obj1);
	    Taxi obj2=new Taxi('A',6);
	    taxi.put(obj2.getId(), obj2);
	    Taxi obj3=new Taxi('A',6);
	    taxi.put(obj3.getId(), obj3);
	    Taxi obj4=new Taxi('A',6);
	    taxi.put(obj4.getId(), obj4);
	    
	    while(true)
	    {
	        System.out.println("1-Booking 2-Taxi Details");
	        int n=sc.nextInt();
	        
	        switch(n)
	        {
	            case 1:
	                bookTaxi(taxi,sc);
	                break;
	            case 2:
	                getTaxiDetails(taxi);
	                break;
	            default:
	                System.out.println("Invalid choice. Please select 1 or 2.");
	        }
	    }
	}
}
