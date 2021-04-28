// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
  private DoubleSolenoid intakePistons;
  private VictorSPX intakeVictor;
  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {
    intakePistons = new DoubleSolenoid(Constants.IntakeConstants.INTAKE_PISTONS[0], Constants.IntakeConstants.INTAKE_PISTONS[1]);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
