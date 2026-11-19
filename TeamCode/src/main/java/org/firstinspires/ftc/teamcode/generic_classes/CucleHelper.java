package org.firstinspires.ftc.teamcode.generic_classes;

import java.util.function.Consumer;

public class CucleHelper {
    public int cucle = 0;
    public int cucles_num = 3;
    boolean trigger_in = false;
    boolean last_in = false;

    Consumer<Integer> func;
    public CucleHelper(Consumer<Integer> func, int cucles_num){
        this.func = func;
        this.cucles_num = cucles_num;
    }
    public CucleHelper(Consumer<Integer> func, int cucles_num, int init_state){
        this.func = func;
        this.cucles_num = cucles_num;
        this.cucle = init_state;
    }
    public void acceptIn(boolean st){
        this.trigger_in = st;
        update();
    }
    public void update(){
        if(trigger_in!= last_in){
            last_in = trigger_in;
            if(trigger_in == true) {
                cucle += 1;
            }
        }
        cucle = cucle % cucles_num;
        func.accept(cucle);
    }
}
