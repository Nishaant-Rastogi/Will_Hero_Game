package sample;

public class Sword extends Weapon{
    Sword(int level, int damage, int radius) {
        super(level, damage, radius,"src/assets/Sword.png",120,30);
    }

    @Override
    public void useWeapon(){
        System.out.println("Sword");
    }
    @Override
    public void upgradeWeapon(){

    }

}
