package com.example;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Scanner;

public class App 
{
    private static ArrayList<Article> list;
    public static void main( String[] args ) throws Exception
    {

        Scanner scan = new Scanner(System.in);
        System.out.println( "Welcome user! This program fetches the latest news data from your country of choice." ); // CHANGE THIS 
        displayChoices(); // displays the Primary Menu choices
        System.out.print("Which action will you take: ");
        int menuChoice = scan.nextInt();
        scan.nextLine();
        
        // Run the program while the user has not chosen to exit out entirely
        while (menuChoice != 5 ) { 

            while (menuChoice >= 1 && menuChoice <= 5) {

        

            if (menuChoice == 1) { // search headlines
                int choice = getMenu1Choice(scan);

                // Run the "search headlines" feature while the user has not chosen to exit out
                while (choice != 3) {

                    while (choice <= 3 && choice >= 1) {
                        if (choice == 1) { // search headlines
                        System.out.println();
                        System.out.println("--------------------");
                        printList(); // print the available countries for the user to search
                        System.out.print("Choose your country from the list above: ");
                        String country = scan.nextLine();
                        String code = convert(country); // convert the country to a code to check for valid input
                    
                        // Repeatedly prompt the user to enter a valid input
                        while (code.equals("na")) {
                            System.out.print("Please enter a valid country: ");
                            country = scan.nextLine();
                            code = convert(country);
                        }

                        System.out.println(country); // TESTING

                        // Compile data for the API
                        String apiKey = "04b5c7b8078642e8bf2db42f958fdd0c";
                        String data = API.getHeadlines(country, apiKey);
                        list = JSON.getArticles(data);

                        // Prompt user after articles have been displayed
                        displayMenu1Choices();
                        System.out.print("Which action will you take: ");
                        choice = scan.nextInt();
                        scan.nextLine();
                    }

                    else if (choice == 2) { // add a headline to favorites
                        System.out.println();
                        System.out.print("Headline to save: #");
                        //int headline = scan.nextInt();

                        while (true) {
                            int headline = scan.nextInt();

                            if (headline >= 1 && headline <= 5) {
                                System.out.println(list); // TESTING
                                System.out.println(list.get(headline - 1)); // TESTING
                                list.get(headline-1).save();
                                break;
                            }

                            else {
                                System.out.println("Please enter a valid input.");
                                System.out.print("Headline to save: #");
                            } 
                        }
                    }
                    else if (choice == 3) {break;} // exit this option

                    }
                    
                    

                    
                    
                        System.out.print("Enter a valid choice: ");
                        choice = scan.nextInt();
                        scan.nextLine();
                    }
                    choice = getMenu1Choice(scan);
                    
                }
           

            else if (menuChoice == 2) { // view favorited headlines
                System.out.println("\nYOUR FAVORITES");
                Article.load();
            }

            else if (menuChoice == 3) { // modify favorited headlines
                int choice = getMenu3Choice(scan);
                while (choice != 3) {

                    if (choice == 1) {
                        Article.load();
                        System.out.print("Enter article to delete: #");
                        int index = scan.nextInt();
                        scan.nextLine();

                        while (index > Article.getFavorites().size()) {
                            System.out.println("Invalid.");
                             System.out.print("Enter article to delete: #");
                             index = scan.nextInt();
                             scan.nextLine();
                        }

                        Article.delete(index);

                    }

                    else if (choice == 2) { // delete all headlines from favorites
                        Article.delete();
                    }

                    else if (choice == 3) {break;}
                
                

                


                    else {
                        System.out.print("Enter a valid choice: ");
                        choice = scan.nextInt();
                        scan.nextLine();
                    }

                    choice = getMenu3Choice(scan);
                }}

                

            

            else if (menuChoice == 4) {}

            }

            System.out.print("Enter a valid choice: ");
            menuChoice = scan.nextInt();
            scan.nextLine();

           

            menuChoice = getMenuChoice(scan);
        }

            System.out.println("Thank you for visiting!");

    }


    public static int getMenuChoice(Scanner scan) {
        int num = Integer.MIN_VALUE;
        System.out.println();
        System.out.println("--------------------");
        displayChoices();
        System.out.print("Which action will you take: ");
        num = scan.nextInt();
        scan.nextLine();
        return num;
    }

    /*
     * 
     * 
    while (num < 1 || num > 3) {
        System.out.println();
        System.out.println("--------------------");
        displayMenu1Choices();
        System.out.print("Which action will you take: ");

        if (scan.hasNextInt()) {
            num = scan.nextInt();
            scan.nextLine();

            if (num < 1 || num > 3) {
                System.out.println("Please enter a valid response (1–3).");
            }
        } else {
            System.out.println("Please enter a number.");
            scan.nextLine();
        }
    }
     */

