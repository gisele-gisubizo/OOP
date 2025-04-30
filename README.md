OOP Assignment Repository
This repository contains three Java projects for the assignment, each with its own Docker image built using OpenJDK 21. The local project directory is named gisele.
Projects

MissionManagementSystem

Description: Manages missions (Recon, Rescue, Combat, Humanitarian) with personnel and resources. Features include creating missions, assigning personnel/resources, and generating reports.
Source: MissionManagementSystem/src/com/gisele/missionmanagement
Docker Image: gisele-gisubizo/mission-management:26188
Run Command: docker run -it gisele-gisubizo/mission-management:26188


LandManagementSystem

Description: Manages land types (Agricultural, Residential, Commercial, Industrial) with tax calculations and zoning checks.
Source: LandManagementSystem/src/com/gisele/landmanagement
Docker Image: gisele-gisubizo/land-management:26188
Run Command: docker run -it gisele-gisubizo/land-management:26188


NurserySchoolManagementSystem

Description: Manages nursery classes (Baby, Middle, Top), teachers, and students, with features for enrollment and activities.
Source: NurserySchoolManagementSystem/src/com/gisele/nurseryschool
Docker Image: gisele-gisubizo/nursery-school:26188
Run Command: docker run -it gisele-gisubizo/nursery-school:26188



Setup Instructions

Clone the repository:git clone https://github.com/gisele-gisubizo/OOP.git


Rename the cloned directory to gisele (if not already):mv OOP gisele


Open the gisele directory in IntelliJ IDEA with JDK 21 configured.
Build each project to generate out/production directories:
In IntelliJ, select each project and run Build > Build Project (Ctrl+F9).


Build Docker images using the Dockerfile in each project directory:cd gisele/MissionManagementSystem
docker build -t mission-management:26188.
cd ../LandManagementSystem
docker build -t land-management:26188 .
cd ../NurserySchoolManagementSystem
docker build -t nursery-school:26188 .


Run Docker images as shown above.

Submission Details

GitHub Repository: https://github.com/gisele-gisubizo/OOP
Docker Hub: https://hub.docker.com/u/gisele-gisubizo (if images are pushed)
Docker Images:
gisele-gisubizo/mission-management:26188
gisele-gisubizo/land-management:26188
gisele-gisubizo/nursery-school:26188



