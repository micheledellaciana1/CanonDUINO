package com.magicMike;

import java.awt.geom.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.Device.Command;

import com.Device.GUI.JDSlider;
import com.Device.GUI.JSliderFrame;

import LoggerCore.LoggerFrameMinimal;
import LoggerCore.PointsArrayStream;
import LoggerCore.PointsStream;

import com.magicMike.CanonDUINO.CanonDUINO;

public class App {

    public static void main(String[] args) throws InterruptedException {

        final CanonDUINO CanonDUINO0 = new CanonDUINO("CanonDUINO");

        CanonDUINO0.open.execute(500);

        JSliderFrame SliderFrame = new JSliderFrame("demo", 200, 30);
        SliderFrame.setSize(300, 30);
        SliderFrame.addSlider("Laser PWM", 0d, 1d, 0, new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Double valueSlider = JDSlider.class.cast(e.getSource()).getDoubleValue();
                valueSlider *= 0xffff;
                CanonDUINO0.SetLaserPWM.execute(valueSlider.intValue());
            }
        });

        SliderFrame.addSlider("SetFocusPosition", 0, 900, 0, new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int valueSlider = JSlider.class.cast(e.getSource()).getValue();
                CanonDUINO0.executeCommand("SetFocusPosition", valueSlider);
            }
        });

        SliderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        SliderFrame.setVisible(true);
    }
}
