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

        stage('Docker Build & Push') {
            steps {
                script {
                    // Wrap EVERYTHING in the credentials block
                    withCredentials([usernamePassword(credentialsId: DOCKER_CREDS_ID, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        
                        echo '1. Logging in...'
                        // Login FIRST so we can pull the base images
                        sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                        
                        echo '2. Building Image...'
                        // Now the build works because we are authenticated
                        sh "docker build -t ${IMAGE_NAME}:latest ."
                        
                        echo '3. Pushing to Docker Hub...'
                        sh "docker push ${IMAGE_NAME}:latest"
                        
                        echo '4. Logging out...'
                        sh 'docker logout'
                    }
                }
            }
        }
    }
}
