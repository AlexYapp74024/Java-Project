
import java.time.*;

public class Record {

    public Record() {
        this.height = 0.f;
        this.weight = 0.f;
        this.bodyTemp = 0.f;
        this.dateTime = LocalDateTime.now().withSecond(0).withNano(0);
    }

    public Record(float height, float weight, float bodyTemp, LocalDateTime date) {
        this.height = height;
        this.weight = weight;
        this.bodyTemp = bodyTemp;
        this.dateTime = date;
    }

    @Override
    public boolean equals(Object r) {
        Record record = (Record) r;

        return this.height == record.height && this.weight == record.weight && this.bodyTemp == record.bodyTemp
                && this.dateTime.equals(record.dateTime);
    }

    public BMI Bmi() {
        return new BMI(height, weight);
    }

    public float height;
    public float weight;
    public float bodyTemp;
    public LocalDateTime dateTime;

    public float getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }

    public float getBodyTemp() {
        return bodyTemp;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

}
