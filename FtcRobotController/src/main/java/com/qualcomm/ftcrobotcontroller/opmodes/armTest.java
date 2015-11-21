package com.qualcomm.ftcrobotcontroller.opmodes;
 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
   import com.qualcomm.robotcore.hardware.DcMotor;
/**
 * Created by Jarred on 10/25/2015.
 */
public class armTest extends OpMode {

DcMotor motor1;
DcMotor motor2;
    @Override
    public void init() {
        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");

    }
    @Override
    public void loop() {

        float upward = gamepad1.left_stick_y;
        float rotate = gamepad1.right_stick_y;

        motor1.setPower(upward/2);
        motor2.setPower(rotate/2);





    }
}
