import java.util.*;

class EmployeeWorkHours {
    int employeeId;
    double[] hoursWorkedPerDay;

    public EmployeeWorkHours(int employeeId, double[] hoursWorkedPerDay) {
        this.employeeId = employeeId;
        this.hoursWorkedPerDay = hoursWorkedPerDay;
    }

    public double getTotalHoursWorked() {
        double totalHours = 0;
        for (double hours : hoursWorkedPerDay) {
            totalHours += hours;
        }
        return totalHours;
    }
}

public class employeeWorkAnalyzer {

    public static Map<String, Integer> countEmployeesByWorkHours(List<EmployeeWorkHours> workHoursList) {
        Map<String, Integer> countByWorkHours = new HashMap<>();
        for (EmployeeWorkHours workHours : workHoursList) {
            double totalHours = workHours.getTotalHoursWorked();
            String hoursRange = getHoursRange(totalHours);
            countByWorkHours.put(hoursRange, countByWorkHours.getOrDefault(hoursRange, 0) + 1);
        }
        return countByWorkHours;
    }

    public static Map<String, Double> calculateAverageHoursPerDayByWorkHours(List<EmployeeWorkHours> workHoursList) {
        Map<String, Double> avgHoursPerDayByWorkHours = new HashMap<>();
        Map<String, Double> totalHoursByWorkHours = new HashMap<>();
        Map<String, Integer> countByWorkHours = new HashMap<>();

        for (EmployeeWorkHours workHours : workHoursList) {
            double totalHours = workHours.getTotalHoursWorked();
            String hoursRange = getHoursRange(totalHours);

            double totalHoursForRange = totalHoursByWorkHours.getOrDefault(hoursRange, 0.0);
            int countForRange = countByWorkHours.getOrDefault(hoursRange, 0);

            for (double hours : workHours.hoursWorkedPerDay) {
                totalHoursForRange += hours;
            }

            totalHoursByWorkHours.put(hoursRange, totalHoursForRange);
            countByWorkHours.put(hoursRange, countForRange + 1);
        }

        for (Map.Entry<String, Integer> entry : countByWorkHours.entrySet()) {
            String hoursRange = entry.getKey();
            double totalHoursForRange = totalHoursByWorkHours.get(hoursRange);
            int countForRange = entry.getValue();
            double avgHoursPerDay = totalHoursForRange / (countForRange * 7); // Assuming 7 days in a week
            avgHoursPerDayByWorkHours.put(hoursRange, avgHoursPerDay);
        }

        return avgHoursPerDayByWorkHours;
    }

    public static String getHoursRange(double totalHours) {
        if (totalHours > 40) {
            return "More than 40 hours";
        } else if (totalHours == 40) {
            return "Exactly 40 hours";
        } else {
            return "Less than 40 hours";
        }
    }

    public static void main(String[] args) {
        // Sample employee work hours
        List<EmployeeWorkHours> workHoursList = new ArrayList<>();
        workHoursList.add(new EmployeeWorkHours(1, new double[]{8, 8, 8, 8, 8})); // Total 40 hours
        workHoursList.add(new EmployeeWorkHours(2, new double[]{7, 7, 7, 7, 7})); // Total 35 hours
        workHoursList.add(new EmployeeWorkHours(3, new double[]{9, 9, 8, 8, 8})); // Total 42 hours

        // Count employees by work hours
        Map<String, Integer> employeesByWorkHours = countEmployeesByWorkHours(workHoursList);

        // Calculate average hours per day by work hours
        Map<String, Double> avgHoursPerDayByWorkHours = calculateAverageHoursPerDayByWorkHours(workHoursList);

        // Display results
        System.out.println("Number of employees by work hours:");
        for (Map.Entry<String, Integer> entry : employeesByWorkHours.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " employees");
        }

        System.out.println("\nAverage hours per day by work hours:");
        for (Map.Entry<String, Double> entry : avgHoursPerDayByWorkHours.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " hours");
        }
    }
}
 97 changes: 97 additions & 0 deletions97  
