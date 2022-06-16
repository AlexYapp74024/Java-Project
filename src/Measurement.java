import java.text.DecimalFormat;

interface Unit_measurement {
    public String withUnit();
}

public abstract class Measurement implements Comparable<Measurement>, Unit_measurement {

    Measurement() {}

    Measurement(float value) {
        this.value = value;
    }

    @Override
    public int compareTo(Measurement o) {
        return Float.valueOf(value).compareTo(o.value);
    }

    public abstract String withUnit();

    public float value;
    protected DecimalFormat df = new DecimalFormat("0.00");
}

class Height extends Measurement {

    Height() {}

    Height(float value) {
        super(value);
    }

    @Override
    public String withUnit() {
        return df.format(value) + "cm";
    }
}

class Weight extends Measurement {

    Weight() {}

    Weight(float value) {
        super(value);
    }

    @Override
    public String withUnit() {
        return df.format(value) + "kg";
    }
}

class Temperature extends Measurement {

    Temperature() {}

    Temperature(float value) {
        super(value);
    }

    @Override
    public String withUnit() {
        return df.format(value) + "°C";
    }
}
