package ru.mralexeimk.boss;

import ru.mralexeimk.boss.entities.Player;

public class Main {
    public static void main(String[] args) {
        Arena arena = new Arena();
        arena.addPlayer(new Player(4, 500));
        arena.addPlayer(new Player(8, 300));
        arena.addPlayer(new Player(7, 50));
        arena.start();
    }
}
