package org.firstinspires.ftc.teamcode.generic_classes;


import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

public class OpRobotSystemsFramework extends OpModeFramework{
    GoBildaPinpointDriver pinpoint;

    @Override
    public void initAllSystems(){
        initPinpoint();
        super.initAllSystems();
    }
    @Override
    public void tickAll(){
        pinpoint.update();
        telemetryOdometry();
        super.tickAll();
    }




    @Override
    public void runOpMode() throws InterruptedException {

    }

    private void initPinpoint() {
        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");

        configurePinpoint();

        pinpoint.setPosition(new Pose2D(DistanceUnit.CM, 0, 0, AngleUnit.DEGREES, 0));
    }

    private void configurePinpoint() {
        pinpoint.setOffsets(200, 50, DistanceUnit.MM);

        pinpoint.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_SWINGARM_POD);

        pinpoint.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD,
                GoBildaPinpointDriver.EncoderDirection.FORWARD);

        pinpoint.resetPosAndIMU();
    }
    private void telemetryOdometry() {
        Pose2D pose2D = pinpoint.getPosition();
        telemetry.addData("X", pose2D.getX(DistanceUnit.CM));
        telemetry.addData("Y", pose2D.getY(DistanceUnit.CM));
        telemetry.addData("H", pose2D.getHeading(AngleUnit.DEGREES));
    }
}
