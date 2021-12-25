package sample;

import javafx.scene.media.AudioClip;

import java.util.Objects;

public class Shield_R_Orc extends Orc {
    Shield_R_Orc(double x, double y, double sx, double sy, int width, int height) {
        super(x, y, sx, sy, "src/assets/Red_Standard_Orc.png", width, height);
        this.setHealth(20);
    }

    @Override
    public boolean collide(Game_objects game_objects) {
        if (game_objects instanceof Hero) {
            Hero h1 = (Hero) game_objects;
            if (h1.getWeapon() != null) {
                if (this.getImg().getBoundsInLocal().intersects(game_objects.getImg().getBoundsInLocal())) {
                    if ((this.getImg().getX() <= h1.getImg().getX()) || (this.getImg().getX() + 70 >= h1.getImg().getX() + 55)) {
                        if ((this.getImg().getX() <= h1.getImg().getX() + 55)) {
                            if (this.getImg().getY() + 60 <= h1.getImg().getY()) {
                                return true;
                            }
                        }
                    }
                }
                if (this.getImg().getBoundsInLocal().intersects(h1.getWeapon().getImg().getBoundsInLocal())) {
                    if ((this.getImg().getX() <= h1.getWeapon().getImg().getX()) || (this.getImg().getX() + 70 >= h1.getWeapon().getImg().getX() + 55)) {
                        //System.out.println((this.getImg().getY() +60)+" "+h1.getImg().getY()+" "+h1.getWeapon().getImg().getY());
                        if(this.getHealth()<=0){
                        setDead(true);
                        h1.getCoinCase().addAll(this.getCoins());
                        orcDeathAnimation();
                        }
                        else{
                            //System.out.println("Health reduction of shielded orc");
                            this.getImg().setX(this.getImg().getX()+1);
                            this.setHealth(getHealth()-h1.getWeapon().getDamage());
                        }
                    }
                }
            } else {
                if (this.getImg().getBoundsInLocal().intersects(game_objects.getImg().getBoundsInLocal())) {
                    if ((this.getImg().getX() <= h1.getImg().getX()) || (this.getImg().getX() + 70 >= h1.getImg().getX() + 55)) {
                        if ((this.getImg().getX() <= h1.getImg().getX() + 55)) {
                            if (this.getImg().getY() + 60 <= h1.getImg().getY()) {
                                return true;
                            }
                            AudioClip buzzer = new AudioClip(Objects.requireNonNull(getClass().getResource("../assets/orcCollide.mp3")).toExternalForm());
                            buzzer.play();
                            this.getImg().setX(this.getImg().getX() + 20);
                            int index = 0;
                            for (int i = 0; i < getCurrIsland().getOrcs().size(); i++) {
                                if (getCurrIsland().getOrcs().get(i) instanceof Shield_R_Orc) {
                                    if (this.equals((Shield_R_Orc) this.getCurrIsland().getOrcs().get(i))) {
                                        index = i;
                                        break;
                                    }
                                }
                            }
                            orcToOrcCollision(index, h1);
                            return false;
                        }

                    }
                }

            }
        }
        return false;
    }
}