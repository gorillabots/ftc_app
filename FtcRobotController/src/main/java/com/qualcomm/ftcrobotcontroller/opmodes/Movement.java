package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

/**
 * Created by Jarred on 2/2/2016.
 */
public class movement extends LinearOpMode {


    UltrasonicSensor distance;
    UltrasonicSensor distance2;

    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    DcMotor motor4;
    DcMotor motor5;
    DcMotor motor6;

    ColorSensor color;

    Servo screw;
    Servo leftGo;
    Servo rightGo;
    Servo pivot;
    Servo swoop;
    Servo elbow;
    Servo hook;

    TouchSensor posOne;
    TouchSensor posTwo;
    TouchSensor posThree;
    TouchSensor limit;

    boolean stateOne;
    boolean stateTwo;
    boolean stateThree;
    int currentPos;
    int directionGo;
    int direction;








   public void turn_left(double power, long time) throws InterruptedException {
        motor1.setPower(-power);
        motor2.setPower(-power);
        motor3.setPower(-power);
        motor4.setPower(-power);
        sleep(time);
    }

   public void turn_right(double power, long time) throws InterruptedException {
        motor1.setPower(power);
        motor2.setPower(power);
        motor3.setPower(power);
        motor4.setPower(power);
        sleep(time);
    }

   public void forward(double power) {
        motor1.setPower(-power);
        motor2.setPower(-power);
        motor3.setPower(power);
        motor4.setPower(power);
    }

   public void forward_with_time(double power, long time) throws InterruptedException {
        motor1.setPower(-power);
        motor2.setPower(-power);
        motor3.setPower(power);
        motor4.setPower(power);
        sleep(time);
    }
   public void backward(double power, long time) throws InterruptedException {
        motor1.setPower(power);
        motor2.setPower(power);
        motor3.setPower(-power);
        motor4.setPower(-power);
        sleep(time);
    }

   public void backward_without_time(double power) {
        motor1.setPower(power);
        motor2.setPower(power);
        motor3.setPower(-power);
        motor4.setPower(-power);
    }

   public void stop_robot(long time) throws InterruptedException {
        //this will stop the robot
        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);
        sleep(time);
    }

   public void startup() throws InterruptedException{
        backward(0.175, 1500);
        stop_robot(1000);
    }

   public void your_mom() throws InterruptedException{
        while( getDistance() > getDistance2()){
            motor1.setPower(-0.1);
            motor2.setPower(-0.1);
            motor3.setPower(-0.1);
            motor4.setPower(-0.1);
        }
        while(getDistance() < getDistance()){
            motor1.setPower(0.1);
            motor2.setPower(0.1);
            motor3.setPower(0.1);
            motor4.setPower(0.1);
        }
        if(distance.getUltrasonicLevel() == distance2.getUltrasonicLevel()){

            stop_robot(5000000);
        }
    }

    public double getDistance(){
        return distance.getUltrasonicLevel();

    }

    public double getDistance2(){
        return distance2.getUltrasonicLevel();
    }

    public boolean getlimit(){
        return limit.isPressed();
    }

    public int updateState(){

        stateOne = posOne.isPressed();
        stateTwo = posTwo.isPressed();
        stateThree = posThree.isPressed();

        if(stateOne && ! stateTwo ){
            currentPos = 1;

        }
        else if(!stateOne && stateTwo ){
            currentPos=2;
        }
        else if(stateOne && stateTwo ){
            currentPos=3;
        }
        return currentPos;

    }
   public void moveNet(double stage){

        currentPos = updateState();

        if(stage > currentPos){
            swoop.setPosition(0);

        }
        else if(stage < currentPos){
            directionGo=-1;
            swoop.setPosition(1);
        }
        else{
            swoop.setPosition(.5);
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
        screw = hardwareMap.servo.get("screw");
        pivot = hardwareMap.servo.get("pivot");
        leftGo = hardwareMap.servo.get("backGo");
        rightGo =hardwareMap.servo.get("frontGo");
        distance = hardwareMap.ultrasonicSensor.get("distance");
        distance2 = hardwareMap.ultrasonicSensor.get("distance2");
        swoop = hardwareMap.servo.get("swoop");
        elbow = hardwareMap.servo.get("elbow");
        posOne = hardwareMap.touchSensor.get("posOne");
        posTwo = hardwareMap.touchSensor.get("posTwo");
        limit = hardwareMap.touchSensor.get("limit");
        hook = hardwareMap.servo.get("hook");
        motor1.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motor2.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motor3.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motor4.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motor1.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motor2.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motor3.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motor4.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        pivot.setPosition(.77);
        screw.setPosition(.5);
        leftGo.setPosition(0.0);
        rightGo.setPosition(.8);
        swoop.setPosition(.5);
        elbow.setPosition(1);


    }
   public void runOpMode() throws InterruptedException {

    }

}
