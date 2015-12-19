package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorController.RunMode;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.qualcomm.robotcore.hardware.Servo;
/**
 * Created by emper on 12/11/2015.
 */
public class AdafruitRGBExample extends LinearOpMode {
  ColorSensor color;
  UltrasonicSensor distance;
  UltrasonicSensor distance2;
  DcMotor motor1;
  DcMotor motor2;
  DcMotor motor3;
  DcMotor motor4;
  //    Servo servo1;
  //  Servo servo2;
  Servo screw;
  Servo pivot;

  void turn_left(double power, long time) throws InterruptedException {
    motor1.setPower(-power);
    motor2.setPower(-power);
    motor3.setPower(-power);
    motor4.setPower(-power);
    sleep(time);
  }

  void turn_right(double power, long time) throws InterruptedException {
    motor1.setPower(power);
    motor2.setPower(power);
    motor3.setPower(power);
    motor4.setPower(power);
    sleep(time);
  }

  void forward(double power) {
    motor1.setPower(-power);
    motor2.setPower(-power);
    motor3.setPower(power);
    motor4.setPower(power);
  }

  void forward_with_time(double power, long time) throws InterruptedException {
    motor1.setPower(-power);
    motor2.setPower(-power);
    motor3.setPower(power);
    motor4.setPower(power);
    sleep(time);
  }
  void forward_with_stops(double power, long time) throws InterruptedException{
    for(int i = 0; i<= time; i = i+250){
      forward_with_time(power,250);
      if(color.red() > 2){
        break;
      }
      if(color.blue() >2){
        break;
      }
    }
  }
  void backward(double power, long time) throws InterruptedException {
    motor1.setPower(power);
    motor2.setPower(power);
    motor3.setPower(-power);
    motor4.setPower(-power);
    sleep(time);
  }

  void backward_without_time(double power) {
    motor1.setPower(power);
    motor2.setPower(power);
    motor3.setPower(-power);
    motor4.setPower(-power);
  }

  void stop_robot(long time) throws InterruptedException {
    //this will stop the robot
    motor1.setPower(0);
    motor2.setPower(0);
    motor3.setPower(0);
    motor4.setPower(0);
    sleep(time);
  }

  public void _init() {
    motor1 = hardwareMap.dcMotor.get("motor1");//motor1 on AL00VTH7
    motor2 = hardwareMap.dcMotor.get("motor2");//motor2 on AL00VTH7
    motor3 = hardwareMap.dcMotor.get("motor3");//motor3 on AL00YC5Z
    motor4 = hardwareMap.dcMotor.get("motor4");//motor4 on AL00YC5Z
    color = hardwareMap.colorSensor.get("color");//beacon sensor
    distance = hardwareMap.ultrasonicSensor.get("distance");
    distance2 = hardwareMap.ultrasonicSensor.get("distance2");
    //servo1 = hardwareMap.servo.get("frontGo");
    //servo2 = hardwareMap.servo.get("backGo");
    screw = hardwareMap.servo.get("screw");
    pivot = hardwareMap.servo.get("pivot");
    motor1.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    motor2.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    motor3.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    motor4.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    motor1.setChannelMode(RunMode.RUN_USING_ENCODERS);
    motor2.setChannelMode(RunMode.RUN_USING_ENCODERS);
    motor3.setChannelMode(RunMode.RUN_USING_ENCODERS);
    motor4.setChannelMode(RunMode.RUN_USING_ENCODERS);
    pivot.setPosition(Servo.MAX_POSITION);
    screw.setPosition(.5);
  }

  @Override
  public void runOpMode() throws InterruptedException {
    _init();
    waitForStart();
    while (opModeIsActive()) {
      //  while(distance.getUltrasonicLevel() > 32 && distance2.getUltrasonicLevel() > 53) {
      //    telemetry.addData("left", distance2.getUltrasonicLevel());
      //  telemetry.addData("right", distance.getUltrasonicLevel());
      //}
      backward(0.4,6750);
      turn_left(0.5, 450);
      backward(0.2, 1400);
      motor1.setPower(0.0);
      motor2.setPower(0.0);
      motor3.setPower(0.0);
      motor4.setPower(0.0);

      pivot.setPosition(Servo.MIN_POSITION);
      sleep(2000);
      screw.setPosition(0.0);
      sleep(7000);
      screw.setPosition(.5);
      stop_robot(100000);
      //amount of time needed for 135 degree turn
      //while(true){
      //telemetry.addData("blue", color.blue());
      //telemetry.addData("red", color.red());
      //stop_robot(10);}
    }
  }
}
