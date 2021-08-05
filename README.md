# Linear-Equations-Solver
Linear Equations Solver. It's able to solve any complex matrix with Gauss-Jordan elimination, but there is a limitation due to approximation error in many calculations and time complexity.

Need to specify -in *.txt -out *.txt args  

An input should be in the format:  
[number of rows*] [number of columns*]  
[upper-left number] ... [upper-right number] [first right-hand side number]  
...........................................................................  
[bottom-left number] ... [bottom-right number] [last right-hand side number]  
*of not-extended matrix  

For example:  
```
4 4  
1 2 3 4 1  
2 3 4 1 1  
3 4 1 2 1  
4 1 2 3 1  
```

Made on https://hyperskill.org  
