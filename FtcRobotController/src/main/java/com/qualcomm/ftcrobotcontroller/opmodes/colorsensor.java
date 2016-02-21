package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * Created by emper on 11/1/2015.
 */
public class colorsensor extends OpMode{
    ColorSensor color;
    public void init() {
    color = hardwareMap.colorSensor.get("color_sensor");
    }
    public void loop() {
        color.enableLed(false);
        telemetry.addData("red value", color.red());
        telemetry.addData("blue value", color.blue());
        if (color.red()>color.blue() && color.red()>color.green() ){
            telemetry.addData("current_color", "Red");
        }
        if (color.red()<color.blue()){
            telemetry.addData("current_color", "Blue");
        }
        if (color.red()>color.green() && color.red()>color.blue()){
            telemetry.addData("current_color", "red line");;
        }
        if (color.red()<color.alpha()){
            telemetry.addData("current_color", "blue line");
        }
//        if (color.alpha()>color.blue() && color.alpha()>color.green() && color.alpha()>color.red()){
//            telemetry.addData("current_color", "white line");
//        }

        telemetry.addData("red value", color.red());
        telemetry.addData("blue value", color.blue());
        telemetry.addData("green value", color.green());
        telemetry.addData("white value", color.alpha());

}
    public void stop() {

    }
}