housingAnalyzer.java
@@ -0,0 +1,97 @@
import java.util.*;

class HousingSale {
    double price;
    double squareFootage;

    public HousingSale(double price, double squareFootage) {
        this.price = price;
        this.squareFootage = squareFootage;
    }

    public double getPrice() {
        return price;
    }

    public double getSquareFootage() {
        return squareFootage;
    }
}

public class housingAnalyzer {

    public static Map<String, Integer> countHousesByPriceRange(List<HousingSale> sales) {
        Map<String, Integer> countByPriceRange = new HashMap<>();
        for (HousingSale sale : sales) {
            String priceRange = getPriceRange(sale.price);
            countByPriceRange.put(priceRange, countByPriceRange.getOrDefault(priceRange, 0) + 1);
        }
        return countByPriceRange;
    }

    public static Map<String, Double> calculateAverageSquareFootageByPriceRange(List<HousingSale> sales) {
        Map<String, Double> avgSquareFootageByPriceRange = new HashMap<>();
        Map<String, Double> totalSquareFootageByPriceRange = new HashMap<>();
        Map<String, Integer> countByPriceRange = new HashMap<>();

        for (HousingSale sale : sales) {
            String priceRange = getPriceRange(sale.price);
            double squareFootage = sale.getSquareFootage();

            double totalSquareFootage = totalSquareFootageByPriceRange.getOrDefault(priceRange, 0.0);
            int count = countByPriceRange.getOrDefault(priceRange, 0);

            totalSquareFootageByPriceRange.put(priceRange, totalSquareFootage + squareFootage);
            countByPriceRange.put(priceRange, count + 1);
        }

        for (Map.Entry<String, Integer> entry : countByPriceRange.entrySet()) {
            String priceRange = entry.getKey();
            int count = entry.getValue();
            double totalSquareFootage = totalSquareFootageByPriceRange.get(priceRange);
            double avgSquareFootage = totalSquareFootage / count;
            avgSquareFootageByPriceRange.put(priceRange, avgSquareFootage);
        }

        return avgSquareFootageByPriceRange;
    }

    public static String getPriceRange(double price) {
        if (price < 100000) {
            return "<$100,000";
        } else if (price < 200000) {
            return "$100,000-$200,000";
        } else if (price < 300000) {
            return "$200,000-$300,000";
        } else {
            return ">$300,000";
        }
    }

    public static void main(String[] args) {
        // Sample housing sales data
        List<HousingSale> sales = new ArrayList<>();
        sales.add(new HousingSale(90000, 1200));
        sales.add(new HousingSale(150000, 1500));
        sales.add(new HousingSale(250000, 1800));
        sales.add(new HousingSale(320000, 2000));
        sales.add(new HousingSale(180000, 1600));

        // Count houses by price range
        Map<String, Integer> housesByPriceRange = countHousesByPriceRange(sales);

        // Calculate average square footage by price range
        Map<String, Double> avgSquareFootageByPriceRange = calculateAverageSquareFootageByPriceRange(sales);

        // Display results
        System.out.println("Number of houses sold by price range:");
        for (Map.Entry<String, Integer> entry : housesByPriceRange.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " houses");
        }

        System.out.println("\nAverage square footage for each price range:");
        for (Map.Entry<String, Double> entry : avgSquareFootageByPriceRange.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " square feet");
        }
    }
}
 88 changes: 88 additions & 0 deletions88  
medicalTestAnalyzer.java
@@ -0,0 +1,88 @@
import java.util.*;

class MedicalTestResult {
    double resultValue;

    public MedicalTestResult(double resultValue) {
        this.resultValue = resultValue;
    }

    public double getResultValue() {
        return resultValue;
    }
}

public class medicalTestAnalyzer {

    public static Map<String, Integer> countPatientsByResultRange(List<MedicalTestResult> resultsList) {
        Map<String, Integer> countByResultRange = new HashMap<>();
        for (MedicalTestResult result : resultsList) {
            String resultRange = getResultRange(result.resultValue);
            countByResultRange.put(resultRange, countByResultRange.getOrDefault(resultRange, 0) + 1);
        }
        return countByResultRange;
    }

