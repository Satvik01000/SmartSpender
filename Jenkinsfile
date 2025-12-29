pipeline {
    agent any

    environment {
        IMAGE_NAME = "satvik0100/smart-spender"
        // This variable holds the string "satvik0100"
        DOCKER_CREDS_ID = "satvik0100"
    }

    stages {
        stage('Build JAR') {
            steps {
                script {
                    echo 'Compiling with Maven Wrapper...'
                    sh 'chmod +x mvnw'
                    sh './mvnw clean package -DskipTests'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    echo 'Building Docker Image...'
                    sh "docker build -t ${IMAGE_NAME}:latest ."
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                script {
                    echo 'Pushing to Docker Hub...'
                    // FIX: Use the variable DOCKER_CREDS_ID here
                    withCredentials([usernamePassword(credentialsId: DOCKER_CREDS_ID, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                        sh "docker push ${IMAGE_NAME}:latest"
                        sh 'docker logout'
                    }
                }
            }
        }
    }
}
