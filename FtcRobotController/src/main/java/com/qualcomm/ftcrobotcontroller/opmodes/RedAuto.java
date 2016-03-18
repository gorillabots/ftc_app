

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorController.RunMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

/**
 * Created by emper on 1/9/2016.
 */

// Do this one
public class RedAuto extends LinearOpMode {
    double d1;
    double d2;
    //ColorSensor color;
    UltrasonicSensor distance;
    UltrasonicSensor distance2;
    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    DcMotor motor4;
    DcMotor motor5;
    DcMotor motor6;
    Servo swoop;
    Servo elbow;
    boolean stateOne;
    boolean stateTwo;
    ColorSensor Leftcolor;
    ColorSensor Floorcolor;
    String teamcolor = "red";
    String notteamcolor = "blue";
    Servo leftarm;
    Servo rightarm;
    String whatColorIsLeft;
    String whatColorIs1Left;
    String whatColorIs2Left;
    String whatColorIsFloor;


    int stateOneTest;
    int stateTwoTest;

    AnalogInput posOne;
    AnalogInput posTwo;

    int currentPos;
    double stager;


    void turn_left(double power, long time) throws InterruptedException {
        motor1.setPower(-power);
        motor2.setPower(-power);
        motor3.setPower(-power);
        motor4.setPower(-power);
        sleep(time);
    }

    void turn_right(double power, long time) throws InterruptedException {
        motor1.setPower(power);
        motor2.setPower(power);
        motor3.setPower(power);
        motor4.setPower(power);
        sleep(time);
    }

    void forward(double power) {
        motor1.setPower(-power);
        motor2.setPower(-power);
        motor3.setPower(power);
        motor4.setPower(power);
    }

    void forward_with_time(double power, long time) throws InterruptedException {
        motor1.setPower(-power);
        motor2.setPower(-power);
        motor3.setPower(power);
        motor4.setPower(power);
        sleep(time);
    }

    void backward(double power, long time) throws InterruptedException {
        motor1.setPower(power);
        motor2.setPower(power);
        motor3.setPower(-power);
        motor4.setPower(-power);
        sleep(time);
    }

    void backward_without_time(double power) {
        motor1.setPower(power);
        motor2.setPower(power);
        motor3.setPower(-power);
        motor4.setPower(-power);
    }

