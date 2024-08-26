VERSION=$(awk -F'[<>]' '/<version>/{print $3; exit}' pom.xml)

mkdir -p target

docker buildx build --platform linux/amd64 \
    -t datadog/build-dd-serverless-java-agent:$VERSION \
    . --load

dockerId=$(docker create datadog/build-dd-serverless-java-agent:$VERSION)
docker cp $dockerId:/app/target/dd-serverless-azure-java-agent-$VERSION-jar-with-dependencies.jar ./target/dd-serverless-azure-java-agent-$VERSION-jar-with-dependencies.jar
