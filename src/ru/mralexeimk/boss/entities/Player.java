package ru.mralexeimk.boss.entities;

import ru.mralexeimk.boss.Entity;

public class Player extends Entity {
    private long last_time_hit_boss; //для того, чтобы каждый игрок не мог ударять босса больше 1 раза за секунду
    public Player(double damage, double hp) {
        super(damage, hp);
        last_time_hit_boss = 0;
    }

    public Long getLastTimeHitBoss() {
        return last_time_hit_boss;
    }

    public boolean canHitBoss() {
        long cur = System.currentTimeMillis();
        if(cur - last_time_hit_boss >= 1000) return true;
        return false;
    }

    @Override
    public void damageEntity(Entity en) {
        if(en instanceof Boss) {
            Boss boss = (Boss) en;
            if(canHitBoss()) super.damageEntity(en);
        }
        else super.damageEntity(en);
    }
}
