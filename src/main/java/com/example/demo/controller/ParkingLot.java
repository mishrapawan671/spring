package com.example.demo.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Car;
import com.example.demo.repo.ParkingRepo;

@Controller
public class ParkingLot {
	
   @Autowired
	ParkingRepo parkingRepo;
	
	@GetMapping("/")
	public String getHome(HttpSession session,Model m )
	{
		session.setAttribute("flag",false);
		ArrayList<Car> list=parkingRepo.getAllCar();
		m.addAttribute("car",list);
		System.out.println(list);
		return "index";
	}
	
	@GetMapping("/delete/{slot}")
	public String delete(@PathVariable("slot") String slot )
	{
		System.out.println(slot);
  	 System.out.println(parkingRepo.removeCar(Integer.parseInt(slot)));
		
		return "redirect:/";
	}
	
	
	
	@GetMapping("parkcar")
	public String getSlot(HttpSession session )
	{
		session.setAttribute("flag",false);
		session.removeAttribute("msg");
		session.removeAttribute("CarDetails");
		return "parkcar";
	}
	@PostMapping("parkcar")
	public String postSlot(@ModelAttribute Car c,HttpSession session )
	{
		Car car;
		if(!c.validate(c))
		{   
			session.setAttribute("flag",true);
			session.setAttribute("msg","Invalid Details");
		}
		else
		{
			 car=parkingRepo.addCar(c);
		if(car!=null)
		{
		session.setAttribute("flag",true);
		session.setAttribute("msg","Slot Succesfully alloted ");
		}
		else
		{
			
			System.out.println(car);
			session.setAttribute("flag",true);
			session.setAttribute("msg","Sorry No Slot is Available");
			
			
		}
		}
		
		return "parkcar";
	}

}
