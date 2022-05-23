
class Main {

    public static void main(String[] args) {

        var records = Database.GetAllRecords();
        Database.SaveRecords(records);

    } // example
}
