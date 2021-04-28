// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.LED;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LEDSubsystem;
import java.awt.Color;
import java.util.Arrays;
import java.util.LinkedList;

public class SetAnimatedGradientCommand extends CommandBase {
  private LEDSubsystem ledSubsystem;
  private Color firstColor;
  private Color secondColor;
  private LinkedList<Color> colorQueue;

  public SetAnimatedGradientCommand(LEDSubsystem ledSubsystem, Color firstColor, Color secondColor) {
    this.ledSubsystem = ledSubsystem;
    this.firstColor = firstColor;
    this.secondColor = secondColor;
    
    Color[] initialState = new Color[ledSubsystem.getBufferLength()];
    double[] currRGB = new double[]{
      firstColor.getRed(),   // R
      firstColor.getGreen(), // G
      firstColor.getBlue()   // B
    };

    /**
      Generate the initial state of the LED strip so colorQueue can easily shift the colors down by 1 
      each time to create a motion effect. Instead of making a gradient from firstColor -> secondColor, 
      make one from firstColor -> secondColor -> firstColor so the gradient looks smooth after pushing
      on the queue.
    */
    if(ledSubsystem.getBufferLength() % 2 == 0){ // If the length is even, then we can iterate through two exact halves
      double[] increments = new double[]{
        (double) (secondColor.getRed() - firstColor.getRed()) / (ledSubsystem.getBufferLength() / 2),     // R
        (double) (secondColor.getGreen() - firstColor.getGreen()) / (ledSubsystem.getBufferLength() / 2), // G
        (double) (secondColor.getBlue() - firstColor.getBlue()) / (ledSubsystem.getBufferLength() / 2)    // B
      };
      for(int i = 0; i < initialState.length / 2; i++){
        initialState[i] = new Color((int) Math.round(currRGB[0]), (int) Math.round(currRGB[1]), (int) Math.round(currRGB[2]));
        for(int j = 0; j < currRGB.length; j++){
          currRGB[j] += increments[j];
        }
      }
      int idx = initialState.length / 2 - 1;
      for(int i = initialState.length / 2; i < initialState.length; i++){
        initialState[i] = new Color(initialState[idx].getRed(), initialState[idx].getGreen(), initialState[idx].getBlue());
        idx--;
      }
    } else { // Otherwise, there will be an extra value in the center of the array, so we account for it by iterating through the first half 1 extra time
      double[] increments = new double[]{
        (double) (secondColor.getRed() - firstColor.getRed()) / (ledSubsystem.getBufferLength() / 2 + 1),     // R
        (double) (secondColor.getGreen() - firstColor.getGreen()) / (ledSubsystem.getBufferLength() / 2 + 1), // G
        (double) (secondColor.getBlue() - firstColor.getBlue()) / (ledSubsystem.getBufferLength() / 2 + 1)    // B
      };
      for(int i = 0; i < initialState.length / 2 + 1; i++){
        initialState[i] = new Color((int) Math.round(currRGB[0]), (int) Math.round(currRGB[1]), (int) Math.round(currRGB[2]));
        for(int j = 0; j < currRGB.length; j++){
          currRGB[j] += increments[j];
        }
      }
      int idx = initialState.length / 2 - 1;
      for(int i = initialState.length / 2 + 1; i < initialState.length; i++){
        initialState[i] = new Color(initialState[idx].getRed(), initialState[idx].getGreen(), initialState[idx].getBlue());
        idx--;
      }
    }

    colorQueue = new LinkedList<Color>(Arrays.asList(initialState));
    addRequirements(ledSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute(){
    for(int i = 0; i < ledSubsystem.getBufferLength(); i++){
      ledSubsystem.setRGB(i, colorQueue.get(i).getRed(), colorQueue.get(i).getGreen(), colorQueue.get(i).getBlue());
    }
    // pop the leading element off and append it to the end of the queue
    colorQueue.addLast(colorQueue.removeFirst());
    ledSubsystem.sendData();
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
