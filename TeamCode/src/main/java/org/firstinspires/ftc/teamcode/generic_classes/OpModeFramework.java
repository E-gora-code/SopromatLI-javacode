package org.firstinspires.ftc.teamcode.generic_classes;


import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public abstract class OpModeFramework extends LinearOpMode {
    protected Telemetry dash;


    protected RobotHardware.DriveBase.motor_classes.FrontLeft FL;
    protected RobotHardware.DriveBase.motor_classes.FrontRight FR;
    protected RobotHardware.DriveBase.motor_classes.BackLeft BL;
    protected RobotHardware.DriveBase.motor_classes.BackRight BR;
    protected RobotHardware.Motors.BasicServo front_ejector,back_ejector,front_wall,back_wall,turret_y,brake_pad;
    protected RobotHardware.Motors.BasicCRServo spindexer,turret_x, barabim1, barabim2;
    protected RobotHardware.Motors.DCMotor extr, extl,factory_ext;

    protected RobotHardware.Motors.DCMotor front_intake, back_intake,shooter_left,shooter_right;
    protected RobotHardware robotHardware;
    protected GamepadDriver controllerDriver_1;
    protected GamepadDriver controllerDriver_2;
    protected RobotHardware.Motors motors;
    protected RobotHardware.Sensors sensors;
    protected RobotHardware.DriveBase driveBase;
    protected RobotHardware.GyroIMU gyro;
    protected RobotHardware.DriveBase.motor_classes motor_classes;

    protected RobotHardware.Sensors.BasicChannel ch0, ch1;
    protected RobotHardware.Sensors.BasicColorSensor cv0, cv1;

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

        dash = FtcDashboard.getInstance().getTelemetry();

        motors = robotHardware.new Motors();
        sensors = robotHardware.new Sensors();

        spindexer = motors.new BasicCRServo(motors, RobotHardware.Motors.NameKeys.servoNameKeys.spindexer);
        turret_x = motors.new BasicCRServo(motors, RobotHardware.Motors.NameKeys.servoNameKeys.turret_x);


        barabim1 = motors.new BasicCRServo(motors, RobotHardware.Motors.NameKeys.servoNameKeys.barabim1);
        barabim2 = motors.new BasicCRServo(motors, RobotHardware.Motors.NameKeys.servoNameKeys.barabim2);




        front_ejector = motors.new BasicServo(motors, RobotHardware.Motors.NameKeys.servoNameKeys.front_ejector);
        back_ejector = motors.new BasicServo(motors, RobotHardware.Motors.NameKeys.servoNameKeys.back_ejector);
        front_wall = motors.new BasicServo(motors, RobotHardware.Motors.NameKeys.servoNameKeys.front_wall);
        back_wall = motors.new BasicServo(motors, RobotHardware.Motors.NameKeys.servoNameKeys.back_wall);
                turret_y = motors.new BasicServo(motors, RobotHardware.Motors.NameKeys.servoNameKeys.turret_y);
        brake_pad = motors.new BasicServo(motors, RobotHardware.Motors.NameKeys.servoNameKeys.brake_pad);

        front_intake = motors.new DCMotor(motors, RobotHardware.Motors.NameKeys.motorDCNameKeys.front_intake);
        back_intake = motors.new DCMotor(motors, RobotHardware.Motors.NameKeys.motorDCNameKeys.back_intake);
        shooter_right = motors.new DCMotor(motors, RobotHardware.Motors.NameKeys.motorDCNameKeys.shooter_right);
        shooter_left = motors.new DCMotor(motors, RobotHardware.Motors.NameKeys.motorDCNameKeys.shooter_left);



        ch0 = sensors.new BasicChannel(sensors, RobotHardware.Sensors.NameKeys.channelsNameKeys.ch0);
        ch1 = sensors.new BasicChannel(sensors, RobotHardware.Sensors.NameKeys.channelsNameKeys.ch1);
        cv0 = sensors.new BasicColorSensor(sensors, RobotHardware.Sensors.NameKeys.colorNameKeys.cv0);
        cv1 = sensors.new BasicColorSensor(sensors, RobotHardware.Sensors.NameKeys.colorNameKeys.cv1);

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
        dash.addData(caption,value);
    }
    public void UpdatePrint(){
        telemetry.update();
        dash.update();
    }

    @Override
    public abstract void runOpMode() throws InterruptedException;
}