    public static Map<String, Double> calculateAverageValueByResultRange(List<MedicalTestResult> resultsList) {
        Map<String, Double> avgValueByResultRange = new HashMap<>();
        Map<String, Double> totalValueByResultRange = new HashMap<>();
        Map<String, Integer> countByResultRange = new HashMap<>();

        for (MedicalTestResult result : resultsList) {
            String resultRange = getResultRange(result.resultValue);
            double currentValue = result.getResultValue();

            double totalValue = totalValueByResultRange.getOrDefault(resultRange, 0.0);
            int count = countByResultRange.getOrDefault(resultRange, 0);

            totalValueByResultRange.put(resultRange, totalValue + currentValue);
            countByResultRange.put(resultRange, count + 1);
        }

        for (Map.Entry<String, Integer> entry : countByResultRange.entrySet()) {
            String resultRange = entry.getKey();
            int count = entry.getValue();
            double totalValue = totalValueByResultRange.get(resultRange);
            avgValueByResultRange.put(resultRange, totalValue / count);
        }

        return avgValueByResultRange;
    }

    public static String getResultRange(double resultValue) {
        if (resultValue < 10) {
            return "Normal";
        } else if (resultValue < 20) {
            return "Borderline";
        } else {
            return "High";
        }
    }

    public static void main(String[] args) {
        // Sample medical test results
        List<MedicalTestResult> resultsList = new ArrayList<>();
        resultsList.add(new MedicalTestResult(5));
        resultsList.add(new MedicalTestResult(15));
        resultsList.add(new MedicalTestResult(25));
        resultsList.add(new MedicalTestResult(8));
        resultsList.add(new MedicalTestResult(18));

        // Count patients by result range
        Map<String, Integer> patientsByResultRange = countPatientsByResultRange(resultsList);

        // Calculate average value by result range
        Map<String, Double> avgValueByResultRange = calculateAverageValueByResultRange(resultsList);

        // Display results
        System.out.println("Number of patients with results within each range:");
        for (Map.Entry<String, Integer> entry : patientsByResultRange.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " patients");
        }

