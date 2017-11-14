
import org.glucosio.android.tools.AlgorithmUtil;
import org.glucosio.android.tools.AlgorithmUtil.TrendArrow;
import org.glucosio.android.object.PredictionData;

public class GetTrendArrowDriver {

    public static void main(String[] args) {

        try {

            Double trend = Double.parseDouble(args[0]);

            PredictionData data = new PredictionData();
            data.trend = trend;

            TrendArrow arrow = AlgorithmUtil.getTrendArrow(null, data);

            System.out.println(arrow.name());

        } catch (NumberFormatException e) {
            System.out.println("Error: Trend not a number");
        }
    }
}
