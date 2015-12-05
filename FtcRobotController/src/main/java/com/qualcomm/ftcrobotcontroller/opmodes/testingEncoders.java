package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

public class testingEncoders extends OpMode {

    DcMotor frontrightMotor;
    DcMotor frontleftMotor;
    DcMotor backrightMotor;
    DcMotor backleftMotor;


    final static int ENCODER_CPR = 1440;     //Encoder Counts per Revolution
    final static double GEAR_RATIO = 2;      //Gear Ratio
    final static int WHEEL_DIAMETER = 4;     //Diameter of the wheel in inches
    final static int DISTANCE = 24;          //Distance in inches to drive

    final static double CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;
    final static double ROTATIONS = DISTANCE / CIRCUMFERENCE;
    final static double COUNTS = ENCODER_CPR * ROTATIONS * GEAR_RATIO;

    @Override
    public void init() {
        frontleftMotor = hardwareMap.dcMotor.get("frontLeft");
        frontrightMotor = hardwareMap.dcMotor.get("frontRight");
        backleftMotor = hardwareMap.dcMotor.get("backLeft");
        backrightMotor = hardwareMap.dcMotor.get("backRight");

        frontleftMotor.setDirection(DcMotor.Direction.REVERSE);
        backleftMotor.setDirection(DcMotor.Direction.REVERSE);

        backleftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        backrightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        frontleftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        frontrightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    }

    @Override
    public void start() {
        backleftMotor.setTargetPosition((int) COUNTS);
        backrightMotor.setTargetPosition((int) COUNTS);
        frontleftMotor.setTargetPosition((int) COUNTS);
        frontrightMotor.setTargetPosition((int) COUNTS);

        backleftMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        backrightMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        frontleftMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        frontrightMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);

        backleftMotor.setPower(1.0);
        backrightMotor.setPower(1.0);
        frontleftMotor.setPower(1.0);
        frontrightMotor.setPower(1.0);


    }

    @Override
    public void loop() {
        telemetry.addData("Motor Target", COUNTS);
        telemetry.addData("Left Position", backleftMotor.getCurrentPosition());
        telemetry.addData("Right Position", backrightMotor.getCurrentPosition());
    }
}

