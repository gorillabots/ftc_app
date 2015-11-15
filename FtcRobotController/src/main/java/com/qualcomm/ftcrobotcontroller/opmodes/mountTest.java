package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
/**
 * Created by Jarred on 10/18/2015.
 */
public class mountTest extends OpMode {

    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    DcMotor motor4;
    DcMotor motor5;
    DcMotor motor6;
    double drive;

    public void driveSide(String side, float power) {
        if (side == "left") {
            motor1.setPower((double) power);
            motor2.setPower((double) power);


        } else {
            motor3.setPower((double) power);
            motor4.setPower((double) power);

        }

    }


    @Override
    public void init() {
        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");
        motor3 = hardwareMap.dcMotor.get("motor3");
        motor4 = hardwareMap.dcMotor.get("motor4");
        motor5 = hardwareMap.dcMotor.get("motor5");
        motor6 = hardwareMap.dcMotor.get("motor6");

      double  drive = .5;


    }

    @Override
    public void loop() {
        float throttleLeft = gamepad1.left_stick_y;
        float throttleRight = gamepad1.right_stick_y;
        //boolean speedOne = gamepad1.right_bumper;
        //float speedTwo = gamepad1.right_trigger;
        //boolean  speedThree = gamepad1.left_bumper;
        //float speedFour = gamepad1.left_trigger;

        motor1.setPower((throttleLeft *drive* -1));
        motor2.setPower((throttleLeft *drive* -1));
        motor3.setPower((throttleRight*drive));
        motor4.setPower((throttleRight*drive));




        boolean forward = gamepad2.right_bumper;
        float downward = gamepad2.right_trigger;

        boolean rotateOne = gamepad2.left_bumper;
        float rotateTwo = gamepad2.left_trigger;

        if(gamepad1.right_bumper = true) {
            drive += .25;
        }
        if(gamepad1.right_trigger >= .75) {
            drive -= .25;
        }
        if(drive>1){
            drive = 1;
        }
        if(drive<.25) {
            drive = .25;
        }



        if (forward == true) {
            motor5.setPower(1);

        }



        else if(downward == 1.0) {

            motor5.setPower((-1));
        }
        else{
            motor5.setPower(0.0);
        }
        if(rotateOne == true) {
            motor6.setPower(.5);

        }

        else if(rotateTwo == 1.0) {
            motor6.setPower(-.5);
        }
        else{
            motor6.setPower(0.0);
        }

    }
}