# Linear-Equations-Solver
Linear Equations Solver. It's able to solve any complex (real) matrix with Gauss-Jordan elimination, so there is a limitation due to approximation error in many calculations.

Need to specify -in *.txt -out *.txt args

As input use .txt file in format:
[number of rows*] [number of columns*]
[upper-left number] .... [upper-right number] [first right-hand side number]
...........................................................................
[bottom-left number] ... [bottom-right number] [last right-hand side number]
*of not-extended matrix

For example:
4 4
1 2 3 4 1
2 3 4 1 1
3 4 1 2 1
4 1 2 3 1
