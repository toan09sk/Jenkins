def buildApp() {
    echo "building the application..."
    echo "building version ${NEW_VERSION}"
    echo "I said, Hello Mr. ${username}"
    echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"

    
    echo 'execute gradle...'
    sh 'javac Hello.java'
    sh 'java Hello'
    sh 'gradle -v'
}

def testApp() {
    echo 'testing the application...'
}

def deployApp() {
    echo 'deploying the application...'
    echo "deploying version ${params.VERSION}"
}

return this