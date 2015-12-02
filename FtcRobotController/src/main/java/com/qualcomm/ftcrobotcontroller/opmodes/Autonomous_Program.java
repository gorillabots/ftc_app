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
        double m1old = motor1.getPower();
        double m2old = motor2.getPower();
        double m3old = motor3.getPower();
        double m4old = motor4.getPower();
        motor1.setPower(-power);
        motor2.setPower(-power);
        motor3.setPower(-power);
        motor4.setPower(-power);
        sleep(time);
        motor1.setPower(m1old);
        motor2.setPower(m2old);
        motor3.setPower(m3old);
        motor4.setPower(m4old);
    }
    void turn_right(double power, long time)throws InterruptedException{
        double m1old = motor1.getPower();
        double m2old = motor2.getPower();
        double m3old = motor3.getPower();
        double m4old = motor4.getPower();
        motor1.setPower(power);
        motor2.setPower(power);
        motor3.setPower(power);
        motor4.setPower(power);
        sleep(time);
        motor1.setPower(m1old);
        motor2.setPower(m2old);
        motor3.setPower(m3old);
        motor4.setPower(m4old);
    }
    void forward(double power){
        motor1.setPower(-power);
        motor2.setPower(-power);
        motor3.setPower(power);
        motor4.setPower(power);
    }
    void backward(double power, long time)throws InterruptedException{
        double m1old = motor1.getPower();
        double m2old = motor2.getPower();
        double m3old = motor3.getPower();
        double m4old = motor4.getPower();
        motor1.setPower(power);
        motor2.setPower(power);
        motor3.setPower(-power);
        motor4.setPower(-power);
        sleep(time);
        motor1.setPower(m1old);
        motor2.setPower(m2old);
        motor3.setPower(m3old);
        motor4.setPower(m4old);
    }
    void stop_robot(){
        //this will stop the robot
        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);
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
            //gives lego color sensor value
            stop_robot();
            sleep(500);
            //stops the robot for 0.5 seconds
            forward(0.01);
            //moves forward at 0.01 power
            if (color2.blue() > 40 || color2.red() > 40) {
                stop_robot();
                sleep(500);
                //if the lego color sensor detects any red or blue value greater than 40, it should stop
                if (color2.blue() > color2.red() && color2.blue() > 50) {
                    turn_left(0.01, 2000);
                    //if blue is greater than red and greater than 50, the robot turns left for 2 seconds
                }
                else if(color2.red() > color2.blue() && color2.red() > 50){
                    turn_right(0.01, 2000);
                }
            }
        }
    }
}
