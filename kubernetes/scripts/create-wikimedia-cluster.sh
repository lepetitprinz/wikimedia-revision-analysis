#!/bin/bash

kind create cluster --name wikimedia --config wikimedia-cluster-config.yaml --image kindest/node:v1.27.0