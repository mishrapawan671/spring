package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.entity.Car;
import com.example.demo.repo.ParkingRepo;

@Configuration
public class Config {

	@Bean
	public ParkingRepo getParkingRepo()
	{
		return new ParkingRepo();
	}
	@Bean
	public Car getCar()
	{
		return new Car();
	}
}
