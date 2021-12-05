package sample;

import java.util.ArrayList;

public class Weapon_chest extends Chests {
    private Weapon weapon;
    public Weapon_chest(double x, double y, double sx, double sy, String path, int width, int height){
        super(x,y,sx,sy,path,width,height);
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon1) {
        this.weapon = weapon1;
    }

    @Override
    public void collide(Game_objects game_objects){

    }

}
