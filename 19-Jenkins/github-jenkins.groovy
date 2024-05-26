pipeline {
    agent any 

    triggers {
        githubPush()
    }

    stages{
        stage('Checkout') {
            steps {
                checkout([
                    $class: 'GitSCM', 
                    branches: [[name: '*/main']], 
                    userRemoteConfigs: [[
                        url: 'https://github.com/mpapili/DevopsZeroToHero.git',
                        credentialsId: 'Github-Token'
                    ]]
                ])
                sh 'ls' // verify we cloned
            }
        }
        stage("Build"){
            steps {
                echo "Building the image"
                sh script:'''
                  #!/bin/bash
                  echo "This is start $(pwd)"
                  cd ./19-Jenkins
                  echo "This is $(pwd)"
                  docker build -t test-py .
                '''
            }
        }
        stage("Push to Docker Hub"){
            steps {
                echo "Pushing the image to docker hub"
                // withCredentials([usernamePassword(credentialsId:"dockerHub",passwordVariable:"dockerHubPass",usernameVariable:"dockerHubUser")]){
                // sh "docker tag my-note-app ${env.dockerHubUser}/my-note-app:latest"
                // sh "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPass}"
                // sh "docker push ${env.dockerHubUser}/my-note-app:latest"
            }
        }
        stage("Deploy"){
            steps {
                echo "Deploying the container"
                // sh "docker-compose down && docker-compose up -d"
            }
        }
    }
}