    void stop_robot(long time) throws InterruptedException {
        //this will stop the robot
        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);
        sleep(time);
    }

    void startup() throws InterruptedException {
        backward(0.175, 1500);
        stop_robot(1000);
    }

    void your_mom() throws InterruptedException {
        while (distance.getUltrasonicLevel() > distance2.getUltrasonicLevel()) {
            motor1.setPower(-0.1);
            motor2.setPower(-0.1);
            motor3.setPower(-0.1);
            motor4.setPower(-0.1);
        }
        while (distance.getUltrasonicLevel() < distance2.getUltrasonicLevel()) {
            motor1.setPower(0.1);
            motor2.setPower(0.1);
            motor3.setPower(0.1);
            motor4.setPower(0.1);
        }
        if (distance.getUltrasonicLevel() == distance2.getUltrasonicLevel()) {

            stop_robot(5000000);
        }
    }

    public String getFloorcolor() {
        String currentcolor = "none";
        telemetry.addData("floorcolor-red", Floorcolor.red());
        telemetry.addData("floorcolor-blue", Floorcolor.blue());
        telemetry.addData("floorcolor-green", Floorcolor.green());
        if (Floorcolor.red() > Floorcolor.green() && Floorcolor.red() > Floorcolor.blue() && Floorcolor.green() >= Floorcolor.blue()) {
            currentcolor = "red";
        }
        telemetry.addData("floorcolor-red", Floorcolor.red());
        telemetry.addData("floorcolor-blue", Floorcolor.blue());
        telemetry.addData("floorcolor-green", Floorcolor.green());
        if (Floorcolor.red() < Floorcolor.green() && Floorcolor.blue() < Floorcolor.green() && Floorcolor.red() == Floorcolor.blue()) {
            currentcolor = "blue";
        }
        if (Floorcolor.blue() < Floorcolor.red() && Floorcolor.red() < Floorcolor.green() && Floorcolor.green() < Floorcolor.alpha()) {
            currentcolor = "alpha";
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

        telemetry.addData("beconcolor-red", color.red());
        telemetry.addData("beconcolor-blue", color.blue());
        telemetry.addData("beconcolor-green", color.green());
        if (color.red() > color.blue() && color.red() > color.green() && color.green() == color.blue()) {
            currentcolor = "red";
        }
        telemetry.addData("beaconcolor-red", color.red());
        telemetry.addData("beconcolor-blue", color.blue());
        telemetry.addData("beconcolor-green", color.green());
        if (color.red() < color.blue() && color.green() < color.blue() && color.blue() > 1) {
            currentcolor = "blue";
        }

        return currentcolor;
    }


    public int updateState() {

        stateOneTest = posOne.getValue();
        stateTwoTest = posTwo.getValue();

        telemetry.addData("state1", stateOneTest);
        telemetry.addData("state2", stateTwoTest);

        stateOne = stateOneTest >= 1020;
        stateTwo = stateTwoTest >= 1020;

        if (stateOne == false && stateTwo == true) {
            currentPos = 1;

        } else if (stateOne == true && stateTwo == false) {
            currentPos = 2;
        } else if (stateOne == false && stateTwo == false) {
            currentPos = 3;
        }

        return currentPos;

    }
    /*
    The above method checks the states of both of the limit switches
    It then interprets these states into positions.
     */

    public void moveNet(double stager) {


        currentPos = updateState();

        if (stager != currentPos) {
            swoop.setPosition(0);

        }// else if (stager < currentPos) {
//
        //          swoop.setPosition(1);
        else if (stager == currentPos) {
            swoop.setPosition(.502);

        }
    }

    public void _init() {
        motor1 = hardwareMap.dcMotor.get("motor1");//motor1 on AL00VTH7
        motor2 = hardwareMap.dcMotor.get("motor2");//motor2 on AL00VTH7
        motor3 = hardwareMap.dcMotor.get("motor3");//motor3 on AL00YC5Z
        motor4 = hardwareMap.dcMotor.get("motor4");//motor4 on AL00YC5Z
        motor5 = hardwareMap.dcMotor.get("motor5");
        motor6 = hardwareMap.dcMotor.get("motor6");
        //color = hardwareMap.colorSensor.get("color");//beacon sensor
        distance = hardwareMap.ultrasonicSensor.get("distance");
        distance2 = hardwareMap.ultrasonicSensor.get("distance2");
        swoop = hardwareMap.servo.get("swoop");
        elbow = hardwareMap.servo.get("elbow");
        posOne = hardwareMap.analogInput.get("A0");
        posTwo = hardwareMap.analogInput.get("A1");
        motor1.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motor2.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motor3.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motor4.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motor1.setChannelMode(RunMode.RUN_USING_ENCODERS);
        motor2.setChannelMode(RunMode.RUN_USING_ENCODERS);
        motor3.setChannelMode(RunMode.RUN_USING_ENCODERS);
        motor4.setChannelMode(RunMode.RUN_USING_ENCODERS);
        Leftcolor = hardwareMap.colorSensor.get("Leftcolor_sensor");
        Leftcolor.setI2cAddress(60);
        Floorcolor = hardwareMap.colorSensor.get("Floorcolor_sensor");
        Floorcolor.setI2cAddress(62);
        Leftcolor.enableLed(false);
        Floorcolor.enableLed(true);
        telemetry.addData("state", "setup color sensors");
        leftarm = hardwareMap.servo.get("extend");
        leftarm.setPosition(0);
        rightarm = hardwareMap.servo.get("swing");
        rightarm.setPosition(1);
        telemetry.addData("sate", "setup servos");
        elbow.setPosition(.823);

        stager = 2;
        updateState();

    }

    @Override
    public void runOpMode() throws InterruptedException {
        _init();
        waitForStart();
        while (opModeIsActive()) {
            //putsleetHEREEEEEEE

            elbow.setPosition(.286);
            moveNet(1);


            backward(0.25, 1300);
            stop_robot(1000);
            while (true) {
                stop_robot(30);
                d1 = distance.getUltrasonicLevel();
                d2 = distance2.getUltrasonicLevel();
                telemetry.addData("distance", d1);
                telemetry.addData("distance2", d2);
                if (d2 == d1 && d1 != 0 && d2 != 0) {
                    telemetry.addData("State", "About to finish aligning");
                    break;
                } else if (d2 < d1 && d1 != 0 && d2 != 0) {
                    telemetry.addData("State", "About to turn left");
                    turn_left(0.08, 100);
                    stop_robot(10);
                } else if (d1 < d2 && d1 != 0 && d2 != 0) {
                    telemetry.addData("State", "About to turn right");
                    turn_right(0.08, 100);
                    stop_robot(10);
                }
            }
            turn_left(0.3, 500);
            stop_robot(500);
            backward(0.25, 2900);
            stop_robot(500);
            turn_left(0.3, 450);
            stop_robot(500);
            backward(0.1, 900);

            stop_robot(1000);
/**
 * check color for floor--> if not white, don't do anything
 *
 */
            whatColorIsFloor = getFloorcolor();
            if (whatColorIsFloor == "alpha") {
                leftarm.setPosition(.6);
                rightarm.setPosition(.55);

                whatColorIs1Left = getBeaconcolor(Leftcolor);
                if (whatColorIsLeft == teamcolor) {
                    telemetry.addData("state", "preparing left arm to hit team color");
                    rightarm.setPosition(1);
                } else if (whatColorIsLeft == notteamcolor) {
                    telemetry.addData("state", "preparing right arm to hit team color");
                    leftarm.setPosition(0);
                } else {
                    telemetry.addData("state", "stopped because no beacon found");
                    leftarm.setPosition(0);
                    rightarm.setPosition(1);
                }
                /**
                 *move forward to press button, stop for 2 seconds, back up, stop and lift both arms back.
                 */

                motor1.setPower(.3);
                motor2.setPower(.3);
                motor3.setPower(-.3);
                motor4.setPower(-.3);
                sleep(450);

                motor1.setPower(0);
                motor2.setPower(0);
                motor3.setPower(0);
                motor4.setPower(0);
                sleep(2000);

                motor1.setPower(-.3);
                motor2.setPower(-.3);
                motor3.setPower(.3);
                motor4.setPower(.3);
                sleep(100);

                motor1.setPower(0);
                motor2.setPower(0);
                motor3.setPower(0);
                motor4.setPower(0);

                rightarm.setPosition(1);
                leftarm.setPosition(0);
            }

            while (true) {
                sleep(100);
                telemetry.addData("Batman ", "was here");
            }
        }
    }

}