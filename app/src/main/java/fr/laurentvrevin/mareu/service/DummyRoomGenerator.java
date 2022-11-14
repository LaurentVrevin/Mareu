package fr.laurentvrevin.mareu.service;

import fr.laurentvrevin.mareu.model.Room;

public class DummyRoomGenerator {
    //récupère les 10 salles :
    public static Room[] getRooms() {
        Room room1 = new Room("Thousand Sunny", "#E9D0C6");
        Room room2 = new Room("Oro Jackson", "#9ABCA4");
        Room room3 = new Room("Moby Dick", "#9ab8bc");
        Room room4 = new Room("Red Force","#9a9bbc");
        Room room5 = new Room("Vogue Merry","#b59abc");
        Room room6 = new Room("Thriller Bark","#bc9aa5");
        Room room7 = new Room("Queen Mama Chamber","#9abcb5");
        Room room8 = new Room("Polar Tang","#bca89a");
        Room room9 = new Room("Victoria Punk","#a8bc9a");
        Room room10 = new Room("Noah","#9aa0bc");
        return new Room[]{room1, room2, room3, room4, room5, room6, room7, room8, room9, room10};
    }
}
