// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.LED;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LEDSubsystem;

public class SetRBGColorCommand extends CommandBase {
  private LEDSubsystem ledSubsystem;
  private int red;
  private int green;
  private int blue;

  public SetRBGColorCommand(LEDSubsystem ledSubsystem, int red, int green, int blue) {
    this.ledSubsystem = ledSubsystem;
    this.red = red;
    this.blue = blue;
    this.green = green;
    addRequirements(ledSubsystem);
  }

  @Override
  public void initialize(){
    for(int i = 0; i < ledSubsystem.getBufferLength(); i++){
      ledSubsystem.setRGB(i, red, green, blue);
    }
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    ledSubsystem.sendData();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
