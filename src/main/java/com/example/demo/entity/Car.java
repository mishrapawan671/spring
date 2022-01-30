package com.example.demo.entity;


public class Car {
	
	String registration;
	String color;
	int slot;
	String Date ;
	
	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public int getSlot() {
		return slot;
	}

	public void setSlot(int slot) {
		this.slot = slot;
	}

	
	
     public boolean isValid(Car c)
     {
    	 if(c.color.trim().length()==0||c.registration.trim().length()<9)
    	 {
    		 return false;
    	 }
    	  return true;
     }

	@Override
	public String toString() {
		return "Car [slot=" + slot + ", registration=" + registration + ", color=" + color + "]";
	}

	public Car(){
		
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Car(String registration, String color) {
		super();
		this.registration = registration;
		this.color = color;
	}

	
	

}
