
import org.glucosio.android.tools.ReadingTools;

import java.util.Locale;

public class ParseReadingDriver {

    public static void main(String[] args) {

        String[] splitArgs = args[0].split("~", -1);    // Splits the input into
        String localeString = splitArgs[0];             // a String representing the Locale, and
        String reading = splitArgs[1];                  // a String representing the reading

        Locale.setDefault(new Locale(localeString));
	Number result = ReadingTools.parseReading(reading);
        System.out.println(result == null ? result : result.doubleValue());
    }
}
