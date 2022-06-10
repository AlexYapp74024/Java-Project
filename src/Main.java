
class Main {

    public static void printList() {
        for (var r : Records.GetRecordList())
            System.out.println(r.height + " \t" + r.weight + " \t" + r.bodyTemp + " \t" + r.Bmi().value);
    }

    public static void main(String[] args) {
        new MainPanel();
    }
}
