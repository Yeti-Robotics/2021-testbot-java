// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.LED;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LEDSubsystem;

public class SetYETIBlueCommand extends CommandBase {
  private LEDSubsystem ledSubsystem;

  public SetYETIBlueCommand(LEDSubsystem ledSubsystem) {
    this.ledSubsystem = ledSubsystem;
    addRequirements(ledSubsystem);
  }

  @Override
  public void initialize(){
    for(int i = 0; i < ledSubsystem.getBufferLength(); i++){
      ledSubsystem.setRGB(i, 84, 182, 229); // YETI blue in RGB
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
