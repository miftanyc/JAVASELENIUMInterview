package first50;


public class dataProviderAndReceiverViaTwoDimensionalArray {

    public static void main(String[] args) {

        Object[][] data = dataProvider();
        for(Object row[] :data){
            int id = (int) row[0];
            String firstName = (String) row[1];
            String lastName = (String) row[2];

            dataReceiver(id, firstName, lastName);
        }

    }

    public static void dataReceiver(int id, String firstName, String lastName){
        System.out.print(id);
        System.out.print("      |       ");
        System.out.print(firstName);
        System.out.print("      |       ");
        System.out.print(lastName);
        System.out.println("\n========================================================");
    }

    public static Object[][] dataProvider() {
        Object[][] data = { {001, "Mifta", "Chowdhury"},
                            {002, "Smith", "Jhonson"},
                            {003, "Arav", "Mitra"},
                            {004, "Razi", "Hasan"},
                            {005, "Daniel", "Dawson"},
                            {006, "Camila", "Flores"}};

        return data;
    }

}
