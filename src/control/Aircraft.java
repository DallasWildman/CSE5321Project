package control;



public class Aircraft {
	public enum STATUS{
		ALARM,
		WARNING,
		NONE;
	}
	public enum CONTROL{
		UP,
		NONE,
		DOWN;
	}
	private int time, speed, altitude;
	private STATUS GEAR_NOT_DOWN, AIR_SPEED;
	private CONTROL THROTTLE, ELEVON;
	private boolean airbrake, lower_gear, override;
	
	/**
	 * Constructor for the class, intializes the object
	 * with the starting values as per the requirements.
	 * @param s_interval		This variable is used to set
	 * the interval at which the speed is manually
	 * adjusted.
	 */
	public Aircraft(){
		time = 250;
		speed = 350;
		altitude = 2000;
		GEAR_NOT_DOWN = STATUS.NONE;
		AIR_SPEED = STATUS.NONE;
		airbrake = false;
		lower_gear = false;
		override = false;
		THROTTLE = CONTROL.NONE;
		ELEVON = CONTROL.NONE;
	}
	
	public boolean toggle_gear(){
		lower_gear = !lower_gear;
		return lower_gear;
	}
	
	public static void calculate_state(Aircraft subject){
		subject.calculate_state();
	}
	
	/**
	 * This function calculates the status of alarms and other outputs based on the
	 * current values.
	 */
	void calculate_state(){
		//First check gear not down alarm
		override = false;
		if(!lower_gear && (altitude < 1000 || time <= 120)){
			if(GEAR_NOT_DOWN == STATUS.NONE)
				GEAR_NOT_DOWN = STATUS.ALARM;
		}else
			GEAR_NOT_DOWN = STATUS.NONE;
		//Check air speed alarm
		if(lower_gear && speed > 300){
			if(AIR_SPEED == STATUS.NONE)
				AIR_SPEED = STATUS.ALARM;
			if(speed > 400){
				override = true;
				lower_gear = false;
			}
		}else
			AIR_SPEED = STATUS.NONE;
		//Check airbrake
		if(time < 60 && speed >= 250)
			airbrake = true;
		else
			airbrake = false;
	}
	
	/**
	 * This advances the state of the aircraft by one second and increments
	 * the values as per the requirements
	 */
	@SuppressWarnings("incomplete-switch")
	public void tick(){
		switch(THROTTLE){
		case UP:
			speed += (airbrake) ? 0 : 10;
			break;
		case DOWN:
			speed -= 10;
		}
		if(airbrake)
			speed -= 10;
		if(speed > 0)
			speed -= 5;
		else
			speed = 0;
		if(speed < 0)
			speed = 0;
		switch(ELEVON){
		case UP:
			altitude += 20;
			break;
		case DOWN:
			altitude -= 20;
		}
		if(altitude > 0)
			altitude -= 20;
		else
			altitude = 0;
		if(time > 0)
			time--;
		calculate_state();
	}
	
	/**
	 * If the Gear Not Down alarm is active this silences it by
	 * switching it to warning state.
	 * @see STATUS
	 * @see GEAR_NOT_DOWN
	 */
	public void silence_GND_Alarm(){
		if(GEAR_NOT_DOWN == STATUS.ALARM)
			GEAR_NOT_DOWN = STATUS.WARNING;
	}
	
	/**
	 * If the Air Speed alarm is active this silences it by switching
	 * it to warning state.
	 * @see STATUS
	 * @see AIR_SPEED
	 */
	public void silence_AS_Alarm(){
		if(AIR_SPEED == STATUS.ALARM)
			AIR_SPEED = STATUS.WARNING;
	}
	
	public void set_throttle(CONTROL state){
		THROTTLE = state;
	}
	
	public void set_elevon(CONTROL state){
		ELEVON = state;
	}
	
	void set_time(int t){
		time = t;
	}
	
	void set_speed(int s){
		speed = s;
	}
	
	void set_altitude(int a){
		altitude = a;
	}
	
	void set_GND_Status(STATUS in){
		GEAR_NOT_DOWN = in;
	}
	
	void set_AS_Status(STATUS in){
		AIR_SPEED = in;
	}
	
	public int get_time(){
		return time;
	}
	
	public int get_speed(){
		return speed;
	}
	
	public int get_altitude(){
		return altitude;
	}
	
	public boolean is_gear_down(){
		return lower_gear;
	}
	
	public boolean is_airbrake(){
		return airbrake;
	}
	
	public boolean is_override(){
		return override;
	}
	
	public STATUS get_GND_Status(){
		return GEAR_NOT_DOWN;
	}
	
	public STATUS get_AS_Status(){
		return AIR_SPEED;
	}
}
