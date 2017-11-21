
import org.glucosio.android.tools.SplitDateTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
            System.out.println("Error");
        }
    }

}
