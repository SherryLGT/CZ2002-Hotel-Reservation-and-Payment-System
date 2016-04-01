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
			ArrayList al = menuDb.readMenu(filename);
			for (int i = 0; i < al.size(); i++){
				Menu m = (Menu) al.get(i);
			}

			al.add(item);
			
			//write item record/s to file.
			menuDb.saveMenu(filename, al);
			System.out.println("Item stored successfully!");
			
		} catch (IOException e){
			System.out.println("IOException > " + e.getMessage());
		}
		
	}
	
	
	public void updateItem(){
		System.out.println("~~~~~UPDATE ITEM~~~~~");
		
		// Initialize attributes 
		Integer id = 0;
		String name = "", description = "";
		double price = 0;
		
		// Prompt user to input item details
		Menu item = null;
		System.out.println("Enter Item ID");
		id = sc.nextInt();
		
		ArrayList al = getItem();
		for(int i = 0; i < al.size(); i++){
			Menu updateitem = (Menu) al.get(i);
			
			System.out.println(id + "_" + updateitem.getID());
			System.out.println(id.equals(updateitem.getID()));
			if (id.equals(updateitem.getID())){
				
				// UPDATE MENU ITEMS HERE
				System.out.println("Update Item Name: ");
				name = sc.nextLine();
				System.out.println("Update Item Description: ");
				description = sc.nextLine();
				System.out.println("Update Item Price: ");
				price = sc.nextDouble();
				if ((name.equals("")) || (description.equals(""))) {
					System.out.println("Please enter a valid item name / description.");
				} else {
					item.setName(name);
					item.setDescription(description);
					item.setPrice(price);
				}
			}
			System.out.println("ID Invalid.");
		}
	}
	
	public ArrayList getItem() {
		ArrayList al = null;
		try {
			al = menuDb.readMenu(filename);
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
		return al;
	}
	
	
	
	
	
	
	

}
