#!/bin/bash

# Set the URL for the Jenkins job
URL="http://localhost:8090/job/first-jenkins-job/build?token=my-test-token"

# Prompt for username and password
echo "username:"
read username
echo "password:"
read -s password  # The -s option hides password input

response=$(curl -s -X POST "$URL" -u "${username}:${password}")

# Debug: Show the response from the Jenkins server
echo "Response from Jenkins: $response"
