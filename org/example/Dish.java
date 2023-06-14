//commit 2
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dish {
    private int restro_id;
    private List<String> dishes;

    public Dish(int restro_id, List<String> dishes) {
        this.restro_id = restro_id;
        this.dishes = dishes;
    }

    public int getRestroId() {
        return restro_id;
    }

    public List<String> getDishes() {
        return dishes;
    }

    public void setRestroId(int restro_id) {
        this.restro_id = restro_id;
    }

    public void setDishes(List<String> dishes) {
        this.dishes = dishes;
    }

    public static void main(String[] args) throws IOException {
        Map<Integer, Dish> dishMap = new HashMap<>();

        BufferedReader dishReader = Files.newBufferedReader(Paths.get("C:\\Users\\ADITYA\\IdeaProjects\\Sharda_Swiggy\\Sharda_Swiggy\\data\\Dish.csv"),
                StandardCharsets.UTF_8
        );

        String line;
        while ((line = dishReader.readLine()) != null) {
            String[] partsOfLine = line.split(",");

            if (partsOfLine.length >= 2) {
                int restro_id = Integer.parseInt(partsOfLine[0].trim());
                String dish = partsOfLine[1].trim();

                if (dishMap.containsKey(restro_id)) {
                    Dish existingDish = dishMap.get(restro_id);
                    existingDish.getDishes().add(dish);
                } else {
                    List<String> dishes = new ArrayList<>();
                    dishes.add(dish);
                    Dish newDish = new Dish(restro_id, dishes);
                    dishMap.put(restro_id, newDish);
                }
            }
        }

        dishReader.close();


        int targetRestroId = 1;
        Dish targetDish = dishMap.get(targetRestroId);
        if (targetDish != null) {
            System.out.println("Restaurant ID: " + targetDish.getRestroId());
            System.out.println("Dishes:");
            for (String dish : targetDish.getDishes()) {
                System.out.println(dish);
            }
        } else {
            System.out.println("Restaurant ID not found.");
        }
    }
}

