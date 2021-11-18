package ru.mralexeimk.boss.entities;

import ru.mralexeimk.boss.Entity;

import java.util.HashMap;

public class Boss extends Entity {
    private Player target;
    private double mini_damage;
    private double super_damage;

    public Boss(double damage, double hp, Player target, double mini_damage, double super_damage) {
        super(damage, hp);
        this.target = target;
        this.mini_damage = mini_damage;
        this.super_damage = super_damage;
    }

    public double getMiniDamage() {
        return mini_damage;
    }
    public double getSuperDamage() {
        return super_damage;
    }

    public Player getTarget() {
        return target;
    }

    public void setTarget(Player target) {
        this.target = target;
    }
}
