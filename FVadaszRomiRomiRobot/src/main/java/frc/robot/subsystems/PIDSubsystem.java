// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PIDSubsystem extends SubsystemBase {
  /** Creates a new PIDSubsystem. */
  private final PIDController pid;

  double FrontLeftCurrentDegrees;
  double FrontRightCurrentDegrees;
  double BackLeftCurrentDegrees;
  double BackRIghtCUrrentDegrees;

  private final CANcoder FrontLeftEncoder;
  private final TalonFX FrontLeftTurnMotor;
  private final CANcoder FrontRightEncoder;
  private final TalonFX FrontRightTurnMotor;
  private final CANcoder BackLeftEncoder;
  private final TalonFX BackLeftTurnMotor; 
  private final CANcoder BackRightEncoder;
  private final TalonFX BackRightTurnMotor;

  public PIDSubsystem() {
    pid = new PIDController(0.005,0,0);
    pid.enableContinuousInput(0, 360); //
    FrontLeftCurrentDegrees = 0;

    FrontLeftEncoder = new CANcoder(9, "Drivetrain");
    FrontLeftTurnMotor = new TalonFX(1, "Drivetrain");

    FrontRightEncoder = new CANcoder(10, "Drivetrain");
    FrontRightTurnMotor = new TalonFX(3, "Drivetrain");

    BackLeftEncoder = new CANcoder(12, "Drivetrain");
    BackLeftTurnMotor = new TalonFX(7, "Drivetrain");

    BackRightEncoder = new CANcoder(11, "Drivetrain");
    BackRightTurnMotor = new TalonFX(5, "Drivetrain");
  }

  public void turnMotor(double degree){
    FrontLeftCurrentDegrees = ((((FrontLeftEncoder.getPosition().getValueAsDouble() % 1) + 1) % 1) * 360);
    FrontLeftTurnMotor.set(pid.calculate(FrontLeftCurrentDegrees, degree) * -1);
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Front Left Degree", ((((FrontLeftEncoder.getPosition().getValueAsDouble() % 1) + 1) % 1) * 360));
    SmartDashboard.putNumber("Front Right Degree", ((((FrontRightEncoder.getPosition().getValueAsDouble() % 1) + 1) % 1) * 360));
    SmartDashboard.putNumber("Back Left Degree", ((((BackLeftEncoder.getPosition().getValueAsDouble() % 1) + 1) % 1) * 360));
    SmartDashboard.putNumber("Back Right Degree", ((((BackRightEncoder.getPosition().getValueAsDouble() % 1) + 1) % 1) * 360));
  }
}
