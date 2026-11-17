package org.firstinspires.ftc.teamcode.debug;


import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

@Autonomous()
public class OdometryKitTest extends LinearOpMode {
    GoBildaPinpointDriver pinpoint;
    ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        initPinpoint();

        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            pinpoint.update();
            telemetryOdometry();
            telemetry.update();

            sleep(20);
        }
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
        telemetry.addData("T", runtime.seconds());
    }
}
