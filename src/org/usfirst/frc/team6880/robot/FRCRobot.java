package org.usfirst.frc.team6880.robot;

import org.usfirst.frc.team6880.robot.driveTrain.DriveSystem;
import org.usfirst.frc.team6880.robot.navigation.Navigation;
import org.usfirst.frc.team6880.robot.task.*;

public class FRCRobot {
	Robot wpilibrobot;
	public DriveSystem driveSys;
	public Navigation navigation;
	
	RobotTask curTask;
	RobotTask tasks [] = {new TaskMoveForward20m(this)};
	int taskNum;
	boolean tasksDone;
	
	public FRCRobot(Robot wpilibrobot)
	{
		this.wpilibrobot = wpilibrobot;
		this.driveSys = new DriveSystem(this);
	}
	
	public void runTeleOp()
	{
		//TODO: Map controller sticks to drive system
		//Possible: map misc. controller buttons to tasks?
	}
	
	public void initAutonomous()
	{
		//Resent encoders
		driveSys.resetEncoders();
		//Start with first task
		curTask = tasks[0];
		taskNum = 0;
		tasksDone = false;
	}
	
	public void runAutonomous()
	{
		//Run the current task. If current task ended
		if (!tasksDone && curTask.runTask())
		{
			//If there are still tasks to run
			if (taskNum + 1 < tasks.length)
			{
				System.out.println("Finished running task number " + taskNum);
				//Go to next task
				curTask = tasks[++taskNum];
				//Begin the next task
				curTask.initTask();
			}
			else
			{
				tasksDone = true;
				System.out.println("Robot has finished running the autonomous tasks");
			}
		}
	}
		
	public boolean isEnabled()
	{
		return wpilibrobot.isEnabled();
	}
}
