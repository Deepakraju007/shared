def call() {
    pipeline {
        agent any
        stages {
               stage('Git Checkout') {
                 steps {
                     script {
                        git branch: 'node-dev', url: 'https://github.com/Deepakraju007/multi-branch.git' //project-repo
                     }
                 }
             }
            stage ('Build') {
                steps {
                    script {
                        sh 'npm install'
                    }
                }
            }
            // stage ('SonarQube Analysis') {
            //     steps {
            //         script { 
            //             def scannerHome = tool 'sonarscanner4'
            //             withSonarQubeEnv('sonar-pro') {
            //                 sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=node.js-app"
            //             }
            //         }
            //     }
            // }
            stage('Docker Build Images') {
                steps {
                    script {
                        sh 'docker build -t deepak5227/multi:v1 .'
                        sh 'docker images'
                        sh 'docker ps'
                    }
                }
            }
            // stage('Docker Push') {
            //     steps {
            //         script {
            //             withCredentials([string(credentialsId: 'dockerPass', variable: 'dockerPassword')]) {
            //                 sh "docker login -u naresh2603 -p ${dockerPassword}"
            //                 sh 'docker push naresh2603/multi:v2'
            //                 sh 'docker rmi naresh2603/multi:v2'
            //             }
            //         }
            //     }
            // }
            // stage('Deploy on k8s') {
            //     steps {
            //         script {
            //             withKubeCredentials(kubectlCredentials: [[ credentialsId: 'kubernetes', namespace: 'ms' ]]) {
            //                 sh 'kubectl apply -f kube.yaml'
            //                 sh 'kubectl get pods -o wide'
            //             }
            //         }
            //     }
            // }
        }
    }
}
