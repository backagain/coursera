/**
 * Created by apple on 15/2/10.
 */
public class PercolationStats {

    private double[] result;

    public PercolationStats(int N, int T) {
        if (N < 1 || T < 1)
            throw new IllegalArgumentException("illegal");
        result = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation p = new Percolation(N);
            double count = 0;
            int randomx = StdRandom.uniform(1, N + 1);
            int randomy = StdRandom.uniform(1, N + 1);
            while (!p.percolates()) {
                if (!p.isOpen(randomx, randomy)) {
                    p.open(randomx, randomy);
                    count++;
                }
                randomx = StdRandom.uniform(1, N + 1);
                randomy = StdRandom.uniform(1, N + 1);
            }
            result[i] = count / (N * N);
        }
    }

    public double mean() {
        double sum = 0;
        for (int i = 0; i < result.length; i++) {
            sum += result[i];
        }
        return sum / result.length;
    }

    public double stddev() {
        double sum = 0;
        for (int i = 0; i < result.length; i++) {
            sum += (result[i] - mean()) * (result[i] - mean());
        }
        return Math.sqrt(sum / (result.length - 1));
    }

    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(result.length);
    }

    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(result.length);
    }

    public static void main(String[] args) {
        PercolationStats p = new PercolationStats(2, 100000);
        StdOut.println(p.mean());
        StdOut.println(p.stddev());
        StdOut.println(p.confidenceLo());
        StdOut.println(p.confidenceHi());
        System.out.print(1);
    }

}
