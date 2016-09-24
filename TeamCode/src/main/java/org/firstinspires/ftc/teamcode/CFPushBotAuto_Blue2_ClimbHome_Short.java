package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.ControlFreaks.*;


/**
 * Created by adevries on 11/6/2015.
 */
@Autonomous(name="Blue2 ClimbHome Short", group="Blue")
//@Disabled
public class CFPushBotAuto_Blue2_ClimbHome_Short extends LinearOpMode
{
    /* Declare OpMode members. */
        CFPushBotHardware robot   = new CFPushBotHardware();



    //--------------------------------------------------------------------------
    //
    // loop
    //
    /**
     * Implement a state machine that controls the robot during auto-operation.
     * The state machine uses a class member and encoder input to transition
     * between states.
     *
     * The system calls this member repeatedly while the OpMode is running.
     */
    @Override
    public void runOpMode() throws InterruptedException {

        robot.init(this);
        robot.blueled_on();
        robot.led7seg_timer_init(30);
        waitForStart();
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            robot.hardware_loop();

            if (robot.led7seg_timer_complete() == true) {
                robot.set_second_message("timer complete stop");
                robot.set_drive_power(0f, 0f);
                v_state = 100;
            }
            switch (v_state) {
                //
                // Synchronize the state machine and hardware.
                //
                case 0:
                    //
                    // drive Forward 68 inches
                    //
                    robot.led7seg_timer_start(30);
                    robot.drive_inches(.7f, 72, true);
                    v_state++;
                    break;
                case 1:

                    //
                    // Transition to the next state when this method is called again.
                    if (robot.drive_inches_complete()) {
                        //
                        v_state++;
                    }

                    break;

                case 2:
                    // positive is right turn
                    robot.turn_degrees(85, true, true);
                    robot.set_second_message("turn 85 degrees to the right");
                    v_state++;
                    break;
                //
                // Wait...
                //
                case 3:
                    //keep checking if we have reached the distance we need to reach
                    if (robot.turn_complete()) {
                        robot.set_second_message("turn Complete");
                        v_state++;
                    }
                    break;
                case 4:
                    //
                    // drive Forward 12 inches
                    //
                    robot.rpabase_moveToClimb();
                    //middle of moutain 85
                    //drive_inches(.7f,85, true);
                    robot.drive_inches(.7f, 108, true);
                    //set_drive_power(1.0d, 1.0d);
                    v_state++;
                    break;
                case 5:
                    //
                    // Transition to the next state when this method is called again.
                    if (robot.drive_inches_complete()) {
                        //
                        v_state++;
                    }
                    break;

                default:
                    //
                    // The autonomous actions have been accomplished (i.e. the state has
                    // transitioned into its final state.
                    //
                    break;
            }

            if (robot.is_slow_tick()) {
                //robot.update_telemetry(); // Update common telemetry
                telemetry.addData("18", "State: " + v_state);
            }

        } // loop
    }
    //--------------------------------------------------------------------------
    //
    // v_state
    //
    /**
     * This class member remembers which state is currently active.  When the
     * start method is called, the state will be initialized (0).  When the loop
     * starts, the state will change from initialize to state_1.  When state_1
     * actions are complete, the state will change to state_2.  This implements
     * a state machine for the loop method.
     */
    private int v_state = 0;


}
