public class MessageRandomizer {
    private static String[] nouns = {"Apple", "Banana", "Cucumber", "Dragon Fruit", "Elephant Garlic", "Grape"};
    private static String[] adjectives = {"Yellow", "Sweet", "Rotten", "Fresh", "Gross", "Juicy", "Green", "Ripe", "Red"};

    private static String[] firstNames = {"John", "Beverly", "Claire", "Ryon", "Jim", "Pam", "Natalie", "Simon", "Jane"};
    private static String[] lastNames = {"Doe", "Smith", "Black", "White", "Davis", "Walker", "Lee", "McDonald", "Meyer", "Hall"};

    private static String randomItem(String list[]){
        return list[(int) (Math.random() * list.length)];
    }

    public static String createMessage(){
        return randomItem(adjectives) + " " + randomItem(nouns);
    }

    public static String createSender() {
        return randomItem(firstNames) + " " + randomItem(lastNames);
    }
}