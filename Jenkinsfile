pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                echo 'Repository already checked out by Jenkins'
            }
        }

        stage('Build Maven Project') {
            steps {
                dir('student-service') {
                    sh 'mvn -version'
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
