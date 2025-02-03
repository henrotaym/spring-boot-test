#!/usr/bin/env bash

# docker compose run --rm -it --service-ports app ./mvnw install dependency:copy-dependencies
./mvnw install dependency:copy-dependencies
