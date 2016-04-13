package controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import database.MenuDB;
import entity.Menu;
import java.io.IOException;

public class MenuController {
	private MenuDB menuDb = new MenuDB();
	private String filename = "menu.txt";
	Scanner sc = new Scanner(System.in);
	int option = -1;

	public void createItem() {
		System.out.println();
		System.out.println("~~~~~CREATE NEW ITEM~~~~~");

		// Initialize attributes of order
		Integer id = 0;
		String name = "", description = "";
		double price = 0;

		// Prompt user to input item details
		Menu item = new Menu();

		System.out.println();
		System.out.println("Please enter the menu item details: ");
		System.out.println();

		ArrayList al;
		try {
			al = menuDb.readMenu(filename);
			String repeated = new String(new char[92]).replace("\0", "-");
			System.out.println(repeated);
			System.out.printf("%3s %23s %53s %10s", "ID", "NAME", "DESCRIPTION", "S$ PRICE");
			System.out.println();
			System.out.println(repeated);
			for (int i = 0; i < al.size(); i++) {
				Menu menu = (Menu) al.get(i);
				System.out.format("%3s %23s %53s %10s", menu.getID(), menu.getName(), menu.getDescription(),
						menu.getPrice());
				System.out.println();
			}
			System.out.println(repeated);

			System.out.print("Item Name: ");
			name = sc.nextLine();

			System.out.print("Item Description: ");
			description = sc.nextLine();

			System.out.print("Item Price: ");
			price = sc.nextDouble();

			do {
				System.out.println();
				System.out.print("Menu Item Confirmation - (1) Yes   (2) No: ");
				System.out.println();
				try {
					option = sc.nextInt();
					item.setName(name);
					item.setDescription(description);
					item.setPrice(price);
				} catch (InputMismatchException e) {
					System.out.println("You have entered an invalid input. Please try again.");
					sc.next();
				}
			} while (option < 1 || option > 2);

			try {
				Menu m = (Menu) al.get(al.size() - 1);
				item.setID(al.size() + 1);

				if (option == 1) {
					item.setID(al.size() + 1);
					System.out.println(repeated);
					System.out.printf("%3s %23s %53s %10s", "ID", "NAME", "DESCRIPTION", "S$ PRICE");
					System.out.println();
					System.out.println(repeated);

					System.out.format("%3s %23s %53s %10s", al.size() + 1, name, description, price);
					System.out.println();

					System.out.println(repeated);

					al.add(item);

					// write item record/s to file.
					menuDb.saveMenu(filename, al);
					System.out.println();
					System.out.println("Item stored successfully!");

				} else {
					System.out.println("Item not saved.");
				}

			} catch (IOException e) {
				System.out.println("IOException > " + e.getMessage());
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	
	
	
	public void updateItem() {
		System.out.println();
		System.out.println("~~~~~UPDATE ITEM~~~~~");
		System.out.println();

		// Initialize attributes
		Integer id = 0;
		String name = "", description = "";
		double price = 0;

		// Prompt user to input item details
		ArrayList al;
		try {
			al = menuDb.readMenu(filename);
			String repeated = new String(new char[92]).replace("\0", "-");
			System.out.println(repeated);
			System.out.printf("%3s %23s %53s %10s", "ID", "NAME", "DESCRIPTION", "S$ PRICE");
			System.out.println();
			System.out.println(repeated);
			for (int i = 0; i < al.size(); i++) {
				Menu menu = (Menu) al.get(i);
				System.out.format("%3s %23s %53s %10s", menu.getID(), menu.getName(), menu.getDescription(),
						menu.getPrice());
				System.out.println();
			}
			System.out.println(repeated);

			System.out.print("Enter Item ID: ");
			id = sc.nextInt();

			getItem();

			for (int i = 0; i < al.size(); i++) {
				Menu updateitem = (Menu) al.get(i);

				if (id.equals(updateitem.getID())) {
					// UPDATE MENU ITEMS HERE
						
					System.out.print("\n~~~~~~~~~ MENU OPTIONS ~~~~~~~~~~\n|");
					System.out.format("%10s%8s", "1. ", "Update Name\t\t|\n|");
					System.out.format("%10s%8s", "2. ", "Update Description\t|\n|");
					System.out.format("%10s%8s", "3. ", "Update Price\t\t|\n");
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					
					do {
						System.out.print("Select an option: ");
						try {
							option = sc.nextInt();
							if (option < 1 || option > 3) 
								System.out.println("You have not selected option between 1-3. Please try again.");
						} catch (InputMismatchException e) {
							System.out.println("You have entered an invalid input. Please try again.");
							sc.next();
						}
					} while (option < 1 || option > 3);
					switch (option) {
					case 1:
						System.out.println();
						sc.nextLine();
						do {
							System.out.print("Update Item Name: ");
							try {
								name = sc.nextLine();
								if ((name.equals(" "))) {
									System.out.println();
									System.out.println("You have not input the new item name. Please try again.");
									System.out.println();
								} else {
									updateitem.setName(name);
									updateitem.getDescription();
									updateitem.getPrice();
								} 
								} catch (InputMismatchException e) {
									System.out.println("You have entered an invalid input. Please try again.");
									sc.next();
						} 
						} while (name.equals(" "));
						break;
					case 2:
						System.out.println();
						sc.nextLine();
						do {
							System.out.print("Update Item Description: ");
							try {
								description = sc.nextLine();
								if ((description.equals(" "))) {
									System.out.println();
									System.out.println("You have not input the new item description. Please try again.");
									System.out.println();
								} else {
									updateitem.setDescription(description);
									updateitem.getName();
									updateitem.getPrice();
								} 
								} catch (InputMismatchException e) {
									System.out.println("You have entered an invalid input. Please try again.");
									sc.next();
						} 
						} while (description.equals(" "));
						break;
					case 3:
						System.out.println();
						do {
							System.out.print("Update Item Price: ");
							try {
								price = sc.nextDouble();
								if (price == 0) {
									System.out.println();
									System.out.println("Invalid price. Please try again.");
									System.out.println();
								} else {
									updateitem.setPrice(price);
									updateitem.getName();
									updateitem.getDescription();
								} 
								} catch (InputMismatchException e) {
									System.out.println();
									System.out.println("You have entered an invalid input. Please try again.");
									sc.next();
						} 
						} while (price == 0);						
						break;
						}
					
					
					if ((name.equals(" ")) || (description.equals(" "))) {
						System.out.println("Please enter a valid item name / description.");
					} else {
						System.out.println();
						System.out.println(repeated);
						System.out.printf("%3s %23s %53s %10s", "ID", "NAME", "DESCRIPTION", "S$ PRICE");
						System.out.println();
						System.out.println(repeated);

						System.out.format("%3s %23s %53s %10s", id, updateitem.getName(), updateitem.getDescription(),
								updateitem.getPrice());
						System.out.println();

						System.out.println(repeated);
						try {
							menuDb.saveMenu(filename, al);
							System.out.println();
							System.out.println("Menu Item ID " + id + " updated accordingly!");

						} catch (IOException e) {
							System.out.println("IOException > " + e.getMessage());
						}
					}

				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	
	
	
	public void removeItem() {
		System.out.println();
		System.out.println("~~~~~REMOVE ITEM~~~~~");
		System.out.println();

		// Initialize attributes
		Integer id = 0;

		// Prompt user to input item details
		ArrayList al;
		try {
			al = menuDb.readMenu(filename);
			String repeated = new String(new char[92]).replace("\0", "-");
			System.out.println(repeated);
			System.out.printf("%3s %23s %53s %10s", "ID", "NAME", "DESCRIPTION", "S$ PRICE");
			System.out.println();
			System.out.println(repeated);
			for (int i = 0; i < al.size(); i++) {
				Menu menu = (Menu) al.get(i);
				System.out.format("%3s %23s %53s %10s", menu.getID(), menu.getName(), menu.getDescription(),
						menu.getPrice());
				System.out.println();
			}
			System.out.println(repeated);

			System.out.print("Enter Item ID: ");
			id = sc.nextInt();
			getItem();

			do {
				System.out.println();
				System.out.print("Remove Item Confirmation - (1) Yes   (2) No: ");

				try {
					option = sc.nextInt();
					System.out.println();
					for (int i = 0; i < al.size(); i++) {
						Menu removeitem = (Menu) al.get(i);
						if (id.equals(removeitem.getID()) && option == 1) {
							removeitem.getName();
							removeitem.getDescription();
							removeitem.getPrice();

							System.out.println(repeated);
							System.out.printf("%3s %23s %53s %10s", "ID", "NAME", "DESCRIPTION", "S$ PRICE");
							System.out.println();
							System.out.println(repeated);

							System.out.format("%3s %23s %53s %10s", id, removeitem.getName(),
									removeitem.getDescription(), removeitem.getPrice());
							System.out.println();

							System.out.println(repeated);

							al.remove(i);
						}
					}
				} catch (InputMismatchException e) {
					System.out.println("You have entered an invalid input. Please try again.");
				}
			} while (option < 1 || option > 2);

			try {
				menuDb.saveMenu(filename, al);
				System.out.println();
				System.out.println("Menu item ID " + id + " removed!");

			} catch (IOException e) {
				System.out.println("IOException > " + e.getMessage());
			}

		} catch (IOException e1) {
			e1.printStackTrace();
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

	public Menu getItemById(Menu menu) {
		ArrayList items = getItem();
		Menu item = new Menu();
		
		for(int i = 0; i < items.size(); i++) {
			item = (Menu) items.get(i);
			
			if(item.getID().equals(menu.getID()))
				return item;
		}
		return null;
	}
}
