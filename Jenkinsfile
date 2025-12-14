pipeline {
    agent any

    environment {
        IMAGE_NAME = "student-service"
        IMAGE_TAG  = "1.0"
    }

    stages {

        stage('Checkout Code') {
            steps {
                git 'https://github.com/<your-username>/Student-management-devops.git'
            }
        }

        stage('Build Maven Project') {
            steps {
                dir('student-service') {
                    sh 'mvn clean package -DskipTests'
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

        stage('Load Image to Minikube') {
            steps {
                sh 'minikube image load $IMAGE_NAME:$IMAGE_TAG'
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                dir('k8s') {
                    sh 'kubectl apply -f mysql-deployment.yaml'
                    sh 'kubectl apply -f mysql-service.yaml'
                    sh 'kubectl apply -f student-service-deployment.yaml'
                    sh 'kubectl apply -f student-service-service.yaml'
                }
            }
        }
    }

    post {
        success {
            echo '✅ Deployment Successful!'
        }
        failure {
            echo '❌ Deployment Failed!'
        }
    }
}
