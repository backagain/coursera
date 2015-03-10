/**
 * Created by apple on 15/2/3.
 */
public class Percolation {

    private int size;
    private boolean[] grid; // false => blocked
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF uf1; //to solve backwash problem
    private int top = 0;
    private int bottom = 1;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        if (N < 1) {
            throw new IllegalArgumentException("");
        }
        size = N;
        grid = new boolean[size * size];
        uf = new WeightedQuickUnionUF(size * size + 2);
        uf1 = new WeightedQuickUnionUF(size * size + 1);
        for (int i = 1; i <= size; i++) {
            uf.union(top, xyto1D(1, i) + 2);
            uf1.union(top, xyto1D(1, i) + 1);
            uf.union(bottom, xyto1D(size, i) + 2);
        }
    }

    // open site (row i, column j) if it is not open already
    public void open(int i, int j) {
        if (!grid[xyto1D(i, j)]) {
            grid[xyto1D(i, j)] = true;
            if (i > 1 && isOpen(i - 1, j)) {
                uf.union(xyto1D(i, j) + 2, xyto1D(i - 1, j) + 2);
                uf1.union(xyto1D(i, j) + 1, xyto1D(i - 1, j) + 1);
            }
            if (i < size && isOpen(i + 1, j)) {
                uf.union(xyto1D(i, j) + 2, xyto1D(i + 1, j) + 2);
                uf1.union(xyto1D(i, j) + 1, xyto1D(i + 1, j) + 1);
            }
            if (j > 1 && isOpen(i, j - 1)) {
                uf.union(xyto1D(i, j) + 2, xyto1D(i, j - 1) + 2);
                uf1.union(xyto1D(i, j) + 1, xyto1D(i, j - 1) + 1);
            }
            if (j < size && isOpen(i, j + 1)) {
                uf.union(xyto1D(i, j) + 2, xyto1D(i, j + 1) + 2);
                uf1.union(xyto1D(i, j) + 1, xyto1D(i, j + 1) + 1);
            }
        }
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        validate(i, j);
        return grid[xyto1D(i, j)];
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        if (percolates()) {
            return isOpen(i, j) && uf1.connected(top, xyto1D(i, j) + 1);
        }
        return isOpen(i, j) && uf.connected(top, xyto1D(i, j) + 2);
    }

    public boolean percolates() {
        //1*1 grid
        if (size == 1) {
            return isOpen(1, 1);
        }
        return uf.connected(top, bottom);
    }

    private int xyto1D(int i, int j) {
        validate(i, j);
        return (i - 1) * size + (j - 1);
    }

    private void validate(int i, int j) {
        if (i < 1 || i > size || j < 1 || j > size)
            throw new IndexOutOfBoundsException("row index i out of bounds");
    }

    public static void main(String[] args) throws Exception {

    }
}
