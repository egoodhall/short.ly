#!/bin/bash

colorize() {
    echo -e "$(tput setaf $2)$1$(tput sgr0)";
}

log() {
    while read line; do
        echo "$(colorize "[$1]" $2) $line";
    done;
}

pythonLogClr=3
nodeLogClr=1

trap 'kill $(jobs -p) &> /dev/null' SIGINT

# This script starts up the development session on localhost:8080
pushd $(dirname $0)/../dist &> /dev/null

mkdir -p logs
logdir=$(pwd)/logs

colorize 'Starting Server (use ctrl-C to exit)' 2

pushd backend &> /dev/null
java -jar shortly.jar server config.yml 2>&1 | tee ${logdir}/java.log | log 'java' 4 &
popd &> /dev/null

pushd frontend &> /dev/null
serve -p 8080 . 2>&1 | tee $logdir/node.log | log 'node' 1
popd &> /dev/null

popd &> /dev/null
sync
colorize '\nServer successfully stopped' 2

exit 0
