package solver.service;

import solver.data.Complex;
import solver.helper.SolvingHelper;

import java.util.ArrayList;
import java.util.List;

public class Solver {

    private final SolvingHelper helper;
    private final List<List<Complex>> matrix;

    public Solver(List<List<Complex>> matrix) {
        this.helper = new SolvingHelper(matrix);
        this.matrix = matrix;
    }

    public List<String> solveMatrix() {
        System.out.println("Start solving the equation...");
        List<String> solution = new ArrayList<>();
        System.out.println("Row manipulation: ");
        switch (firstPhase()) {
            case -2 -> {
                solution.add("Infinitely many solutions");
                return solution;
            }
            case -1 -> {
                solution.add("No solutions");
                return solution;
            }
            default -> {
                secondPhase();
                helper.rollbackSwaps();
                for (List<Complex> row : matrix) {
                    solution.add(row.get(matrix.get(0).size() - 1).toString());
                }
                return solution;
            }
        }
    }

    private int firstPhase() {
        helper.clearBlankLines();
        for (int i = 0; i < matrix.size(); ++i) {
            if (matrix.get(i).get(i).equals(Complex.ZERO)) {
                if (!helper.replaceZeroInDiagonal(i)) {
                    helper.clearBlankLines();
                    if (checkContradiction()) {
                        return -1;
                    } else if (lessThanEnoughEquations()) {
                        return -2;
                    }
                }
            }
            if (!matrix.get(i).get(i).equals(Complex.ONE)) {
                Complex divider = matrix.get(i).get(i);
                for (int col = 0; col < matrix.get(0).size(); ++col) {
                    Complex newValue = matrix.get(i).get(col).divide(divider);
                    matrix.get(i).set(col, newValue);
                }

                System.out.printf("R%d / %s -> R%d%n",
                        i + 1, divider.toString(),  i + 1);
            }
            if (checkContradiction()) {
                return -1;
            }
            for (int j = i + 1; j < matrix.size(); ++j) {
                Complex multiplier = matrix.get(j).get(i).negate();
                if (!multiplier.equals(Complex.ZERO)) {
                    for (int k = 0; k < matrix.get(0).size(); ++k) {
                        Complex newValue = matrix.get(j).get(k).add(multiplier.multiply(matrix.get(i).get(k)));
                        matrix.get(j).set(k, newValue);
                    }

                    System.out.printf("%s * R%d + R%d -> R%d%n",
                            multiplier.toString(), i + 1, j + 1, j + 1);
                }
            }
        }
        helper.clearBlankLines();
        if (lessThanEnoughEquations()) {
            return -2;
        }
        return 1;
    }

    private void secondPhase() {
        for (int i = matrix.size() - 1; i >= 0; --i) {
            for (int j = i - 1; j >= 0; --j) {
                Complex multiplier = matrix.get(j).get(i).negate();
                for (int col = matrix.get(0).size() - 1; col >= 0; --col) {
                    Complex newValue = matrix.get(j).get(col).add(multiplier.multiply(matrix.get(i).get(col)));
                    matrix.get(j).set(col, newValue);
                }

                System.out.printf("%s * R%d + R%d -> R%d%n",
                        multiplier.toString(), i + 1, j + 1, j + 1);
            }
        }
    }

    private boolean lessThanEnoughEquations() {
        return matrix.size() < matrix.get(0).size() - 1;
    }

    private boolean checkContradiction() {
        boolean onlyZeros = true;
        for (List<Complex> row : matrix) {
            for (int col = 0; col < row.size() - 1; ++col) {
                if (!row.get(col).equals(Complex.ZERO)) {
                    onlyZeros = false;
                    break;
                }
            }
            if (onlyZeros && !(row.get(row.size() - 1)).equals(Complex.ZERO)) {
                return true;
            }
            onlyZeros = true;
        }
        return false;
    }
}
