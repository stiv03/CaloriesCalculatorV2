package mapper;

import dto.WeightRecordDTO;
import entity.WeightRecord;

public final class WeightRecordMapper {

    private WeightRecordMapper() {
    }

    public static WeightRecordDTO toDTO(WeightRecord weightRecord) {
        return new WeightRecordDTO(weightRecord.getWeight(), weightRecord.getDate());
    }
}
