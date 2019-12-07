package entity.enemy;

import entity.MapObject;
import entity.Player;
import tileMap.TileMap;

public class Enemy extends MapObject {

    protected int health;
    protected int maxHealth;
    protected boolean dead;
    protected int damage;

    protected int rewardedHealth;
    protected int rewardedFireballPower;

    protected boolean flinching;
    protected long flinchTimer;

    public Enemy(TileMap tm) {
        super(tm);
    }

    public boolean isDead() { return dead; }
    public int getDamage() { return damage; }
    public int getRewardedHealth() {return rewardedHealth;}
    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getRewardedFireballPower() {
        return rewardedFireballPower;
    }

    public boolean isFlinching() {
        return flinching;
    }

    private void reward(Player player) {
        player.rewardHealth(rewardedHealth);
        player.rewardFireballs(rewardedFireballPower);
    }

    public void hit(Player player, int damage) {
        if(dead || flinching) return;
        health -= damage;
        if(health < 0) health = 0;
        if(health == 0) {dead = true; reward(player);}
        flinching = true;
        flinchTimer = System.nanoTime();
    }

    public void update() {}

}














