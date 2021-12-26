package sample;

public class Lance extends Weapon {
    Lance(int level, int damage, int radius, int x, int y) {
        super(level, damage, radius, x, y, 0, 0,"src/assets/Lance.png",150,30);
    }

    @Override
    public void useWeapon(){
        System.out.println("Lance");
    }


    @Override
    public boolean collide(Game_objects game_objects) {
        return false;
    }
}
