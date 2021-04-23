// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.LED;

import java.security.InvalidParameterException;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LEDSubsystem;

public class SetGradientCommand extends CommandBase {
  private LEDSubsystem ledSubsystem;
  private int firstHue;
  private int secondHue;

  public SetGradientCommand(LEDSubsystem ledSubsystem, int firstHue, int secondHue){
    this.ledSubsystem = ledSubsystem;
    // use min/max just in case parameters are passed in incorrectly
    this.firstHue = Math.min(firstHue, secondHue);
    this.secondHue = Math.max(firstHue, secondHue);
    addRequirements(ledSubsystem);
  }

  @Override
  public void initialize(){
    if(firstHue < 0 || secondHue > 180){
			throw new InvalidParameterException("Invalid hue(s) in SetGradientCommand");
    }
		double hue = firstHue;
		double increment = (double) (secondHue - firstHue) / ledSubsystem.getBufferLength();
		for(int i = 0; i < ledSubsystem.getBufferLength(); i++){
			ledSubsystem.setHSV(i, (int) Math.round(hue), 255, 128);
			hue += increment;
		}
  }

  @Override
  public void execute(){}

  @Override
  public void end(boolean interrupted){
    ledSubsystem.sendData();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
