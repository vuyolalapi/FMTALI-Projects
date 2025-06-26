import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class BMI{
    public static void displayCategory(double bmi){
        if(bmi < 16.0){
            System.out.println("Severely Underweight");
        }
        else if(bmi >= 16.0 && bmi <=18.4){
            System.out.println("Underweight");
        }
        else if(bmi >= 18.5 && bmi <=24.9){
            System.out.println("Normal");
        }
        else if(bmi >= 25.0 && bmi <=29.9){
            System.out.println("Overweight");
        }
        else if(bmi >= 30.0 && bmi <= 34.9){
            System.out.println("Moderately Obese");
        }
        else if(bmi >= 35.0){
            System.out.println("severely Obese");
        }
    }
        public static void main(String [] args){
            Scanner scanner = new Scanner(System.in);
            scanner.useLocale(Locale.US);
            
            char repeat = 0;
           
            UserProgressTracker tracker = new UserProgressTracker();

            //Do-While loop

            do { 
              
             int unitChoice = getUnitChoice(scanner);
             double weight = (unitChoice == 1) ? getValidInput(scanner, "Enter your weight in kilograms: ", 10,600)
             : getValidInput(scanner, "Enter your weight in pounds", 22,1300);

             double height = (unitChoice == 1) ? getValidInput(scanner, "Enter your height in meters: ", 0.5,2.5)
             : getValidInput(scanner, "Enter your height in inches", 20,100);

             double bmi = calculateBMI(unitChoice, weight, height);
             System.out.println("Your BMI is " + bmi);
             displayCategory(bmi);

              UserProgress progress = new UserProgress(weight, height, bmi, LocalDate.now());
             
             tracker.addProgress(progress);

                //Asks the user if they want to repeat the process

             repeat = askToRepeat(scanner);
          
             if(repeat == 'N' || repeat == 'n'){
                if(bmi >= 18.5 && bmi <=24.9){
                    System.out.println("Thank you. Stay healthy!");
                }else{
                     System.out.println(" \n                              ====================================Here are tips to maintain your weight ================================= \n \n1. Exercise regularly - Aim for at least 150 minutes of moderate aerobic activity a week—walking, dancing, cycling, or whatever makes you feel good and energized. Add strength training a couple of times weekly for an extra metabolism boost.\n \n2. Eat more food with healthy fats - Focus on whole, nutrient-rich foods like vegetables, fruits, lean proteins, and whole grains. Pay attention to portion sizes and eat slowly enough to notice when you're full. \n \n3. Stay hydrated - Sometimes thirst shows up as hunger. Drinking enough water keeps your system running efficiently and your hunger cues honest. \n");
              
                    System.out.println("\nYour BMI Progress: \n");
                    tracker.viewProgress();

                     break;
                } 
             }
            } 
            while (repeat == 'Y' || repeat == 'y');
        }

        public static double bmi(int unitChoice, double weight, double height){

            double bmi;
            if(unitChoice == 1){
                bmi = (weight/(height*height));
            } else{
                bmi = (weight/(height*height))*703;       
          }
                return bmi;
        }

        public static char askToRepeat(Scanner scanner){
            
                System.out.print("Do you want to run the program again? ");
                char userInput = scanner.next().charAt(0);

            while (true) { 
                if(userInput == 'Y' || userInput == 'y' || userInput == 'N' || userInput == 'n'){
                    break;
                }
                else{
                    System.out.println("Invalid input. Please enter Y/y for YES, or N/n for NO:                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              ");
                }
            }
            return userInput;
        }

        public static int getUnitChoice(Scanner scanner){
            int choice;

            while(true){
                System.out.println("""
                                   Select a prefferd unit: 
                                    1. Metric (kg, m)
                                    2. Imperial (lb, in)
                                   Please select beither option 1 or option 2""");

                if(scanner.hasNextInt()){
                    choice = scanner.nextInt();
                    if(choice ==1 || choice == 2){
                        break;
                    }
                    else {
                        System.out.println("Invalid choice. Please enter either 1 or 2");
                    }
                }
                else{
                        System.out.println("Invalid choice. Please enter a number (1 or 2)");
                    }
                }
                return choice;
            } 
            public static double getValidInput(Scanner scanner, String prompt, double min, double max){
                    double  value;

                    while(true){ 
                        System.out.println(prompt);

                        if(scanner.hasNextDouble()){
                            value = scanner.nextDouble();
                            if(value >= min && value <= max){
                                break;
                            }else{
                                System.out.printf("Please enter a value between %.1f and %1f. \n", min, max);
                            }
                         } else{
                                System.out.println("Invalid input. Please enter a value");
                                scanner.next();
                            }
                        }
                    
                    return value;
            }
            public static double calculateBMI(int unitChoice,double weight,double height){
               double totalBMI;

               if(unitChoice ==1){
                totalBMI = weight/(height*height);
               }else{
                totalBMI =(703*weight)/(height*height);
               }

               return totalBMI;
            }
        }
    class UserProgress {
    private double weight;
    private double height;
    private double bmi;
    private LocalDate date;

    public UserProgress(double weight, double height, double bmi, LocalDate date) {
        this.weight = weight;
        this.height = height;
        this.bmi = bmi;
        this.date = LocalDate.now();
    }

    // Getters and setters
    public double getWeight() { return weight; }

    public double getHeight() { return height; }
    public double getBmi() { return bmi; }
    public LocalDate getDate() { return date; }
}
  class UserProgressTracker {
    private final List<UserProgress> progressList;

    public UserProgressTracker() {
        this.progressList = new ArrayList<>();
    }

    public void addProgress(UserProgress up) {
        UserProgress progress = new UserProgress(up.getWeight(), up.getHeight(), up.getBmi(), LocalDate.now());
        progressList.add(progress);
    }

    public void viewProgress() {
        for (UserProgress progress : progressList) {
            System.out.println("Date: " + progress.getDate());
            System.out.println("Weight: " + progress.getWeight() + " kg");
            System.out.println("Height: " + progress.getHeight() + " m");
            System.out.println("BMI: " + progress.getBmi());
            System.out.println();
        }
    }
} 

