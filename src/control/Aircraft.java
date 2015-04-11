package control;



public class Aircraft {
	public enum STATUS{
		ALARM,
		WARNING,
		NONE;
	}
	private int time, speed, speed_interval, altitude;
	private STATUS GEAR_NOT_DOWN, AIR_SPEED;
	private boolean airbrake, lower_gear, override;
	
	public Aircraft(int interval){
		time = 250;
		speed = 350;
		altitude = 2000;
		GEAR_NOT_DOWN = STATUS.NONE;
		AIR_SPEED = STATUS.NONE;
		airbrake = false;
		lower_gear = false;
		override = false;
		speed_interval = interval;
	}
	
	public void toggle_gear(){
		lower_gear = !lower_gear;
	}
	
	void calculate_state(){
		//First check gear not down alarm
		if((GEAR_NOT_DOWN == STATUS.NONE) && (time <= 120 || altitude < 1000) && !lower_gear)
			GEAR_NOT_DOWN = STATUS.ALARM;
		else if((GEAR_NOT_DOWN != STATUS.NONE && time > 120 && altitude >= 1000) || lower_gear)
			GEAR_NOT_DOWN = STATUS.NONE;
		//Check air speed alarm
		if(!lower_gear)
			override = false;
		if((AIR_SPEED == STATUS.NONE) && lower_gear && speed >300){
			AIR_SPEED = STATUS.ALARM;
			if(speed > 400){
				override = true;
				lower_gear = false;
			}
		}else if(AIR_SPEED != STATUS.NONE && (!lower_gear || speed <= 300))
			AIR_SPEED = STATUS.NONE;
		//Check airbrake
		if(!airbrake && time < 60 && speed >= 250)
			airbrake = true;
		else if(airbrake && (time >= 60 || speed < 250))
			airbrake = false;
	}
	
	public void tick(){
		speed -= 5;
		if(airbrake)
			speed -= 15;
		altitude -= 20;
		time--;
		calculate_state();
	}
	
	public void silence_GND_Alarm(){
		if(GEAR_NOT_DOWN == STATUS.ALARM)
			GEAR_NOT_DOWN = STATUS.WARNING;
	}
	
	public void silence_AS_Alarm(){
		if(AIR_SPEED == STATUS.ALARM)
			AIR_SPEED = STATUS.WARNING;
	}
	
	public int increase_speed(){
		speed += speed_interval;
		return speed;
	}
	
	public int decrease_speed(){
		speed -= speed_interval;
		return speed;
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
