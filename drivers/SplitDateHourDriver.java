
import org.glucosio.android.tools.SplitDateTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Driver which tests the getHour method of org.glucosio.android.tools.SplitDateTime.
 *
 * Valid input should be a single String composed of a String representing the Date,
 * and a String representing the DateFormat, separated by a '~'. For example,
 * "01-12-1984 02:30~mm-dd-yyyy HH:MM"
 */
public class SplitDateHourDriver {

    public static void main(String[] args) {
        String[] splitArgs = args[0].split("~");
        String dateString = splitArgs[0];
        String formatString = splitArgs[1];

        DateFormat format = new SimpleDateFormat(formatString);

        try {

            SplitDateTime dateTime = new SplitDateTime(format.parse(dateString), format);
            System.out.println(dateTime.getHour());

        } catch (ParseException e) {

            // Input could not be parsed into a Date
            System.out.println("Error");

        }
    }

}
