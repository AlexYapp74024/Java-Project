
import java.io.File;
import java.time.*;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;

public class RecordDatastore {

    private static File RecordsFile = new File("Records.json");

    public static ArrayList<Record> Retrieve() {
        try {
            var out = Json.ReadFromFile(RecordsFile, new TypeReference<ArrayList<Record>>() {});
            for (var r : out) {
                r.dateTime = r.dateTime.withSecond(0).withNano(0);
            }
            return out;
        } catch (Exception e) {
            e.printStackTrace();
            return GenerateRandomRecords(100);
        }
    }

    public static void Save(ArrayList<Record> records) {
        try {
            Json.SaveToFile(RecordsFile, records);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Record> GenerateRandomRecords(int count) {

        var random = new Random();
        ArrayList<Record> out = new ArrayList<Record>();

        for (int i = 0; i < count; i++) {
            out.add(new Record(Randheight(random), Randweight(random), RandBodyTemperature(random),
                    RandDateTime(random)));
        }

        Collections.sort(out, (r1, r2) -> {
            return r1.dateTime.compareTo(r2.dateTime);
        });
        return out;
    }

    private static Height Randheight(Random random) {
        return new Height((float) random.nextDouble(160.f, 170.f));
    }

    private static Weight Randweight(Random random) {
        return new Weight((float) random.nextDouble(60.f, 70.f));
    }

    private static Temperature RandBodyTemperature(Random random) {
        return new Temperature((float) random.nextDouble(35.5f, 37.f));
    }

    private static LocalDateTime RandDateTime(Random random) {
        final LocalDateTime maxDate = LocalDateTime.now();
        final LocalDateTime minDate = maxDate.minusMonths(6);

        final long minEpochSecond = minDate.atZone(ZoneId.systemDefault()).toEpochSecond();
        final long maxEpochSecond = maxDate.atZone(ZoneId.systemDefault()).toEpochSecond();

        long epochSecond = random.nextLong(minEpochSecond, maxEpochSecond);
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(epochSecond), ZoneId.systemDefault());
    }
}
