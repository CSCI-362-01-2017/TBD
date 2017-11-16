
import org.glucosio.android.tools.ReadingTools;

public class HourToSpinnerTypeDriver {

    public static void main(String[] args) {

        try {

            int input = Integer.parseInt(args[0]);

            int result = new ReadingTools().hourToSpinnerType(input);

            System.out.println(result);

        } catch (NumberFormatException e) {
        }
    }
}
