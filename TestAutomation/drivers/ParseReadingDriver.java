
import org.glucosio.android.tools.ReadingTools;

import java.util.Locale;

/**
 * Driver which tests the parseReading method of org.glucosio.android.tools.ReadingTools.
 *
 * Valid input should be a single String composed of a Locale code (e.g. en) and a String
 * representing the reading, separated by a '~'. For example, "en~1.23"
 */
public class ParseReadingDriver {

    public static void main(String[] args) {

        String[] splitArgs = args[0].split("~", -1);    // Splits the input into
        String localeString = splitArgs[0];             // a String representing the Locale, and
        String reading = splitArgs[1];                  // a String representing the reading

        Locale.setDefault(new Locale(localeString));
	    Number result = ReadingTools.parseReading(reading);

	    if (result != null) {   // If no valid reading was able to be parsed, should return null
	        result = result.doubleValue();
        }

        System.out.println(result);
    }
}
