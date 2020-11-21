package solver.helper;

import solver.data.Complex;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.ListIterator;

public class SolvingHelper {

    private final Deque<Integer> swapsOfCols;
    private final List<List<Complex>> matrix;

    public SolvingHelper(List<List<Complex>> matrix) {
        this.swapsOfCols = new ArrayDeque<>();
        this.matrix = matrix;
    }

    public boolean swapRows(int init) {
        for (int row = init + 1; row < matrix.size(); ++row) {
            if (!matrix.get(row).get(init).equals(Complex.ZERO)) {
                List<Complex> temp = matrix.get(row);
                matrix.set(row, matrix.get(init));
                matrix.set(init, temp);
                System.out.printf("R%d <-> R%d%n\n", init + 1, row + 1);
                return true;
            }
        }
        return false;
    }

    public boolean swapCols(int init) {
        for (int col = init + 1; col < matrix.get(init).size() - 1; ++col) {
            if (!matrix.get(init).get(col).equals(Complex.ZERO)) {
                for (List<Complex> rows : matrix) {
                    Complex temp = rows.get(col);
                    rows.set(col, rows.get(init));
                    rows.set(init, temp);
                }
                swapsOfCols.push(init);
                swapsOfCols.push(col);
                return true;
            }
        }
        return false;
    }

    private boolean swapRowsAndCols(int init) {
        for (int row = init + 1; row < matrix.size(); ++row) {
            for (int col = init - 1; col >= 0; --col) {
                if (!matrix.get(row).get(col).equals(Complex.ZERO)) {
                    List<Complex> tempRow = matrix.get(row);
                    matrix.set(row, matrix.get(init));
                    matrix.set(init, tempRow);
                    System.out.printf("R%d <-> R%d%n\n", init + 1, row + 1);
                    for (List<Complex> rows : matrix) {
                        Complex tempNum = rows.get(col);
                        rows.set(col, rows.get(init));
                        rows.set(init, tempNum);
                    }
                    swapsOfCols.push(init);
                    swapsOfCols.push(col);
                    return true;
                }
            }
        }
        return false;
    }

    public void rollbackSwaps() {
        while(!swapsOfCols.isEmpty()) {
            int col = swapsOfCols.pop();
            int initial = swapsOfCols.pop();
            for (List<Complex> rows : matrix) {
                Complex temp = rows.get(col);
                rows.set(col, rows.get(initial));
                rows.set(initial, temp);
            }
        }
    }

    public void clearBlankLines() {
        boolean onlyZeros = true;
        ListIterator<List<Complex>> iter = matrix.listIterator();
        while (iter.hasNext()) {
            List<Complex> current = iter.next();
            for (Complex value : current) {
                if (!value.equals(Complex.ZERO)) {
                    onlyZeros = false;
                    break;
                }
            }
            if (onlyZeros) {
                iter.remove();
            } else {
                onlyZeros = true;
            }
        }
    }

    public boolean replaceZeroInDiagonal(int init) {
        if (swapRows(init)) {
            return true;
        } else if (swapCols(init)) {
            return true;
        } else return swapRowsAndCols(init);
    }
}
