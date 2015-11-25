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

    }
    public void stop() {

    }
}
