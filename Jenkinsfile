pipeline {
    agent any
    stages {
        stage('Build test code') {
            steps {
                bat 'mvn clean install -DskipTests'
            }
        }
        stage('Run selenium grid') {
            steps {
                bat 'docker-compose up -d'
            }
        }
        stage('Execute test') {
            steps {
                bat 'mvn test'
                bat 'docker-compose down'
            }
        }
    }
    post {
        always {
            script {
                allure([
                        includeProperties: false,
                        jdk              : '',
                        properties       : [],
                        reportBuildPolicy: 'ALWAYS',
                        results          : [[path: 'target/allure-results']]
                ])
            }
        }
    }
}