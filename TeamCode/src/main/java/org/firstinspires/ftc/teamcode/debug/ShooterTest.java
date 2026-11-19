package org.firstinspires.ftc.teamcode.debug;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.generic_classes.OpRobotSystemsFramework;


@Autonomous
public class ShooterTest extends OpRobotSystemsFramework {
    double k_speed = 0.3;

    @Override
    public void runOpMode() {
        selfInit();
        initAllSystems();

        waitForStart();
        while (opModeIsActive()) {
            tickAll();

            telemetryMotors();

            shoot_L.setPower(k_speed * gamepad1.left_stick_x);
            shoot_R.setPower(k_speed * gamepad1.left_stick_x);

            sleep(20);
        }
    }

    private void telemetryMotors() {
        telemetry.addData("L", shoot_L.getPower());
        telemetry.addData("R", shoot_R.getPower());
    }
}
