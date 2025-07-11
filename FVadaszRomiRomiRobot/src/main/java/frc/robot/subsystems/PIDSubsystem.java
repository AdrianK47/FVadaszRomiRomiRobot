// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SwerveConstants;

public class PIDSubsystem extends SubsystemBase {
  /** Creates a new PIDSubsystem. */
  private final PIDController pid;
  private final PIDController pidGyro;

  double FrontLeftCurrentDegrees;
  double FrontRightCurrentDegrees;
  double BackLeftCurrentDegrees;
  double BackRightCurrentDegrees;

  private final CANcoder FrontLeftEncoder; 
  private final CANcoder FrontRightEncoder;
  private final CANcoder BackLeftEncoder;
  private final CANcoder BackRightEncoder;

  private final TalonFX FrontLeftTurnMotor;
  private final TalonFX FrontRightTurnMotor;
  private final TalonFX BackLeftTurnMotor; 
  private final TalonFX BackRightTurnMotor;

  private final TalonFX FrontLeftDriveMotor;
  private final TalonFX FrontRightDriveMotor;
  private final TalonFX BackLeftDriveMotor;
  private final TalonFX BackRightDriveMotor;

  public PIDSubsystem() {
    pid = new PIDController(0.005,0,0);
    pidGyro = new PIDController(0.005, 0, 0);
    pid.enableContinuousInput(0, 360);

    FrontLeftCurrentDegrees = 0;
    FrontRightCurrentDegrees = 0;
    BackLeftCurrentDegrees = 0;
    BackRightCurrentDegrees = 0;

    FrontLeftEncoder = new CANcoder(9, "Drivetrain");
    FrontLeftTurnMotor = new TalonFX(1, "Drivetrain");
    FrontLeftDriveMotor = new TalonFX(2, "Drivetrain");

    FrontRightEncoder = new CANcoder(10, "Drivetrain");
    FrontRightTurnMotor = new TalonFX(3, "Drivetrain");
    FrontRightDriveMotor = new TalonFX(4, "Drivetrain");

    BackLeftEncoder = new CANcoder(12, "Drivetrain");
    BackLeftTurnMotor = new TalonFX(7, "Drivetrain");
    BackLeftDriveMotor = new TalonFX(8, "Drivetrain");

    BackRightEncoder = new CANcoder(11, "Drivetrain");
    BackRightTurnMotor = new TalonFX(5, "Drivetrain");
    BackRightDriveMotor = new TalonFX(6, "Drivetrain");
  }

  public void turnMotor(double degree){
    FrontLeftCurrentDegrees = (((((((FrontLeftEncoder.getPosition().getValueAsDouble() % 1) + 1) % 1) * 360) - SwerveConstants.frontLeftDegreeOffset) + 360) % 360);
    FrontRightCurrentDegrees = (((((((FrontRightEncoder.getPosition().getValueAsDouble() % 1) + 1) % 1) * 360) - SwerveConstants.frontRightDegreeOffset) + 360) % 360);
    BackLeftCurrentDegrees = (((((((BackLeftEncoder.getPosition().getValueAsDouble() % 1) + 1) % 1) * 360) - SwerveConstants.backLeftDegreeOffset) + 360) % 360);
    BackRightCurrentDegrees = (((((((BackRightEncoder.getPosition().getValueAsDouble() % 1) + 1) % 1) * 360) - SwerveConstants.backRightDegreeOffset) + 360) % 360);
    FrontLeftTurnMotor.set(pid.calculate(FrontLeftCurrentDegrees, degree));
    FrontRightTurnMotor.set(pid.calculate(FrontRightCurrentDegrees, degree));
    BackLeftTurnMotor.set(pid.calculate(BackLeftCurrentDegrees, degree));
    BackRightTurnMotor.set(pid.calculate(BackRightCurrentDegrees, degree));
  }

 

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Front Left Degree", ((((FrontLeftEncoder.getPosition().getValueAsDouble() % 1) + 1) % 1) * 360));
    SmartDashboard.putNumber("Front Right Degree", ((((FrontRightEncoder.getPosition().getValueAsDouble() % 1) + 1) % 1) * 360));
    SmartDashboard.putNumber("Back Left Degree", ((((BackLeftEncoder.getPosition().getValueAsDouble() % 1) + 1) % 1) * 360));
    SmartDashboard.putNumber("Back Right Degree", ((((BackRightEncoder.getPosition().getValueAsDouble() % 1) + 1) % 1) * 360));
  } 
  // public void moveMotor(double speed){
  //   FrontLeftDriveMotor.set(speed);
  //   FrontRightDriveMotor.set(speed);
  //   BackLeftDriveMotor.set(speed);
  //   BackRightDriveMotor.set(speed);
  // }
}
