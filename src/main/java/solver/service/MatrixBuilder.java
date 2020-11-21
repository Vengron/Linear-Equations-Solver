package solver.service;

import solver.data.Complex;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class MatrixBuilder {

    public static List<List<Complex>> createMatrix(List<String> source) {
        Iterator<String> iterator = source.iterator();

        int rowLength = Integer.parseInt(iterator.next()) + 1;
        int colLength = Integer.parseInt(iterator.next());

        List<List<Complex>> matrix = new ArrayList<>();

        for (int i = 0; i < colLength; ++i) {
            matrix.add(new ArrayList<>());
            for (int j = 0; j < rowLength; ++j) {

                matrix.get(i).add(new Complex(iterator.next()));
            }
        }

        return matrix;
    }

}
