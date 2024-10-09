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

}
