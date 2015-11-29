package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorController.RunMode;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
/**
 * Created by emper on 11/15/2015.
 */

public class Autonomous_Program extends LinearOpMode{
    ColorSensor color;
    ColorSensor color2;
    UltrasonicSensor distance;
    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    DcMotor motor4;
    void turn_left(double power, long time)throws InterruptedException{
        motor1.setPower(-power);
        motor2.setPower(-power);
        motor3.setPower(-power);
        motor4.setPower(-power);
        Thread.sleep(time);
        telemetry.addData("red", color2.red());
    }

    public void _init() {
        motor1 = hardwareMap.dcMotor.get("motor1");//motor1 on AL00VTH7
        motor2 = hardwareMap.dcMotor.get("motor2");//motor2 on AL00VTH7
        motor3 = hardwareMap.dcMotor.get("motor3");//motor3 on AL00YC5Z
        motor4 = hardwareMap.dcMotor.get("motor4");//motor4 on AL00YC5Z
        color = hardwareMap.colorSensor.get("color");//beacon sensor
        color2 = hardwareMap.colorSensor.get("color2");//lego sensor
        distance = hardwareMap.ultrasonicSensor.get("distance");
        motor1.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motor2.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motor3.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motor4.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motor1.setChannelMode(RunMode.RUN_USING_ENCODERS);
        motor2.setChannelMode(RunMode.RUN_USING_ENCODERS);
        motor3.setChannelMode(RunMode.RUN_USING_ENCODERS);
        motor4.setChannelMode(RunMode.RUN_USING_ENCODERS);
    }
    @Override
    public void runOpMode() throws InterruptedException{
        _init();
        waitForStart();
        while(opModeIsActive()) {
            telemetry.addData("red", color2.red());
            telemetry.addData("blue", color2.blue());
            motor1.setPower(-0.01);
            motor2.setPower(-0.01);
            motor3.setPower(0.01);
            motor4.setPower(0.01);
            if (color2.blue() > 40 || color2.red() > 40) {
                motor1.setPower(0.0);
                motor2.setPower(0.0);
                motor3.setPower(0.0);
                motor4.setPower(0.0);
                if (color2.blue() > color2.red() && color2.blue() > 50) {
                    turn_left(0.01, 2000);
                }
            }
        }
    }
}
