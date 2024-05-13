# Hospital Information System (HIS)
The Hospital Information System (HIS) is a project developed by the EECS2311 group 2, focusing on the needs of hospital administrators, physicians, nurses, and volunteers. It provides a comprehensive platform for managing patient information, appointments, medications, and more.

## Project Overview
The HIS project aims to streamline hospital operations by providing specific functionalities tailored to different user roles within the hospital environment. 
## Setup
Hello, please just follow the following steps to do the setup. Generally, no specific setup is needed and only downloading the src files and connecting to DB would be enough, but here are the general steps if you want to create new roles and see functionalities:
1- Download all files in src into the eclipseIDE (JavaSE-17).
2- In the databaseConnection class, modify the following line and choose your own password to connect to the database in SQL: private static final String PASSWORD = "choose your password";
3-  Run roleSelectionGUI.java, to see the starter GUI on your EclipseIDE.
4-  Select your role and create your log-in information
5 - If you log into the role admin, you will be able to create other roles with your specified information.
6 - You will be able to log in as a role you have created with its specific username and password afterward.

## Key features include:
- User authentication and role-based access control for doctors, nurses, volunteers, and administrators.
- Patient management, including admission, discharge, and assignment to doctors and nurses.
- Doctor functionalities such as requesting tests, medications, and discharge procedures.
- Nurse functionalities including access to doctor notes, medication lists, and patient admission.
- Volunteer functionalities for adding patient requests and assisting doctors and nurses.
- Administrator functionalities for hiring and managing staff, viewing patient assignments, and managing inpatient and outpatient profiles.

## Tools Used
- **JavaSwing** : Utilized for developing the hospital application.
- **PSQL** : Utilized as the database service for this application.
- **JBDC** : Utilized to connect program to PSQL database.

## Roadmap: Project Iterations
- **Iteration 0:**
  - Initial non-coding tasks to lay the foundation of the project.
  - Meeting with customers to understand requirements.
  - Planning the project's scope, vision, and goals.
  - Defining user stories and creating a backlog of features.

- **Iteration 1:**
  - Coding and unit testing phase where the initial features are implemented.
  - Focus on implementing basic functionalities such as user authentication and role-based access control.

- **Iteration 2:**
  - Continuation of coding and unit testing with a focus on expanding functionality.
  - Setup of the database to store patient information and other relevant data.
  - Integration testing to ensure seamless interaction between different components.
  - Implementation of a simple design for the user interface and full implementation of the third user story.

- **Iteration 3:**
  - Further coding and unit testing to refine and enhance existing features.
  - Complete implementation of the database including patient management, appointments, and staff information.
  - Integration testing to ensure the entire system works as expected.
  - Design improvements to enhance the user experience and interface.
  - Refactoring of code to improve readability, maintainability, and performance.
  - Creation of acceptance tests to validate the functionality against user requirements.
  - Preparation for the final release of the project.
 
    # Healthcare Information System (HIS) - User Stories Update for ITR3 The complete version of user stories and how to recreate them is uploaded in a pdf file "Wiki User stories update for ITR3". Please check this document for detailed version.

The HIS is developed around multiple distinct user stories, each targeting specific functionalities within the system. Below, we provide summaries for each user story, elaborating on the process overviews, testing strategies, and verification steps involved.

**The complete version of user stories and how to recreate them is uploaded in a pdf file "Wiki User stories update for ITR3". Please check this document for detailed version.**

