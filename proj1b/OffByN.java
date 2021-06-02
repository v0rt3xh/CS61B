public class OffByN implements CharacterComparator{
    private int N;
    public OffByN(int input) {
        N = input;
    }
    @Override
    public boolean equalChars(char x, char y) {
        int judge = Math.abs(x-y);
        return judge == N ? true : false;
    }

}
