package ru.mralexeimk.boss;

import ru.mralexeimk.boss.entities.Boss;
import ru.mralexeimk.boss.entities.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//тут всё реализовано так, что новые игроки могут присоединяться/ливать во время битвы
public class Arena {
    private List<Player> players;
    private Top top;
    private Boss boss;
    private boolean stopped;

    public Arena() {
        players = new ArrayList<>();
        top = new Top();
        boss = new Boss(5, 99999, null, 1, 30);
        stopped = true;
    }

    public void addPlayer(Player p) {
        players.add(p);
        top.addPlayerToTop(p);
    }

    public void removePlayer(Player p) {
        if(players.contains(p)) {
            players.remove(p);
            top.removePlayerFromTop(p);
        }
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getPlayer(int index) {
        return players.get(index);
    }

    public Boss getBoss() {
        return boss;
    }

    public int getPlayersCount() {
        return getPlayers().size();
    }

    public void start() {
        stopped = false;
        Thread th = new Thread(() -> {
            int i = 0; //сколько секунд прошло
            while(!stopped) {
                if(getPlayersCount() == 0) stopped = true; //ну, считаем битву с боссом оконченной, если все погибли и за время битвы никто не успел присоединиться
                if(getBoss().getHp() == 0) stopped = true; //WIN
                if(i >= 60*10) stopped = true;

                if(getBoss().getTarget() == null) {
                    int rand = new Random().nextInt(getPlayersCount());
                    Player p = getPlayer(rand);
                    getBoss().setTarget(p);
                }
                getBoss().damageEntity(getBoss().getTarget()); //наносим урон типа
                if(getBoss().getTarget().getHp() == 0) removePlayer(getBoss().getTarget());

                if(i%60 == 0) {
                    for(Player p : getPlayers()) {
                        getBoss().damageEntity(p); //наносим мини-урон всем
                        if(p.getHp() == 0) removePlayer(p);
                    }
                }
                if(i%120 == 0) {
                    for(Player p : top.getTopPlayers()) {
                        getBoss().damageEntity(p); //наносим супер-урон топерам
                        if(p.getHp() == 0) removePlayer(p);
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ++i;
            }
        });
    }
}
