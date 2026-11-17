package org.firstinspires.ftc.teamcode.debug;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous
public class DcMotorTest extends LinearOpMode {
    DcMotor leftDrive;
    DcMotor rightDrive;
    double SPEED = 0.3;
    ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        initMotors();

        waitForStart();

        runtime.reset();
        while (opModeIsActive()) {
            telemetryMotors();
            telemetry.update();

            leftDrive.setPower(SPEED);
            rightDrive.setPower(SPEED);

            sleep(20);
        }
    }

    private void initMotors() {
        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
    }

    private void telemetryMotors() {
        telemetry.addData("L", leftDrive.getPower());
        telemetry.addData("R", rightDrive.getPower());
    }
}
