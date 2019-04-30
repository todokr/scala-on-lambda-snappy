# How to build

// TODO check required

```sh 
docker cp build.sbt <container ID>:/build/main/
docker cp project/ <container ID>:/build/main/
docker cp hello/ native-builder:/build/main/
docker run -it <container name> sbt 'project hello' nativeLink

docker cp <container ID>:/build/main/target/<artifact> ./hello/bootstrap
zip function.zip bootstrap

# runtime layer
docker cp <container ID>:/build/runtime/ ./runtime/
cd runtime
zip -r runtime.zip .
```


