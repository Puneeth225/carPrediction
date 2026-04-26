import java.util.*;

class RecommendService {

    List<Car> cars = Arrays.asList(
            new Car("Hyundai i20", 8, "petrol", 20, 4),
            new Car("Tata Nexon", 10, "petrol", 17, 5),
            new Car("Maruti Swift", 7, "petrol", 22, 3),
            new Car("Honda City", 12, "petrol", 18, 4),

            new Car("Tata Punch", 6, "petrol", 20, 4),
            new Car("Maruti Baleno", 8, "petrol", 22, 3),
            new Car("Hyundai Creta", 11, "petrol", 17, 4),
            new Car("Kia Seltos", 12, "diesel", 18, 4),

            new Car("Mahindra XUV300", 10, "diesel", 20, 5),
            new Car("Mahindra Thar", 15, "diesel", 15, 4),
            new Car("Toyota Fortuner", 35, "diesel", 12, 5),

            new Car("Maruti WagonR", 6, "petrol", 24, 2),
            new Car("Tata Altroz", 8, "petrol", 19, 5),
            new Car("Honda Amaze", 9, "diesel", 24, 4),

            new Car("Skoda Slavia", 13, "petrol", 19, 5),
            new Car("Volkswagen Virtus", 14, "petrol", 18, 5),

            new Car("Hyundai Venue", 9, "petrol", 18, 4),
            new Car("Kia Sonet", 10, "diesel", 19, 4),

            new Car("Mahindra Scorpio", 18, "diesel", 15, 4),
            new Car("Toyota Innova Crysta", 20, "diesel", 14, 5)
    );

    public List<String> recommend(int budget, String fuel, String priority) {
        List<Map.Entry<Car, Integer>> scored = new ArrayList<>();

        for (Car car : cars) {
            int score = 0;

            if (car.price <= budget) score += 3;
            if (car.fuel.equalsIgnoreCase(fuel)) score += 2;

            if (priority.equals("mileage"))
                score += car.mileage;
            else if (priority.equals("safety"))
                score += car.safety * 2;

            scored.add(new AbstractMap.SimpleEntry<>(car, score));
        }

        scored.sort((a, b) -> b.getValue() - a.getValue());

        List<String> result = new ArrayList<>();
        for (int i = 0; i < Math.min(3, scored.size()); i++) {
            Car car = scored.get(i).getKey();
            result.add("{\"name\":\"" + car.name + "\",\"reason\":\"Fits your needs\"}");
        }

        return result;
    }
}