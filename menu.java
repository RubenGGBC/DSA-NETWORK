import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class menu {

    private List<people> people = new ArrayList<>();
    private DoubleOrderedList<Relationships> relations = new DoubleOrderedList<>();
    private DoubleOrderedList<movie_lists> movies = new DoubleOrderedList<>();
    private static Map<String, List<String>> adjacencyList = new HashMap<>();

    public static void main(String[] args) {
        menu menu = new menu();
        menu.mainMenu();

    }

    public void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        String surname, city, d1, d2;
        int choice;

        do {
            System.out.println("Menu");
            System.out.println("1. Load file");
            System.out.println("2. Load relationships");
            System.out.println("3. Print people");
            System.out.println("4. Print relationships");
            System.out.println("6. Friends of people");
            System.out.println("7. People born in specific city");
            System.out.println("8. People born between dates");
            System.out.println("9. Residential");
            System.out.println("10. Build movie groups");
            System.out.println("11. BFS");
            System.out.println("12. DFS");
            System.out.println("13. 4 Cliques");
            System.out.println("14. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    loadPeopleNodes();
                    break;
                case 2:
                    loadRelationshipsNode();
                    break;
                case 3:
                    printPeople();
                    break;
                case 4:
                    printRelationships();
                    break;
                case 6:
                    System.out.println("Enter surname: ");
                    surname = scanner.nextLine();
                    retrieveFriends(surname);
                    break;
                case 7:
                    System.out.println("Enter city name: ");
                    city = scanner.nextLine();
                    searchBornPlace(city);
                    break;
                case 8:
                    System.out.println("Enter 'from' date (dd/MM/yyyy): ");
                    d1 = scanner.nextLine();
                    System.out.println("Enter 'to' date (dd/MM/yyyy): ");
                    d2 = scanner.nextLine();
                    if (d1.compareTo(d2) <= 0) {
                        bornBetweenDates(d1, d2);
                    } else {
                        System.err.println("Invalid date comparison!!!");
                    }
                    break;
                case 9:
                    residential();
                    break;
                case 10:
                    movieCategories();
                    break;
                case 11:
                    if (adjacencyList.isEmpty()) {
                        buildAdjacencyList();
                    } else {
                        System.out.print("Enter the identifier of the first person: ");
                        String person1 = scanner.nextLine().trim();
                        System.out.print("Enter the identifier of the second person: ");
                        String person2 = scanner.nextLine().trim();

                        if (!adjacencyList.containsKey(person1) || !adjacencyList.containsKey(person2)) {
                            System.out.println("One or both identifiers are invalid.");
                        } else {
                            List<String> shortestChain = findShortestChain(person1, person2);
                            if (shortestChain.isEmpty()) {
                                System.out.println("No connection found.");
                            } else {
                                System.out.println("Shortest chain: " + shortestChain);
                            }
                        }
                    }
                    break;

                case 12:
                    if (adjacencyList.isEmpty()) {
                        System.out.println("Relationships not loaded. Please load relationships first.");
                        buildAdjacencyList();
                    } else {
                        System.out.print("Enter the identifier of the first person: ");
                        String person1 = scanner.nextLine().trim();
                        System.out.print("Enter the identifier of the second person: ");
                        String person2 = scanner.nextLine().trim();

                        if (!adjacencyList.containsKey(person1) || !adjacencyList.containsKey(person2)) {
                            System.out.println("One or both identifiers are invalid.");
                        } else {
                            List<String> alternativeChain = findAlternativeChain( person1, person2);
                            if (alternativeChain.isEmpty()) {
                                System.out.println("No alternative connection found.");
                            } else {
                                System.out.println("Alternative chain: " + alternativeChain);
                            }
                        }
                    }
                    break;

                case 13:
                    if (adjacencyList.isEmpty()) {
                        buildAdjacencyList();
                        System.out.println("Relationships not loaded. Please load relationships first.");
                    } else {
                        List<Set<String>> cliques = findCliques();
                        if (cliques.isEmpty()) {
                            System.out.println("No cliques with more than 4 friends found.");
                        } else {
                            System.out.println("Cliques with more than 4 friends:");
                            for (Set<String> clique : cliques) {
                                System.out.println(clique);
                            }
                        }
                    }
                    break;
                case 14:
                    System.out.println("Exi1ting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 14);

        scanner.close();
    }

    // Step 2: Load People from File
    private void loadPeopleFromFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter filename: ");
        String filename = scanner.nextLine();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(","); // Assuming comma as the delimiter
                if (data.length == 11) { // Ensure the line has the correct number of parameters
                    String id = data[0];
                    String name = data[1];
                    String surname = data[2];
                    LocalDate birthDate = LocalDate.parse(data[3], formato);
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
    public  void loadPeopleNodes() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter filename: ");
        String filePath  = scanner.nextLine();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String person = line.trim();
                if (!adjacencyList.containsKey(person)) {
                    adjacencyList.put(person, new ArrayList<>());
                }
            }
            System.out.println("People loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
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

    private void loadRelationships() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter filename: ");
        String filename = scanner.nextLine();
        String persona_orig;
        String persona_amiga;


        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine();

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
    public void loadRelationshipsNode() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter filename: ");
        String filePath = scanner.nextLine();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String person1 = parts[0].trim();
                    String person2 = parts[1].trim();

                    // Asegurarse de que ambas personas existen en el grafo
                    adjacencyList.putIfAbsent(person1, new ArrayList<>());
                    adjacencyList.putIfAbsent(person2, new ArrayList<>());

                    // Crear relaciones bidireccionales
                    adjacencyList.get(person1).add(person2);
                    adjacencyList.get(person2).add(person1);
                }
            }
            System.out.println("Relationships loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }


    private void printRelationships() {
        if (relations.isEmpty()) {
            System.out.println("No people in the network.");
        } else {
            System.out.println("People in the network:");
            for (Relationships relationships : relations) {
                System.out.println(relationships.getFriend_orig() + ", " + relationships.getFriend_dest());
            }
        }
    }

    private void retrieveFriends(String pSurname) {
        people match;
        for (people person : people) {
            if (person.getSurname().equals(pSurname)) {
                match = person;
                System.out.println("-------------------------");
                System.out.println(match.getIdentifier() + "'s friends:");
                for (Relationships relationships : relations) {
                    System.out.println("-------------------------");
                    if (relationships.getFriend_orig().equals(match.getIdentifier())) {
                        System.out.println(relationships.getFriend_dest());
                    }
                }
                System.out.println("-------------------------");
            }
        }
    }

    private void searchBornPlace(String City) {
        System.out.println("People born in " + City);
        System.out.println("---------------------");
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getBirthplace().equals(City)) {
                System.out.println(people.get(i).getIdentifier() + ", " +
                        people.get(i).getSurname());
            }
        }
        System.out.println("---------------------");
    }


    private void bornBetweenDates(String pFrom, String pTo) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate d1 = LocalDate.parse(pFrom, formato);
        LocalDate d2 = LocalDate.parse(pTo, formato);
        ArrayList<people> match = new ArrayList<>();
        int born_year = 0;
        for (people person : people) {
            if (person.getBirthDate().getYear() >= d1.getYear() && person.getBirthDate().getYear() <= d2.getYear()) {
                match.add(person);
            }
        }

        people.sort(null);
        System.out.println("People born between " + d1.getYear() + " and " + d2.getYear());
        System.out.println("--------------------------------");
        for (people person : match) {
            System.out.println(person.getBirthplace() + ',' +
                    person.getSurname() + ',' +
                    person.getName());
        }
        System.out.println("--------------------------------");
    }

    private void residential() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter filename: ");
        String nombre = scanner.nextLine();
        ArrayList<people> residential = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombre))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                for (int i = 0; i < people.size(); i++) {
                    if (people.get(i).getIdentifier().equals(line)) {
                        String City = people.get(i).getHometown();
                        System.out.println("---------------------------");
                        System.out.println(people.get(i).getIdentifier() + "'s hometown: " + people.get(i).getHometown());
                        System.out.println("People born in " + people.get(i).getIdentifier() + "'s hometown: ");
                        System.out.println("---------------------------");
                        for (int j = 0; j < people.size(); j++) {
                            if (people.get(j).getBirthplace().equals(City)) {
                                System.out.println(people.get(j).getIdentifier());
                            }
                        }
                    }
                }
            }
            System.out.println("---------------------------");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void movieCategories() {
        movie_lists actual_category;
        for (int i = 0; i < people.size(); i++) {
            actual_category = new movie_lists(people.get(i).getMovies());
            if (!movies.contains(actual_category)) {
                actual_category.getPeopleList().add(people.get(i));
                for (int j = i + 1; j < people.size(); j++) {
                    if (people.get(i).getMovies().equals(people.get(j).getMovies())) {
                        actual_category.getPeopleList().add(people.get(j));
                    }
                }
                movies.add(actual_category);
            }
        }

        for (movie_lists movie : movies) {
            System.out.println("------------------");
            System.out.println("Movie Group : " + movie.getListName());
            System.out.println("Users inside group");
            System.out.println("------------------");
            for (people person : movie.getPeopleList()) {
                System.out.println(person.getName() + " " + person.getSurname());
            }
        }
    }
    private List<String> findShortestChain(String startPerson, String endPerson) {
        Queue<List<String>> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(Arrays.asList(startPerson));
        visited.add(startPerson);

        while (!queue.isEmpty()) {
            List<String> path = queue.poll();
            String lastPerson = path.get(path.size() - 1);

            if (lastPerson.equals(endPerson)) {
                return path;
            }

            for (String neighbor : adjacencyList.getOrDefault(lastPerson, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    List<String> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    queue.add(newPath);
                }
            }
        }

        List<String> o = null;
        return o; // No connection found
    }

    private Map<String, List<String>> buildAdjacencyList() {
        for (Relationships relationship : relations) {
            adjacencyList.computeIfAbsent(relationship.getFriend_orig(), k -> new ArrayList<>())
                    .add(relationship.getFriend_dest());
            System.out.println("a");
            adjacencyList.computeIfAbsent(relationship.getFriend_dest(), k -> new ArrayList<>())
                    .add(relationship.getFriend_orig());
        }
        return adjacencyList;
    }
    private List<String> findAlternativeChain(String startPerson, String endPerson) {
        List<String> longestChain = new ArrayList<>();
        Set<String> visited = new HashSet<>();

        findLongestChainDFS(startPerson, endPerson, new ArrayList<>(), visited, adjacencyList, longestChain);
        return longestChain;
    }

    private void findLongestChainDFS(String current, String target, List<String> path, Set<String> visited,
                                     Map<String, List<String>> adjacencyList, List<String> longestChain) {
        visited.add(current);
        path.add(current);

        if (current.equals(target)) {
            if (path.size() > longestChain.size()) {
                longestChain.clear();
                longestChain.addAll(path);
            }
        } else {
            for (String neighbor : adjacencyList.getOrDefault(current, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    findLongestChainDFS(neighbor, target, path, visited, adjacencyList, longestChain);
                }
            }
        }

        visited.remove(current);
        path.remove(path.size() - 1);
    }
    private List<Set<String>> findCliques() {
        List<Set<String>> cliques = new ArrayList<>();
        Set<String> visited = new HashSet<>();

        for (String person : adjacencyList.keySet()) {
            findCliquesBacktracking(person, new HashSet<>(), cliques, adjacencyList);
        }

        return cliques.stream().filter(clique -> clique.size() > 4).toList();
    }

    private void findCliquesBacktracking(String current, Set<String> clique, List<Set<String>> cliques,
                                         Map<String, List<String>> adjacencyList) {
        clique.add(current);

        if (isClique(clique, adjacencyList)) {
            cliques.add(new HashSet<>(clique));
        }

        for (String neighbor : adjacencyList.getOrDefault(current, new ArrayList<>())) {
            if (!clique.contains(neighbor)) {
                findCliquesBacktracking(neighbor, clique, cliques, adjacencyList);
            }
        }

        clique.remove(current);
    }

    private boolean isClique(Set<String> clique, Map<String, List<String>> adjacencyList) {
        for (String person : clique) {
            for (String other : clique) {
                if (!person.equals(other) && !adjacencyList.getOrDefault(person, new ArrayList<>()).contains(other)) {
                    return false;
                }
            }
        }
        return true;
    }



}

