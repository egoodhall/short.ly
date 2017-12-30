#! /bin/bash

pushd $(dirname $0) >> /dev/null

package() {
    pushd .. >> /dev/null;
    mvn package;
    popd >> /dev/null;
    return 0;
}

serve() {
    pushd .. >> /dev/null;
    java -jar ./target/shortly.jar server ./shortly.yml
    popd >> /dev/null;
}

pack='false'

# idiomatic parameter and option handling in sh
while test $# -gt 0
do
    case "$1" in
        --pack)
            pack='true'
            ;;
        --serve)
            ;;
        --*) echo "bad option $1"
            ;;
        *) echo "argument $1"
            ;;
    esac
    shift
done

if [[ $pack = 'true' ]]; then
    package
fi

serve

popd >> /dev/null