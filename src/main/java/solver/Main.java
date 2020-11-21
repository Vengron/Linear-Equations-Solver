package solver;

import solver.data.Complex;
import solver.helper.IOHelper;
import solver.service.MatrixBuilder;
import solver.service.Solver;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        IOHelper.processArgs(args);

        List<List<Complex>> matrix = MatrixBuilder.createMatrix(IOHelper.getData());
        Solver solver = new Solver(matrix);
        List<String> solution = solver.solveMatrix();

        System.out.println(IOHelper.printSolution(solution));
        IOHelper.saveSolution(solution);
    }
}
