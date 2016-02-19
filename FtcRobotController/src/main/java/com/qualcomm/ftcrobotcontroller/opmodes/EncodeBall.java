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
    double pickUp;
    double retract;
    TouchSensor posOne;
    TouchSensor posTwo;
    TouchSensor posThree;
    boolean stateOne;
    boolean stateTwo;
    boolean stateThree;
    int currentPos;
    int directionGo;
    double stager;

    public void updateState(){

        if(posOne.isPressed()){
            stateOne = true;
        }
        else{
            stateOne = false;
        }

        if(posTwo.isPressed()){
            stateTwo = true;
        }
        else{
            stateTwo = false;
        }

        if(posThree.isPressed()){
            stateThree = true;
        }
        else{
            stateThree = false;
        }

        if(stateOne==true && stateTwo==false && stateThree==false){
            currentPos = 1;

        }
        if(stateOne==true && stateTwo == true && stateThree==false){
            currentPos=2;
        }
        if(stateOne==false && stateTwo == true && stateThree == true){
            currentPos=3;
        }
        if(stateOne==false && stateTwo == false && stateThree == true){
            currentPos=4;
        }

    }

    public void moveNet(double stage){

        if(stage > currentPos){
            directionGo = 0;
        }
        else if(stage < currentPos){
            directionGo=-1;
        }
        while(currentPos != stage){
            swoop.setPosition(directionGo + 1);
        }

    }



    public void init() {
        swoop = hardwareMap.servo.get("swoop");
        elbow = hardwareMap.servo.get("elbow");
        posOne = hardwareMap.touchSensor.get("posOne");
        posTwo = hardwareMap.touchSensor.get("posTwo");
        posThree = hardwareMap.touchSensor.get("posTwo");

        retract = 1;
        pickUp = 0.;

        swoop.setPosition(.5);
        elbow.setPosition(1);
         stager = 3;

    }


    public void loop() {

        updateState();


        if (gamepad1.right_bumper == true) {
            stager += .25;
            telemetry.addData("shifted", "up");
        }
        if (gamepad1.left_bumper == true) {
            stager -= .25;
            telemetry.addData("shifted", "down");
        }
        if (stager > 1) {
            stager = 1;
        }
        if (stager < .25) {
            stager = .25;
        }
        if (gamepad1.a == true) {
            moveNet(stager);
        } else {

            swoop.setPosition(.5);


            if (gamepad1.y == true) {

                elbow.setPosition(.5);

            } else if (gamepad1.x == true) {
                elbow.setPosition(1);
            }
        }
    }
}
