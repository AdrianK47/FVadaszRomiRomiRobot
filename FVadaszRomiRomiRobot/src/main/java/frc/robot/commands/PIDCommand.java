// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.PIDSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class PIDCommand extends Command {
  /** Creates a new PIDCommand. */
  PIDSubsystem m_PIDSubsystem;
  Joystick stick;
  public PIDCommand(PIDSubsystem pidsubsystem, Joystick stick) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_PIDSubsystem = pidsubsystem;
    this.stick = stick;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_PIDSubsystem.turnMotor(stick.getDirectionDegrees());
    System.out.println("Joystick degrees: " + (stick.getDirectionDegrees()));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
