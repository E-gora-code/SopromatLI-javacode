package org.firstinspires.ftc.teamcode.generic_classes;


//import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public abstract class OpModeFramework extends LinearOpMode {
//    protected Telemetry dash;


    protected RobotHardware.DriveBase.motor_classes.FrontLeft FL;
    protected RobotHardware.DriveBase.motor_classes.FrontRight FR;
    protected RobotHardware.DriveBase.motor_classes.BackLeft BL;
    protected RobotHardware.DriveBase.motor_classes.BackRight BR;
    protected RobotHardware.Motors.BasicServo pos_test_servo_1;
    protected RobotHardware.Motors.BasicCRServo cr_test_servo_1;
    protected RobotHardware.Motors.DCMotor shoot_R, shoot_L;



    protected RobotHardware.Sensors.BasicChannel ch0, ch1;
    protected RobotHardware.Sensors.BasicColorSensor color_sensor_test_1;





    protected RobotHardware robotHardware;
    protected GamepadDriver controllerDriver_1;
    protected GamepadDriver controllerDriver_2;
    protected RobotHardware.Motors motors;
    protected RobotHardware.Sensors sensors;
    protected RobotHardware.DriveBase driveBase;
    protected RobotHardware.GyroIMU gyro;
    protected RobotHardware.DriveBase.motor_classes motor_classes;



    public void selfInit(){
        //Error processing settings
        robotHardware = new RobotHardware(hardwareMap,RobotHardware.errorResponses::ignore);

        controllerDriver_1 = new GamepadDriver(gamepad1);
        controllerDriver_2 = new GamepadDriver(gamepad2);

        gyro = robotHardware.new GyroIMU();
        driveBase = robotHardware.new DriveBase();
        motor_classes = driveBase.new motor_classes();

        FL = motor_classes.new FrontLeft(driveBase);
        FR = motor_classes.new FrontRight(driveBase);
        BL = motor_classes.new BackLeft(driveBase);
        BR = motor_classes.new BackRight(driveBase);

//        dash = FtcDashboard.getInstance().getTelemetry();

        motors = robotHardware.new Motors();
        sensors = robotHardware.new Sensors();





        pos_test_servo_1 = motors.new BasicServo(motors, RobotHardware.Motors.NameKeys.servoNameKeys.pos_test_servo_1);

        cr_test_servo_1 = motors.new BasicCRServo(motors, RobotHardware.Motors.NameKeys.servoNameKeys.cr_test_servo_1);



        shoot_R = motors.new DCMotor(motors, RobotHardware.Motors.NameKeys.motorDCNameKeys.shoot_R);
        shoot_L = motors.new DCMotor(motors, RobotHardware.Motors.NameKeys.motorDCNameKeys.shoot_L);



        ch0 = sensors.new BasicChannel(sensors, RobotHardware.Sensors.NameKeys.channelsNameKeys.ch0);
        ch1 = sensors.new BasicChannel(sensors, RobotHardware.Sensors.NameKeys.channelsNameKeys.ch1);


        color_sensor_test_1 = sensors.new BasicColorSensor(sensors, RobotHardware.Sensors.NameKeys.colorNameKeys.color_sensor_test_1);

    }

    public void initAllSystems(){
        driveBase.init_all();
        motors.init_all();
        sensors.init_all();
        gyro.init_all();
    }
    public void tickAll(){
        driveBase.class_tick();
        motors.class_tick();
        sensors.class_tick();
        controllerDriver_1.class_tick();
        controllerDriver_2.class_tick();
    }
    public void printTelemetry(String caption, Object value){
        telemetry.addData(caption,value);
//        dash.addData(caption,value);
    }
    public void UpdatePrint(){
        telemetry.update();
//        dash.update();
    }

    @Override
    public abstract void runOpMode() throws InterruptedException;
}