pipeline {
    agent any

    environment {
        IMAGE_NAME = "ramanjaisw86/student-service"
        IMAGE_TAG  = "1.0"
    }

    stages {

        stage('Verify Workspace') {
            steps {
                sh '''
                  echo "Workspace:"
                  pwd
                  ls
                  ls student-service
                '''
            }
        }

        stage('Build Maven Project') {
            steps {
                sh '''
                  docker run --rm \
                    -v "$WORKSPACE/student-service":/app \
                    -v "$HOME/.m2":/root/.m2 \
                    -w /app \
                    maven:3.9.9-eclipse-temurin-17 \
                    mvn clean package -DskipTests
                '''
            }
        }

        stage('Build Docker Image') {
            steps {
                sh '''
                  docker build -t $IMAGE_NAME:$IMAGE_TAG student-service
                '''
            }
        }

        stage('Push Image to Docker Hub') {
            steps {
                sh '''
                  docker push $IMAGE_NAME:$IMAGE_TAG
                '''
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                sh '''
                  kubectl apply -f k8s/mysql-deployment.yaml
                  kubectl apply -f k8s/mysql-service.yaml
                  kubectl apply -f k8s/student-service-deployment.yaml
                  kubectl apply -f k8s/student-service-service.yaml
                '''
            }
        }
    }

    post {
        success {
            echo '✅ CI/CD Pipeline Completed Successfully'
        }
        failure {
            echo '❌ CI/CD Pipeline Failed'
        }
    }
}
