import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class menu {

        private List<people> people = new ArrayList<>();
        private DoubleOrderedList<Relationships> relations = new DoubleOrderedList<>();

        public static void main(String[] args) {
            menu menu = new menu();
            menu.mainMenu();
        }

        public void mainMenu() {
            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                System.out.println("Menu");
                System.out.println("1. Cargar Archivo");
                System.out.println("2. Cargar Relaciones");
                System.out.println("3. Imprimir Personas");
                System.out.println("4. Imprimir Relaciones");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        loadPeopleFromFile();
                        break;
                    case 2:
                        loadRelationships();
                        break;
                    case 3:
                        printPeople();
                        break;
                    case 4:
                        printRelationships();
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 5);

            scanner.close();
        }

        // Step 2: Load People from File
        private void loadPeopleFromFile() {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter filename: ");
            String filename = scanner.nextLine();

            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(","); // Assuming comma as the delimiter
                    if (data.length == 11) { // Ensure the line has the correct number of parameters
                        String id = data[0];
                        String name = data[1];
                        String surname = data[2];
                        String birthDate = data[3];
                        String gender = data[4];
                        String birthplace = data[5];
                        String hometown = data[6];
                        String studiedAt = data[7];
                        String workedAt = data[8];
                        String movies = data[9];
                        String groupCode = data[10];

                        // Create a new Person object and add it to the list
                        people personita = new people(id, name, surname, birthDate, gender, birthplace, hometown, studiedAt, workedAt, movies, groupCode);
                        people.add(personita);
                    } else {
                        System.out.println("Invalid data format in line: " + line);
                    }
                }
                System.out.println("People loaded successfully from " + filename);
            } catch (IOException e) {
                System.out.println("An error occurred while reading the file: " + e.getMessage());
            }
        }

        // Step 3: Print People
        private void printPeople() {
            if (people.isEmpty()) {
                System.out.println("No people in the network.");
            } else {
                System.out.println("People in the network:");
                for (people person : people) {
                    System.out.println(person);
                }
            }
        }

        private void loadRelationships(){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter filename: ");
            String filename = scanner.nextLine();
            String persona_orig;
            String persona_amiga;


            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(","); // Assuming comma as the delimiter
                    if (data.length == 2) { // Ensure the line has the correct number of parameters
                        persona_orig = data[0];
                        persona_amiga = data[1];
                        relations.add(new Relationships(persona_orig, persona_amiga));
                    } else {
                        System.out.println("Invalid data format in line: " + line);
                    }
                }
                System.out.println("People loaded successfully from " + filename);
            } catch (IOException e) {
                System.out.println("An error occurred while reading the file: " + e.getMessage());
            }
        }

        private void printRelationships(){
            if (relations.isEmpty()) {
                System.out.println("No people in the network.");
            } else {
                System.out.println("People in the network:");
                for (Relationships relationships : relations) {
                    System.out.println(relationships.getFriend_orig()+", "+relationships.getFriend_dest());
                }
            }
        }
        private void SearchBornPlace(String City){
            for(int i=0; i<people.size(); i++){
                if(people.get(i).getBirthplace().equals(City)){
                    System.out.println(people.get(i).getIdentifier()+people.get(i).getSurname());
                }
            }
        }
        private void SerchFriends(String Surname){
            for (int i=0; i<people.size(); i++){}


        }
        private void residential() {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter filename: ");
            String nombre = scanner.nextLine();
            ArrayList<people> residential = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(nombre))) {
                String line;
                while ((line = br.readLine()) != null) {
                    for(int i=0;i<people.size();i++){
                        if(people.get(i).getIdentifier().equals(line)){
                            String City = people.get(i).getHometown();
                            for(int j=0; j<people.size();j++){
                                if(people.get(j).getBirthplace().equals(City)){
                                    System.out.println(people.get(j).getName()+
                                            people.get(j).getSurname()+
                                            people.get(j).getBirthplace()+
                                            people.get(j).getHometown());

                                }

                            }
                        }
                    }
                }

        } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        private ArrayList<people> MoviesCategory() {
            ArrayList<String[]> movies = new ArrayList<>();
            for(int i=0; i<people.size(); i++) {
                String pelis = people.get(i).getMovies();
                String[] pelisA = pelis.split(",");
                if (movies.contains(pelisA) == false) {
                    movies.add(pelisA);
                    Arrays.stream(movies.get(i)).sorted();
                }
            }





        for(int i=0; i<people.size(); i++){
                String[] peliActual= people.get(i).getMovies().split(",");
                Arrays.sort(peliActual);
                int index= movies.indexOf(peliActual);
                if(movies(peliActual)){


                }

            }

        }
    private void printSurnameFriends(String surname){
        for (Relationships relationships : relations) {
            if(relationships.getFriend_orig().equals(surname)){
                System.out.println(relationships.toString());
            }
        }
    }

    }


