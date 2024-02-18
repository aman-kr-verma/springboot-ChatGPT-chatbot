#!/bin/bash

if [ $# -ne 2 ]; then
echo "Required arguments not provided: OPEN_API_KEY & HOST_PORT"
echo "Please provide valid OPENAI_API_KEY & valid HOST_PORT in that order:)"
exit 1
fi

export OPENAI_API_KEY=$1
export HOST_PORT=$2

docker-compose -f docker-compose.yml up -d 
