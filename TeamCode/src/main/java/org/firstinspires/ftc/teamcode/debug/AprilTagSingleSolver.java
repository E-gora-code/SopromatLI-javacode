package org.firstinspires.ftc.teamcode.debug;

import android.annotation.SuppressLint;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.generic_classes.OpRobotSystemsFramework;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagSingleDetection;
import java.util.List;

@TeleOp()
public class AprilTagSingleSolver extends OpRobotSystemsFramework {

    @Override
    public void runOpMode() {
        selfInit();
        initAllSystems();

        waitForStart();

        while (opModeIsActive()) {
            tickAll();

            telemetryAprilTag();

            sleep(20);
        }

        visionPortal.close();
    }


    @SuppressLint("DefaultLocale")
    private void telemetryAprilTag() {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        telemetry.addData("# Single Tags Detected", currentDetections.size());

        for (AprilTagDetection detection : currentDetections) {
            if (detection instanceof AprilTagSingleDetection) {
                AprilTagSingleDetection singleDet = (AprilTagSingleDetection) detection;

                String tagName = (singleDet.metadata != null) ? singleDet.metadata.name : "Isolated Custom Target";
                telemetry.addLine(String.format("\n==== [TAG %d] %s", singleDet.id, tagName));

                if (detection.ftcPose != null) {
                    telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (cm)",
                            detection.ftcPose.x,
                            detection.ftcPose.y,
                            detection.ftcPose.z));
                    telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)",
                            detection.ftcPose.pitch,
                            detection.ftcPose.roll,
                            detection.ftcPose.yaw));
                }
            }
        }
    }
}
