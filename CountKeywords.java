import java.util.*;
import java.io.*;

public class CountKeywords {
  public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      System.out.println("Usage: java CountKeywords <Java-source-file>");
      System.exit(1);
    }

    String filename = args[0];
    File file = new File(filename);

    if (file.exists()) {
      System.out.println("The number of keywords in " + filename +
          " is " + countKeywords(file));
    } else {
      System.out.println("File " + filename + " does not exist");
    }
  }

  public static int countKeywords(File file) throws Exception {
    String[] keywordString = { "abstract", "assert", "boolean",
        "break", "byte", "case", "catch", "char", "class", "const",
        "continue", "default", "do", "double", "else", "enum",
        "extends", "for", "final", "finally", "float", "goto",
        "if", "implements", "import", "instanceof", "int",
        "interface", "long", "native", "new", "package", "private",
        "protected", "public", "return", "short", "static",
        "strictfp", "super", "switch", "synchronized", "this",
        "throw", "throws", "transient", "try", "void", "volatile",
        "while", "true", "false", "null" };

    Set<String> keywordSet = new HashSet<>(Arrays.asList(keywordString));
    int count = 0;

    try (Scanner input = new Scanner(file)) {
      boolean inComment = false;

      while (input.hasNext()) {
        String word = input.next();

        if (word.startsWith("/*")) {
          inComment = true;
        }

        if (!inComment && keywordSet.contains(word)) {
          count++;
        }

        if (word.endsWith("*/")) {
          inComment = false;
        }
      }
    }

    return count;
  }
}
