public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        int judge = Math.abs(x-y);
        return judge == 1 ? true : false;
    }
}
