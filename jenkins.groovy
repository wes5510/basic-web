NODE_VER = "node10.16.3"

node {
	stage('Init') {
		checkout scm
	}

	stage('Deploy') {
		withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: 'jenkins', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY']]) {
			step([$class: 'AWSCodeDeployPublisher', applicationName: 'basic-web-deploy', awsAccessKey: AWS_ACCESS_KEY_ID, awsSecretKey: AWS_SECRET_ACCESS_KEY, credentials: 'awsAccessKey', deploymentGroupAppspec: false, deploymentGroupName: 'basic-web-dg', deploymentMethod: 'deploy', excludes: '', iamRoleArn: '', includes: '**', proxyHost: '', proxyPort: 0, region: 'ap-northeast-2', s3bucket: 'basic-web-bin', s3prefix: '', subdirectory: '', versionFileName: '', waitForCompletion: false])
		}
		
	}
}