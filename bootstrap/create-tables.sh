#!/usr/bin/env sh

for i in $(find table -name "*-table.json"); do
  echo "Creating table : "$i
  aws dynamodb create-table --cli-input-json file://$i --region "ap-southeast-1" --endpoint-url http://dynamodb.host:4566
done

