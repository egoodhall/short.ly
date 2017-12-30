#!/bin/bash

# if anything errors out, quit
set -e

#put me in the right dir
pushd $(dirname $0)/..

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
mvn install
popd >> /dev/null

# Install js libs with yarn if it's found, otherwise with npm
pushd frontend >> /dev/null
type yarn &> /dev/null
if [[ $? == 0 ]]; then
  echo 'Installing static dependencies with yarn'
  yarn install
else
  echo 'Installing static dependencies with npm'
  npm install
fi

# success
popd >> /dev/null
popd >> /dev/null

echo "Setup completed successfully"
exit 0
