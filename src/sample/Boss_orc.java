package sample;

public class Boss_orc extends Orc{
    Boss_orc(double x, double y, double sx, double sy,int width, int height){
        super(x,y,sx,sy,"src/assets/Boss_orc.png",width,height);

    }
    @Override
    public boolean collide(Game_objects game_objects){
        if(game_objects instanceof Hero) {
            Hero h1 = (Hero) game_objects;
            if (h1.getWeapon() != null) {
                if (this.getImg().getBoundsInLocal().intersects(h1.getWeapon().getImg().getBoundsInLocal())) {
                    if ((this.getImg().getX() <= h1.getWeapon().getImg().getX()) || (this.getImg().getX() + 150 >= h1.getWeapon().getImg().getX() + 55)) {
                        if(this.getHealth()<=0) {
                            setDead(true);
                            h1.getCoinCase().addAll(this.getCoins());
                            orcDeathAnimation();
                        }else{
                            this.getImg().setX(this.getImg().getX()+50);
                            this.setHealth(getHealth()-h1.getWeapon().getDamage());
                        }
                    }
                }
            } else {
                if (this.getImg().getBoundsInLocal().intersects(game_objects.getImg().getBoundsInLocal())) {
                    if ((this.getImg().getX() <= h1.getImg().getX()) || (this.getImg().getX() + 150 >= h1.getImg().getX() + 55)) {
                        if (this.getImg().getY() + 150 <= h1.getImg().getY()) {
                            h1.setAlive(false);
                            return true;
                        }

                    }
                }

            }
        }
        return false;
    }
    public void move(){

    }

}
