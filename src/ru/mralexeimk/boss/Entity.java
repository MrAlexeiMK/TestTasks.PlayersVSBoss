package ru.mralexeimk.boss;

public abstract class Entity {
    private double damage;
    private double hp;

    public Entity(double damage, double hp) {
        this.damage = damage;
        this.hp = hp;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        if(hp < 0) hp = 0;
        this.hp = hp;
    }

    public void damageEntity(Entity en) {
        en.setHp(en.getHp() - damage);
    }
}
