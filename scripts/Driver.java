import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Driver {

    public static final String TEST_ID = "testID";
    public static final String REQUIREMENT = "requirement";
    public static final String COMPONENT = "component";
    public static final String METHOD = "method";
    public static final String INPUT = "input";
    public static final String OUTPUT = "output";
    public static final String ORACLE = "oracle";

    public static void main(String[] args) {

        File destination = new File(args[0]);
        List<File> cases = new ArrayList<>();

        if (destination.exists()) {
            if (destination.isDirectory()) {    // The String passed in is a path to a directory
                File[] listFiles = destination.listFiles();
                if (listFiles != null) {    // Said directory isn't empty
                    for (File file : listFiles) {
                        if (file.getName().substring(file.getName().lastIndexOf(".")).equals(".txt")) {  // If the file is a .txt file
                            cases.add(file);
                        }
                    }
                }

            } else if (destination.isFile()) {  // The String passed in is a path to a file
                cases.add(destination);
            }

            String contents;
            String[] lines;
            for (File testCase : cases) {
                try {
                    contents = readFile(testCase, Charset.defaultCharset());
                    lines = contents.split("\\r?\\n", -1);  // Split each line, ignoring empty lines

                    String testID = replaceIfNull(findLine(TEST_ID, lines), "UNKNOWN_ID");
                    String requirement = replaceIfNull(findLine(REQUIREMENT, lines), "UNKNOWN_REQUIREMENT");
                    String component = findLine(COMPONENT, lines);
                    if (component == null) {
                        throw new MissingSpecificationException("Missing component name.");
                    }
                    String method = findLine(METHOD, lines);
                    if (method == null) {
                        throw new MissingSpecificationException("Missing method name.");
                    }
                    String input = findLine(INPUT, lines);
                    if (input == null) {
                        throw new MissingSpecificationException("Missing inputs.");
                    }
                    String oracle = findLine(ORACLE, lines);
                    if (oracle == null) {
                        throw new MissingSpecificationException("Missing oracle.");
                    }

                    System.out.println(TEST_ID + " : " + testID);
                    System.out.println(REQUIREMENT + " : " + requirement);
                    System.out.println(COMPONENT + " : " + component);
                    System.out.println(METHOD + " : " + method);
                    System.out.println(INPUT + " : " + input);
                    System.out.println(ORACLE + " : " + oracle);

                } catch (MissingSpecificationException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println("Unknown IOException.");
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("No test cases present.");
        }
    }

    private static String readFile(File file, Charset encoding) throws IOException {
        byte[] contents = Files.readAllBytes(file.toPath());
        return new String(contents, encoding);
    }

    private static String findLine(String id, String[] lines) {
        String[] parts;
        for (String line : lines) {
            parts = line.split("-");
            if (parts[0].equals(id)) {
                return parts[1].trim();
            }
        }
        return null;
    }

    private static String replaceIfNull(String string, String replacement) {
        return (string != null) ? string : replacement;
    }
}

