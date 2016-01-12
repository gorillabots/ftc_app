package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by Jarred on 10/18/2015.
 */
public class Comp2Red extends OpMode {

    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    DcMotor motor4;
    DcMotor motor5;
    DcMotor motor6;
    double drive;
    Servo screw;
    Servo pivot;
    Servo rightGo;
    //Servo leftGo;
    int direction;
    TouchSensor limit;
/* in lines 10 through ______ we declare the use of differenr motors.
*/

/*
    public void driveSide(String side, float power) {
        if (side == "left") {
      //      motor1.setPower((double) power);
            motor2.setPower((double) power);


        } else {
        //    motor3.setPower((double) power);
            motor4.setPower((double) power);

        }
*/

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
        pivot = hardwareMap.servo.get("pivot");
        screw = hardwareMap.servo.get("screw");
     //   leftGo = hardwareMap.servo.get("backGo");
        rightGo = hardwareMap.servo.get("frontGo");
        limit = hardwareMap.touchSensor.get("limit");

        double drive = 1;
        direction = 1;

        screw.setPosition(.5);
    pivot.setPosition(.7777777);

        //leftGo.setPosition(0);
        rightGo.setPosition(.8);

    }

    /*
    Above, in the init phase, we map all of the hardware.
     */
    @Override
    public void loop() {
        telemetry.addData("drive is ", drive);
        telemetry.addData("screw", screw.getDirection());
        telemetry.addData("pivot is at", pivot.getPosition());
        //telemetry.addData("left toucher is at ", leftGo.getPosition() );
        telemetry.addData("right toucher is at", rightGo.getPosition());

        if(direction == 1){
            telemetry.addData("direction","forward");
        }
        if(direction == -1){
            telemetry.addData("direction","reverse");

        }


        float throttleLeft = gamepad1.left_stick_y;
        float throttleRight = gamepad1.right_stick_y;
        //boolean speedOne = gamepad1.right_bumper;
        //float speedTwo = gamepad1.right_trigger;
        //boolean  speedThree = gamepad1.left_bumper;
        //float speedFour = gamepad1.left_trigger;
        motor4.setPower((gamepad1.left_stick_y * drive  * -1)*direction );
        motor3.setPower((throttleLeft * drive  * -1)*direction);
        motor2.setPower((gamepad1.right_stick_y * drive)*direction );
        motor1.setPower((throttleRight * drive)*direction);






        telemetry.addData("Left drive control",gamepad1.left_stick_y );
        telemetry.addData("Right drive control",gamepad1.right_stick_y);



    /*
        if (throttleLeft >= .0625) {
            telemetry.addData("left side", "backward");
        }
        if (throttleLeft <= -.0625) {
            telemetry.addData("left side", "forward");
        }
        if (throttleRight >= .0625) {
            telemetry.addData("right side", "forward");
        }
        if (throttleRight <= -.0625) {
            telemetry.addData("right side", "backward");
        }
  */

/*
above is the code that is used to drive the robot using the left and right sticks on the first controller.
 */


        if (gamepad1.right_bumper == true) {
            drive += .25;
            telemetry.addData("shifted", "up");
        }
        if (gamepad1.left_bumper == true) {
            drive -= .25;
            telemetry.addData("shifted", "down");
        }
        if (drive > 1) {
            drive = 1;
        }
        if (drive < .25) {
            drive = .25;
        }


        if(gamepad1.dpad_up == true){
            direction = 1;
        }

        else if(gamepad1.dpad_down == true){
            direction = -1;
        }

/*
Above is the the shifter for the drive train that allows the drive train to run at 4 different speeds with the default speed being .5
 */
        int armExtend = Math.round(gamepad2.right_stick_y);
        float armRotate = gamepad2.left_stick_y;

        if(limit.isPressed()== true){

            motor6.setPower(((Math.abs(gamepad2.right_stick_y)))/2);
        }
        else{


            motor6.setPower(armExtend);


        }
        motor5.setPower(armRotate);

        if (armExtend >= .25) {
            telemetry.addData("arm", "extending");
        }
        if (armExtend <= -.25) {
            telemetry.addData("arm", "retracting");
        }



        if (gamepad2.right_bumper == true) {
            pivot.setPosition(Servo.MAX_POSITION);
        }

        if (gamepad2.right_trigger >= .75) {
            pivot.setPosition(Servo.MIN_POSITION);
        }

        if (gamepad2.left_bumper == true) {
            screw.setPosition(0.0);
        } else //if (gamepad2.left_trigger >= .75)
         {
            telemetry.addData("screw", "off");
            screw.setPosition(.5);
        }


        /*
        The above lines of code control the corkscrew and the arm it is mounted on.
        The arm moves from maximum to minimum positions and also toggles the corkscrew
         */




//        if (gamepad1.left_trigger >= .5) {

  //          leftGo.setPosition(gamepad1.left_trigger*.6);
    //    } else {
      //      leftGo.setPosition(.0);

        //}
        if (gamepad1.right_trigger >= .5) {
            rightGo.setPosition((gamepad1.right_trigger * -.7) +.8);

        }
        else {
            rightGo.setPosition(0.8);
        }

/*
        if(gamepad1.x == true) {
            leftGo.setPosition(.6);
        }

        if(gamepad1.a == true) {
            rightGo.setPosition(.2);
        }
  */
        /*
        The above 4 if statements control the pokers. The pokers flip the levers controlling the zip line.
         They can be controlled with the x and a buttons or for more precise control


         */

    }

}