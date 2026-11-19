package org.firstinspires.ftc.teamcode.debug;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.generic_classes.OpRobotSystemsFramework;


@Autonomous()
public class OdometryKitTest extends OpRobotSystemsFramework {

    @Override
    public void runOpMode() {
        selfInit();
        initAllSystems();

        waitForStart();
        while (opModeIsActive()) {
            tickAll();

            telemetryOdometry();

            sleep(20);
        }
    }

    private void telemetryOdometry() {
        Pose2D pose2D = pinpoint.getPosition();
        telemetry.addData("X", pose2D.getX(DistanceUnit.CM));
        telemetry.addData("Y", pose2D.getY(DistanceUnit.CM));
        telemetry.addData("H", pose2D.getHeading(AngleUnit.DEGREES));
    }
}
