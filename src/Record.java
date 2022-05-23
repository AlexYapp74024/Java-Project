
import java.time.*;

public class Record {

    public Record() {
        this.height = 0.f;
        this.weight = 0.f;
        this.bodyTemp = 0.f;
        this.date = LocalDateTime.now();
    }

    public Record(float height, float weight, float bodyTemp, LocalDateTime date) {
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
