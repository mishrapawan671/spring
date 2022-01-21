package com.example.demo.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;

import com.example.demo.entity.Car;

public class ParkingRepo {
	
	private HashMap<Integer,Car>parkSpace=new HashMap<>();
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
		if(priority.size()==0)
		{
			return null;
		}
		else
		{   
			
		 	 c.setSlot(priority.poll());
		 	 parkSpace.put(c.getSlot(),c);
		 	return c;
		}
		
		
	}
	
	public Car removeCar(int slot)
	{
		if(parkSpace.containsKey(slot))
		{
			Car c=parkSpace.remove(slot);
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
		for(Entry<Integer, Car> e :parkSpace.entrySet())
		{
			list.add(e.getValue());
		}
		return list;
	}

	
	
	

}
