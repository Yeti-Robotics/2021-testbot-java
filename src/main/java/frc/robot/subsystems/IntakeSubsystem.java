// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class IntakeSubsystem extends SubsystemBase {
  public enum IntakeStatus{
    RETRACTED, EXTENDED
  }
  public IntakeStatus intakeStatus;
  private DoubleSolenoid intakePistons;
  private VictorSPX intakeVictor;
  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {
    intakePistons = new DoubleSolenoid(Constants.IntakeConstants.INTAKE_PISTONS[0], Constants.IntakeConstants.INTAKE_PISTONS[1]);
    intakeVictor = new VictorSPX(Constants.IntakeConstants.INTAKE_VICTOR);
    intakeStatus = IntakeStatus.EXTENDED;
  }

  //victor methods
  public void intakeIn() {
    intakeVictor.set(ControlMode.PercentOutput, Constants.IntakeConstants.INTAKE_IN_POWER);
  }
  public void moveIntake(double power){
    intakeVictor.set(ControlMode.PercentOutput, power);
  }
  public void intakeOut(){
    intakeVictor.set(ControlMode.PercentOutput, Constants.IntakeConstants.INTAKE_OUT_POWER);
  }
  public void intakeStop(){
    intakeVictor.set(ControlMode.PercentOutput, 0);
  }

  //piston methods
  public void retractIntake(){
    intakePistons.set(DoubleSolenoid.Value.kReverse);
    intakeStatus = IntakeStatus.RETRACTED;
  }
  public void extendIntake(){
    intakePistons.set(DoubleSolenoid.Value.kForward);
    intakeStatus = IntakeStatus.EXTENDED;
  }
  public IntakeStatus getIntakeStatus(){
    return intakeStatus;
  }
}
