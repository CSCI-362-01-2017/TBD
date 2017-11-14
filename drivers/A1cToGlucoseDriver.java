
import org.glucosio.android.tools.GlucosioConverter;

public class A1cToGlucoseDriver {

    public static void main(String[] args) {

        try {

            double value = Double.parseDouble(args[0]);
            System.out.println(GlucosioConverter.a1cToGlucose(value));

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input");
        }
    }
}
