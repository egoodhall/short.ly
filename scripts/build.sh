#!/bin/bash

# if anything errors out, quit
set -e

#put me in the right dir
pushd $(dirname $0)/.. >> /dev/null

type mvn &> /dev/null
if [[ $? != 0 ]]; then
  echo "Please install maven and then run this script again..."
  exit 1
fi

# Check for either npm or yarn
type npm &> /dev/null
if [[ $? != 0 ]]; then
  type yarn &> /dev/null
  if [[ $? != 0 ]]; then
    echo "Please install yarn or npm and then run this script again..."
    exit 1
  fi
fi

# install maven requirments
pushd backend >> /dev/null
mvn package
popd >> /dev/null

# Install js libs with yarn if it's found, otherwise with npm
pushd frontend >> /dev/null
type yarn &> /dev/null
if [[ $? == 0 ]]; then
  echo 'Installing static dependencies with yarn'
  yarn build
else
  echo 'Installing static dependencies with npm'
  npm run build
fi

# success
popd >> /dev/null

mkdir -p dist
mkdir -p dist/frontend
mkdir -p dist/backend

cp -R frontend/dist/ dist/frontend/
cp backend/target/shortly.jar dist/backend/
cp backend/config.yml dist/backend

popd >> /dev/null

echo "Setup completed successfully"
exit 0
