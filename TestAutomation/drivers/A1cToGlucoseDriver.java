
import org.glucosio.android.tools.GlucosioConverter;

/**
 * Driver which tests the a1cToGlucose method of org.glucosio.android.tools.GlucosioConverter.
 *
 * Valid input should be formatted as a String which is parseable to a Double.
 */
public class A1cToGlucoseDriver {

    public static void main(String[] args) {

        try {

            double value = Double.parseDouble(args[0]);
            System.out.println(GlucosioConverter.a1cToGlucose(value));

        } catch (NumberFormatException e) {

            // Input could not be parsed to a Double
            System.out.println("Error: Invalid input");

        }
    }
}
