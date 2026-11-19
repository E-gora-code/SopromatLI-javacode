package org.firstinspires.ftc.teamcode.debug;

import android.annotation.SuppressLint;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.generic_classes.OpRobotSystemsFramework;
import org.firstinspires.ftc.vision.apriltag.AprilTagClusterDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import java.util.List;

@TeleOp()
public class AprilTagClusterSolver extends OpRobotSystemsFramework {

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

        int clusterCount = 0;
        for (AprilTagDetection detection : currentDetections) {
            if (detection instanceof AprilTagClusterDetection) {
                clusterCount++;
            }
        }
        telemetry.addData("# Clusters Detected", clusterCount);

        for (AprilTagDetection detection : currentDetections) {
            if (detection instanceof AprilTagClusterDetection) {
                AprilTagClusterDetection clusterDet = (AprilTagClusterDetection) detection;

                telemetry.addLine(String.format("\n==== [CLUSTER %s] %s %% ", clusterDet.metadata.name, clusterDet.percentClusterFound));

                if (clusterDet.ftcPose != null) {
                    telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (cm)",
                            clusterDet.ftcPose.x,
                            clusterDet.ftcPose.y,
                            clusterDet.ftcPose.z));
                    telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)",
                            clusterDet.ftcPose.pitch,
                            clusterDet.ftcPose.roll,
                            clusterDet.ftcPose.yaw));
                }
            }
        }
    }
}