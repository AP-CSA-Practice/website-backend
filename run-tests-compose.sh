#!/bin/bash
set -e

echo "Starting test environment with docker-compose..."

# Ensure MongoDB is running
docker-compose up -d mongodb

# Wait for MongoDB to start
echo "Waiting for MongoDB to start..."
sleep 5

# Run tests
echo "Running tests..."
docker-compose run --rm test

echo "Tests completed."