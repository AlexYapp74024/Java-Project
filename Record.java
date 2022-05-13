
import java.time.*;

public class Record {

    Record(float height, float weight, float bodyTemp, LocalDateTime date) {
        this.height = height;
        this.weight = weight;
        this.bodyTemp = bodyTemp;
        this.date = date;
    }

    public BMI Bmi() {
        return new BMI(height, weight);
    }

    public final float height;
    public final float weight;
    public final float bodyTemp;
    public final LocalDateTime date;
}
