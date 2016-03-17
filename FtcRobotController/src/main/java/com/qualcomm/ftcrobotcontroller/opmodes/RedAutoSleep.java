
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
public class RedAutoSleep extends LinearOpMode{
    double d1;
    double d2;
    ColorSensor color;
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

    void startup() throws InterruptedException{
        backward(0.175, 1500);
        stop_robot(1000);
    }

    void your_mom() throws InterruptedException{
        while(distance.getUltrasonicLevel() > distance2.getUltrasonicLevel()){
            motor1.setPower(-0.1);
            motor2.setPower(-0.1);
            motor3.setPower(-0.1);
            motor4.setPower(-0.1);
        }
        while(distance.getUltrasonicLevel() < distance2.getUltrasonicLevel()){
            motor1.setPower(0.1);
            motor2.setPower(0.1);
            motor3.setPower(0.1);
            motor4.setPower(0.1);
        }
        if(distance.getUltrasonicLevel() == distance2.getUltrasonicLevel()){

            stop_robot(5000000);
        }
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
        color = hardwareMap.colorSensor.get("color");//beacon sensor
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

        elbow.setPosition(.823);

        stager=2;
        updateState();

    }

    @Override
    public void runOpMode() throws InterruptedException {
        _init();
        waitForStart();
        while (opModeIsActive()) {
            stop_robot(10000);

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
            turn_left(0.3,450);
            stop_robot(500);
            backward(0.1,900);
            //pivot.setPosition(0.5);
            //pivot.setPosition(0.0);
            //pivot.setPosition(1.0);
            while (true){
                stop_robot(500);
                telemetry.addData("Batman ", "was here");
                //Made by Joshua Kartzman
            }
        }
    }
}
