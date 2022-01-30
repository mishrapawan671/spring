package com.example.demo.controller;

import java.lang.ProcessBuilder.Redirect;
import java.net.http.HttpRequest;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Car;
import com.example.demo.repo.ParkingRepo;

@Controller
public class ParkingLot {

	@Autowired
	ParkingRepo parkingRepo;

	@GetMapping("/")
	public ModelAndView getHome(HttpSession session, ModelAndView m) {
		m.clear();
		session.setAttribute("flag", false);
		ArrayList<Car> list = parkingRepo.getAllCar();
		m.addObject("car", list);
		System.out.println("at home page  "+ list);
		m.setViewName("Index");
		return m;
	}

	@GetMapping("/delete/{slot}")
	public String delete(@PathVariable("slot") String slot) {
		System.out.println(slot);
		System.out.println(parkingRepo.removeCar(Integer.parseInt(slot)));

		return "redirect:/";
	}

	@GetMapping("parkcar")
	public String getSlot(HttpSession session) {
		session.setAttribute("flag", false);
		session.removeAttribute("msg");
		session.removeAttribute("CarDetails");
		return "parkcar";
	}

	@PostMapping("parkcar")
	public ModelAndView postSlot(@ModelAttribute Car c, HttpSession session, ModelAndView mv) {
        mv.clear();    
		mv.setViewName("parkcar");
		if (!c.isValid(c)) 
		{   mv.addObject("msg","Incomplete details");
			mv.addObject("car", null);
		} else {
			
			c = parkingRepo.addCar(c);
			    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");  
			   LocalDateTime now = LocalDateTime.now();  
			        c.setDate(dtf.format(now).toString()); 
			if (c == null) {
				mv.addObject("msg","Car already Parked");
				mv.addObject("car", null);
			} else {
                  mv.addObject("car", c);

			}
		}

		return mv;
	}
	
	
	
	@PostMapping("removeByReg")
	public ModelAndView removeByReg(@ModelAttribute Car c, ModelAndView m) {
		
		
		System.out.println(c);
		
		  if(c.getRegistration().trim().length()==10)
		  {
			 
			 String msg= parkingRepo.removeByreg(c.getRegistration());
			 
			  m.addObject("msg", msg);
			  m.addObject("car", parkingRepo.getAllCar());
			 
			  
		  }
		  else
		  {
			  m.clear();
			  m.addObject("msg", "No Car Found");
		  }
		  m.setViewName("index");
		  
		return m;
	}
	

}
