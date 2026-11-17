package org.firstinspires.ftc.teamcode.debug;

import android.annotation.SuppressLint;
import android.util.Size;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagClusterDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import java.util.List;

@TeleOp()
public class AprilTagClusterSolver extends LinearOpMode {

    private AprilTagProcessor aprilTag;
    private VisionPortal visionPortal;

    @Override
    public void runOpMode() {
        initAprilTag();

        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            telemetryAprilTag();
            telemetry.update();

            sleep(20);
        }

        visionPortal.close();
    }


    private static final boolean USE_WEBCAM = false;    // false for phone, true for usb webcam

    private void initAprilTag() {
        AprilTagProcessor.Builder processorBuilder = new AprilTagProcessor.Builder();

        if (USE_WEBCAM) {
            processorBuilder.setLensIntrinsics(622.001, 622.001, 319.5, 239.5)
                    .setOutputUnits(DistanceUnit.CM, AngleUnit.DEGREES);
        } else {
            processorBuilder.setLensIntrinsics(491.159, 491.159, 319.096, 237.471)  // MI 9
                    .setOutputUnits(DistanceUnit.CM, AngleUnit.DEGREES);
        }

        aprilTag = processorBuilder.build();

        VisionPortal.Builder portalBuilder = new VisionPortal.Builder();
        if (USE_WEBCAM) {
            portalBuilder.setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"));
        } else {
            portalBuilder.setCamera(BuiltinCameraDirection.BACK);
        }

        portalBuilder.setCameraResolution(new Size(640, 480))
                .addProcessor(aprilTag);

        visionPortal = portalBuilder.build();
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