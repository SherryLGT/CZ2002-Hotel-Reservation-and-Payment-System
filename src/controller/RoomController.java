package controller;

public class RoomController {

	// Request from ReservationController by Geraldine
	/*
	 * print rooms with details when status is not "Under Maintenance"
	 */
	public void printRooms() {
		System.out.println("Rooms Avaliable: ");
		System.out.println("\n");
	}

	// Request from Occupancy Report by Geraldine
	/*
	 * print rooms with vacancy or occupied
	 */
	public void printRoomStatus() {
		System.out.println("Vacant :");
		for (int i = 0; i < 10; i++) {
			System.out.println("Rooms : 02-03, 03-04, 03-05");			
		}

		System.out.println("Occupied :");
		for (int i = 0; i < 10; i++) {
			System.out.println("Rooms : 02-04, 05-04, 05-05");		
		}

	}

	/*
	 * print individual room type status
	 */
	public void printRoomOccupancy() {
		System.out.println("Single : Number : 10 out of 20");
		for (int i = 0; i < 10; i++) {
			System.out.println("Rooms : 02-03, 03-04, 03-05");
		}
		System.out.println("Double : Number : 5 out of 10");
		for (int i = 0; i < 10; i++) {
			System.out.println("Rooms : 02-04, 05-04, 05-05");
		}
	}
}
