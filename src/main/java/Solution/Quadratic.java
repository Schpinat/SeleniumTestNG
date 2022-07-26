package Solution;

public class Quadratic {
    private int a;
    private int b;
    private int c;
    private double D;
    Solution solution;


    public Solution resultDis (int a, int b, int c)
    {
    solution = new Solution();
        this.D = b * b - 4 * a * c;
        this.a = a;
        this.b = b;
        this.c = c;

       if (D > 0 || D == 0) {
            solution.setX1((-b + Math.sqrt(D)) / (2 * a)) ;
            solution.setX2((-b - Math.sqrt(D)) / (2 * a)) ;
        } else  {
            solution = null;
        }
        return solution;
    }

}

