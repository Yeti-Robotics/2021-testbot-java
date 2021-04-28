// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.LED;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LEDSubsystem;
import java.awt.Color;

public class SetFadingGradientCommand extends CommandBase {
  private LEDSubsystem ledSubsystem;
  private Color firstColor;
  private Color secondColor;
  private double[] increments = new double[3];
  private double[] currRGB = new double[3];
  private boolean increasing = true;
  private int ticks = 0;

  public SetFadingGradientCommand(LEDSubsystem ledSubsystem, Color firstColor, Color secondColor) {
    this.ledSubsystem = ledSubsystem;
    this.firstColor = firstColor;
    this.secondColor = secondColor;
    this.increments = new double[]{
      	(double) (secondColor.getRed() - firstColor.getRed()) / ledSubsystem.getBufferLength(),     // R
	(double) (secondColor.getGreen() - firstColor.getGreen()) / ledSubsystem.getBufferLength(), // G
	(double) (secondColor.getBlue() - firstColor.getBlue()) / ledSubsystem.getBufferLength()    // B
    };
    this.currRGB = new double[]{
      	firstColor.getRed(),   // R
	firstColor.getGreen(), // G
	firstColor.getBlue()   // B
    };
    addRequirements(ledSubsystem);
  }

  @Override
  public void execute(){
    ticks++;
    for(int i = 0; i < ledSubsystem.getBufferLength(); i++){
      	ledSubsystem.setRGB(i, (int) Math.round(currRGB[0]), (int) Math.round(currRGB[1]), (int) Math.round(currRGB[2]));
    }
    for(int i = 0; i < currRGB.length; i++){
      	currRGB[i] += (increasing) ? increments[i] : -increments[i];
    }
    if(ticks >= ledSubsystem.getBufferLength()){
      	ticks = 0;
      	increasing = !increasing; // iterate in the opposite direction on next execute call
    }
    ledSubsystem.sendData();
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
