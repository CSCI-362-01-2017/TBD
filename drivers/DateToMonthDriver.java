
import org.glucosio.android.tools.FormatDateTime;

public class DateToMonthDriver {

    public static void main(String[] args) {

        try {

            String input = args[0];

            FormatDateTime format = new FormatDateTime(null);

            System.out.println(format.convertDateToMonthOverview(input));

        } catch (Exception e) {
            System.out.println("Error");
        }
    }

}
