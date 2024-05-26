pipeline {
    agent any
    stages {
        stage("test-stage1") {
            steps {
                // say which worker we're using
                echo "hello world from ${env.HOSTNAME}"
            }
        }
        stage("get-code") {
            steps {
                sh 'cp -r /home/mike/Downloads/git/DevopsZeroToHero/19-Jenkins ${WORKSPACE}'
                sh 'docker build . -t test-py'
            }
        }
        stage("run-tests") {
            steps {
                echo "running unit tests in docker container"
                script {
                    // grab our desired docker image
                    def dockerImage = docker.image('test-py')
                    // run the docker image
                    dockerImage.inside('--rm') {
                        // Execute commands inside the Docker container
                        sh 'echo "Running inside Docker"'
                        sh 'python3 /app/my_script.py'
                    }
                }
            }
        }
        stage("check-format") {
            steps {
                script {
                    def dockerImage = docker.image('test-py')
                    dockerImage.inside('--rm') {
                        // Execute commands inside the Docker container
                        sh 'echo "Running inside Docker"'
                        sh 'cd /app'
                        sh 'flake8 .'
                    }
                }
            }
        }
    }
}