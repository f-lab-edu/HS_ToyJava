


public final class MatchResult {

    private final long strikes;
    private final long balls;

    //final선언 X
    private long matchCount = 0;

    public MatchResult(long strikes, long balls) {
        this.strikes = strikes;
        this.balls = balls;
        this.matchCount++;
    }

    public long getStrikes() {
        return strikes;
    }
    public long getBalls() {
        return balls;
    }

    public long getMatchCount() {
        return matchCount;
    }


}
