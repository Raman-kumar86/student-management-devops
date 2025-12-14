# Student Management DevOps Project

## Tech Stack
- Spring Boot
- MySQL
- Docker
- Kubernetes (Minikube)
- Jenkins CI/CD

## Flow
1. Code pushed to GitHub
2. Jenkins auto-triggers pipeline
3. Maven builds JAR
4. Docker image is built
5. Image loaded to Minikube
6. App deployed to Kubernetes

## API
GET /students  
POST /students

## How to Run
- Start Jenkins
- Start Minikube
- Push code → pipeline runs automatically
Testing Jenkins auto build
