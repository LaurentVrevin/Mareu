package fr.laurentvrevin.mareu.service;

import fr.laurentvrevin.mareu.model.Rooms;

public class DummyRoomsGenerator {
    //récupère les 10 salles :
    public static Rooms[] getRooms() {
        Rooms room1 = new Rooms("Thousand Sunny", "#E9D0C6");
        Rooms room2 = new Rooms("Oro Jackson", "#9ABCA4");
        Rooms room3 = new Rooms("Moby Dick", "#9ab8bc");
        Rooms room4 = new Rooms("Red Force","#9a9bbc");
        Rooms room5 = new Rooms("Vogue Merry","#b59abc");
        Rooms room6 = new Rooms("Thriller Bark","#bc9aa5");
        Rooms room7 = new Rooms("Queen Mama Chamber","#9abcb5");
        Rooms room8 = new Rooms("Polar Tang","#bca89a");
        Rooms room9 = new Rooms("Victoria Punk","#a8bc9a");
        Rooms room10 = new Rooms("Noah","#9aa0bc");
        return new Rooms[]{room1, room2, room3, room4, room5, room6, room7, room8, room9, room10};
    }
}
