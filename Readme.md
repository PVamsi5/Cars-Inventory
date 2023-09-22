#Basic Crud Application with spring boot using repository pattern pubilshed in GCP. 
>technologies used are java, spring framework, GKE (google k8s engine).

##Also Created Api's to get and post data into application. 
>Had collection of api's in postman. 

##Had Dockerfile to create docker image.
```
docker build -t <image_name>:<image_tag> .
```

##Published into docker hub. 
```
docker tag <image_name>:<image_tag> <repository_name>:<image:newtag>

docker push <repository_name>:<image:newtag>
```
##Then had cloud configuration file cars-crud-k8s-demp.yaml to publish image in to GCP.
1.Create a Project in GCP.

2.Create a cluster in GKE.

3.Open the cluster in cloud shell

4.Upload the cloud config file (check with 'ls' command whether file is uploaded or not)

5.Run the below command and after deployment expose it.
```
kubectl apply -f <configfile_name>
```