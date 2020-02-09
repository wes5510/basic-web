NODE_VER = "node10.16.3"

node {
	stage('Init') {
		checkout scm
		nodejs(NODE_VER) {
			sh "npm install"
		}
	}

	stage('Build') {
		nodejs(NODE_VER) {
			sh "npm run build"
		}
	}
	
	stage('Build') {
		withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: 'jenkins', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY']]) {
			step([$class: 'AWSCodeDeployPublisher', applicationName: 'basic-web', awsAccessKey: AWS_ACCESS_KEY_ID, awsSecretKey: AWS_SECRET_ACCESS_KEY, credentials: 'awsAccessKey', deploymentGroupAppspec: false, deploymentGroupName: 'test', deploymentMethod: 'deploy', excludes: '', iamRoleArn: '', includes: '**', proxyHost: '', proxyPort: 0, region: 'ap-northeast-1', s3bucket: '', s3prefix: '', subdirectory: '', versionFileName: '', waitForCompletion: false])
		}
		
	}
}