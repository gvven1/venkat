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
    sh "docker build -t amuldevopstools.azurecr.io/react_jenkins:v1 ./node"
    sh "docker push amuldevopstools.azurecr.io/react_jenkins:v1"
    }
    }
    }
    }

    stage(‘Deploy’) {
    steps {
    sh 'kubectl apply -f ./node/my-react-deployments.yaml   --force'
    sh 'kubectl apply -f ./node/my-replication-controller.yaml --force'
    sh 'kubectl apply -f ./node/service-nodeport.yaml --force'
    }
    }
    }
    }