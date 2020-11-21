package solver.data;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Complex {
    public static final double EPSILON = 0.000001;
    public static final Complex ZERO = new Complex(0, 0);
    public static final Complex ONE = new Complex(1, 0);
    private final double real;
    private final double imaginary;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Complex(String s) throws NumberFormatException {
        final String[] strs = split(s);
        if (strs[1].contains("i")) {
            strs[1] = strs[1].replace("i", "1");
        }
        if (strs[1].equals("-")) {
            strs[1] = "-1";
        }
        if (strs[1].equals("+")) {
            strs[1] = "1";
        }
        real = Double.parseDouble(strs[0]);
        imaginary = Double.parseDouble(strs[1]);
    }

    public Complex add(Complex other) {
        return new Complex(this.real + other.real, this.imaginary + other.imaginary);
    }

    public Complex divide(Complex other) {
        final Complex oConjugated = other.conjugate();
        final Complex a1 = this.multiply(oConjugated);
        final Complex b1 = other.multiply(oConjugated);

        return new Complex(a1.real / b1.real, a1.imaginary / b1.real);
    }

    public Complex multiply(Complex other) {
        return new Complex(this.real * other.real - this.imaginary * other.imaginary,
                this.real * other.imaginary + this.imaginary * other.real);
    }

    public Complex negate() {
        return new Complex(-this.real, -this.imaginary);
    }

    public Complex conjugate() {
        return new Complex(this.real, -this.imaginary);
    }

    @Override
    public String toString() {
        final DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        final DecimalFormat realFormat = new DecimalFormat("0.####", symbols);
        final DecimalFormat imagFormat = new DecimalFormat("0.####i", symbols);
        if (Math.abs(imaginary) < EPSILON) {
            return realFormat.format(real);
        }
        if (Math.abs(real) < EPSILON) {
            return imagFormat.format(imaginary);
        }
        imagFormat.setPositivePrefix("+");
        return String.format("%s%s", realFormat.format(real), imagFormat.format(imaginary));
    }

    private String[] split(String s) throws NumberFormatException {
        if (s.equals("i")) {
            return new String[]{"0", "1"};
        }
        if (s.equals("-i")) {
            return new String[]{"0", "-1"};
        }
        String realString = "0";
        String imagString = "0";
        int i = 1;
        for (; i < s.length(); ++i) {
            if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                realString = s.substring(0, i);
                imagString = s.substring(i, s.length() - 1);
                if (s.charAt(s.length()-1) != 'i') {
                    throw new NumberFormatException("can't parse complex");
                }
                break;
            }
            if (s.charAt(i) == 'i') {
                if (i != s.length() - 1) {
                    throw  new NumberFormatException("can't parse complex");
                }
                imagString = s.substring(0, i);
                break;
            }
        }
        if (i == s.length()) {
            realString = s;
        }
        if (imagString.length() == 0) {
            imagString = "1";
        }
        return new String[]{realString, imagString};
    }

    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (!(other instanceof Complex)) {
            return false;
        }

        Complex o = (Complex) other;
        return Math.abs(o.imaginary - imaginary) < EPSILON && Math.abs(o.real - real) < EPSILON;
    }

}