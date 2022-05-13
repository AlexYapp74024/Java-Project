public class BMI {

    public BMI(float bmi) {
        this.value = bmi;
    }

    public BMI(float height, float weight) {
        this.value = weight / height / height / 100 / 100;
    }

    public Boolean IsUnderWeight() {
        return value < healthyValue;
    }

    public Boolean IsHealthy() {
        return healthyValue <= value && value < overweightValue;
    }

    public Boolean IsOverweight() {
        return overweightValue <= value && value < obeseValue;
    }

    public Boolean IsObese() {
        return obeseValue <= value;
    }

    public final float value;

    private final float healthyValue = 25.f;
    private final float overweightValue = 30.5f;
    private final float obeseValue = 18.5f;
}
