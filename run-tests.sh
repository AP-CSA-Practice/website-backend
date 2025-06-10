#!/bin/bash
set -e

IMAGE_NAME="apcsa-backend-test"
CONTAINER_NAME="apcsa-test-container"
NETWORK_NAME="apcsa-test-network"
MONGO_CONTAINER_NAME="apcsa-mongodb"

# Check if Docker is installed
if ! command -v docker &> /dev/null; then
    echo "Docker is not installed. Please install Docker and try again."
    exit 1
fi

# Check if Docker daemon is running
if ! docker info > /dev/null 2>&1; then
    echo "Docker daemon is not running. Please start Docker and try again."
    exit 1
fi

# Create a Docker network if it doesn't exist
if ! docker network inspect $NETWORK_NAME > /dev/null 2>&1; then
    echo "Creating Docker network $NETWORK_NAME..."
    docker network create $NETWORK_NAME
fi

# Remove existing containers if they exist
for container in $CONTAINER_NAME $MONGO_CONTAINER_NAME; do
    if [ "$(docker ps -aq -f name=$container)" ]; then
        echo "Removing existing container $container..."
        docker rm -f $container
    fi
done

# Start MongoDB container
echo "Starting MongoDB container..."
docker run -d --name $MONGO_CONTAINER_NAME \
    --network $NETWORK_NAME \
    -p 27017:27017 \
    mongo:4.4

# Wait for MongoDB to start
echo "Waiting for MongoDB to start..."
sleep 5

# Build Docker image
echo "Building Docker image..."
docker build -t $IMAGE_NAME .

# Run tests inside container with MongoDB connection
echo "Running tests in Docker container..."
docker run --rm --name $CONTAINER_NAME \
    --network $NETWORK_NAME \
    -e SPRING_DATA_MONGODB_URI=mongodb://$MONGO_CONTAINER_NAME:27017/apcsadb_test \
    $IMAGE_NAME

# Clean up
echo "Cleaning up resources..."
docker stop $MONGO_CONTAINER_NAME
docker rm $MONGO_CONTAINER_NAME
docker network rm $NETWORK_NAME

echo "Test run completed."