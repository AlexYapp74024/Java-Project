public class BMI extends Measurement {

    public BMI(float bmi) {
        super(bmi);
    }

    public BMI(Height height, Weight weight) {
        super(weight.value / height.value / height.value * 100 * 100);
    }

    public Boolean IsUnderWeight() {
        return value < HEALTHY_VALUE;
    }

    public Boolean IsHealthy() {
        return HEALTHY_VALUE <= value && value < OVER_WEIGHT_VALUE;
    }

    public Boolean IsOverweight() {
        return OVER_WEIGHT_VALUE <= value && value < OBESE_VALUE;
    }

    public Boolean IsObese() {
        return OBESE_VALUE <= value;
    }

    private final float HEALTHY_VALUE = 25.f;
    private final float OVER_WEIGHT_VALUE = 30.5f;
    private final float OBESE_VALUE = 18.5f;

    @Override
    public String withUnit() {
        return df.format(value);
    }
}
