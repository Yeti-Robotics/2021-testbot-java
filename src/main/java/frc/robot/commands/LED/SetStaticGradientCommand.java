// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.LED;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LEDSubsystem;
import java.awt.Color;

public class SetStaticGradientCommand extends CommandBase {
  private LEDSubsystem ledSubsystem;
  private Color firstColor;
  private Color secondColor;

  public SetStaticGradientCommand(LEDSubsystem ledSubsystem, Color firstColor, Color secondColor){
    this.ledSubsystem = ledSubsystem;
  	this.firstColor = firstColor;
  	this.secondColor = secondColor;
    addRequirements(ledSubsystem);
  }

  @Override
  public void initialize(){
		double[] increments = {
			(double) (secondColor.getRed() - firstColor.getRed()) / ledSubsystem.getBufferLength(),     // R
			(double) (secondColor.getGreen() - firstColor.getGreen()) / ledSubsystem.getBufferLength(), // G
			(double) (secondColor.getBlue() - firstColor.getBlue()) / ledSubsystem.getBufferLength()    // B
		};

		double[] currRGB = {
			firstColor.getRed(),   // R
			firstColor.getGreen(), // G
			firstColor.getBlue()   // B
		};

		for(int i = 0; i < ledSubsystem.getBufferLength(); i++){
			ledSubsystem.setRGB(i, (int) Math.round(currRGB[0]), (int) Math.round(currRGB[1]), (int) Math.round(currRGB[2]));
			for(int j = 0; j < currRGB.length; j++){
				currRGB[j] += increments[j];
			}
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
