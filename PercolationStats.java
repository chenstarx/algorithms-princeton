import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("invalid arguments");  
        }

        Percolation p;

        int randX;
        int randY;

        double[] results = new double[trials];

        for (int i = 0; i < trials; i++) {

            p = new Percolation(n);

            while (!p.percolates()) {

                randX = StdRandom.uniform(1, n + 1);
                randY = StdRandom.uniform(1, n + 1);
    
                p.open(randX, randY);

            }

            double sites = p.numberOfOpenSites();
            double area = n * n;

            results[i] = sites / area;

        }

        mean = StdStats.mean(results);
        stddev = StdStats.stddev(results);

        double conf = 1.96 * stddev / Math.sqrt(trials);

        confidenceLo = mean - conf;
        confidenceHi = mean + conf;

    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return confidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return confidenceHi;
    }

    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(200, 100);
        
        System.out.println("mean                    = " + ps.mean());
        System.out.println("stddev                  = " + ps.stddev());
        System.out.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
    }
}
