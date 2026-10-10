// Get value from an input
function getValue(id) {
    return document.getElementById(id)?.value || "";
}


// Save resume in browser and database
async function saveResume() {

    const resume = {

        name: getValue("name"),
        email: getValue("email"),
        phone: getValue("phone"),
        location: getValue("location"),
        linkedin: getValue("linkedin"),
        github: getValue("github"),

        summary: getValue("summary"),
        education: getValue("education"),
        skills: getValue("skills"),
        projects: getValue("projects"),
        experience: getValue("experience"),
        certifications: getValue("certifications"),
        achievements: getValue("achievements")
    };


    // Basic validation

    if (!resume.name || !resume.email) {

        document.getElementById("message").innerText =
            "Please enter your name and email.";

        return;
    }


    // Save locally

    localStorage.setItem(
        "empleoResume",
        JSON.stringify(resume)
    );


    // Send to Java backend

    const formData = new URLSearchParams();

    for (const key in resume) {
        formData.append(key, resume[key]);
    }


    try {

        const response = await fetch(
            "http://localhost:8080/api/save",
            {
                method: "POST",
                headers: {
                    "Content-Type":
                        "application/x-www-form-urlencoded"
                },
                body: formData
            }
        );


        const result = await response.text();

        document.getElementById("message").innerText =
            result;

    } catch (error) {

        document.getElementById("message").innerText =
            "Saved locally. Start the Java server to save to MySQL.";

        console.log(error);
    }
}


// Open preview page

function openPreview() {

    const form = document.getElementById("resumeForm");

    if (!form) {
        return;
    }

    const resume = {

        name: getValue("name"),
        email: getValue("email"),
        phone: getValue("phone"),
        location: getValue("location"),
        linkedin: getValue("linkedin"),
        github: getValue("github"),

        summary: getValue("summary"),
        education: getValue("education"),
        skills: getValue("skills"),
        projects: getValue("projects"),
        experience: getValue("experience"),
        certifications: getValue("certifications"),
        achievements: getValue("achievements")
    };


    if (!resume.name) {

        alert("Please enter your name first.");

        return;
    }


    localStorage.setItem(
        "empleoResume",
        JSON.stringify(resume)
    );


    window.location.href = "preview.html";
}


// Load resume on preview page

function loadPreview() {

    const data =
        localStorage.getItem("empleoResume");

    if (!data) {
        return;
    }


    const resume = JSON.parse(data);


    document.getElementById("previewName").innerText =
        resume.name;

    document.getElementById("previewContact").innerText =
        `${resume.email} | ${resume.phone} | ${resume.location}`;


    document.getElementById("previewSummary").innerText =
        resume.summary;

    document.getElementById("previewEducation").innerText =
        resume.education;

    document.getElementById("previewSkills").innerText =
        resume.skills;

    document.getElementById("previewProjects").innerText =
        resume.projects;

    document.getElementById("previewExperience").innerText =
        resume.experience;

    document.getElementById("previewCertifications").innerText =
        resume.certifications;

    document.getElementById("previewAchievements").innerText =
        resume.achievements;
}


// Automatically load preview

if (document.getElementById("resumePreview")) {
    loadPreview();
}