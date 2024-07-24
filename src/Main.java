public class Main {

    // Returns true if there is a subset of set[] with sum equal to given sum
    public static boolean isSubsetSum(int[] set, int n, int sum) {
        // Base Cases
        if (sum == 0)
            return true;
        if (n == 0 && sum != 0)
            return false;

        // If last element is greater than sum, then ignore it
        if (set[n - 1] > sum)
            return isSubsetSum(set, n - 1, sum);

        // Check if sum can be obtained by any of the following
        // (a) including the last element
        // (b) excluding the last element
        return isSubsetSum(set, n - 1, sum) || isSubsetSum(set, n - 1, sum - set[n - 1]);
    }
    // Returns true if there is a subset of set[] with sum equal to given sum
    public static boolean isSubsetSum1(int[] set, int n, int sum) {
        boolean[][] subset = new boolean[n + 1][sum + 1];
        for (int i = 0; i <= n; i++)
            subset[i][0] = true;
        for (int i = 1; i <= sum; i++)
            subset[0][i] = false;

        // Fill the subset table in bottom up manner
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j < set[i - 1])
                    subset[i][j] = subset[i - 1][j];
                if (j >= set[i - 1])
                    subset[i][j] = subset[i - 1][j] || subset[i - 1][j - set[i - 1]];
            }
        }
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= sum; j++)
                System.out.print(subset[i][j] ? "1 " : "0 ");
            System.out.println();
        }

        return subset[n][sum];
    }
    public static void main(String[] args) {
        int[] set = {1,5,11,5};
        int sum=0;
        for(int i=0;i<set.length;i++)
            sum+=set[i];
        if(sum%2==0){
            int n = set.length;
            if (isSubsetSum1(set, n, sum))
                System.out.println("Found a subset with given sum");
            else
                System.out.println("No subset with given sum");
        }
        else
            System.out.println("No subset with given sum");

    }
}

