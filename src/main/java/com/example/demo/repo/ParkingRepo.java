package com.example.demo.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;

import com.example.demo.entity.Car;

public class ParkingRepo {
	
	private HashMap<String,Car>parkSpace=new HashMap<>();
	private PriorityQueue<Integer>priority=new PriorityQueue<>();
	
	public ParkingRepo()
	{
		for(int i=1;i<=100;i++)
		{
			priority.add(i);
		}
	}
	
	public Car addCar(Car c)
	{
		if(priority.size()==0||parkSpace.containsKey(c.getRegistration()))
		{
			return null;
		}
		else
		{   
			
		 	 c.setSlot(priority.poll());
		 	 parkSpace.put(c.getRegistration(),c);
		 	return c;
		}
		
		
	}
	
	public Car removeCar(int slot)
	{
		String reg="N";
		for(Entry<String, Car> e :parkSpace.entrySet())
		{
			if(e.getValue().getSlot()==slot)
			{
				reg=e.getValue().getRegistration();
				break;
			}
		}
		if(parkSpace.containsKey(reg))
		{
			Car c=parkSpace.remove(reg);
			priority.offer(c.getSlot());
			
			return c;
			
		}
		else
		{
			return null;
		}
		
		
	}
	
	public ArrayList<Car> getAllCar()
	{
		ArrayList<Car>list=new ArrayList<>();
		for(Entry<String, Car> e :parkSpace.entrySet())
		{
			list.add(e.getValue());
		}
		return list;
	}

	public String removeByreg(String reg) {
		    
		
		      Car c=parkSpace.remove(reg);
		      System.out.println("removed from reo "+c);
		      if(c==null)
		      {
		    	  return "No Car With this Registration";
		      }
		      else
		      {
		    	  return "Car Removed";
		      }
	
		
	}

	
	
	

}
