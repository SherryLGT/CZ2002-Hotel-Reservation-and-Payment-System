package controller;

import java.util.ArrayList;
import java.util.Scanner;
import database.MenuDB;
import entity.Menu;
import java.io.IOException;

public class MenuController {
	private MenuDB menuDb = new MenuDB();
	private String filename = "menu.txt";
	Scanner sc = new Scanner (System.in);
	
	public void createItem(){
		System.out.println("~~~~~CREATE NEW ITEM~~~~~");
		
		// Initialize attributes of order
		Integer id = 0;
		String name = "", description = "";
		double price = 0;
		
		// Prompt user to input item details
		Menu item =  new Menu();
		System.out.println("Please enter the menu item details: ");
		
		ArrayList al;
		try {
			al = menuDb.readMenu(filename);
			String repeated = new String(new char[92]).replace("\0", "-");
			System.out.println(repeated);
		    System.out.printf("%3s %23s %53s %10s", "ID", "NAME", "DESCRIPTION", "S$ PRICE");
		    System.out.println();
		    System.out.println(repeated);
			for (int i = 0 ; i < al.size() ; i++) {
					Menu menu = (Menu)al.get(i);
					System.out.format("%3s %23s %53s %10s", menu.getID(), menu.getName(), menu.getDescription(), menu.getPrice());
					System.out.println();
			}
				    System.out.println(repeated);
				
			do {
				System.out.println("Item Name: ");
				name = sc.nextLine();
				if(name.equals("")){
					System.out.println("Please enter a valid name.");
				} 
				else {
					item.setName(name);
				}
			} while(name.equals(""));
			
			System.out.println("Item Description: ");
			description = sc.nextLine();
			item.setDescription(description);
			
			System.out.println("Item Price: ");
			price = sc.nextDouble();
			item.setPrice(price);
			
			try {
				//ArrayList al = menuDb.readMenu(filename);
				Menu m = (Menu) al.get(al.size()-1);
				item.setID(al.size()+1);
				al.add(item);
				
				//write item record/s to file.
				menuDb.saveMenu(filename, al);
				System.out.println("Item stored successfully!");
				
			} catch (IOException e){
				System.out.println("IOException > " + e.getMessage());
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	
	public void updateItem(){
		System.out.println("~~~~~UPDATE ITEM~~~~~");
		
		// Initialize attributes 
		Integer id = 0;
		String name = "", description = "";
		double price = 0;
		
		// Prompt user to input item details
		
		System.out.println("Enter Item ID: ");
		id = sc.nextInt();
		
		ArrayList al = getItem();
		for(int i = 0; i < al.size(); i++){
			Menu updateitem = (Menu) al.get(i);
			
			if (id.equals(updateitem.getID())){
				
				// UPDATE MENU ITEMS HERE
				sc.nextLine();
				System.out.println("Update Item Name: ");
				name = sc.nextLine();

				System.out.println("Update Item Description: ");
				description = sc.nextLine();

				System.out.println("Update Item Price: ");
				price = sc.nextDouble();
				
				if ((name.equals(" ")) || (description.equals(" "))) {
					System.out.println("Please enter a valid item name / description.");
				} else {
					updateitem.setName(name);
					updateitem.setDescription(description);
					updateitem.setPrice(price);
				}
				
			}
		}
		try {
			menuDb.saveMenu(filename, al);
			System.out.println("Menu Item ID " + id + " updated accordingly!");
			
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
	}
	
	public ArrayList getItem(){
		ArrayList al = null;
		try {
			al = menuDb.readMenu(filename);
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
		return al;
	}
	
	
	public void removeItem(){
	System.out.println("~~~~~REMOVE ITEM~~~~~");
	
	// Initialize attributes 
	Integer id = 0;
			
	// Prompt user to input item details

	System.out.println("Enter Item ID: ");
	id = sc.nextInt();
	
	ArrayList al = getItem();
	for (int i = 0; i < al.size(); i++) {
		Menu removeitem = (Menu) al.get(i);
		
		if (id.equals(removeitem.getID())){
			removeitem.getName();
			removeitem.getDescription();
			removeitem.getPrice();
			al.remove(i);
		}
	}
	
	try {
		menuDb.saveMenu(filename, al);
		System.out.println("Menu item ID " + id + " removed!");
		
	} catch (IOException e) {
		System.out.println("IOException > " + e.getMessage());
	}

	
	}
	


}
