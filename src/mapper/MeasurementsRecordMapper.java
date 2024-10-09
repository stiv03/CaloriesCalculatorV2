package mapper;


import dto.MeasurementsRecordDTO;
import entity.MeasurementsRecord;


public final class MeasurementsRecordMapper {

    private MeasurementsRecordMapper() {
    }

    public static MeasurementsRecordDTO toDTO(MeasurementsRecord measurementsRecordRecord) {
        return new MeasurementsRecordDTO(measurementsRecordRecord.getShoulder(),
                measurementsRecordRecord.getChest(),
                measurementsRecordRecord.getBiceps(),
                measurementsRecordRecord.getWaist(),
                measurementsRecordRecord.getHips(),
                measurementsRecordRecord.getThigh(),
                measurementsRecordRecord.getCalf(),
                measurementsRecordRecord.getDate());
    }
}

