package sample;

public abstract class Game_objects {
    double position[];
    double speedx;
    double speedy;
    Game_objects(){
        position = new double[2];
        speedx=0;
        speedy=0;
    }
    public abstract void collide(Game_objects game_objects);
    public double[] getposition(){
        return this.position;
    }
    public void setPosition(double[] a){
        this.position[0]=a[0];
        this.position[1]=a[1];

    }

    public double getSpeedx() {
        return speedx;
    }

    public void setSpeedx(double speedx) {
        this.speedx = speedx;
    }

    public double getSpeedy() {
        return speedy;
    }

    public void setSpeedy(double speedy) {
        this.speedy = speedy;
    }

    public void display(){

    }

    public void disappear(){

    }
}
