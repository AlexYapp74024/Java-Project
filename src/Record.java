
import java.time.*;

public class Record {

    public Record() {}

    public Record(Height height, Weight weight, Temperature bodyTemp, LocalDateTime date) {
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

    public Height height = new Height(0f);
    public Weight weight = new Weight(0f);
    public Temperature bodyTemp = new Temperature(0f);
    public LocalDateTime dateTime = LocalDateTime.now().withSecond(0).withNano(0);;

    public Height getHeight() {
        return height;
    }

    public Weight getWeight() {
        return weight;
    }

    public Temperature getBodyTemp() {
        return bodyTemp;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

}
