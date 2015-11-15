package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
/**
 * Created by Jarred on 10/18/2015.
 */
public class teletest3 extends OpMode  {

    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    DcMotor motor4;




    public void driveSide(String side,float power) {
        if(side == "left") {
            motor1.setPower((double) power);
            motor2.setPower((double) power);



        }
        else{
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


    }
    @Override
    public void loop()  {
        float throttleLeft = gamepad1.left_stick_y;
        float throttleRight = gamepad1.right_stick_y;
        //        driveSide("left",throttleLeft);
        //      driveSide("left",throttleRight);


        //motor1.setPower((throttleLeft*-1));
        //motor2.setPower((throttleLeft*-1));
        //motor3.setPower((throttleRight));
        //motor4.setPower((throttleRight));

        while(gamepad1.y == true && gamepad1.a == false){
            motor1.setPower(-0.5);
            motor3.setPower(-0.5);
            motor2.setPower(0.0);
            motor4.setPower(0.0);
        }
        while(gamepad1.y == false && gamepad1.a == true){
            motor1.setPower(0.5);
            motor3.setPower(0.5);
            motor2.setPower(0.0);
            motor4.setPower(0.0);
        }

    }













}