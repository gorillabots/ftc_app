package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by Jarred on 11/21/2015.
 */
public class ArmStopFinder extends OpMode {

    DcMotor motor5;
    DcMotor motor6;

    //public static int ENCODER_CPR = 1440;
    //public static double GEAR_RATIO = 10 ;
    //public static int WHEEL_DIAMETER = 2 ;
    //public int DISTANCE = 24;

    //final public  double CIRCUMFRENCE = Math.PI* WHEEL_DIAMETER;
    //final public   double ROTATIONS = DISTANCE/CIRCUMFRENCE;
    //final public  double COUNTS = ENCODER_CPR * ROTATIONS *GEAR_RATIO;



    public void init() {

        motor5 = hardwareMap.dcMotor.get("motor5");
        motor6 = hardwareMap.dcMotor.get("motor6");

        motor5.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motor6.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    }
    @Override
    public void loop() {



        float extension = gamepad2.left_stick_y;
        float swing = gamepad2.right_stick_y;

        motor5.setPower(extension);
        motor6.setPower(swing);

        //float extensionValue = 1440*80;


        if(gamepad1.right_bumper == true) {
            telemetry.addData("motor is at", motor5.getCurrentPosition());
        }
        //if(motor5.getCurrentPosition( ) >= extensionValue) {
        //    motor5.setPower(0.0);
        ///}
      // / else{
          //  motor5.setPower(extension);
        //}

    }

}






