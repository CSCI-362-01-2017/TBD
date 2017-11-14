
import org.glucosio.android.tools.GlucosioConverter;

public class RoundDriver {

    public static void main(String[] args) {

        try {
	    String[] splitArgs = args[0].split("~");

            double value = Double.parseDouble(splitArgs[0]);
            int numPlaces = Integer.parseInt(splitArgs[1]);

            double rtnval = GlucosioConverter.round(value, numPlaces);

            System.out.println(Double.toString(rtnval));

        } catch (IllegalArgumentException e) {
            System.out.println("Error: Invalid input");
        }
    }
}
