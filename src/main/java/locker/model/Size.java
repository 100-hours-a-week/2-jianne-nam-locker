package locker.model;

public enum Size {

    SMALL(2, 1000, 1, 500),
    MEDIUM(2, 2000, 1, 1000),
    LARGE(2, 3000, 1, 1500);

    private final Integer baseMinutes;
    private final Integer baseFee;
    private final Integer unitMinutes;
    private final Integer feePerUnit;

    Size(Integer baseMinutes, Integer baseFee, Integer unitMinutes, Integer feePerUnit) {
        this.baseMinutes = baseMinutes;
        this.baseFee = baseFee;
        this.unitMinutes = unitMinutes;
        this.feePerUnit = feePerUnit;
    }

    public Long calculateFee(Long totalMinutes) {
        long extraMinutes = (totalMinutes > baseMinutes)? totalMinutes - baseMinutes : 0L;
        return baseFee + feePerUnit * (long) Math.ceil( 1.0 * extraMinutes / unitMinutes);
    }
}
