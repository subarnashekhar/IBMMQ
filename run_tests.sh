#!/bin/bash

# Command Line Parameters testtype
# Execution Method
# Update the following configuration files
# 
# run_tests.sh <test name> <duration in seconds>
# nohup ./run_tests.sh run1_baseline_gctomcat 300 > run1_baseline_fctomcat.log &


testTypeResults=$1"_results"
testDuration=$2
basePath=`pwd`




for appname in src-perf-consumer-service-m7i2-red
do
 for connectionpoolvalue in 15 20 25 30 35
 do
   cf login -a https://api.system.stl.pcfstage00.mastercard.int -u e074247 -p Hello@2018 -o Performance -s perf-smoke-2
   cf scale $appname -i 1
   cf set-env $appname HTTP_CLIENT_TLS_CONNECTION-POOL_MAXTOTAL $connectionpoolvalue
   cf set-env $appname  HTTP_CLIENT_TLS_CONNECTION-POOL_DEFAULTMAXPERROUTE $connectionpoolvalue
   cf restage $appname   
   for instances in 1 2 5 10
   do
     cf login -a https://api.system.stl.pcfstage00.mastercard.int -u e074247 -p Hello@2018 -o Performance -s perf-smoke-2
     api_url=`cat $appname` 
     cf scale $appname -i $instances
     sleep 60
     mkdir $appname 
     testType=${appname}"_"${instances}
     for users in 1 5 10 20 30 40 50 60 70 80 90 100
     do
      echo "Starting test run for users "$users""`date`
      sh -x ./execute.sh test${test} $users $testDuration $testType $api_url $connectionpoolvalue $instances
      echo "Ending test run for users "$users""`date`
      sleep 5 
     done
   done
 done
done




