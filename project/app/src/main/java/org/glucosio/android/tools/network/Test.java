package org.glucosio.android.tools.network;

import android.support.v4.content.ContextCompat;

import org.glucosio.android.R;
import org.glucosio.android.tools.FormatDateTime;
import org.glucosio.android.tools.GlucosioConverter;

/**
 * Created by marottajb on 11/14/17.
 */

public class Test {

    public static void main(String[] args) {

        FormatDateTime format = new FormatDateTime(null);

        System.out.println(format.convertDateToMonthOverview("1995--3-15"));

        System.out.println(GlucosioConverter.a1cToGlucose(0.12345));
        System.out.println(GlucosioConverter.a1cToGlucose(-1.40223));
        System.out.println(GlucosioConverter.a1cToGlucose(3.24e-3));
    }
}
