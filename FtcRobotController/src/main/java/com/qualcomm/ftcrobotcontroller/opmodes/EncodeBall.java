package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.hardware.HardwareDeviceManager;
import com.qualcomm.robotcore.hardware.AnalogInputController;
import com.qualcomm.robotcore.hardware.DeviceManager;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;

/**
 * Created by Jarred on 2/5/2016.
 */
public class EncodeBall extends OpMode {

    Servo swoop;
    Servo elbow;

    AnalogInput posOne;
    AnalogInput posTwo;

    boolean stateOne;
    boolean stateTwo;

    int stateOneTest;
    int stateTwoTest;


    int currentPos;
    int directionGo;
    double stager;

    public int updateState(){

        stateOneTest = posOne.getValue();
        stateTwoTest = posTwo.getValue();

        telemetry.addData("state1", stateOneTest);
        telemetry.addData("state2", stateTwoTest);

        stateOne = stateOneTest >= 1020;
        stateTwo = stateTwoTest >= 1020;

        if(!stateOne &&  stateTwo ){
            currentPos = 1;

        }
        else if(stateOne && !stateTwo ){
            currentPos=2;
        }
        else if(!stateOne && !stateTwo ){
            currentPos=3;
        }
        return currentPos;

    }

    public void moveNet(double stager){

        currentPos = updateState();

        if(stager > currentPos){
            swoop.setPosition(0);

        }
        else if(stager < currentPos){
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
        posOne = hardwareMap.analogInput.get("A0");
        posTwo = hardwareMap.analogInput.get("A1");

        currentPos = 1;
        swoop.setPosition(.5);
        elbow.setPosition(1);


    }


    public void loop() {

        telemetry.addData("stager",stager);
        telemetry.addData("current", currentPos);

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
