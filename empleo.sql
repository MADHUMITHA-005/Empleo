CREATE DATABASE empleo;

USE empleo;

CREATE TABLE resumes (

    id INT PRIMARY KEY AUTO_INCREMENT,

    name VARCHAR(100) NOT NULL,

    email VARCHAR(150) NOT NULL,

    phone VARCHAR(30),

    location VARCHAR(100),

    linkedin VARCHAR(255),

    github VARCHAR(255),

    summary TEXT,

    education TEXT,

    skills TEXT,

    projects TEXT,

    experience TEXT,

    certifications TEXT,

    achievements TEXT,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);