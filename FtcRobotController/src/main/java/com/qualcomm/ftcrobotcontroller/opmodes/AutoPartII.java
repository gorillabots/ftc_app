package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
/**
 * Created by Jarred on 12/13/2015.
 */
public class AutoPartII extends LinearOpMode{

    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    DcMotor motor4;
    Servo pivot;
    Servo screw;


    @Override
    public void runOpMode() throws InterruptedException {
          motor1 = hardwareMap.dcMotor.get("motor1");
          motor2 = hardwareMap.dcMotor.get("motor2");
          motor3 = hardwareMap.dcMotor.get("motor3");
          motor4 = hardwareMap.dcMotor.get("motor4");
          pivot = hardwareMap.servo.get("pivot");
          screw = hardwareMap.servo.get("screw");

        motor1.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motor2.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motor3.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motor4.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);

        waitForStart();
        while(opModeIsActive()){







        }
    }
}
