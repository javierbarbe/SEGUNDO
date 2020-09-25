import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.xml.sax.InputSource;

public class Datos {
static BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

public static float pideFloat() throws NumberFormatException, IOException{
	
		return Float.parseFloat(entrada.readLine());
	
}

}
