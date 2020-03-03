public class WordCounter {
    private final int counter;
    private final int spaces;

    public WordCounter(int counter, int spaces) {
        this.counter = counter;
        this.spaces = spaces;
    }

    public WordCounter accumulate(Character character) {
        if (Character.isWhitespace(character)) {
            return new WordCounter(counter, spaces+1);
        }
        else{
            if(spaces%2==1){
                return new WordCounter(counter+1, 0);
            }
            else{
                return new WordCounter(counter, 0);
            }
        }
    }

    public WordCounter combine(WordCounter wordCounter) {
        return new WordCounter(counter + wordCounter.counter, wordCounter.spaces);
    }

    public int getCounter() {
        return counter;
    }
}