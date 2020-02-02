UID = "ADMIN-${env.BUILD_ID}"
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
}