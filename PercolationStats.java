import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("invalid arguments");  
        }

        Percolation p = new Percolation(n);

        int randX;
        int randY;

        double[] results = new double[trials];

        for (int i = 0; i < trials; i++) {

            p.init();

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

        System.out.println("mean                    = " + mean);
        System.out.println("stddev                  = " + stddev);
        System.out.println("95% confidence interval = [" + confidenceLo + ", " + confidenceHi + "]");

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

    // public static void main(String[] args) {
    //     new PercolationStats(200, 100);
    // }
}
