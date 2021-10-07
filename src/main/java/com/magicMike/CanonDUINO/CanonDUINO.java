package com.magicMike.CanonDUINO;

import com.Device.Command;
import com.Device.SerialDevice;
import com.Device.Command.CommandInteger;

public class CanonDUINO extends SerialDevice {

    public Command SetLaserPWM;
    public Command SetFocusPosition;

    public Command GetA0;
    public Command GetArrayA0;

    public CanonDUINO(String name) {
        super(name);

        SetLaserPWM = new Command.CommandInteger() {
            @Override
            protected Object execution(Integer arg) {
                get_bufferedSerialPort().println("SetLaserPWM");
                get_bufferedSerialPort().println(arg);
                return null;
            }
        };

        addCommand("SetLaserPWM", SetLaserPWM);

        GetA0 = new Command() {
            @Override
            protected Object execution(Object arg) {
                get_bufferedSerialPort().println("GetA0");
                return get_bufferedSerialPort().WaitToReadDouble();
            }
        };

        addCommand("GetA0", GetA0);

        SetFocusPosition = new CommandInteger() {
            @Override
            protected Object execution(Integer arg) {
                get_bufferedSerialPort().println("SetFocusPosition");
                get_bufferedSerialPort().println(arg);
                return null;
            }
        };

        addCommand("SetFocusPosition", SetFocusPosition);

        GetArrayA0 = new Command() {
            @Override
            protected Object execution(Object arg) {
                int _Nsample = (int) arg;

                get_bufferedSerialPort().println("GetArrayA0");
                get_bufferedSerialPort().println(_Nsample);

                int[] values = new int[_Nsample];
                for (int i = 0; i < values.length; i++)
                    values[i] = get_bufferedSerialPort().WaitToReadInt();

                return values;
            }

            @Override
            protected Object executionSimulation(Object arg) {
                int _Nsample = (int) arg;

                int[] values = new int[_Nsample];
                for (int i = 0; i < values.length; i++)
                    values[i] = (int) (Math.random() * 0xff);

                return values;
            }
        };

        addCommand("GetArrayA0", GetArrayA0);
    }
}
