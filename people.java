import java.util.Comparator;

public class people {
    private String identifier;
    private String name;
    private String surname;
    private String birthDate;
    private String gender;
    private String birthplace;
    private String hometown;
    private String studiedAt;
    private String workedAt;
    private String movies;
    private String groupCode;


    public people(String identifier, String name, String surname, String birthDate, String gender,
                  String birthplace, String hometown, String studiedAt,
                  String workedAt,String movies, String groupCode) {
        this.identifier = identifier;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.gender = gender;
        this.birthplace = birthplace;
        this.hometown = hometown;
        this.studiedAt = studiedAt;
        this.workedAt = workedAt;
        this.movies = movies;
        this.groupCode = groupCode;

    }

    public String getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public String getHometown() {
        return hometown;
    }

    public String getStudiedAt() {
        return studiedAt;
    }

    public String getWorkedAt() {
        return workedAt;
    }

    public String getMovies() {
        return movies;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public void setStudiedAt(String studiedAt) {
        this.studiedAt = studiedAt;
    }

    public void setWorkedAt(String workedAt) {
        this.workedAt = workedAt;
    }

    public void setMovies(String movies) {
        this.movies = movies;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    @Override
    public String toString() {
        return "people{" +
                "identifier='" + identifier + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", gender='" + gender + '\'' +
                ", birthplace='" + birthplace + '\'' +
                ", hometown='" + hometown + '\'' +
                ", studiedAt='" + studiedAt + '\'' +
                ", workedAt='" + workedAt + '\'' +
                ", movies='" + movies + '\'' +
                ", groupCode='" + groupCode + '\'' +
                '}';
    }

    /**
    @Override
    public int compare(people o1, people o2) {
        if (o1.getBirthplace().compareTo(o2.getBirthplace()) != 0) {
            return 0;

        }
        if (o1.getBirthDate().compareTo(o2.getBirthDate()) != 0) {}
    }
    */
}
