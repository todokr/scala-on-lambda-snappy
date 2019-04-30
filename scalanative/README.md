# How to build

// TODO check required

```sh 
docker cp ./* <container ID>:/build/main/
docker run -it <container name> bash

# in docker container
sbt 'project hello' nativeLink

# after exit from container
docker cp <container ID>:/build/main/target/<artifact> ./hello/
zip function.zip bootstrap <artifact>

docker cp <container ID>:/build/runtime/ ./runtime/
zip -r runtime.zip ./runtime/*
```


