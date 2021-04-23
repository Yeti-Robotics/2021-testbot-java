// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.LED;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LEDSubsystem;
import java.awt.Color;

public class SetGradientCommand extends CommandBase {
  private LEDSubsystem ledSubsystem;
  private Color firstColor;
  private Color secondColor;

  public SetGradientCommand(LEDSubsystem ledSubsystem, Color firstColor, Color secondColor){
    this.ledSubsystem = ledSubsystem;
		this.firstColor = firstColor;
		this.secondColor = secondColor;
    addRequirements(ledSubsystem);
  }

  @Override
  public void initialize(){
		double[] increments = {
			Math.abs((double) (firstColor.getRed() - secondColor.getRed()) / ledSubsystem.getBufferLength()),     // R
			Math.abs((double) (firstColor.getGreen() - secondColor.getGreen()) / ledSubsystem.getBufferLength()), // G
			Math.abs((double) (firstColor.getBlue() - secondColor.getBlue()) / ledSubsystem.getBufferLength())    // B
		};

		double[] currRGB = {
			firstColor.getRed(),   // R
			firstColor.getGreen(), // G
			firstColor.getBlue()   // B
		};

		if(firstColor.getRed() > secondColor.getRed()){
			increments[0] *= -1;
		}
		if(firstColor.getGreen() > secondColor.getGreen()){
			increments[1] *= -1;
		}
		if(firstColor.getBlue() > secondColor.getBlue()){
			increments[2] *= -1;
		}

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
