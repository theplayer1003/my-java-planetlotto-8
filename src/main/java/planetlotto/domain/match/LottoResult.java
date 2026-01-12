package planetlotto.domain.match;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class LottoResult {
    private final EnumMap<Prize, Integer> resultMap;

    public LottoResult(EnumMap<Prize, Integer> resultMap) {
        this.resultMap = resultMap;
    }

    public Map<Integer, Integer> getStatistics() {
        Map<Integer, Integer> stat = new HashMap<>();

        for (Entry<Prize, Integer> entry : resultMap.entrySet()) {
            stat.put(entry.getKey().getIndex(), entry.getValue());
        }

        return stat;
    }
}
