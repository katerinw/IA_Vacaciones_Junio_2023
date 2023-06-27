package IA1jun23;
import robocode.*;
import java.awt.Color;
import java.util.Random;
import static robocode.util.Utils.normalRelativeAngleDegrees;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * R201908321 - a robot by (your name here)
 */
public class R201908321 extends AdvancedRobot
{
	/**
	 * run: R201908321's default behavior
	 */
	boolean derecha = true;
	double movimientoMaximo = 0;
	double base = 90;
	double medio = 180;

public void run() {
		// Initialization of the robot should be put herea
		setBodyColor(new Color(228, 205, 241));
		setGunColor(new Color(218, 247, 166));
		setRadarColor(new Color(113, 37, 216));
		setBulletColor(new Color(249, 0, 0));
		movimientoMaximo = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar

		// Robot main loop
		while(true) {
			// Replace the next 4 lines with any behavior you would like
			zigzag();
			fire(1);
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		setTurnRight(getHeading() - getGunHeading());
		setAhead(e.getDistance()-100);
		
		if(e.getDistance()  < 150){
			fire(1);
		}else{
			setAhead(e.getDistance() - 50);
			fire(1);
		}
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	//Cuando nos alcance una bala
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		if(getEnergy() <45){
			setTurnRight(getHeading() - getGunHeading());
			setBack(movimientoMaximo/5);
			setTurnRight(100);
			setBack(movimientoMaximo/5);
		}
		derecha = !derecha;
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		
		if(e.getBearing() > -90 && e.getBearing() < 60){
			setBack(movimientoMaximo/5);
			setTurnRight(100);
			setBack(movimientoMaximo/5);
		}else{
			setAhead(movimientoMaximo/5);
			setTurnRight(100);
			setAhead(movimientoMaximo/5);
		}
		derecha = !derecha;
	}	
	
	//cuando choca con un robot
	public void onHitRobot(HitRobotEvent e){
		if(e.getBearing() > -90 && e.getBearing() < 60){
			setBack(movimientoMaximo/5);
			setTurnRight(100);
			setBack(movimientoMaximo/5);
			fire(1);
		}else{
			setAhead(movimientoMaximo/5);
			setTurnRight(100);
			setAhead(movimientoMaximo/5);
			fire(1);
		}
	}	
	
	public void zigzag () {
		// simula un movimiento semi circular, permite esquivar al azar
		// los metodos waitFor permiten esperar hasta que el robot termine de girar
		setTurnRight(base);
		waitFor(new TurnCompleteCondition(this));

		setTurnLeft(medio);
		waitFor(new TurnCompleteCondition(this));

		setTurnRight(medio);
		waitFor(new TurnCompleteCondition(this));
	}
}
