# Student Management DevOps Project

Spring Boot student management service with MySQL, Docker, Kubernetes, and Jenkins CI/CD.

## Tech Stack
- Spring Boot
- MySQL
- Docker
- Kubernetes
- Jenkins CI/CD

## Project Structure
- `student-service/` - Spring Boot application
- `docker-compose.yml` - Local Docker setup for app + MySQL
- `k8s/` - Kubernetes manifests for MySQL and student service
- `Jenkinsfile` - CI pipeline for build and Docker push

## API
- `GET /students` - list all students
- `POST /students` - create a new student

## Local Development
1. Start MySQL and the app with Docker Compose:

   ```bash
   docker compose up -d
   ```

2. Or run the Spring Boot app directly:

   ```bash
   cd student-service
   ./mvnw spring-boot:run
   ```

## Docker Build
Build the application JAR first, then build the image:

```bash
cd student-service
./mvnw clean package -DskipTests
docker build -t student-service:1.0 .
```

## Kubernetes Deployment
The Kubernetes manifests deploy MySQL and the student service.

```bash
kubectl apply -f k8s/mysql-deployment.yaml
kubectl apply -f k8s/mysql-service.yaml
kubectl apply -f k8s/student-service-deployment.yaml
kubectl apply -f k8s/student-service-service.yaml
```

The student service is exposed as a `NodePort` service on port `30080`.

## Jenkins CI/CD Flow
1. Code is pushed to GitHub.
2. Jenkins checks out the repository.
3. Maven builds the Spring Boot JAR.
4. Docker image is built and pushed to Docker Hub.
5. Kubernetes can then pull and deploy the published image.

## Environment Files
- `student-service/src/main/resources/application.yml` uses the `dev` profile by default.
- `student-service/src/main/resources/application-docker.yml` points the app to the MySQL service inside Docker or Kubernetes.

## Notes
- MySQL credentials in the local and Docker setup are `devuser` / `devpass`.
- The app listens on port `8080`.
