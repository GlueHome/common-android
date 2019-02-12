#!/bin/bash

echo "releasing $1"

git tag -a "$1" -m "$1"; git push --tags
