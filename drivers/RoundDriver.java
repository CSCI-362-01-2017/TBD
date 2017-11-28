
import org.glucosio.android.tools.GlucosioConverter;

/**
 * Driver which tests the round method of org.glucosio.android.tools.GlucosioConverter.
 *
 * Valid input should be a single String composed of a String representing a Double to
 * be rounded, and a String representing an Integer number of places to round to,
 * separated by a '~'. For example, "-1.2345~2"
 */
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
