public class BMI {

    public BMI(float bmi) {
        this.VALUE = bmi;
    }

    public BMI(float height, float weight) {
        this.VALUE = weight / height / height * 100 * 100;
    }

    public Boolean IsUnderWeight() {
        return VALUE < HEALTHY_VALUE;
    }

    public Boolean IsHealthy() {
        return HEALTHY_VALUE <= VALUE && VALUE < OVER_WEIGHT_VALUE;
    }

    public Boolean IsOverweight() {
        return OVER_WEIGHT_VALUE <= VALUE && VALUE < OBESE_VALUE;
    }

    public Boolean IsObese() {
        return OBESE_VALUE <= VALUE;
    }

    public final float VALUE;

    private final float HEALTHY_VALUE = 25.f;
    private final float OVER_WEIGHT_VALUE = 30.5f;
    private final float OBESE_VALUE = 18.5f;
}
