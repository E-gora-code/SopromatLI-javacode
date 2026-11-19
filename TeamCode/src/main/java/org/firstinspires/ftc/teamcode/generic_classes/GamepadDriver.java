package org.firstinspires.ftc.teamcode.generic_classes;

import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.List;

public class GamepadDriver {
    private Gamepad gamepad;
    public InternalTouchpad internal_touchpad;
    public GamepadDriver(Gamepad gamepad){
        this.gamepad = gamepad;
        this.internal_touchpad = new InternalTouchpad();

    }

    public class InternalTouchpad {
        public double touchpad_1_start_x =0;

        public double touchpad_1_start_y = 0;
        public double touchpad_1_current_x = 0;
        public double touchpad_1_current_y = 0;
        public double touchpad1_X =0;
        public double touchpad1_Y =0;
        public boolean touchpad_1_last_state = false;

        public double getX(){
            return touchpad1_X;
        }
        public double getY(){
            return touchpad1_Y;
        }
    }




    public List<Double> getTouchpadAsStick(){
        return null;
    }


    public void class_tick(){
        if((gamepad.touchpad_finger_1==true)&&(internal_touchpad.touchpad_1_last_state==false)){
            internal_touchpad.touchpad_1_start_x=gamepad.touchpad_finger_1_x;
            internal_touchpad.touchpad_1_start_y=gamepad.touchpad_finger_1_y;


        }
        internal_touchpad.touchpad_1_current_x = gamepad.touchpad_finger_1_x;
        internal_touchpad.touchpad_1_current_y = gamepad.touchpad_finger_1_y;

        if(gamepad.touchpad_finger_1==true){
            internal_touchpad.touchpad1_X = internal_touchpad.touchpad_1_current_x - internal_touchpad.touchpad_1_start_x;
            internal_touchpad.touchpad1_Y = internal_touchpad.touchpad_1_current_y - internal_touchpad.touchpad_1_start_y;
        }else {
            internal_touchpad.touchpad1_X = 0;
            internal_touchpad.touchpad1_Y = 0;
        }
        internal_touchpad.touchpad_1_last_state = gamepad.touchpad_finger_1;
    }
}
