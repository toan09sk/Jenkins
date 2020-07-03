def username = 'Jenkins'
def gv
pipeline {

    agent any
    parameters{
        choice(name: 'VERSION', choices: ['1.1.0','1.2.0','1.3.0'], description: '')
        booleanParam(name: 'executeTests', defaultValue: true, description: '')
    }

    environment {
        NEW_VERSION = '1.3.0'
    }

    tools {
        gradle 'Gradle'
    }
    
    stages {

         stage("init") {
                steps {
                    script {
                        gv = load "script.groovy"
                }

                echo 'execute yarn...'
                nodejs('Node') {
                    sh 'yarn install'
                    sh 'node -v'
                    sh 'npm -v'
                }

            }
         }
         stage("build") {
                when {
                    expression {
                        BRANCH_NAME == 'develop'
                    }
                }
                steps {
                    script {
                        gv.buildApp()
                }
            }
        }

        stage("test") {
            when {
                expression {
                        params.executeTests
                }
            }
            steps {
                    script {
                        gv.testApp()
                }
            }
        }

        stage("deploy") {

           steps {
                    script {
                        gv.deployApp()
                }
            }
        }
    }
     post {
        always {
            echo 'hello world'
            //  emailext body: "${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}",
            //  recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']],
            //  subject: "Jenkins Build ${currentBuild.currentResult}: Job ${env.JOB_NAME}"
        }
        failure {
            echo 'will email'
        }
    }
}