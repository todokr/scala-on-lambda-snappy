# How to build

// TODO check required

```sh 
scala-on-lambda-snappy/scalanative $ docker cp ./ native-builder:/build/main/
scala-on-lambda-snappy/scalanative $ docker exec -it native-builder sbt 'project <project-name>' nativeLink
scala-on-lambda-snappy/scalanative $ docker cp native-builder:/build/main/<project-name>/target/scala-2.11/<lower-project-name>-out <projectName>/bootstrap
cd <projectName>
zip function.zip bootstrap

# runtime layer
docker cp <container ID>:/build/runtime/ ./runtime/
cd runtime
zip -r runtime.zip .
```