## Table of Contents
- [1. Parmoun Khalkhali Sharifi](#1-parmoun-khalkhali-sharifi)
  - [1.1 Family Doctor Assignment](#11-family-doctor-assignment)
  - [1.2 Patient Consent Form Status](#12-patient-consent-form-status)
  - [1.3 Prescribing Medication](#13-prescribing-medication)
- [2. Mehregan Mesgari](#2-mehregan-mesgari)
  - [2.1 Add Lab Request](#21-add-lab-request)
  - [2.2 Retrieve Lab Results](#22-retrieve-lab-results)
  - [2.3 Email Family Doctor Post-Discharge](#23-email-family-doctor-post-discharge)
  - [2.4 Laboratory Dashboard](#24-laboratory-dashboard)
- [3. Gaurav Charan Moturi](#3-gaurav-charan-moturi)
  - [3.1 Hire Physician](#31-hire-physician)
  - [3.2 Requesting Lab on the Physician's Page](#32-requesting-lab-on-the-physicians-page)
  - [3.3 Registration, Login, and Validation](#33-registration-login-and-validation)
- [4. Amira Mohamed](#4-amira-mohamed)
  - [4.1 Finding and Displaying Patient Information](#41-finding-and-displaying-patient-information)
  - [4.2 Record and Retrieve Vital Signs](#42-record-and-retrieve-vital-signs)
  - [4.3 Discharge Patients](#43-discharge-patients)
- [5. Harrish Elango](#5-harrish-elango)
  - [5.1 Hire Physician/Nurse](#51-hire-physiciannurse)
  - [5.2 Resign Physician/Nurse](#52-resign-physiciannurse)
  - [5.3 Assign Physician and Nurse to Patient](#53-assign-physician-and-nurse-to-patient)
  - [5.4 Display General Information about Patients](#54-display-general-information-about-patients)
- [Refractored Developer Stories](#refractored-developer-stories)
- [Links to Other Documents](#links-to-other-documents)

## 1. Parmoun Khalkhali Sharifi
### 1.1 Family Doctor Assignment
**Process Overview:**
Nurses log in to assign family doctors to patients, with options to manage consent forms and view patient lists. The process includes filling a form with patient details and the option to assign a family doctor.

**Testing and Verification:**
Tested with JUnit and end-to-end to ensure reliability. Handles invalid inputs by displaying a "not found" message for non-existent family doctor credentials.

### 1.2 Patient Consent Form Status
**Process Overview:**
Nurses manage patient consent forms, updating the database with consent status upon confirmation.

**Testing and Verification:**
Validated with JUnit tests to ensure the GUI framework correctly updates the database.

### 1.3 Prescribing Medication
**Process Overview:**
Doctors prescribe medication through the patient portal, with detailed entry of medication information.

**Testing and Verification:**
Thoroughly tested end-to-end, with JUnit tests confirming correct database updates through the GUI.

## 2. Mehregan Mesgari
### 2.1 Add Lab Request
Physicians can request labs for patients using their ID, specifying lab type and description.

### 2.2 Retrieve Lab Results
Physicians retrieve lab results from their homepage by searching with the patient's ID.

### 2.3 Email Family Doctor Post-Discharge
Upon patient discharge, if consent is given, the family doctor receives an email with visit information.

### 2.4 Laboratory Dashboard
Lab technicians manage new lab requests and fulfill labs through a dedicated dashboard.

## 3. Gaurav Charan Moturi
### 3.1 Hire Physician
Admins hire physicians or nurses, entering necessary information into the system and saving it in the database.

### 3.2 Requesting Lab on the Physician's Page
Physicians use their dashboard to request lab tests, with the system ensuring data validation and database updates.

### 3.3 Registration, Login, and Validation
Covers user registration, login procedures, and role-specific functionality access upon successful login.

## 4. Amira Mohamed
### 4.1 Finding and Displaying Patient Information
Doctors view patient information through the patient portal, with options to display all patients or search by ID or name.

### 4.2 Record and Retrieve Vital Signs
Physicians record and retrieve patients' vital signs, ensuring data validation and database integrity.

### 4.3 Discharge Patients
Doctors discharge patients from the hospital, with the system updating accordingly and managing patient records.

## 5. Harrish Elango
### 5.1 Hire Physician/Nurse
Similar to Gaurav's user story but includes specific testing scenarios and acceptance criteria.

### 5.2 Resign Physician/Nurse
Admins manage the resignation of physicians or nurses, removing them from the system database.

### 5.3 Assign Physician and Nurse to Patient
Nurses assign physicians and themselves to new patients, updating the hospital system records.

### 5.4 Display General Information about Patients
Nurses view information about patients assigned to them, ensuring accurate and up-to-date patient management.

## Refractored Developer Stories
Improvements and bug fixes in the system, including ID management, function refactoring, and user interface enhancements.

## Links to Other Documents
- Bug configuration and class refactoring: [Google Docs Link](https://docs.google.com/document/d/1vuzcxLJhiPnCYpfaGo8mG83GN-1Cvggm6v6y3LxoD64/edit)
- Planning Document: [Google Docs Link](https://docs.google.com/document/d/1oyA1L4V6MDJ_Ccv_4-wr7w6yoowYAcSZf6i3pw12wFA/edit)


## Group Members
- Mehregan Mesgari
- Parmoun Khalkhali Sharifi
- Gaurav Charan Moturi
- Amira Mohamed
- Harrish Elango
