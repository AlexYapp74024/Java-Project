import java.util.ArrayList;

public class BloodType {
    enum Group {
        A, B, O, AB
    }

    enum Rh {
        plus, minus;

        @Override
        public String toString() {
            switch (this) {
            case plus:
                return "+";
            case minus:
                return "-";
            }
            return "";
        }
    }

    BloodType(String string) {
        for (var group : Group.values())
            if (string.contains(group.toString()))
                this.group = group;

        for (var rh : Rh.values())
            if (string.contains(rh.toString()))
                this.rh = rh;
    }

    public String toString() {
        return group.toString() + rh.toString();
    }

    public static ArrayList<String> values() {
        ArrayList<String> out = new ArrayList<>();
        for (BloodType.Group g : BloodType.Group.values())
            for (BloodType.Rh rh : BloodType.Rh.values())
                out.add(g.toString() + rh.toString());
        return out;
    }

    public Group group;
    public Rh rh;
}