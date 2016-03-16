package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
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
public class CoolerBlueAuto extends LinearOpMode {
    UltrasonicSensor distance;
    UltrasonicSensor distance2;
    double ultrasonic_average;
    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    DcMotor motor4;
    DcMotor motor5;
    DcMotor motor6;
    double d1;
    double d2;
    double ultrasonic_difference;
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
    void turn_right_without_time(double power) throws InterruptedException {
        motor1.setPower(power);
        motor2.setPower(power);
        motor3.setPower(power);
        motor4.setPower(power);
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

    public void _init() {
        motor1 = hardwareMap.dcMotor.get("motor1");//motor1 on AL00VTH7
        motor2 = hardwareMap.dcMotor.get("motor2");//motor2 on AL00VTH7
        motor3 = hardwareMap.dcMotor.get("motor3");//motor3 on AL00YC5Z
        motor4 = hardwareMap.dcMotor.get("motor4");//motor4 on AL00YC5Z
        motor5 = hardwareMap.dcMotor.get("motor5");
        motor6 = hardwareMap.dcMotor.get("motor6");
        distance = hardwareMap.ultrasonicSensor.get("distance");
        distance2 = hardwareMap.ultrasonicSensor.get("distance2");
    }

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("State", "About to run init");
        _init();
        telemetry.addData("State", "About to waitforstart");
        waitForStart();
        while (true) {
            telemetry.addData("State", "About to opModeIsActive");
            while (opModeIsActive()) {
                telemetry.addData("State", "About to give a value to d1/d2");
                d1 = distance.getUltrasonicLevel();
                d2 = distance2.getUltrasonicLevel();
                ultrasonic_average = (d1 + d2) / 2;
                telemetry.addData("State", "About to go forward");
                while (true) {
                    d1 = distance.getUltrasonicLevel();
                    d2 = distance2.getUltrasonicLevel();
                    ultrasonic_average = (d1 + d2) / 2;

                    backward(0.5, 50);
                    stop_robot(50);
                    if (ultrasonic_average == 10) {
                        telemetry.addData("State", "About to finish going forward");
                        break;
                    }
                    if(ultrasonic_average > 10){
                        forward_with_time(0.2, 50);
                    }
                }
                stop_robot(500);
                telemetry.addData("State", "About to align");
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
                stop_robot(500);
                d1 = distance.getUltrasonicLevel();
                d2 = distance2.getUltrasonicLevel();
                ultrasonic_difference = d1 - d2;
                while(ultrasonic_difference < 25){
                    d1 = distance.getUltrasonicLevel();
                    d2 = distance2.getUltrasonicLevel();
                    ultrasonic_difference = d1-d2;
                    turn_right(0.2, 50);
                    if(ultrasonic_difference == 25){
                        break;
                    }
                    if(ultrasonic_difference > 25){
                        turn_left(0.2, 50);
                    }
                }
                // TODO: Change to ultrasonic based
                stop_robot(500);
                while(true){
                    telemetry.addData("distance", distance.getUltrasonicLevel());
                    telemetry.addData("distance2", distance2.getUltrasonicLevel());
                    ultrasonic_difference = distance.getUltrasonicLevel()-distance2.getUltrasonicLevel();
                    telemetry.addData("distance_difference", ultrasonic_difference);
                    stop_robot(500);
                }
                /*while(true){
                    telemetry.addData("distance", distance.getUltrasonicLevel());
                    telemetry.addData("distance2", distance2.getUltrasonicLevel());
                    ultrasonic_difference = distance2.getUltrasonicLevel()-distance.getUltrasonicLevel();
                    telemetry.addData("distance_difference", ultrasonic_difference);
                    stop_robot(500);
                }*/
                /*backward(0.25, 2900);
                telemetry.addData("State", "About to go backwards");
                stop_robot(500);
                turn_right(0.3, 60);
                stop_robot(500);
                backward(0.1, 800);
                while (true) {
                    stop_robot(500);
                }*/
            }
        }
    }
}