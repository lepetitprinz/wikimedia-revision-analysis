#!/bin/bash

kubectl run kafka-client --rm -it --image bitnami/kafka:3.1.0 -- bash