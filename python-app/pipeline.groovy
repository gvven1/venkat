  pipeline {
    agent any

   stages {
   stage ('Git Checkout') {
   steps {
   git branch: 'main', url: 'https://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@github.com/gvven1/venkat.git'
     }
  }
  

    stage(‘Docker’) {
    steps {
    script {
    withCredentials([usernamePassword(credentialsId: 'my-docker-hub-credential', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
    sh "docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD amuldevopstools.azurecr.io"
    sh "docker build -t amuldevopstools.azurecr.io/python_jenkins:v2 ./python-app"
    sh "docker push amuldevopstools.azurecr.io/python_jenkins:v2"
    }
    }
    }
    }

    stage(‘Deploy’) {
    steps {
    sh 'kubectl apply -f ./python-app/my-python-deployments.yaml   --force'
    }
    }
    }
    }