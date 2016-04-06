package entity;

import java.io.Serializable;

/**
 * Represents the creation, update and removal of the hotel's Menu Item.
 * 
 * @author Tan Wanyi Cherry
 * @version 1.0
 * @since 2016-03-30
 */

public class Menu implements Serializable {
	private int id;
	private String name;
	private String description;
	private double price;

	public Menu() {

	}

	/**
	 * Menu items can be created, updated and removed. Each menu item has an id
	 * which is a unique identifier. Furthermore, the item is also represented
	 * by the name, description and price.
	 * 
	 * @param id
	 *            This menu item's id.
	 * @param name
	 *            This menu item's name.
	 * @param description
	 *            This menu item's description.
	 * @param price
	 *            This menu item's price.
	 */
	public Menu(Integer id, String name, String description, double price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	/**
	 * Gets the menu item's id.
	 * 
	 * @return this item's id.
	 */
	public Integer getID() {
		return id;
	}

	/**
	 * Changes the menu item's id.
	 * 
	 * @param id
	 *            This item's id.
	 */
	public void setID(int id) {
		this.id = id;
	}

	/**
	 * Gets the menu item's name.
	 * 
	 * @return this item's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Changes the menu item's name.
	 * 
	 * @param name
	 *            This item's name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the menu item's description.
	 * 
	 * @return this item's description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Changes the menu item's description.
	 * 
	 * @param description
	 *            This item's description.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the menu item's price.
	 * 
	 * @return this item's price.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Changes the menu item's price.
	 * 
	 * @param description
	 *            This item's price.
	 */
	public void setPrice(double price) {
		this.price = price;
	}

}
