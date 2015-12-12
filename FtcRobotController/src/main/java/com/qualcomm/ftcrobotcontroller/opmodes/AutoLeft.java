package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by Ben Hardin on 12/11/2015.
 */

public class AutoLeft extends OpMode {

    DcMotor frontrightMotor;
    DcMotor frontleftMotor;
    DcMotor backrightMotor;
    DcMotor backleftMotor;

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

    }

    @Override
    public void start() {
        backleftMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        backrightMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);

        backleftMotor.setPower(1.0);
        backrightMotor.setPower(1.0);

    }

    @Override
    public void loop() {

        moveRobot(24,"s");

    }
    public void resetEncoder() throws InterruptedException {
        backleftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        backrightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);

    }

    public void moveRobot(int driveDistance, String direction) {

	int powerSettting = 1;
        int ENCODER_CPR = 1440;     //Encoder Counts per Revolution
        double GEAR_RATIO = 2;      //Gear Ratio
        int WHEEL_DIAMETER = 4;     //Diameter of the wheel in inches
        int DISTANCE = driveDistance;          //Distance in inches to drive
        final double CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;
        final double ROTATIONS = DISTANCE / CIRCUMFERENCE;
        final double COUNTS = ENCODER_CPR * ROTATIONS * GEAR_RATIO;

        backleftMotor.setTargetPosition((int) COUNTS);
        backrightMotor.setTargetPosition((int) COUNTS);

        if  (driveDistance < 0) {
            powerSetting = -1;
        }

        telemetry.addData("Motor Target", COUNTS);
        telemetry.addData("Left Position", backleftMotor.getCurrentPosition());
        telemetry.addData("Right Position", backrightMotor.getCurrentPosition());

        if  ((Math.abs(backleftMotor.getCurrentPosition()) < Math.abs(backleftMotor.getTargetPosition()-1))&&
                (Math.abs(backrightMotor.getCurrentPosition()) < Math.abs(backrightMotor.getTargetPosition()-1))) {
            frontleftMotor.setPower(powerSetting);
            frontrightMotor.setPower(powerSetting);
        }
        else {
            frontleftMotor.setPowerFloat();
            frontrightMotor.setPowerFloat();
        }

	backleftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        backrightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);

	while (backleftMotor.getCurrentPosition() <> 0 && backleftMotor.getCurrentPosition() <> 0) {
	telemetry.addData("Left Position", backleftMotor.getCurrentPosition());
        telemetry.addData("Right Position", backrightMotor.getCurrentPosition());
	wait();
	}


    }


}

