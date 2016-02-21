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

    public String getColor(){
        String currentcolor = "none";

        if (color.red()>color.blue() && color.red()>color.green() && color.green()== color.blue()){

            currentcolor = "red";
        }

        if (color.red()<color.blue() && color.green()<color.blue()){

            currentcolor = "blue";
        }

        if (color.red()>color.green() && color.red()>color.blue() && color.green()>color.blue()){

            currentcolor = "red line";
        }

        if (color.red()<color.green() && color.blue()<color.green() && color.red() == color.blue()){

            currentcolor = "blue line";
        }
        telemetry.addData("current_color" , currentcolor);
        return currentcolor;
    }
    public void loop() {
        color.enableLed(false);
        telemetry.addData("red value", color.red());
        telemetry.addData("blue value", color.blue());
        String whatColorAmILookingAt = getColor();

//        if (color.alpha()>color.blue() && color.alpha()>color.green() && color.alpha()>color.red()){
//            telemetry.addData("current_color", "white line");

//        if (color.blue()<color.red() && color.red()<color.green() && color.green()>color.blue())
//        }

        telemetry.addData("red value", color.red());
        telemetry.addData("blue value", color.blue());
        telemetry.addData("green value", color.green());
//        telemetry.addData("white value", color.alpha());

}
    public void stop() {

    }
}
