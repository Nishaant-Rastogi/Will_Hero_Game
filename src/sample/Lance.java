package sample;

public class Lance extends Weapon {
    Lance(int level, int damage, int radius) {
        super(level, damage, radius,"src/assets/Lance.png",150,30);
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
