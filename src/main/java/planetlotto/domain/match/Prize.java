package planetlotto.domain.match;

public enum Prize {
    FIRST("1등", 5, false, 100_000_000L, 1),
    SECOND("2등", 4, true, 10_000_000L, 2),
    THRID("3등", 4, false, 1_500_000L, 3),
    FORTH("4등", 3, true, 500_000L, 4),
    FIFTH("5등", 2, true, 5_000, 5),
    NONE("미당첨", 0, false, 0, 0);

    private final String description;
    private final int matchCount;
    private final boolean bonusMatch;
    private final long prize;
    private final int index;

    Prize(String description, int matchCount, boolean bonusMatch, long prize, int index) {
        this.description = description;
        this.matchCount = matchCount;
        this.bonusMatch = bonusMatch;
        this.prize = prize;
        this.index = index;
    }

    public static Prize valueOf(int matchCount, boolean bonusMatch) {
        if (matchCount == 5 && bonusMatch == false) {
            return Prize.FIRST;
        }
        if (matchCount == 4 && bonusMatch == true) {
            return Prize.SECOND;
        }
        if (matchCount == 4 && bonusMatch == false) {
            return Prize.THRID;
        }
        if (matchCount == 3 && bonusMatch == true) {
            return Prize.FORTH;
        }
        if (matchCount == 2 && bonusMatch == true) {
            return Prize.FIFTH;
        }

        return Prize.NONE;
    }

    public String getDescription() {
        return description;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isBonusMatch() {
        return bonusMatch;
    }

    public long getPrize() {
        return prize;
    }

    public int getIndex() {
        return index;
    }
}
