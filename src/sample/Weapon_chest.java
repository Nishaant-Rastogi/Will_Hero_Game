package sample;

import java.util.ArrayList;

public class Weapon_chest extends Chests {
    private Weapon weapon;
    public Weapon_chest(){
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
