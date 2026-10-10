import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ResumeController {

    private static final String URL =
            "jdbc:mysql://localhost:3306/empleo";

    private static final String USER =
            "root";

    private static final String PASSWORD =
            "12345";


    public static void saveResume(Resume resume)
            throws Exception {

        Connection connection =
                DriverManager.getConnection(
                        URL,
                        USER,
                        PASSWORD
                );


        String sql =
                "INSERT INTO resumes " +
                "(name, email, phone, location, linkedin, github, " +
                "summary, education, skills, projects, experience, " +
                "certifications, achievements) " +

                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


        PreparedStatement statement =
                connection.prepareStatement(sql);


        statement.setString(1, resume.name);
        statement.setString(2, resume.email);
        statement.setString(3, resume.phone);
        statement.setString(4, resume.location);

        statement.setString(5, resume.linkedin);
        statement.setString(6, resume.github);

        statement.setString(7, resume.summary);
        statement.setString(8, resume.education);
        statement.setString(9, resume.skills);
        statement.setString(10, resume.projects);
        statement.setString(11, resume.experience);

        statement.setString(12, resume.certifications);
        statement.setString(13, resume.achievements);


        statement.executeUpdate();

        statement.close();
        connection.close();
    }
}