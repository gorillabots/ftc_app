package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
/**
 * Created by emper on 2/7/2016.
 */
public class SensorTest extends LinearOpMode {
    UltrasonicSensor distance;
    UltrasonicSensor distance2;

    public void _init() {
        distance = hardwareMap.ultrasonicSensor.get("distance");
        distance2 = hardwareMap.ultrasonicSensor.get("distance2");
    }

    @Override
    public void runOpMode() throws InterruptedException {
        _init();
        waitForStart();
        while (opModeIsActive()) {
            while(true){
                telemetry.addData("distance", distance.getUltrasonicLevel());
                telemetry.addData("distance2", distance2.getUltrasonicLevel());
            }
        }
    }
}