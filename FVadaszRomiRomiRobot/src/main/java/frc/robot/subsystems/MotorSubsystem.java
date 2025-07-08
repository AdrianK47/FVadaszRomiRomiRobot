// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MotorSubsystem extends SubsystemBase {
  /** Creates a new MotorSubsystem. */
  TalonFX motor1;
  public MotorSubsystem() {
    motor1 = new TalonFX(2, "Drivetrain");
  }
  public void moveMotor(double s){
    motor1.set(s);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
