
import java.io.File;
import java.time.*;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;

public class RecordDatastore {

    private static File RecordsFile = new File("Records.json");

    public static ArrayList<Record> Retrieve() {
        try {
            return Json.ReadFromFile(RecordsFile, new TypeReference<ArrayList<Record>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return GenerateRandomRecords(30);
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

        Collections.sort(out, new DateComparator());
        return out;
    }

    private static float Randheight(Random random) {
        return (float) random.nextDouble(160.f, 170.f);
    }

    private static float Randweight(Random random) {
        return (float) random.nextDouble(60.f, 70.f);
    }

    private static float RandBodyTemperature(Random random) {
        return (float) random.nextDouble(35.5f, 37.f);
    }

    private static LocalDateTime RandDateTime(Random random) {
        final LocalDateTime minDate = LocalDateTime.of(2022, 1, 1, 0, 0);
        final LocalDateTime maxDate = LocalDateTime.now();

        final long minEpochSecond = minDate.atZone(ZoneId.systemDefault()).toEpochSecond();
        final long maxEpochSecond = maxDate.atZone(ZoneId.systemDefault()).toEpochSecond();

        long epochSecond = random.nextLong(minEpochSecond, maxEpochSecond);
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(epochSecond), ZoneId.systemDefault());
    }

    private static class DateComparator implements Comparator<Record> {
        public int compare(Record c1, Record c2) {
            return c1.dateTime.compareTo(c2.dateTime);
        }
    }
}
