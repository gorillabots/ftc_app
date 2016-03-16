package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.hardware.HardwareDeviceManager;
import com.qualcomm.robotcore.hardware.AnalogInputController;
import com.qualcomm.robotcore.hardware.DeviceManager;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Jarred on 2/5/2016.
 */
public class EncodeBall extends LinearOpMode {


    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    DcMotor motor4;


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

    ElapsedTime timer;

    double stopper;




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



        currentPos = updateState();

        if (stager != currentPos)  {
            swoop.setPosition(0);

        }
        else if(currentPos == stager )  {
            telemetry.addData("stopper",stopper);
            swoop.setPosition(stopper);

        }

    }



    // .49803922
    //0.00196078
    //0.50196078
    public void _init() {
        swoop = hardwareMap.servo.get("swoop");
        elbow = hardwareMap.servo.get("elbow");
        posOne = hardwareMap.analogInput.get("A0");
        posTwo = hardwareMap.analogInput.get("A1");

        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");
        motor3 = hardwareMap.dcMotor.get("motor3");
        motor4 = hardwareMap.dcMotor.get("motor4");

        //stopper = 0.52;

        stopper =.502;
        updateState();

        stager = 1;
        elbow.setPosition(1);
        swoop.setPosition(stopper);

        timer = new ElapsedTime();
        timer.startTime();


    }


    public void runOpMode() throws InterruptedException {
        _init();
        waitForStart();
        while (opModeIsActive()) {
            moveNet(stager);
            telemetry.addData("stager", stager);
            telemetry.addData("current", currentPos);
            telemetry.addData("timer", timer.toString());
            telemetry.addData(("spin"), swoop.getPosition());

            if (gamepad2.right_bumper && timer.time() >= 1) {
                stager += 1;
                telemetry.addData("shifted", "up");
                timer.reset();
            }
            if (gamepad2.left_bumper && timer.time() >= 1) {
                stager -= 1;
                telemetry.addData("shifted", "down");
                timer.reset();
            }
            if (stager > 3) {
                stager = 3;
            }

        if(stager<1){

            stager =1;
        }

            if (gamepad2.y == true) {
                elbow.setPosition(.5);
            } else if (gamepad2.x == true) {
                elbow.setPosition(1);
            }

            motor4.setPower((gamepad1.left_stick_y*-1));
            motor3.setPower((gamepad1.left_stick_y*-1));
            motor2.setPower((gamepad1.right_stick_y));
            motor1.setPower((gamepad1.right_stick_y));

            if(gamepad2.dpad_up==true && timer.time() > 1 ){
                stopper += .0001;
                timer.reset();
                    }
            if(gamepad2.dpad_down==true && timer.time() > 1 ){
                stopper -= .0001;
                timer.reset();
            }

        }
    }
}
