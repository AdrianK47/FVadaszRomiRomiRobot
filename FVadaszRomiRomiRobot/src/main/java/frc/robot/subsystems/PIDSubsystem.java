// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PIDSubsystem extends SubsystemBase {
  /** Creates a new PIDSubsystem. */
  private final PIDController pid;
  private final CANcoder sensor;
  double currentDegrees;
  double rotations;
  TalonFX motor;

  public PIDSubsystem() {
    pid = new PIDController(0.1,0.1,0.1);
    sensor = new CANcoder(9);
    pid.enableContinuousInput(0, 360);
    currentDegrees = 0;
    rotations = 0;
    motor = new TalonFX(1, "Drivetrain");
  }

  public void turnMotor(double degree){
    currentDegrees = (sensor.getPosition().getValueAsDouble() * 360) % 360;
    rotations = currentDegrees / 360;
    if(currentDegrees < 0){
      currentDegrees = 360 + currentDegrees;
    }
    motor.set(pid.calculate(currentDegrees, degree));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    pid.calculate(10,1);
    pid.setTolerance(1);
    sensor.getPosition();
  }
}
