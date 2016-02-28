package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.hardware.HardwareDeviceManager;
import com.qualcomm.robotcore.hardware.AnalogInputController;
import com.qualcomm.robotcore.hardware.DeviceManager;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Jarred on 2/5/2016.
 */
public class EncodeBall extends LinearOpMode {

    Servo swoop;
    //Servo elbow;

    AnalogInput posOne;
    AnalogInput posTwo;

    boolean stateOne;
    boolean stateTwo;

    int stateOneTest;
    int stateTwoTest;


    int currentPos;
    int directionGo;
    double stager;

    ElapsedTime timer;

    boolean running;

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

    public void moveNet(double stager) throws  InterruptedException{

        updateState();

        currentPos = updateState();

        if (stager > currentPos) {
            swoop.setPosition(.2);

        } else if (stager < currentPos) {

            swoop.setPosition(.8);
        } else if (stager == currentPos) {
            swoop.setPosition(.50196078);

        }

    }



    // .49803922
    //0.00196078
    //0.50196078
    public void _init() {
        swoop = hardwareMap.servo.get("swoop");
        //elbow = hardwareMap.servo.get("elbow");
        posOne = hardwareMap.analogInput.get("A0");
        posTwo = hardwareMap.analogInput.get("A1");
        running = false;
        updateState();

        stager = 1;
        swoop.setPosition(0.5);
        // elbow.setPosition(1);
        timer = new ElapsedTime();
        timer.startTime();
    }


    public void runOpMode() throws InterruptedException {
        _init();
        waitForStart();
        while (opModeIsActive()) {



            updateState();
            moveNet(stager);
            telemetry.addData("stager", stager);
            telemetry.addData("current", currentPos);
            telemetry.addData("timer", timer.toString());
            telemetry.addData(("spin"), swoop.getPosition());

            if (gamepad1.right_bumper && timer.time() >= 1) {
                stager += 1;
                telemetry.addData("shifted", "up");
                timer.reset();
            } else if (gamepad1.left_bumper && timer.time() >= 1) {
                stager -= 1;
                telemetry.addData("shifted", "down");
                timer.reset();
            }
            if (stager > 3) {
                stager = 3;
            }
            if (stager < 1) {
                stager = 1;
            }
/*

            if (gamepad1.y == true) {

                elbow.setPosition(.5);

            } else if (gamepad1.x == true) {
                elbow.setPosition(1);
            }

*/


                moveNet(stager);


        }
    }
}
