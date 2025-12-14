pipeline {
    agent {
        docker {
            image 'maven:3.9.9-eclipse-temurin-17'
            args '-v /var/run/docker.sock:/var/run/docker.sock'
        }
    }

    stages {
        stage('Build Maven Project') {
            steps {
                dir('student-service') {
                    sh 'mvn --version'
                    sh 'mvn clean package -DskipTests'
                }
            }
        }
    }

    post {
        success {
            echo '✅ Maven build successful'
        }
        failure {
            echo '❌ Maven build failed'
        }
    }
}
