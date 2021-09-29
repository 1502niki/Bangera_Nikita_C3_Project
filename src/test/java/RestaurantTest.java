import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE
    @BeforeEach
    public void createRestaurant(){
        LocalTime openingTime = LocalTime.parse("11:00:00");
        LocalTime closingTime = LocalTime.parse("23:00:00");
        restaurant = new Restaurant("Kvell Foods","Bangalore",openingTime, closingTime);
        restaurant.addToMenu("South India Thali",250);
        restaurant.addToMenu("North India Thali", 350);
    }


    //<<<<<<<<<<<<<<<<<<<<<<<<<ORDER VALUE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void total_order_value_should_increase_when_an_item_is_selected() {

        List<String> selectedItems = new ArrayList<String>();
        selectedItems.add("South India Thali");
        selectedItems.add("North India Thali");
        assertEquals(600, restaurant.getOrderTotal(selectedItems));
    }

    @Test
    public void total_order_value_should_decrease_when_an_item_is_removed(){
        List<String> selectedItems = new ArrayList<String>();
        selectedItems.add("South India Thali");
        selectedItems.add("North India Thali");
        selectedItems.remove("North India Thali");
        assertEquals(250,restaurant.getOrderTotal(selectedItems));

    }
    @Test
    public void total_order_value_should_be_zero_when_cart_is_empty(){
        List<String> selectedItems = new ArrayList<String>();
        assertEquals(0,restaurant.getOrderTotal(selectedItems));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<ORDER VALUE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        Restaurant testRestaurant = Mockito.spy(restaurant);
        LocalTime time = LocalTime.parse("12:00:00");
        Mockito.when(testRestaurant.getCurrentTime()).thenReturn(time);
        assertTrue(testRestaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE

        Restaurant testRestaurant = Mockito.spy(restaurant);
        LocalTime time = LocalTime.parse("23:30:00");
        Mockito.when(testRestaurant.getCurrentTime()).thenReturn(time);
        assertFalse(testRestaurant.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("North India Thali");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}