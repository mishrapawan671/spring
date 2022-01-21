package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.Car;
import com.example.demo.repo.ParkingRepo;



public class parkingService {
	
	@Autowired
	ParkingRepo parkingRepo;
	 
	public String addcar(Car c)
	{
		
		return " ";
	}
	
	
}
