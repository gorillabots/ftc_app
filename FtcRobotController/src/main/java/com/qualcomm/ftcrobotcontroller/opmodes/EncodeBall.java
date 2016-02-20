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
    TouchSensor posFour;
    boolean stateOne;
    boolean stateTwo;
    boolean stateThree;
    boolean stateFour;
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

        if(posFour.isPressed()){
            stateFour = true;
        }
        else{
            stateFour = false;
        }

        if(stateOne==true && stateTwo==false && stateThree==false && stateFour == false){
            currentPos = 1;

        }
        if(stateOne==false && stateTwo==true && stateThree==false && stateFour == false){
            currentPos=2;
        }
        if(stateOne==false && stateTwo==false && stateThree==true && stateFour == false){
            currentPos=3;
        }
        if(stateOne==false && stateTwo==false && stateThree==false && stateFour == false){
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
        while(currentPos == stage){
            swoop.setPosition(.5);
        }

    }



    public void init() {
        swoop = hardwareMap.servo.get("swoop");
        elbow = hardwareMap.servo.get("elbow");
        posOne = hardwareMap.touchSensor.get("posOne");
        posTwo = hardwareMap.touchSensor.get("posTwo");
        posThree = hardwareMap.touchSensor.get("posThree");
        posFour = hardwareMap.touchSensor.get("posFour");

        retract = 1;
        pickUp = 0.;

        swoop.setPosition(.5);
        elbow.setPosition(1);
         stager = 3;

    }


    public void loop() {

        updateState();


        if (gamepad1.right_bumper == true) {
            stager += 1;
            telemetry.addData("shifted", "up");
        }
        if (gamepad1.left_bumper == true) {
            stager -= 1;
            telemetry.addData("shifted", "down");
        }
        if (stager > 4) {
            stager = 4;
        }
        if (stager < 1) {
            stager = 1;
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
