package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.ControlFreaks.*;
/**
 * Created by adevries on 11/6/2015.
 */
@TeleOp(name="Tank 1", group="Manual")  // @Autonomous(...) is the other common choice
@Disabled
public class CFPushBotManual extends LinearOpMode {

    /* Declare OpMode members. */
    CFPushBotHardware robot           = new CFPushBotHardware();   // Use a Pushbot's hardware

    private static boolean bothControllersEnabled = false;
    private byte v_neopixels_mode = 0;


    //--------------------------------------------------------------------------
    //
    // loop
    //
    /**
     * Implement a state machine that controls the robot during
     * manual-operation.  The state machine uses gamepad input to transition
     * between states.
     *
     * The system calls this member repeatedly while the OpMode is running.
     */
    boolean isMovingArm = false;
    float stickdeadzone = .1f;
    boolean isFirstTimeButtonPress = true;
    @Override
    public void runOpMode() throws InterruptedException {

        robot.init(this);
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            robot.hardware_loop();


            //
            // Manage the drive wheel motors.
            //


            robot.set_drive_power(-gamepad1.left_stick_y, -gamepad1.right_stick_y);
            // Pause for metronome tick.  40 mS each cycle = update 25 times a second.
            robot.waitForTick(40);
        } // loop


    }
}
