package org.firstinspires.ftc.teamcode.generic_classes;

public class ToggleHelper {
    public boolean state = false;
    boolean trigger_in = false;
    boolean last_in = false;

    Runnable func_on;
    Runnable func_off;
    public ToggleHelper(Runnable on, Runnable off){
        this.func_on = on;
        this.func_off = off;

    }
    public ToggleHelper(Runnable on, Runnable off, boolean init_state){
        this.func_on = on;
        this.func_off = off;
        this.state = init_state;
    }
    public void acceptIn(boolean st){
        this.trigger_in = st;
        update();
    }
    public void update(){
        if(trigger_in!= last_in){
            last_in = trigger_in;
            if(trigger_in == true) {
                state = !state;
            }
        }
        if(state==true){
            func_on.run();
        }else {
            func_off.run();;
        }
    }
}
