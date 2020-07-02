CODE_CHANGES = getGitChanges()
pipeline {

    agent any
    environment {
        NEW_VERSION = '1.3.0'
    }
    
    stages {

         stage("build") {
                when {
                    expression {
                        BRANCH_NAME == 'dev' && CODE_CHANGES == true
                    }
                }
                steps {
                    echo 'building the application...'
                    echo "building version ${NEW_VERSION}"
                }
            }

        stage("test") {

            steps {
                echo 'testing the application...'
            }
        }

        stage("deploy") {

            steps {
                echo 'deploying the application...'
            }
        }
    }
}