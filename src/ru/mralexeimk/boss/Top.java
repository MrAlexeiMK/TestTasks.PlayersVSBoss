package ru.mralexeimk.boss;

import ru.mralexeimk.boss.entities.Player;

import java.util.ArrayList;
import java.util.List;

public class Top {
    private List<Player> top_players;
    private Player top100_player; //храним самого последнего игрока в топе, чтобы некоторые вещи быстренько делать

    public Top() {
        top_players = new ArrayList<>();
        top100_player = null;
    }

    public List<Player> getTopPlayers() {
        return top_players;
    }

    public int getTopPlayerCount() {
        return top_players.size();
    }

    public void setTop100Player(Player p) {
        top100_player = p;
    }

    public Player getTop100Player() {
        return top100_player;
    }

    //ну, если у всех будут разные уроны, то будет работать за O(1) благодаря top100_player
    public boolean isTop(Player p) {
        if(p.getDamage() > top100_player.getDamage()) return true;
        if(p.getDamage() == top100_player.getDamage()) return getTopPlayers().contains(p);
        return false;
    }

    public void addPlayerToTop(Player p) {
        if(getTopPlayerCount() < 100) {
            getTopPlayers().add(p);
            updateTop100Player();
        }
        else if(p.getDamage() > top100_player.getDamage()) {
            getTopPlayers().remove(top100_player);
            getTopPlayers().add(p);
            updateTop100Player();
        }
    }

    public void removePlayerFromTop(Player p) {
        if(isTop(p)) {
            if(p.equals(top100_player)) {
                getTopPlayers().remove(p);
                updateTop100Player();
            }
            else getTopPlayers().remove(p);
        }
    }

    public void updateTop100Player() {
        double min = Double.MAX_VALUE;
        for(Player pl : getTopPlayers()) {
            if(pl.getDamage() < min) {
                min = pl.getDamage();
                top100_player = pl;
            }
        }
    }
}
