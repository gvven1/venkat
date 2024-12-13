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
    sh "docker login -u amuldevopstools -p DRz6lLLVh2hbpArHpBZ3cnETJupZ7o1zppn14wyEwf+ACRAMWeOu amuldevopstools.azurecr.io"
    sh "docker build -t amuldevopstools.azurecr.io/python_jenkins:v1 ./python-app"
    sh "docker push amuldevopstools.azurecr.io/python_jenkins:v1"
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