package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * Created by emper on 11/1/2015.
 */
public class colorsensor extends OpMode{
    ColorSensor Leftcolor;
    ColorSensor Rightcolor;
    ColorSensor Floorcolor;
    String teamcolor = "red";
    String notteamcolor = "blue";
    public void init() {
        Leftcolor = hardwareMap.colorSensor.get("Leftcolor_sensor");
        Leftcolor.setI2cAddress(60);
        Rightcolor = hardwareMap.colorSensor.get("Rightcolor_sensor");
        Rightcolor.setI2cAddress(62);
        Floorcolor = hardwareMap.colorSensor.get("Floorcolor_sensor");
        Floorcolor.setI2cAddress(62);
        Leftcolor.enableLed(false);
        Rightcolor.enableLed(false);
        Floorcolor.enableLed(true);

    }

    /**
     * returns red or blue or none depending on what color is read from the floor sensor.
     * @return String
     */
    public String getFloorcolor(){
        String currentcolor = "none";

        if (Floorcolor.red()>Floorcolor.green() && Floorcolor.red()>Floorcolor.blue() && Floorcolor.green()>=Floorcolor.blue()){
            currentcolor = "red";
        }

        if (Floorcolor.red()<Floorcolor.green() && Floorcolor.blue()<Floorcolor.green() && Floorcolor.red() == Floorcolor.blue()) {
            currentcolor = "blue";
        }
        return currentcolor;

    }

    /**
     * retruns red or blue or none depending on what color is read from the beacon sensor.
      * @return String
     */
    public String getBeaconcolor(ColorSensor color){
        String currentcolor = "none";

        if (color.red()>color.blue() && color.red()>color.green() && color.green()== color.blue()){
            currentcolor = "red";
        }
        if (color.red()<color.blue() && color.green()<color.blue() && color.blue()>1){
            currentcolor = "blue";
        }
        return currentcolor;
    }
    public void loop() {

        String whatColorIsRight = getBeaconcolor(Rightcolor);
        String whatColorIsLeft = getBeaconcolor(Leftcolor);
        String whatColorIsFloor = getFloorcolor();
        telemetry.addData("Rightbeacon_color" , whatColorIsRight);
        telemetry.addData("Leftbeacon_color" , whatColorIsLeft);
        telemetry.addData("Floor_color" , whatColorIsFloor);
        if(whatColorIsRight == teamcolor){
            /*
            go foward
            motor1.setPower(1);
            motor2.setPower(1);
            motor3.setPower(-1);
            motor4.setPower(-1);
             */
        }
        else if (whatColorIsRight == notteamcolor){
            /*
            pivot backwards
            pivot other side fully
            pivot.setPosition(Servo.MAX_POSITION);

            go foward
            motor1.setPower(1);
            motor2.setPower(1);
            motor3.setPower(-1);
            motor4.setPower(-1);
             */

        }
        else{
             /*
            stop program
            motor1.setPower(0);
            motor2.setPower(0);
            motor3.setPower(0);
            motor4.setPower(0);

             */


        }

}
    public void stop() {

    }
}