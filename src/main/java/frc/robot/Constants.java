// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public final class Constants {
    public static class LEDConstants {
        public static int LED_STRIP = 1337; //obv placeholder
        public static int LED_LENGTH = 69; //might be actual value? need to check
    }
    public static final class ShooterConstants {
        public static final int LEFT_FLYWHEEL = 1337; //temp
        public static final int RIGHT_FLYWHEEL = 1337;
        public static final double FLYWHEEL_SPEED = 1.0;
        public static final double REVERSE_FLYWHEEL_SPEED = -1.0;
        public static final double ENCODER_RESOLUTION = 2048.0;
    }
    public static final class IntakeConstants {
        public static final int[] INTAKE_PISTONS = {0,1}; //placeholder
        public static final int INTAKE_VICTOR = 572893; //placeholder
        public static final double INTAKE_IN_POWER = .7;
        public static final double INTAKE_OUT_POWER = .7;
    }
}
