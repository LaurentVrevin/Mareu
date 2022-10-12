package fr.laurentvrevin.mareu.service;

import fr.laurentvrevin.mareu.model.Rooms;

public class DummyRoomsGenerator {
    //récupère les 4 salles :
    public static Rooms[] getRooms() {
        Rooms room1 = new Rooms("Thousand Sunny");
        Rooms room2 = new Rooms("Oro Jackson");
        Rooms room3 = new Rooms("Moby Dick");
        Rooms room4 = new Rooms("Red Force");
        return new Rooms[]{room1, room2, room3, room4};
    }
}
