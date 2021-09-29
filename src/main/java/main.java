import java.time.LocalTime;

public class main {
    public static void main(String[] args){

        LocalTime openingTime = LocalTime.parse("11:00:00");
        LocalTime closingTime = LocalTime.parse("23:00:00");
        Restaurant restaurant = new Restaurant("Kvell Foods","Bangalore",openingTime,closingTime);
        restaurant.addToMenu("South India Thali",250);
        restaurant.addToMenu("North Indian Thali", 350);
    }
}
