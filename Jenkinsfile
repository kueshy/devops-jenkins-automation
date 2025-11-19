// // pipeline {
// //     agent any
// //     tools {
// //         jdk 'JDK17'
// //         maven 'maven'
// //     }
// //
// //     environment{
// //         APP_NAME = 'automation_test'
// //         SONAR_SERVER_HOME = tool 'sonarqube-server'
// //         SONAR_HOST_URL = 'http://localhost:9000'
// //         DOCKER_IMAGE = 'automation-test'
// //         IMAGE_TAG = "${BUILD_NUMBER}"
// //         DOCKER_USERNAME = 'codedev001'
// //         DOCKER_CREDENTIALS_ID = 'e440bc91-a2a0-45d8-ae88-b13a659a1bdd'
// //     }
// //
// //     stages {
// //         stage('Git Checkout') {
// //             steps {
// //                 git changelog: false, credentialsId: 'a5d2cdac-fdd7-4b7a-85ab-3e41d14dcd3a', poll: false, url: 'https://github.com/kueshy/devops-jenkins-automation'
// //             }
// //         }
// //
// //         stage('Compile') {
// //             steps {
// //                 sh "mvn clean compile -DskipTests=true"
// //             }
// //         }
// //
// //         stage('OWASP Dependency-Check Vulnerabilities') {
// //             steps {
// //                 dependencyCheck additionalArguments: '''
// //                     -o './'
// //                     -s './'
// //                     -f 'ALL'
// //                     --prettyPrint''', odcInstallation: 'OWASP Dependency-Check Vulnerabilities'
// //                 dependencyCheckPublisher pattern: 'dependency-check-report.xml'
// //             }
// //         }
// //
// //         stage('SonarQube ================') {
// //             steps {
// //                 echo "Running SonarQube ======================..."
// //                 withSonarQubeEnv('sonarqube-server') {
// //                         sh '''
// //                             $SONAR_SERVER_HOME/bin/sonar-scanner \
// //                                 -Dsonar.projectKey=${APP_NAME} \
// //                                 -Dsonar.java.binaries=. \
// //                                 -Dsonar.projectName="${APP_NAME}"
// //                         '''
// //                     }
// //             }
// //         }
// //
// //         stage('SonarQube Analysis') {
// //             steps {
// //                 echo "Running SonarQube analysis..."
// //                 withSonarQubeEnv('sonarqube-server') {
// //                         sh '''
// //                             mvn sonar:sonar \
// //                                 -Dsonar.projectKey=${APP_NAME} \
// //                                 -Dsonar.projectName="${APP_NAME}" \
// //                                 -Dsonar.host.url=${SONAR_HOST_URL}
// //                         '''
// //                     }
// //             }
// //         }
// //
// //         stage('Build') {
// //             steps {
// //                 sh "mvn clean package -DskipTests=true"
// //             }
// //         }
// //
// //         stage('Build Docker Image') {
// //             steps {
// //                 script {
// //                     // Using Docker Pipeline plugin
// //                     echo "Build Docker image..."
// //                     def customImage = docker.build("codedev001/${DOCKER_IMAGE}:${IMAGE_TAG}")
// //                     echo "Image built: ${DOCKER_USERNAME}/${DOCKER_IMAGE}:${IMAGE_TAG}"
// //
// //                     // Tag as latest
// //                     sh "docker tag codedev001/${DOCKER_IMAGE}:${IMAGE_TAG} codedev001/${DOCKER_IMAGE}:latest"
// //                 }
// //             }
// //         }
// //
// //         stage('Push to Registry') {
// //             steps {
// //                 script {
// //                     docker.withRegistry('', 'e440bc91-a2a0-45d8-ae88-b13a659a1bdd') {
// //
// //                         def image = docker.image("${DOCKER_USERNAME}/${DOCKER_IMAGE}:${IMAGE_TAG}")
// //                         image.push("${IMAGE_TAG}")
// //                         image.push('latest')
// //
// //                         echo "Images pushed successfully!"
// //
// //                         // Tag the image correctly for Docker Hub
// //                         //sh "docker tag codedev001/${DOCKER_IMAGE}:${IMAGE_TAG} codedev001/${DOCKER_IMAGE}:${IMAGE_TAG}"
// //                         //sh "docker tag codedev001/${DOCKER_IMAGE}:${IMAGE_TAG} codedev001/${DOCKER_IMAGE}:latest"
// //
// //                         // Now push both tags
// //                         //sh "docker push codedev001/${DOCKER_IMAGE}:${IMAGE_TAG}"
// //                         //sh "docker push codedev001/${DOCKER_IMAGE}:latest"
// //                     }
// //                 }
// //             }
// //         }
// //
// //         stage('Deploy Container') {
// //             steps {
// //                 script {
// //                     sh """
// //                         docker rm -f automation-test || true
// //                         docker pull ${DOCKER_USERNAME}/${DOCKER_IMAGE}:${IMAGE_TAG}
// //                         docker run -d --name automation-test \
// //                             -p 8085:12080 \
// //                             ${DOCKER_USERNAME}/${DOCKER_IMAGE}:${IMAGE_TAG}
// //
// //                         echo "⏳ Waiting for container to start..."
// //                         sleep 10
// //
// //                         echo " Checking running containers..."
// //                         docker ps | grep automation-test || { echo "❌ Container failed to start"; exit 1; }
// //
// //                         echo " Container deployed and working!"
// //
// //                     """
// //                 }
// //             }
// //         }
// //
// //
// // //         stage('Deploy to Remote Server') {
// // //             steps {
// // //                 script {
// // //                     sshagent(['ssh-key-id']) {
// // //                         sh """
// // //                             ssh -o StrictHostKeyChecking=no user@your-server-ip '
// // //                                 echo "Stopping old container..."
// // //                                 docker stop automation-test || true
// // //                                 docker rm automation-test || true
// // //
// // //                                 echo "Pulling latest image..."
// // //                                 docker pull codedev001/automation-test:${IMAGE_TAG}
// // //
// // //                                 echo "Starting new container..."
// // //                                 docker run -d --name automation-test \
// // //                                     -p 8085:8080 \
// // //                                     codedev001/automation-test:${IMAGE_TAG}
// // //
// // //                                 echo "Deployment successful!"
// // //                             '
// // //                         """
// // //                     }
// // //                 }
// // //             }
// // //         }
// //
// //
// //     }
// //     post {
// //         success {
// //             echo "Pipeline completed successfully!"
// //         }
// //         failure {
// //             echo "Pipeline failed!"
// //         }
// //         always {
// //             // Cleanup
// //             sh "docker system prune -f"
// //         }
// //     }
// // }
// //
// //
// //
//
// pipeline {
//     agent any
//     tools {
//         jdk 'JDK17'
//         maven 'maven'
//     }
//
//     environment{
//         APP_NAME = 'automation_test'
//         SONAR_SERVER_HOME = tool 'sonarqube-server'
//         SONAR_HOST_URL = 'http://localhost:9000'
//         DOCKER_IMAGE = 'automation-test'
//         IMAGE_TAG = "${BUILD_NUMBER}"
//         DOCKER_USERNAME = 'codedev001'
//         DOCKER_CREDENTIALS_ID = 'e440bc91-a2a0-45d8-ae88-b13a659a1bdd'
//     }
//
//     stages {
//         stage('Git Checkout') {
//             steps {
//                 git changelog: false, credentialsId: 'a5d2cdac-fdd7-4b7a-85ab-3e41d14dcd3a', poll: false, url: 'https://github.com/kueshy/devops-jenkins-automation'
//             }
//         }
//
//         stage('Compile') {
//             steps {
//                 sh "mvn clean compile -DskipTests=true"
//             }
//         }
//
//         stage('OWASP Dependency-Check Vulnerabilities') {
//             steps {
//                 dependencyCheck additionalArguments: '''
//                     -o './'
//                     -s './'
//                     -f 'ALL'
//                     --prettyPrint''', odcInstallation: 'OWASP Dependency-Check Vulnerabilities'
//                 dependencyCheckPublisher pattern: 'dependency-check-report.xml'
//             }
//         }
//
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
//
//         stage('SonarQube Analysis') {
//             steps {
//                 echo "Running SonarQube analysis..."
//                 withSonarQubeEnv('sonarqube-server') {
//                         sh '''
//                             mvn sonar:sonar \
//                                 -Dsonar.projectKey=${APP_NAME} \
//                                 -Dsonar.projectName="${APP_NAME}" \
//                                 -Dsonar.host.url=${SONAR_HOST_URL}
//                         '''
//                     }
//             }
//         }
//
//         stage('Build') {
//             steps {
//                 sh "mvn clean package -DskipTests=true"
//             }
//         }
//
//         stage('Set Branch Name') {
//             steps {
//                 script {
//                     // Sanitize branch name (replace / with -)
//                     // Get current branch name from git
//                     def branch = sh(
//                         script: "git rev-parse --abbrev-ref HEAD",
//                         returnStdout: true
//                     ).trim()
//
//                     // Sanitize branch name
//                     env.BRANCH_NAME_SANITIZED = branch.replaceAll('/', '-')
//                     env.DOCKER_NEW_IMAGE = "${env.DOCKER_IMAGE}-${env.BRANCH_NAME_SANITIZED}"
//                     echo "Sanitized branch: ${env.BRANCH_NAME_SANITIZED}"
//                     echo "Docker image: ${env.DOCKER_NEW_IMAGE}"
//                 }
//             }
//         }
//
//         stage('Build Docker Image') {
//             steps {
//                 script {
//                     // Using Docker Pipeline plugin
//                     echo "Build Docker image..."
//                     def customImage = docker.build("${DOCKER_NEW_IMAGE}:${IMAGE_TAG}")
//                     echo "Image built: ${DOCKER_NEW_IMAGE}:${IMAGE_TAG}"
//
//                     // Tag as latest
//                     sh "docker tag ${DOCKER_NEW_IMAGE}:${IMAGE_TAG} ${DOCKER_NEW_IMAGE}:latest"
//                 }
//             }
//         }
//
//         stage('Push to Registry') {
//             steps {
//                 script {
//                     docker.withRegistry('http://192.168.4.39:5000', 'a2deaec4-730d-44f2-b680-7a95b9f899a2') {
//
//                         def image = docker.image("${DOCKER_NEW_IMAGE}:${IMAGE_TAG}")
//                         image.push("${IMAGE_TAG}")
//                         image.push('latest')
//
//                         echo "Images pushed successfully!"
//
//                         // Tag the image correctly for Docker Hub
//                         //sh "docker tag codedev001/${DOCKER_IMAGE}:${IMAGE_TAG} codedev001/${DOCKER_IMAGE}:${IMAGE_TAG}"
//                         //sh "docker tag codedev001/${DOCKER_IMAGE}:${IMAGE_TAG} codedev001/${DOCKER_IMAGE}:latest"
//
//                         // Now push both tags
//                         //sh "docker push codedev001/${DOCKER_IMAGE}:${IMAGE_TAG}"
//                         //sh "docker push codedev001/${DOCKER_IMAGE}:latest"
//                     }
//                 }
//             }
//         }
//
//         stage('Deploy via SSH with Password') {
//             steps {
//                 script {
//                     // Store credentials in Jenkins (Username + Password)
//                     withCredentials([usernamePassword(credentialsId: 'ssh-user-pass',
//                                                      usernameVariable: 'SSH_USER',
//                                                      passwordVariable: 'SSH_PASS')]) {
//
//                         sh """
//                             sshpass -p "$SSH_PASS" ssh -o StrictHostKeyChecking=no $SSH_USER@192.168.4.39 '
//                                 cd /opt/deployment-folder
//
//                                 export PIPELINE_IMAGE_TAG=${IMAGE_TAG}
//                                 export PIPELINE_CONTAINER_NAME=${DOCKER_IMAGE}-${BRANCH_NAME_SANITIZED}
//
//                                 echo "Pulling latest image..."
//                                 docker-compose pull
//
//                                 echo "Stopping existing containers..."
//                                 docker-compose down
//
//                                 echo "Starting new deployment..."
//                                 docker-compose up -d --force-recreate
//
//                                 echo "Deployment status:"
//                                 docker ps | grep automation-test
//                             '
//                         """
//                     }
//                 }
//             }
//         }
//
//         stage('Deploy to Production') {
//             when {
//                 expression { env.BRANCH_NAME_SANITIZED == 'main' }
//             }
//             steps {
//                 script {
//                     withCredentials([usernamePassword(credentialsId: 'ssh-user-pass',
//                                                      usernameVariable: 'SSH_USER',
//                                                      passwordVariable: 'SSH_PASS')]) {
//
//                         sh """
//                             sshpass -p "$SSH_PASS" ssh -o StrictHostKeyChecking=no $SSH_USER@192.168.4.40 '
//                                 docker pull 192.168.4.39:5000/${DOCKER_NEW_IMAGE}:${IMAGE_TAG} || true
//                                 docker rm -f ${DOCKER_IMAGE}-${BRANCH_NAME_SANITIZED} || true
//                                 docker run -d --name ${DOCKER_IMAGE}-${BRANCH_NAME_SANITIZED} \
//                                     -p 8080:8080 192.168.4.39:5000/${DOCKER_NEW_IMAGE}:${IMAGE_TAG}
//                                 docker ps | grep ${DOCKER_IMAGE}-${BRANCH_NAME_SANITIZED}
//                             '
//                         """
//                     }
//                 }
//             }
//         }
//
//         // stage('Deploy to Server with Docker Compose') {
//         //     steps {
//         //         sshagent(['ssh-key-id']) {
//         //             sh """
//         //                 ssh -o StrictHostKeyChecking=no root@192.168.4.39 '
//         //                     cd /opt/deployment-folder
//
//         //                     echo "Pulling latest image..."
//         //                     docker-compose pull
//
//         //                     echo "Stopping existing container..."
//         //                     docker-compose down
//
//         //                     echo "Starting new deployment..."
//         //                     docker-compose up -d --force-recreate
//
//         //                     echo "Checking deployment status..."
//         //                     docker ps | grep automation-test
//         //                 '
//         //             """
//         //         }
//         //     }
//         // }
//
//
//         // stage('Deploy Container') {
//         //     steps {
//         //         script {
//         //             sh """
//         //                 docker rm -f automation-test || true
//         //                 docker pull ${DOCKER_USERNAME}/${DOCKER_IMAGE}:${IMAGE_TAG}
//         //                 docker run -d --name automation-test \
//         //                     -p 8085:12080 \
//         //                     ${DOCKER_USERNAME}/${DOCKER_IMAGE}:${IMAGE_TAG}
//
//         //                 echo "⏳ Waiting for container to start..."
//         //                 sleep 10
//
//         //                 echo " Checking running containers..."
//         //                 docker ps | grep automation-test || { echo "❌ Container failed to start"; exit 1; }
//
//         //                 echo " Container deployed and working!"
//
//         //             """
//         //         }
//         //     }
//         // }
//
//     }
//     post {
//         success {
//             echo "Pipeline completed successfully!"
//         }
//         failure {
//             echo "Pipeline failed!"
//         }
//         //always {
//             // Cleanup
//             //sh "docker system prune -f"
//         //}
//     }
// }
//
// // stage('Deploy') {
// //     when {
// //         anyOf {
// //             branch 'main'
// //             branch 'develop'
// //         }
// //     }
// //     steps {
// //         script {
// //             if (env.BRANCH_NAME == 'main') {
// //                 sh "echo Deploying to Production Server"
// //             } else if (env.BRANCH_NAME == 'develop') {
// //                 sh "echo Deploying to Staging Server"
// //             }
// //         }
// //     }
// // }
//
//

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
    parameters{
        booleanParam(
            name: 'RUN_SECURITY_SCAN',
            defaultValue: true,
            description: 'Run security vulnerability scan'
        )
        booleanParam(
            name: 'RUN_QUALITY_GATES',
            defaultValue: false,
            description: 'Run quality gates'
        )
    }

    triggers {
        githubPush() // GitHub
        // gitlabPush()  // For GitLab (requires GitLab plugin)
    }

    stages {
        stage('Git Checkout') {
            steps {
                git changelog: false, credentialsId: 'a5d2cdac-fdd7-4b7a-85ab-3e41d14dcd3a', poll: false, url: 'https://github.com/kueshy/devops-jenkins-automation'
            }
        }

        stage('Code Analysis') {
            parallel {
                stage('Checkstyle') {
                    steps {
                        script {
                            echo "Running Checkstyle..."
                            sh 'mvn checkstyle:check -B'
                        }
                    }
                }

                stage('PMD') {
                    steps {
                        script {
                            echo "Running PMD..."
                            sh 'mvn pmd:check -B'
                        }
                    }
                }

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

        stage('SonarQube ================') {
            steps {
                echo "Running SonarQube ======================..."
                withSonarQubeEnv('sonarqube-server') {
                        sh '''
                            $SONAR_SERVER_HOME/bin/sonar-scanner \
                                -Dsonar.projectKey=${APP_NAME} \
                                -Dsonar.java.binaries=. \
                                -Dsonar.projectName="${APP_NAME}"
                        '''
                    }
            }
        }

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

        stage('Quality Gate') {
            when {
                expression { params.RUN_QUALITY_GATES }
            }
            steps {
                script {
                    echo "Waiting for SonarQube Quality Gate..."

                    timeout(time: 5, unit: 'MINUTES') {
                        def qg = waitForQualityGate()
                        if (qg.status != 'OK') {
                            error "Quality Gate failed: ${qg.status}"
                        }
                        echo "Quality Gate passed!"
                    }
                }
            }
        }

        stage('Build') {
            steps {
                sh "mvn clean package -DskipTests=true"
            }
        }

        stage('Set Branch Name') {
            steps {
                script {
                    // Sanitize branch name (replace / with -)
                    // Get current branch name from git
                    def branch = sh(
                        script: "git rev-parse --abbrev-ref HEAD",
                        returnStdout: true
                    ).trim()

                    // Sanitize branch name
                    env.BRANCH_NAME_SANITIZED = branch.replaceAll('/', '-')
                    env.DOCKER_NEW_IMAGE = "${env.DOCKER_IMAGE}-${env.BRANCH_NAME_SANITIZED}"
                    echo "Sanitized branch: ${env.BRANCH_NAME_SANITIZED}"
                    echo "Docker image: ${env.DOCKER_NEW_IMAGE}"
                }
            }
        }

        stage('Build Docker Image') {
            when {
                anyOf {
                    branch 'master'
                    branch 'main'
                }
            }
            steps {
                script {
                    // Using Docker Pipeline plugin
                    echo "Build Docker image..."
                    def customImage = docker.build("${DOCKER_NEW_IMAGE}:${IMAGE_TAG}")
                    echo "Image built: ${DOCKER_NEW_IMAGE}:${IMAGE_TAG}"

                    // Tag as latest
                    sh "docker tag ${DOCKER_NEW_IMAGE}:${IMAGE_TAG} ${DOCKER_NEW_IMAGE}:latest"
                }
            }
        }

        stage('Push to Registry') {
            when {
                anyOf {
                    branch 'master'
                    branch 'main'
                }
            }
            steps {
                script {
                    docker.withRegistry('http://192.168.4.39:5000', 'a2deaec4-730d-44f2-b680-7a95b9f899a2') {

                        def image = docker.image("${DOCKER_NEW_IMAGE}:${IMAGE_TAG}")
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

        stage('Deploy via SSH with Password') {
            when {
                    branch 'master'
            }
            steps {
                script {
                    // Store credentials in Jenkins (Username + Password)
                    withCredentials([usernamePassword(credentialsId: 'ssh-user-pass',
                                                     usernameVariable: 'SSH_USER',
                                                     passwordVariable: 'SSH_PASS')]) {

                        sh """
                            sshpass -p "$SSH_PASS" ssh -o StrictHostKeyChecking=no $SSH_USER@192.168.4.39 '
                                cd /opt/deployment-folder

                                export PIPELINE_IMAGE_TAG=${IMAGE_TAG}
                                export PIPELINE_CONTAINER_NAME=${DOCKER_IMAGE}-${BRANCH_NAME_SANITIZED}

                                echo "Pulling latest image..."
                                docker-compose pull

                                echo "Stopping existing containers..."
                                docker-compose down

                                echo "Starting new deployment..."
                                docker-compose up -d --force-recreate

                                echo "Deployment status:"
                                docker ps | grep automation-test
                            '
                        """
                    }
                }
            }
        }

        stage('Deploy to Production') {
            when {
                expression { env.BRANCH_NAME_SANITIZED == 'main' }
            }
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'ssh-user-pass',
                                                     usernameVariable: 'SSH_USER',
                                                     passwordVariable: 'SSH_PASS')]) {

                        sh """
                            sshpass -p "$SSH_PASS" ssh -o StrictHostKeyChecking=no $SSH_USER@192.168.4.40 '
                                docker pull 192.168.4.39:5000/${DOCKER_NEW_IMAGE}:${IMAGE_TAG} || true
                                docker rm -f ${DOCKER_IMAGE}-${BRANCH_NAME_SANITIZED} || true
                                docker run -d --name ${DOCKER_IMAGE}-${BRANCH_NAME_SANITIZED} \
                                    -p 8080:8080 192.168.4.39:5000/${DOCKER_NEW_IMAGE}:${IMAGE_TAG}
                                docker ps | grep ${DOCKER_IMAGE}-${BRANCH_NAME_SANITIZED}
                            '
                        """
                    }
                }
            }
        }

        // stage('Deploy to Server with Docker Compose') {
        //     steps {
        //         sshagent(['ssh-key-id']) {
        //             sh """
        //                 ssh -o StrictHostKeyChecking=no root@192.168.4.39 '
        //                     cd /opt/deployment-folder

        //                     echo "Pulling latest image..."
        //                     docker-compose pull

        //                     echo "Stopping existing container..."
        //                     docker-compose down

        //                     echo "Starting new deployment..."
        //                     docker-compose up -d --force-recreate

        //                     echo "Checking deployment status..."
        //                     docker ps | grep automation-test
        //                 '
        //             """
        //         }
        //     }
        // }


        // stage('Deploy Container') {
        //     steps {
        //         script {
        //             sh """
        //                 docker rm -f automation-test || true
        //                 docker pull ${DOCKER_USERNAME}/${DOCKER_IMAGE}:${IMAGE_TAG}
        //                 docker run -d --name automation-test \
        //                     -p 8085:12080 \
        //                     ${DOCKER_USERNAME}/${DOCKER_IMAGE}:${IMAGE_TAG}

        //                 echo "⏳ Waiting for container to start..."
        //                 sleep 10

        //                 echo " Checking running containers..."
        //                 docker ps | grep automation-test || { echo "❌ Container failed to start"; exit 1; }

        //                 echo " Container deployed and working!"

        //             """
        //         }
        //     }
        // }

    }
    post {
        success {
            echo "Pipeline completed successfully!"
        }
        failure {
            echo "Pipeline failed!"
        }
        //always {
            // Cleanup
            //sh "docker system prune -f"
        //}
    }
}

