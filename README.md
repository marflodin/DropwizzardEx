# DropwizzardEx

### Publish image
* Run mvn package to construct jar
* Create image by running:
> docker build -t dropwizard-ex-kubernetes:latest .
* Tag and Publish image to docker
> docker tag [imageId] [imageName]:[imageVersion]
> docker push [imageName]:[imageVersion]

### Setup service in minikube
* Update kubernetes-deployment.yaml with correct image
* Start minikube
> minikube start --vm-driver=xhyve
* Create the Deployment 
> kubectl apply -f kubernetes-deployment.yaml
* Create the Service
> kubectl apply -f kubernetes-service.yaml
* Get service url
> minikube service frontend --url
* Access swagger on [serviceUrl]/swagger

### Minikube dashboard
> minikube dashboard

### Update service
* Change image version (rolling upgrade)
> kubectl set image deployment/dropwizard-api [imageName]:[imageVersion]
* Example:
> kubectl set image deployment/dropwizard-api marflo/dropwizard-ex:1.0.1

### Kubernetes commands


### Run locally with redis
* Start a local redis
> docker pull redis:4.0.4
> docker run -d -p 6379:6379 redis:4.0.4
* Start application with `config-local.yml`


