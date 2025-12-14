pipeline {
    agent any

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
