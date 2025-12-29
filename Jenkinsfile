pipeline {
    agent any

    environment {
        IMAGE_NAME = "satvik0100/smart-spender"
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
                    withCredentials([usernamePassword(credentialsId: satvik0100, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                        sh "docker push ${IMAGE_NAME}:latest"
                        sh 'docker logout'
                    }
                }
            }
        }
    }
}
