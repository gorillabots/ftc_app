package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by emper on 11/1/2015.
 */
public class colorsensor extends LinearOpMode {
    ColorSensor Leftcolor;
    ColorSensor Floorcolor;
    String teamcolor = "red";
    String notteamcolor = "blue";
    Servo leftarm;
    Servo rightarm;
    //Servo screw;
    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    DcMotor motor4;
    DcMotor motor5;
    DcMotor motor6;
    String whatColorIsLeft;
    String whatColorIsFloor;

    /**
     * team color is red for this program
     */


    public void _init() {
        Leftcolor = hardwareMap.colorSensor.get("Leftcolor_sensor");
        Leftcolor.setI2cAddress(60);
        Floorcolor = hardwareMap.colorSensor.get("Floorcolor_sensor");
        Floorcolor.setI2cAddress(62);
        Leftcolor.enableLed(false);
        Floorcolor.enableLed(true);
        telemetry.addData("state","setup color sensors");
        leftarm = hardwareMap.servo.get("extend");
        leftarm.setPosition(0);
        rightarm = hardwareMap.servo.get("swing");
        rightarm.setPosition(1);
        telemetry.addData("sate","setup servos");
        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");
        motor3 = hardwareMap.dcMotor.get("motor3");
        motor4 = hardwareMap.dcMotor.get("motor4");
        motor5 = hardwareMap.dcMotor.get("motor5");
        motor6 = hardwareMap.dcMotor.get("motor6");
    }

    /**
     * returns red or blue or none depending on what color is read from the floor sensor.
     *
     * @return String
     */


    public String getFloorcolor() {
        String currentcolor = "none";

        if (Floorcolor.red() > Floorcolor.green() && Floorcolor.red() > Floorcolor.blue() && Floorcolor.green() >= Floorcolor.blue()) {
            currentcolor = "red";
        }

        if (Floorcolor.red() < Floorcolor.green() && Floorcolor.blue() < Floorcolor.green() && Floorcolor.red() == Floorcolor.blue()) {
            currentcolor = "blue";
        }
        return currentcolor;

    }


    /**
     * retruns red or blue or none depending on what color is read from the beacon sensor.
     *
     * @return String
     */
    public String getBeaconcolor(ColorSensor color) {
        String currentcolor = "none";

        if (color.red() > color.blue() && color.red() > color.green() && color.green() == color.blue()) {
            currentcolor = "red";
        }
        if (color.red() < color.blue() && color.green() < color.blue() && color.blue() > 1) {
            currentcolor = "blue";
        }
        return currentcolor;
    }

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("state","going into init");
        _init();
        telemetry.addData("state", "setting up sensors");
        waitForStart();
        while (opModeIsActive()) {


            whatColorIsLeft = getBeaconcolor(Leftcolor);
            whatColorIsFloor = getFloorcolor();
            telemetry.addData("Leftbeacon_color", whatColorIsLeft);
            telemetry.addData("Floor_color", whatColorIsFloor);
            telemetry.addData("extend", leftarm.getPosition());
            telemetry.addData("swing", rightarm.getPosition());

            telemetry.addData("state", "both arms extended");
            leftarm.setPosition(.7);
            rightarm.setPosition(0);
            telemetry.addData("state", "moving foward");
            motor1.setPower(.3);
            motor2.setPower(.3);
            motor3.setPower(-.3);
            motor4.setPower(-.3);
            sleep(100);
            telemetry.addData("state", "stopped to check color");

            motor1.setPower(0);
            motor2.setPower(0);
            motor3.setPower(0);
            motor4.setPower(0);

            whatColorIsLeft = getBeaconcolor(Leftcolor);
            whatColorIsFloor = getFloorcolor();

            if (whatColorIsLeft == teamcolor) {

                telemetry.addData("state", "preparing left arm to hit team color");
                rightarm.setPosition(1);


            } else if (whatColorIsLeft == notteamcolor) {
                telemetry.addData("state", "preparing right arm to hit team color");
                leftarm.setPosition(0);

                

            } else {
                telemetry.addData("state", "stopped because no beacon found");
            }
            while (true) {
                sleep(1000);

            }
        }
    }

}