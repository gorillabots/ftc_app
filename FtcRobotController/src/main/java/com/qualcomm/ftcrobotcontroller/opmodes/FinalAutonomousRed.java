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
public class FinalAutonomousRed extends LinearOpMode{
    UltrasonicSensor distance;
    UltrasonicSensor distance2;
    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    DcMotor motor4;
    DcMotor motor5;
    DcMotor motor6;
    double d1;
    double d2;
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
    void right_without_time(double power) {
        motor1.setPower(power);
        motor2.setPower(power);
        motor3.setPower(power);
        motor4.setPower(power);
    }
    void left_without_time(double power) {
        motor1.setPower(-power);
        motor2.setPower(-power);
        motor3.setPower(-power);
        motor4.setPower(-power);
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

    public void _init() {
        motor1 = hardwareMap.dcMotor.get("motor1");//motor1 on AL00VTH7
        motor2 = hardwareMap.dcMotor.get("motor2");//motor2 on AL00VTH7
        motor3 = hardwareMap.dcMotor.get("motor3");//motor3 on AL00YC5Z
        motor4 = hardwareMap.dcMotor.get("motor4");//motor4 on AL00YC5Z
        motor5 = hardwareMap.dcMotor.get("motor5");
        motor6 = hardwareMap.dcMotor.get("motor6");
        distance = hardwareMap.ultrasonicSensor.get("distance");
        distance2 = hardwareMap.ultrasonicSensor.get("distance2");
        motor1.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motor2.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motor3.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motor4.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motor1.setChannelMode(RunMode.RUN_USING_ENCODERS);
        motor2.setChannelMode(RunMode.RUN_USING_ENCODERS);
        motor3.setChannelMode(RunMode.RUN_USING_ENCODERS);
        motor4.setChannelMode(RunMode.RUN_USING_ENCODERS);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        _init();
        waitForStart();
        while (opModeIsActive()) {
            d1 = distance.getUltrasonicLevel();
            d2 = distance2.getUltrasonicLevel();
            while(d2 < 10 || d1 < 10) {
                backward_without_time(0.1);
            }
            while (true) {
                stop_robot(25);
                if (d2 == d1 && d2 != 0 && d1 != 0) {
                    break;
                } else if(d2 < d1 && d1 != 0 && d2 != 0) {
                    turn_left(0.5, 100);
                } else if(d1 < d2 && d1 != 0 && d2 != 0) {
                    turn_right(0.5, 100);
                }
            }
                while (true) {
                    stop_robot(25);
                    telemetry.addData("distance", d1);
                    telemetry.addData("distance2", d2);
                    if (d2 == 6 && d1 == 29) {
                        break;
                    } else if (d2 < 6 && d1 < 29) {
                        turn_left(0.5, 100);
                    } else if (d1 > 6 && d2 > 29) {
                        turn_right(0.5, 100);
                    }
                }
            turn_left(0.3, 500);
            stop_robot(500);
            backward(0.25, 2900);
            stop_robot(500);
            turn_left(0.3,450);
            stop_robot(500);
            backward(0.1,1000);
            stop_robot(2147483647);
        }
    }
}