        System.out.println("\nAverage value for each result range:");
        for (Map.Entry<String, Double> entry : avgValueByResultRange.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
 74 changes: 74 additions & 0 deletions74  
salesAnalyzer.java
@@ -0,0 +1,74 @@
import java.util.*;

class ProductSale {
    double price;
    int quantity;

    public ProductSale(double price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    public double getTotalRevenue() {
        return price * quantity;
    }
}

public class salesAnalyzer {

    public static Map<String, Integer> countProductsByPriceRange(List<ProductSale> sales) {
        Map<String, Integer> countByPriceRange = new HashMap<>();
        for (ProductSale sale : sales) {
            String priceRange = getPriceRange(sale.price);
            countByPriceRange.put(priceRange, countByPriceRange.getOrDefault(priceRange, 0) + sale.quantity);
        }
        return countByPriceRange;
    }

    public static Map<String, Double> calculateRevenueByPriceRange(List<ProductSale> sales) {
        Map<String, Double> revenueByPriceRange = new HashMap<>();
        for (ProductSale sale : sales) {
            String priceRange = getPriceRange(sale.price);
            revenueByPriceRange.put(priceRange, revenueByPriceRange.getOrDefault(priceRange, 0.0) + sale.getTotalRevenue());
        }
        return revenueByPriceRange;
    }

    public static String getPriceRange(double price) {
        if (price < 50) {
            return "$0-50";
        } else if (price < 100) {
            return "$50-100";
        } else if (price < 200) {
            return "$100-200";
        } else {
            return "Above $200";
        }
    }

    public static void main(String[] args) {
        // Sample product sales
        List<ProductSale> sales = new ArrayList<>();
        sales.add(new ProductSale(25, 3));
        sales.add(new ProductSale(75, 2));
        sales.add(new ProductSale(150, 5));
        sales.add(new ProductSale(210, 1));

        // Count products by price range
        Map<String, Integer> productCounts = countProductsByPriceRange(sales);

        // Calculate revenue by price range
        Map<String, Double> revenueByPriceRange = calculateRevenueByPriceRange(sales);

        // Display results
        System.out.println("Products sold by price range:");
        for (Map.Entry<String, Integer> entry : productCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " products");
        }

        System.out.println("\nRevenue generated by price range:");
        for (Map.Entry<String, Double> entry : revenueByPriceRange.entrySet()) {
            System.out.println(entry.getKey() + ": $" + entry.getValue());
        }
    }
}
 84 changes: 84 additions & 0 deletions84  
weatherAnalyzer.java
@@ -0,0 +1,84 @@
import java.util.*;

class WeatherData {
    double temperature;
    double humidity;

    public WeatherData(double temperature, double humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }
}

public class weatherAnalyzer {

    public static Map<String, Integer> countDaysByTemperatureRange(List<WeatherData> weatherDataList) {
        Map<String, Integer> countByTemperatureRange = new HashMap<>();
        for (WeatherData data : weatherDataList) {
            String temperatureRange = getTemperatureRange(data.temperature);
            countByTemperatureRange.put(temperatureRange, countByTemperatureRange.getOrDefault(temperatureRange, 0) + 1);
        }
        return countByTemperatureRange;
    }

    public static Map<String, Double> calculateAverageHumidityByTemperatureRange(List<WeatherData> weatherDataList) {
        Map<String, Double> avgHumidityByTemperatureRange = new HashMap<>();
        Map<String, Integer> countByTemperatureRange = new HashMap<>();
        for (WeatherData data : weatherDataList) {
            String temperatureRange = getTemperatureRange(data.temperature);
            double currentHumidity = data.getHumidity();
            double totalHumidity = avgHumidityByTemperatureRange.getOrDefault(temperatureRange, 0.0) * countByTemperatureRange.getOrDefault(temperatureRange, 0);
            totalHumidity += currentHumidity;
            countByTemperatureRange.put(temperatureRange, countByTemperatureRange.getOrDefault(temperatureRange, 0) + 1);
            avgHumidityByTemperatureRange.put(temperatureRange, totalHumidity / countByTemperatureRange.get(temperatureRange));
        }
        return avgHumidityByTemperatureRange;
    }

    public static String getTemperatureRange(double temperature) {
        if (temperature < 0) {
            return "<0°C";
        } else if (temperature < 10) {
            return "0-10°C";
        } else if (temperature < 20) {
            return "10-20°C";
        } else {
            return ">20°C";
        }
    }

    public static void main(String[] args) {
        // Sample weather data
        List<WeatherData> weatherDataList = new ArrayList<>();
        weatherDataList.add(new WeatherData(-5, 80));
        weatherDataList.add(new WeatherData(5, 70));
        weatherDataList.add(new WeatherData(15, 65));
        weatherDataList.add(new WeatherData(25, 75));
        weatherDataList.add(new WeatherData(18, 60));

        // Count days by temperature range
        Map<String, Integer> daysByTemperatureRange = countDaysByTemperatureRange(weatherDataList);

        // Calculate average humidity by temperature range
        Map<String, Double> avgHumidityByTemperatureRange = calculateAverageHumidityByTemperatureRange(weatherDataList);

        // Display results
        System.out.println("Days with temperatures within each range:");
        for (Map.Entry<String, Integer> entry : daysByTemperatureRange.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " days");
        }

        System.out.println("\nAverage humidity for each temperature range:");
        for (Map.Entry<String, Double> entry : avgHumidityByTemperatureRange.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "%");
        }
    }
}
0 comments on commit 4f8e0e0
@ShaikShafiMohammad
Comment
 
Leave a comment
 
 You’re not receiving notifications from this thread.
Footer
