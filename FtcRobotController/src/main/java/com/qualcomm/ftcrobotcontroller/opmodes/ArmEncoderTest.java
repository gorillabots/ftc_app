package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Jarred on 11/21/2015.
 */
public class ArmEncoderTest extends OpMode {

    DcMotor motor5;
    DcMotor motor6;

        public static int ENCODER_CPR = 1440;
        public static double GEAR_RATIO = 10 ;
        public static int WHEEL_DIAMETER = 2 ;
        public int DISTANCE = 24;

   final public  double CIRCUMFRENCE = Math.PI* WHEEL_DIAMETER;
   final public   double ROTATIONS = DISTANCE/CIRCUMFRENCE;
   final public  double COUNTS = ENCODER_CPR * ROTATIONS *GEAR_RATIO;



    public void init() {

        motor5 = hardwareMap.dcMotor.get("motor5");
        motor6 = hardwareMap.dcMotor.get("motor6");


    }
    @Override
    public void loop() {

        motor5.setTargetPosition((int) COUNTS);

        float extension = gamepad2.left_stick_y;
        float swing = gamepad2.right_stick_y;

        motor5.setPower(extension);
        motor6.setPower(swing);




    }





}
