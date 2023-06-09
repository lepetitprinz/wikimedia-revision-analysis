#!/bin/bash

helm repo add superset https://apache.github.io/superset

helm upgrade --install --values values.yaml superset superset/superset \
--namespace superset --create-namespace \
--set nodeSelector.platform=superset