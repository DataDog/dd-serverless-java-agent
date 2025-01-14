VERSION=$(awk -F '[<>]' '/<version>/{print $3; exit}' pom.xml)

mkdir -p target

docker buildx build --platform linux/amd64 \
    -t datadog/build-dd-serverless-compat-java-agent:$VERSION \
    . --load

dockerId=$(docker create datadog/build-dd-serverless-compat-java-agent:$VERSION)
docker cp $dockerId:/app/target/dd-serverless-compat-java-agent-$VERSION.jar ./target/dd-serverless-compat-java-agent-$VERSION.jar
