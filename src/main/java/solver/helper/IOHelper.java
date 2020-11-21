package solver.helper;

import solver.data.Complex;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IOHelper {

    private static File inputFile;
    private static File outputFile;

    public static void processArgs(String[] args) {
        for (int i = 0; i < args.length; ++i) {
            switch (args[i]) {
                case "-in" -> inputFile = new File(args[i + 1]);
                case "-out" -> outputFile = new File(args[i + 1]);
            }
        }
    }

    public static void saveSolution(List<String> solution ) {
        try (FileWriter writer = new FileWriter(outputFile)) {
            for (String s: solution) {
                writer.append(s).append("\n");
            }
            System.out.println("Saved to file " + outputFile.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getData() {
        List<String> source = new ArrayList<>();

        try (Scanner scanner = new Scanner(inputFile)) {
            while (scanner.hasNext()) {
                source.add(scanner.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return source;
    }

    public static String printSolution(List<String> solution) {
        if (solution.size() > 1) {
            return "The solution is: " + solution.toString()
                    .replace("[", "(").replace("]", ")");
        }
        return solution.get(0);
    }

    public static void printMatrix(List<List<Complex>> matrix) {
        for (List<Complex> row: matrix) {
            for (Complex number: row) {
                System.out.print(number.toString() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
