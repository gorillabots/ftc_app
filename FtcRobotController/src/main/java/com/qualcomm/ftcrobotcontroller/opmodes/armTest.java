package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
 import com.qualcomm.robotcore.hardware.TouchSensor;
/**
 * Created by Jarred on 10/25/2015.
 */
public class armTest extends OpMode {

DcMotor motor5;
DcMotor motor6;
TouchSensor limit;
    @Override
    public void init() {
        motor5 = hardwareMap.dcMotor.get("motor5");
        motor6 = hardwareMap.dcMotor.get("motor6");

        limit = hardwareMap.touchSensor.get("limit");
    }
    @Override
    public void loop() {

        float upward = gamepad1.left_stick_y;
        float rotate = gamepad1.right_stick_y;

        if (limit.isPressed()) {
            motor5.setPower(0.0);
        } else {
            motor5.setPower(upward);
        }

        motor6.setPower(rotate);


    }

}
