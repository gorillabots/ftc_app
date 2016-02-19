package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
/**
 * Created by Jarred on 2/14/2016.
 */
public class hook extends OpMode{

    Servo hook;



    public void init(){
        hook = hardwareMap.servo.get("hook");
    }

    public void loop(){

    }
}
