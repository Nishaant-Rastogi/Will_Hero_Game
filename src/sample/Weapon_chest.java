package sample;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Weapon_chest extends Chests {
    private Weapon weapon;
    public Weapon_chest(double x, double y, double sx, double sy, int width, int height){
        super(x,y,sx,sy,"src/assets/WeaponChest_1.png",width,height);
        getChestAnimation().add(new Image(new File("src/assets/WeaponChest_1.png").toURI().toString()));
        getChestAnimation().add(new Image(new File("src/assets/WeaponChest_2.png").toURI().toString()));
        getChestAnimation().add(new Image(new File("src/assets/WeaponChest_3.png").toURI().toString()));
        getChestAnimation().add(new Image(new File("src/assets/WeaponChest_4.png").toURI().toString()));
        getChestAnimation().add(new Image(new File("src/assets/WeaponChest_5.png").toURI().toString()));
        getChestAnimation().add(new Image(new File("src/assets/WeaponChest_6.png").toURI().toString()));
        getChestAnimation().add(new Image(new File("src/assets/WeaponChest_7.png").toURI().toString()));
        Random weapon = new Random();
        int weaponChoice = 1+weapon.nextInt(2);
        if(weaponChoice == 1) setWeapon(new Lance(1,20,1));
        else setWeapon(new Sword(1,10,1));
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon1) {
        this.weapon = weapon1;
    }


}
