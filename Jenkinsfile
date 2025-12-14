pipeline {
    agent none

    environment {
        IMAGE_NAME = "ramanjaisw86/student-service"
        IMAGE_TAG  = "1.0"
    }

    stages {

        stage('Build Maven Project') {
            agent {
                docker {
                    image 'maven:3.9.9-eclipse-temurin-17'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
                dir('student-service') {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Docker Image') {
            agent any
            steps {
                sh 'docker build -t $IMAGE_NAME:$IMAGE_TAG student-service'
            }
        }

        stage('Push Image to Docker Hub') {
            agent any
            steps {
                sh 'docker push $IMAGE_NAME:$IMAGE_TAG'
            }
        }

        stage('Deploy to Kubernetes') {
            agent any
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
