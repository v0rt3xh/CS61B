package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        boolean flag = true;
        int N = oomages.size();
        int[] arrayRecord = new int[M];
        for (int i = 0; i < N; i++) {
            Oomage o = oomages.get(i);
            int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            arrayRecord[bucketNum] += 1;
        }
        for (int j = 0; j < M; j++) {
            if (arrayRecord[j] < (N / 50) || arrayRecord[j] > (N / 2.5)) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}
