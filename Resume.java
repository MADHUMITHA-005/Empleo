public class Resume {

    String name;
    String email;
    String phone;
    String location;

    String linkedin;
    String github;

    String summary;
    String education;
    String skills;
    String projects;
    String experience;

    String certifications;
    String achievements;


    public Resume(
            String name,
            String email,
            String phone,
            String location,
            String linkedin,
            String github,
            String summary,
            String education,
            String skills,
            String projects,
            String experience,
            String certifications,
            String achievements
    ) {

        this.name = name;
        this.email = email;
        this.phone = phone;
        this.location = location;

        this.linkedin = linkedin;
        this.github = github;

        this.summary = summary;
        this.education = education;
        this.skills = skills;
        this.projects = projects;
        this.experience = experience;

        this.certifications = certifications;
        this.achievements = achievements;
    }
}