    public static int getMenu1Choice(Scanner scan) {
        System.out.println();
        System.out.println("--------------------");
        displayMenu1Choices();
        System.out.print("Which action will you take: ");
        int num = scan.nextInt();
        scan.nextLine();
        return num;
    }

    public static int getMenu3Choice(Scanner scan) {
        System.out.println();
        System.out.println("--------------------");
        displayMenu3Choices();
        System.out.print("Which action will you take: ");
        int num = scan.nextInt();
        scan.nextLine();
        return num;
    }

    public static void displayChoices() {
        System.out.println("1. -> Search headlines for a country");
        System.out.println("2. -> View your favorited headlines");
        System.out.println("3. -> Modify your favorite headlines");
        System.out.println("4. -> Analyze sentiments in a given headline");
        System.out.println("5. -> Exit");
    }

    public static void displayMenu1Choices() {
        System.out.println("1. -> Search for headlines");
        System.out.println("2. -> Add a headline to your favorites");
        System.out.println("3. -> Return to menu");
    }

    public static void displayMenu3Choices() {
        System.out.println("1. -> Delete specific headline");
        System.out.println("2. -> Delete ALL headlines");
        System.out.println("3. -> Return to menu");
    }

        public static String convert(String str) {
        str = str.toLowerCase();
        switch(str) {
            case "united arab emirates":
                return "ae";
            case "argentina":
                return "ar";
            case "austria":
                return "at";
            case "australia": 
                return "au";
            case "belgium":
                return "be";
            case "bulgaria":
                return "bg";
            case "brazil":
                return "br";
            case "canada":
                return "ca";
            case "switzerland":
                return "ch";
            case "china":
                return "ch";
            case "colombia":
                return "co";
            case "cuba":
                return "cu";
            case "czech republic":
                return "cz";
            case "germany":
                return "de";
            case "egypt":
                return "eg";
            case "france":
                return "fr";
            case "united kingdom":
                return "gb";
            case "greece":
                return "gr";
            case "hong kong":
                return "hk";
            case "hungary":
                return "hu";
            case "indonesia":
                return "id";
            case "ireland":
                return "ie";
            case "israel":
                return "il";
            case "india":
                return "in";
            case "italy":
                return "it";
            case "japan":
                return "jp";
            case "south korea": 
                return "kr";
            case "lithuania":
                return "lt";
            case "latvia":
                return "lv";
            case "morocco":
                return "ma";
            case "mexico":
                return "mx";
            case "malaysia":
                return "my";
            case "nigeria":
                return "ng";
            case "netherlands":
                return "nl";
            case "norway":
                return "no";
            case "new zealand":
                return "nz";
            case "philippines":
                return "ph";
            case "poland":
                return "pl";
            case "portugal":
                return "pt";
            case "romania":
                return "ro";
            case "serbia":
                return "rs";
            case "russia":
                return "ru";
            case "saudi arabia":
                return "sa";
            case "sweden":
                return "se";
            case "singapore":
                return "sg";
            case "slovenia":
                return "si";
            case "slovakia":
                return "sk";
            case "thailand":
                return "th";
            case "turkey":
                return "tr";
            case "taiwan":
                return "tw";
            case "ukraine":
                return "ua";
            case "united states":
                return "us";
            case "venezuela":
                return "ve";
            case "south africa":
                return "za";
            default: 
                return "na";  
        }
    }

    public static void printList() {
        String[] countries = {
            "united arab emirates",
            "argentina",
            "austria",
            "australia",
            "belgium",
            "bulgaria",
            "brazil",
            "canada",
            "switzerland",
            "china",
            "colombia",
            "cuba",
            "czech republic",
            "germany",
            "egypt",
            "france",
            "united kingdom",
            "greece",
            "hong kong",
            "hungary",
            "indonesia",
            "ireland",
            "israel",
            "india",
            "italy",
            "japan",
            "south korea",
            "lithuania",
            "latvia",
            "morocco",
            "mexico",
            "malaysia",
            "nigeria",
            "netherlands",
            "norway",
            "new zealand",
            "philippines",
            "poland",
            "portugal",
            "romania",
            "serbia",
            "russia",
            "saudi arabia",
            "sweden",
            "singapore",
            "slovenia",
            "slovakia",
            "thailand",
            "turkey",
            "taiwan",
            "ukraine",
            "united states",
            "venezuela",
            "south africa"
        };

        for (String country : countries) {
            System.out.println("-> " + country);
        }
    }
}
