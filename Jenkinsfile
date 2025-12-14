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

        stage('Verify Project') {
            steps {
                sh '''
                pwd
                ls
                ls student-service
                test -f student-service/pom.xml
                '''
            }
        }

        stage('Build Maven Project') {
            steps {
                dir('student-service') {
                    sh '''
                    chmod +x mvnw
                    ./mvnw clean package -DskipTests
                    '''
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                dir('student-service') {
                    sh '''
                    docker build -t $IMAGE_NAME:$IMAGE_TAG .
                    '''
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
            echo '✅ CI Pipeline SUCCESSFUL'
        }
        failure {
            echo '❌ CI Pipeline FAILED'
        }
    }
}
