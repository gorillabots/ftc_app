package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Jarred on 11/21/2015.
 */
public class JarredServoTest extends OpMode{



    boolean pivotP;
    float pivotB;
    boolean corkGo;
    float corkOff;

    final double MAX_POSITION = 1.0 ;
    final double MIN_POSITION = 0.0;

    Servo pivot;
    Servo screw;
    //DcMotor motor1;
    //Servo backGo;
    //Servo frontGo;

    @Override

    public void init() {

        pivot = hardwareMap.servo.get("pivot");
        screw = hardwareMap.servo.get("screw");


        pivotP = gamepad1.right_bumper;
        pivotB = gamepad1.right_trigger;
        corkGo = gamepad1.left_bumper;
        corkOff = gamepad1.left_trigger;
    }

    @Override
    public void loop() {



        telemetry.addData("pivot position", pivot.getPosition());


        if (gamepad1.right_bumper == true) {
            telemetry.addData("pivot", " max position ");

            pivot.setPosition(Servo.MAX_POSITION);
            //telemetry.addData("pivotP", " just moved back ");
        }

        else if (gamepad1.right_trigger >= .75) {
            telemetry.addData("pivot", " min position ");
            pivot.setPosition(Servo.MIN_POSITION);
        //    telemetry.addData("pivotB", " just moved back ");
        }

        if (gamepad1.left_bumper == true) {
            telemetry.addData("screw", " turn on the corkscrew ");
            screw.setDirection(Servo.Direction.FORWARD);
        }
        else if(gamepad1.left_trigger >= .75) {
            telemetry.addData("screw", " turn off the corkscrew ");
            screw.close();
            }

        }
    }