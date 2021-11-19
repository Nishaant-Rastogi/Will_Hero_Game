package sample;

import java.util.ArrayList;

public abstract class Helmet {
    private ArrayList<Weapon> weapons;
    private Weapon activeWeapon;
    public Helmet(){
        //couldn't think would it be initialized
    }
    public ArrayList<Weapon> getWeapons(){
        return  this.weapons;
    }
    public Weapon getActiveWeapon(){
        return this.activeWeapon;
    }
    public void setActiveWeapon(Weapon activeWeapon1){
        this.activeWeapon= activeWeapon1;
    }
    public void switchWeapon(){

    }

}
