
pushd $(dirname $0)/.. &> /dev/null

rm -rf dist

pushd backend &> /dev/null
rm -rf target
popd &> /dev/null

pushd frontend &> /dev/null
rm -rf dist
popd &> /dev/null

popd &> /dev/null

exit 0
