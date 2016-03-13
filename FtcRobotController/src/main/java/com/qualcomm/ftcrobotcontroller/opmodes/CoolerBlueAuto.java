package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorController.RunMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.qualcomm.ftcrobotcontroller.opmodes.Movement;
/**
 * Created by emper on 1/9/2016.
 */

// Do this one
public class CoolerBlueAuto extends Movement{

    ColorSensor color;
    UltrasonicSensor distance;
    UltrasonicSensor distance2;
    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    DcMotor motor4;
    DcMotor motor5;
    DcMotor motor6;
    Servo screw;
    Servo leftGo;
    Servo pivot;
    Servo tape;
    Servo rotate;
    Servo tilt;



    public void runOpMode() throws InterruptedException {

        super._init();
        telemetry.addData("intialization", "complete");
        waitForStart();
        telemetry.addData("about to", "start");
        while (opModeIsActive()) {
            telemetry.addData("loop", "is runningssss");
            screw.setPosition(.5);
            backward(0.25, 1300);
            stop_robot(1000);
            while (true) {
                telemetry.addData("distance", distance.getUltrasonicLevel());
                telemetry.addData("distance2", distance2.getUltrasonicLevel());
                int i;
                for(i = 0; i < 3; i++){
                    stop_robot(100);
                    telemetry.addData("distance", distance.getUltrasonicLevel());
                    telemetry.addData("distance2", distance2.getUltrasonicLevel());
                }
                if (distance2.getUltrasonicLevel() == distance.getUltrasonicLevel() && distance.getUltrasonicLevel() != 0 && distance2.getUltrasonicLevel() != 0) {
                    break;
                } else if (distance2.getUltrasonicLevel() < distance.getUltrasonicLevel() && distance.getUltrasonicLevel() != 0 && distance2.getUltrasonicLevel() != 0) {
                    turn_left(0.08, 100);
                    stop_robot(100);
                } else if (distance.getUltrasonicLevel() < distance2.getUltrasonicLevel() && distance.getUltrasonicLevel() != 0 && distance2.getUltrasonicLevel() != 0) {
                    turn_right(0.08, 100);
                    stop_robot(100);
                }
            }
            turn_right(0.3, 500);
            stop_robot(500);
            backward(0.25, 2900);
            stop_robot(500);
            turn_right(0.3,450);
            stop_robot(500);
            backward(0.1,1000);
            while (true){
                stop_robot(500);
                telemetry.addData("blue", color.blue());
                telemetry.addData("red", color.red());
            }
        }
    }
}
