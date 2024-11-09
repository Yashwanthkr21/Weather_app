pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "yashwanthkr9/weather_app_image" // Replace localhost with the server IP if needed
    }

    stages {
        stage('Build') {
            steps {
                script {
                    // Build the Docker image
                    sh "docker build -f Dockerimg2.dockerfile -t $DOCKER_IMAGE ."
                }
            }
        }
        
        stage('Push to Local Registry') {
            steps {
                script {
                    // Push the Docker image to the local registry
                    sh "docker push $DOCKER_IMAGE"
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    // Apply Kubernetes deployment YAML (modify to use the local registry image)
                    sh """
                    kubectl create namespace weather-app --dry-run=client -o yaml | kubectl apply -f -
                    kubectl apply -f k8s-deployment.yaml -n weather-app
                    """
                }
            }
        }
    }
}
