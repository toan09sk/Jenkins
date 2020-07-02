def buildApp() {
    echo "building the application..."
    echo "building version ${NEW_VERSION}"
    echo "I said, Hello Mr. ${username}"
    echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
}

def testApp() {
    echo 'testing the application...'
}

def deployApp() {
    echo 'deploying the application...'
    echo "deploying version ${params.VERSION}"
}

return this