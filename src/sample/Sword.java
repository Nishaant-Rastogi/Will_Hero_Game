package sample;

public class Sword extends Weapon{
    Sword(int level, int damage, int radius, int x, int y) {
        super(level, damage, radius, x, y,0,0,"src/assets/Sword.png",120,30);
    }

    @Override
    public void useWeapon(){
        System.out.println("Sword");
    }

    @Override
    public boolean collide(Game_objects game_objects) {
        return false;
    }
}
