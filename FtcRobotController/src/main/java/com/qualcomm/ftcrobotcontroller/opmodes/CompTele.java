package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
/**
 * Created by Jarred on 10/18/2015.
 */
public class CompTele extends OpMode {

    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    DcMotor motor4;
    DcMotor motor5;
    DcMotor motor6;
    double drive;

/* in lines 10 through ______ we declare the use of differenr motors.
*/


    public void driveSide(String side, float power) {
        if (side == "left") {
            motor1.setPower((double) power);
            motor2.setPower((double) power);


        } else {
            motor3.setPower((double) power);
            motor4.setPower((double) power);

        }

    }
 /* the above custom metheod is meant to allow us to use the motors that are connected to the the wheels by side without having to
    declare the motor values individually */

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
/*
Above, in the init phase, we map all of the hardware.
 */
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


/*
above is the code that is used to drive the robot using the left and right sticks on the first controller.
 */



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

/*
Above is the the shifter for the drive train that allows the drive train to run at 4 different speeds with the default speed being .5
 */
        boolean forward = gamepad2.right_bumper;
        float downward = gamepad2.right_trigger;

        boolean rotateOne = gamepad2.left_bumper;
        float rotateTwo = gamepad2.left_trigger;
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

        /*
        above is the code for the arm. The arm can extend retract at maximum speed but the arm can only rotate at a maximum speed
        of 50%
         */


    }
}