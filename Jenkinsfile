pipeline {
    agent any
    tools {
        jdk 'JDK17'
        maven 'maven'
    }

    environment{
        APP_NAME = 'automation_test'
        SONAR_SERVER_HOME = tool 'sonarqube-server'
        SONAR_HOST_URL = 'http://localhost:9000'
        DOCKER_IMAGE = 'automation-test'
        IMAGE_TAG = "${BUILD_NUMBER}"
        DOCKER_USERNAME = 'codedev001'
        DOCKER_CREDENTIALS_ID = 'e440bc91-a2a0-45d8-ae88-b13a659a1bdd'
    }

    stages {
        stage('Git Checkout') {
            steps {
                git changelog: false, credentialsId: 'a5d2cdac-fdd7-4b7a-85ab-3e41d14dcd3a', poll: false, url: 'https://github.com/kueshy/devops-jenkins-automation'
            }
        }

        stage('Compile') {
            steps {
                sh "mvn clean compile -DskipTests=true"
            }
        }

        stage('OWASP Dependency-Check Vulnerabilities') {
            steps {
                dependencyCheck additionalArguments: '''
                    -o './'
                    -s './'
                    -f 'ALL'
                    --prettyPrint''', odcInstallation: 'OWASP Dependency-Check Vulnerabilities'
                dependencyCheckPublisher pattern: 'dependency-check-report.xml'
            }
        }

//         stage('SonarQube ================') {
//             steps {
//                 echo "Running SonarQube ======================..."
//                 withSonarQubeEnv('sonarqube-server') {
//                         sh '''
//                             $SONAR_SERVER_HOME/bin/sonar-scanner \
//                                 -Dsonar.projectKey=${APP_NAME} \
//                                 -Dsonar.java.binaries=. \
//                                 -Dsonar.projectName="${APP_NAME}"
//                         '''
//                     }
//             }
//         }

        stage('SonarQube Analysis') {
            steps {
                echo "Running SonarQube analysis..."
                withSonarQubeEnv('sonarqube-server') {
                        sh '''
                            mvn sonar:sonar \
                                -Dsonar.projectKey=${APP_NAME} \
                                -Dsonar.projectName="${APP_NAME}" \
                                -Dsonar.host.url=${SONAR_HOST_URL}
                        '''
                    }
            }
        }

        stage('Build') {
            steps {
                sh "mvn clean package -DskipTests=true"
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Using Docker Pipeline plugin
                    echo "Build Docker image..."
                    def customImage = docker.build("codedev001/${DOCKER_IMAGE}:${IMAGE_TAG}")
                    echo "Image built: ${DOCKER_USERNAME}/${DOCKER_IMAGE}:${IMAGE_TAG}"

                    // Tag as latest
                    sh "docker tag codedev001/${DOCKER_IMAGE}:${IMAGE_TAG} codedev001/${DOCKER_IMAGE}:latest"
                }
            }
        }

        stage('Push to Registry') {
            steps {
                script {
                    docker.withRegistry('', 'e440bc91-a2a0-45d8-ae88-b13a659a1bdd') {

                        def image = docker.image("${DOCKER_USERNAME}/${DOCKER_IMAGE}:${IMAGE_TAG}")
                        image.push("${IMAGE_TAG}")
                        image.push('latest')

                        echo "Images pushed successfully!"

                        // Tag the image correctly for Docker Hub
                        //sh "docker tag codedev001/${DOCKER_IMAGE}:${IMAGE_TAG} codedev001/${DOCKER_IMAGE}:${IMAGE_TAG}"
                        //sh "docker tag codedev001/${DOCKER_IMAGE}:${IMAGE_TAG} codedev001/${DOCKER_IMAGE}:latest"

                        // Now push both tags
                        //sh "docker push codedev001/${DOCKER_IMAGE}:${IMAGE_TAG}"
                        //sh "docker push codedev001/${DOCKER_IMAGE}:latest"
                    }
                }
            }
        }

//         stage('Deploy to Remote Server') {
//             steps {
//                 script {
//                     sshagent(['ssh-key-id']) {
//                         sh """
//                             ssh -o StrictHostKeyChecking=no user@your-server-ip '
//                                 echo "Stopping old container..."
//                                 docker stop automation-test || true
//                                 docker rm automation-test || true
//
//                                 echo "Pulling latest image..."
//                                 docker pull codedev001/automation-test:${IMAGE_TAG}
//
//                                 echo "Starting new container..."
//                                 docker run -d --name automation-test \
//                                     -p 8085:8080 \
//                                     codedev001/automation-test:${IMAGE_TAG}
//
//                                 echo "Deployment successful!"
//                             '
//                         """
//                     }
//                 }
//             }
//         }


    }
    post {
        success {
            echo "Pipeline completed successfully!"
        }
        failure {
            echo "Pipeline failed!"
        }
        always {
            // Cleanup
            sh "docker system prune -f"
        }
    }
}


