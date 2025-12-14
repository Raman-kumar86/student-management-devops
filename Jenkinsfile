pipeline {
    agent any

    environment {
        IMAGE_NAME = "ramanjaisw86/student-service"
        IMAGE_TAG  = "1.0"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build with Maven (Docker)') {
            steps {
                dir('student-service') {
                    sh '''
                    docker run --rm \
                      -v "$PWD":/app \
                      -v "$HOME/.m2":/root/.m2 \
                      -w /app \
                      maven:3.9.9-eclipse-temurin-17 \
                      mvn clean package -DskipTests
                    '''
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                dir('student-service') {
                    sh 'docker build -t $IMAGE_NAME:$IMAGE_TAG .'
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh '''
                    echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                    docker push $IMAGE_NAME:$IMAGE_TAG
                    '''
                }
            }
        }
    }

    post {
        success {
            echo '✅ CI Pipeline Successful'
        }
        failure {
            echo '❌ CI Pipeline Failed'
        }
    }
}
