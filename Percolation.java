import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF uf;
    private boolean[] open;

    private int size;
    private int count;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("index " + n + " is no larger than 0");  
        }
        size = n;

        int length = size * size + 2; // 1 virtual top and 1 virtual bottom

        uf = new WeightedQuickUnionUF(length);
        open = new boolean[length];
        count = 0;

        for (int i = 0; i < length; i++) {
            open[i] = false;
        }
        for (int i = 1; i < size + 1; i++) {
            uf.union(0, i); // 0: virtual top
            uf.union(length - 1, (size * (size - 1)) + i); // length - 1: virtual bottom
        }
        open[0] = true;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);

        if (isOpen(row, col)) return;
        
        count += 1;

        int index = getIndex(row, col);

        open[index] = true;

        if (isExist(row - 1, col) && isOpen(row - 1, col)) uf.union(index, getIndex(row - 1, col));
        if (isExist(row + 1, col) && isOpen(row + 1, col)) uf.union(index, getIndex(row + 1, col));
        if (isExist(row, col - 1) && isOpen(row, col - 1)) uf.union(index, getIndex(row, col - 1));
        if (isExist(row, col + 1) && isOpen(row, col + 1)) uf.union(index, getIndex(row, col + 1));

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);

        return open[getIndex(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);

        if (!isOpen(row, col)) return false;

        return uf.find(0) == uf.find(getIndex(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        int bottom = size * size + 1;
        return uf.find(0) == uf.find(bottom);
    }


    // check if the params are valid
    private void validate(int p, int q) {
        if (p <= 0 || p > size || q <= 0 || q > size) {
            throw new IllegalArgumentException("invalid row or column");
        }
    }

    // get the array index by coordinates
    private boolean isExist(int p, int q) {
        return p > 0 && p <= size && q > 0 && q <= size;
    }

    // get the array index by coordinates
    private int getIndex(int p, int q) {
        validate(p, q);
        return size * (p - 1) + q;
    }

    // public static void main(String[] args) {
    //     Percolation p = new Percolation(5);
    //     System.out.println(p.isFull(2, 3));
    //     p.open(1, 3);
    //     System.out.println(p.isFull(2, 3));
    //     System.out.println(p.isOpen(2, 3));
    //     p.open(2, 3);
    //     System.out.println(p.isFull(2, 3));
    //     System.out.println(p.isOpen(2, 3));
    //     p.open(3, 3);
    //     p.open(4, 3);
    //     System.out.println(p.percolates());
    //     p.open(5, 3);
    //     System.out.println(p.percolates());
    // }
}
