package org.firstinspires.ftc.teamcode.generic_classes;


import android.util.Size;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagLibrary;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

public class OpRobotSystemsFramework extends OpModeFramework{
    public GoBildaPinpointDriver pinpoint;
    public AprilTagProcessor aprilTag;
    public VisionPortal visionPortal;


    @Override
    public void initAllSystems(){
        initPinpoint();
        initAprilTag();
        super.initAllSystems();
    }
    @Override
    public void tickAll(){
        pinpoint.update();
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

        processorBuilder.setLensIntrinsics(622.001, 622.001, 319.5, 239.5)
                .setOutputUnits(DistanceUnit.CM, AngleUnit.DEGREES);

        aprilTag = processorBuilder.build();

        VisionPortal.Builder portalBuilder = new VisionPortal.Builder();
        portalBuilder.setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"));

        portalBuilder.setCameraResolution(new Size(640, 480))
                .addProcessor(aprilTag);

        visionPortal = portalBuilder.build();
    }
}
