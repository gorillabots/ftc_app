package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by Jarred on 2/5/2016.
 */
public class EncodeBall extends OpMode {

    Servo swoop;
    Servo elbow;

    TouchSensor posOne;
    TouchSensor posTwo;
    TouchSensor posThree;

    boolean stateOne;
    boolean stateTwo;
    boolean stateThree;

    int currentPos;
    int directionGo;
    double stager;

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

    public void init() {
        swoop = hardwareMap.servo.get("swoop");
        elbow = hardwareMap.servo.get("elbow");
        posOne = hardwareMap.touchSensor.get("posOne");
        posTwo = hardwareMap.touchSensor.get("posTwo");


        swoop.setPosition(.5);
        elbow.setPosition(1);


    }


    public void loop() {


        if (gamepad1.right_bumper ) {
            stager += 1;
            telemetry.addData("shifted", "up");
        }
        if (gamepad1.left_bumper ) {
            stager -= 1;
            telemetry.addData("shifted", "down");
        }
        if (stager > 4) {
            stager = 4;
        }
        if (stager < 1) {
            stager = 1;
        }


            if (gamepad1.y == true) {

                elbow.setPosition(.5);

            } else if (gamepad1.x == true) {
                elbow.setPosition(1);
            }


        moveNet(stager);

    }
}
