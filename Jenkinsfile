pipeline {
    agent any

    environment {
        IMAGE_NAME = "ramanjaisw86/student-service"
        IMAGE_TAG  = "1.0"
    }

    stages {

        stage('Checkout Code') {
            steps {
                checkout scm
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
                dir('student-service') {
                    sh '''
                      docker build -t ${IMAGE_NAME}:${IMAGE_TAG} .
                    '''
                }
            }
        }

        stage('Push Image to Docker Hub') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh '''
                      echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                      docker push ${IMAGE_NAME}:${IMAGE_TAG}
                      docker logout
                    '''
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                dir('k8s') {
                    sh '''
                      kubectl apply -f mysql-deployment.yaml
                      kubectl apply -f mysql-service.yaml
                      kubectl apply -f student-service-deployment.yaml
                      kubectl apply -f student-service-service.yaml
                    '''
                }
            }
        }
    }

    post {
        success {
            echo "✅ CI/CD Pipeline Completed Successfully"
        }
        failure {
            echo "❌ CI/CD Pipeline Failed"
        }
    }
}
