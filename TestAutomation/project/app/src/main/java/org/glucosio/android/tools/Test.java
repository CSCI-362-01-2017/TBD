package org.glucosio.android.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by marottajb on 11/21/17.
 */

public class Test {

    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            SplitDateTime dateTime = new SplitDateTime(format.parse("1992-05-12 12:20"), format);
            System.out.println(dateTime.getMonth());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
