package control;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class is for the textfile-based test harness and uses the framework created in AircraftTest
 * @author Matthew Waller
 *
 */
public class AircraftFileTest extends AircraftTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	private static final String PATH = "bv_test_data.csv";
	private static FileInputStream file;
	
	public AircraftFileTest() throws Exception {
		// TODO Auto-generated constructor stub
		super();
		AircraftFileTest.setUpBeforeClass();
		file = new FileInputStream(PATH);
	}
	
	public void run() throws IOException{
		String line, tokens[];
		int tests = 0, fails = 0;
		BufferedReader csv_reader = new BufferedReader(new InputStreamReader(file));
		while((line = csv_reader.readLine()) != null && line.length() > 0){
			tokens = line.split(",");
			if(tokens.length == 5)
				subject.set_altitude(Integer.parseInt(tokens[4]));
			try{
				run_test(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),Integer.parseInt(tokens[2]),Boolean.parseBoolean(tokens[3]));
			}catch(AssertionError e){
				System.out.printf("Test failed!\nOutput Scenario: %s\tSpeed: %s\tTime: %s\tGear down: %s",tokens[0],tokens[1],tokens[2],tokens[3]);
				if(tokens.length == 5)
					System.out.printf("Altitude: %s", tokens[4]);
				System.out.println();
				fails++;
			}
			tests++;
		}
		System.out.printf("%d tests completed\n%d failure(s) reported",tests, fails);
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		AircraftFileTest session = new AircraftFileTest();
		session.run();
	}

}
