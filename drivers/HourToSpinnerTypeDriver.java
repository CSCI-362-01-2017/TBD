
import org.glucosio.android.tools.ReadingTools;

/**
 * Driver which tests the hourToSpinnerType method of org.glucosio.android.tools.ReadingTools.
 *
 * Valid input should be a String which can be parsed to an Integer.
 */
public class HourToSpinnerTypeDriver {

    public static void main(String[] args) {

        try {

            int input = Integer.parseInt(args[0]);
            int result = new ReadingTools().hourToSpinnerType(input);
            System.out.println(result);

        } catch (NumberFormatException e) {

            // Input could not be parsed to an Integer.
            System.out.println("Error");
        }
    }
}
