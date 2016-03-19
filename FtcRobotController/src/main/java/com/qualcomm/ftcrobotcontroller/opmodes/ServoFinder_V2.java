package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by Jarred on 12/31/2015.
 */
public class ServoFinder_V2 extends OpMode{
    Servo hook;



    @Override

    public void init(){
        hook = hardwareMap.servo.get("hook");

    }
    @Override

    public void loop(){


            telemetry.addData("hooks", hook.getPosition());

            hook.setPosition(Math.abs(gamepad1.left_stick_y));

    }
}
