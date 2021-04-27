// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class ShooterSubsystem extends SubsystemBase {
	private WPI_TalonFX leftFlywheel;
	private WPI_TalonFX rightFlywheel;

 	public enum ShooterStatus {
    		FORWARD, BACKWARD, OFF;
  	}

	public static ShooterStatus shooterStatus;

	public ShooterSubsystem(){
		leftFlywheel = new WPI_TalonFX(ShooterConstants.LEFT_FLYWHEEL);
		rightFlywheel = new WPI_TalonFX(ShooterConstants.RIGHT_FLYWHEEL);

		leftFlywheel.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);
		rightFlywheel.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);

		rightFlywheel.follow(leftFlywheel);

		leftFlywheel.setNeutralMode(NeutralMode.Coast);
		rightFlywheel.setNeutralMode(NeutralMode.Coast);

		shooterStatus = ShooterStatus.OFF;
	}
	
	public void shootFlywheel(){
		leftFlywheel.set(ControlMode.PercentOutput, ShooterConstants.FLYWHEEL_SPEED);
		shooterStatus = ShooterStatus.FORWARD;
	}

	public void shootFlywheel(double speed){
		leftFlywheel.set(ControlMode.PercentOutput, speed);
		shooterStatus = (speed > 0.0) ? ShooterStatus.FORWARD : ShooterStatus.BACKWARD; 
	}

	public void stopFlywheel(){
		leftFlywheel.set(ControlMode.PercentOutput, 0.0);
		shooterStatus = ShooterStatus.OFF;
	}

	public void reverseFlywheel(){
		leftFlywheel.set(ControlMode.PercentOutput, ShooterConstants.REVERSE_FLYWHEEL_SPEED);
		shooterStatus = ShooterStatus.BACKWARD;
	}

	public double getLeftEncoder(){
		return leftFlywheel.getSelectedSensorVelocity();
	}

	public double getRightEncoder(){
		return rightFlywheel.getSelectedSensorVelocity();
	}

	public double getAverageEncoder(){
		return (getLeftEncoder() + getRightEncoder()) / 2.0;
	}

	public static ShooterStatus getShooterStatus(){
		return shooterStatus;
	}
}
