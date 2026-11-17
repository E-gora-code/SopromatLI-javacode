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
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagLibrary;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagSingleDetection;
import java.util.List;

@TeleOp()
public class AprilTagSingleSolver extends LinearOpMode {

    private AprilTagProcessor aprilTag;
    private VisionPortal visionPortal;

    private static final boolean USE_WEBCAM = false; // false for phone, true for webcam

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

    private void initAprilTag() {
        AprilTagLibrary customLibrary = new AprilTagLibrary.Builder()
                .addTag(30, "Cluster tag 30", 3.25, DistanceUnit.INCH)
                .addTag(31, "Cluster tag 31", 3.25, DistanceUnit.INCH)
                .addTag(32, "Cluster tag 32", 3.25, DistanceUnit.INCH)
                .addTag(33, "Cluster tag 33", 3.25, DistanceUnit.INCH)
                .addTag(34, "Cluster tag 34", 3.25, DistanceUnit.INCH)
                .addTag(35, "Cluster tag 35", 3.25, DistanceUnit.INCH)
                .addTag(36, "Cluster tag 36", 3.25, DistanceUnit.INCH)
                .addTag(37, "Cluster tag 37", 3.25, DistanceUnit.INCH)
                .addTag(38, "Cluster tag 38", 3.25, DistanceUnit.INCH)
                .addTag(39, "Cluster tag 39", 3.25, DistanceUnit.INCH)
                .addTag(40, "Cluster tag 40", 3.25, DistanceUnit.INCH)
                .addTag(41, "Cluster tag 41", 3.25, DistanceUnit.INCH)
                .addTag(42, "Cluster tag 42", 3.25, DistanceUnit.INCH)
                .addTag(43, "Cluster tag 43", 3.25, DistanceUnit.INCH)
                .addTag(44, "Cluster tag 44", 3.25, DistanceUnit.INCH)
                .addTag(45, "Cluster tag 45", 3.25, DistanceUnit.INCH)
                .build();

        AprilTagProcessor.Builder processorBuilder = new AprilTagProcessor.Builder();

        processorBuilder.setTagLibrary(customLibrary);

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
