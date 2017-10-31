package testing;

import testSource.TestObject;

public class TestRun {

    public static String runTestObject(TestObject to) {

        String rtnval = "FAIL";

        if (to != null) {
            if (to.attribute) {
                rtnval = "PASS";
            } else {
                System.out.println("attribute: false");
            }
        } else {
            System.out.println("attribute: NULL");
        }

        return rtnval;
    }

    public static String runTestString(int i) {
        switch(i) {
            case 1:
                return "1";
            case 2:
                return "2";
        }
        return null;
    }

    public static void methodCall() {
        System.out.println("Method called.");
    }

}
