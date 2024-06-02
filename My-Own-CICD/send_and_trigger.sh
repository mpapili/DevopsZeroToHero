#! /bin/bash
TOKEN="this-is-my-test-token"  # I swear I don't commit these to github when they're real
JOB_PATH="/job/Jenkins-CICD/build?token=${TOKEN}" 
JENKINS_WORKSPACE=/var/lib/jenkins/workspace/Jenkins-CICD
#/var/lib/jenkins/workspace/Jenkins-CICD
echo "sending code to jenkins workspace..."
sudo cp -R ./* ${JENKINS_WORKSPACE}

# Set the URL for the Jenkins job
URL="http://localhost:8090${JOB_PATH}"

# Prompt for username and password
echo "username:"
read username
echo "password:"
read -s password  # The -s option hides password input

response=$(curl -s -X POST "$URL" -u "${username}:${password}")

# Debug: Show the response from the Jenkins server
echo "Response from Jenkins: $response"
