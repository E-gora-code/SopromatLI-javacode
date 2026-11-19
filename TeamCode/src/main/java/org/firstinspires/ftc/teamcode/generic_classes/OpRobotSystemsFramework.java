package org.firstinspires.ftc.teamcode.generic_classes;

import org.firstinspires.ftc.teamcode.teamcode.RobotModules.turret.DataPackageInitSpindexer;
import org.firstinspires.ftc.teamcode.teamcode.RobotModules.turret.spindexer;

public class OpRobotSystemsFramework extends OpModeFramework{

    protected DataPackageInitSpindexer InitPackage;
    protected org.firstinspires.ftc.teamcode.teamcode.RobotModules.turret.spindexer spindexerModule;
    protected void initSpindexer(){
        InitPackage = new DataPackageInitSpindexer(hardwareMap);
        InitPackage.spindexer = spindexer;
        InitPackage.Front_wall = front_wall;
        InitPackage.Back_wall = back_wall;
        InitPackage.Front_ejector = front_ejector;
        InitPackage.Back_ejector = back_ejector;
        InitPackage.Front_intake = front_intake;
        InitPackage.Back_intake = back_intake;
        InitPackage.Shooter1 = shooter_right;
        InitPackage.Shooter2 = shooter_left;
        InitPackage.colorSensor = cv0;
        spindexerModule = new spindexer(InitPackage,hardwareMap);
    }

    @Override
    public void initAllSystems(){
        initSpindexer();
        super.initAllSystems();
    }
    @Override
    public void tickAll(){
        spindexerModule.update_3ball();
        super.tickAll();
    }




    @Override
    public void runOpMode() throws InterruptedException {

    }
}
