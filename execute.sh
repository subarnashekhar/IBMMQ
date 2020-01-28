#!/bin/bash

testName=$1"_"$2"u_"$3"s_"$4"_connectionpool_"$6
testRun=$1
testUsers=$2
testDuration=$3
testType=$4
connectionpoolvalue=$6
numofinstances=$7
basePath=`pwd`
testLocation=${basePath}"/connectionpool_"$connectionpoolvalue"/"${testType}
testUrl=$5
jmeterPath=`cat jmeter_path.cfg`
script_name=`cat jmeter_script.cfg`
mkdir ${testLocation}
echo "Starting "$testRun" "$testUsers" "$testType"  "$testUrl" "`date`

startTime=`date +"%m/%d/%Y:%T"`
export JVM_ARGS="-Xms2048m -Xmx4096m"

start_Time=`date +"%m/%d/%Y:%H:%M:%S"`

sh -x ${jmeterPath}/jmeter_v1.sh -Jbaseurl=${testUrl} -Jtotalusers=${testUsers} -Jtotalduration=${testDuration} -n -t ${basePath}/${script_name} -l ${testLocation}/${testName}.jtl

end_Time=`date +"%m/%d/%Y:%H:%M:%S"`

echo "Ending  "$testRun" "$testUsers" "$testType"  "`date`

sh -x ${jmeterPath}/PluginsManagerCMD.sh --tool Reporter --generate-csv ${testLocation}/${testName}_aggResults.csv --input-jtl ${testLocation}/${testName}.jtl --plugin-type AggregateReport

name=$(echo "${testLocation}/${testName}.jtl" | cut -f 1 -d '.')

${jmeterPath}/jmeter_v1 -g ${testLocation}/${testName}.jtl -o $name

cat "$name/content/js/dashboard.js" | grep statisticsTable | cut -c 40- | awk '{print substr($0, 1, length($0)-24)}' > temp.json
	
	echo "Number_of_instances: $numofinstances Number_of_users : $testUsers connection_pool : $connectionpoolvalue" >> "${basePath}/consolidated_result_connpool_${connectionpoolvalue}_instance_${numofinstances}.csv"
	
	java -jar $basePath/jsonparsing.jar temp.json >> "${basePath}/consolidated_result_connpool_${connectionpoolvalue}_instance_${numofinstances}.csv"
	
	echo "Timetaken By CaaS as per application log" >> "${basePath}/consolidated_result_connpool_${connectionpoolvalue}_instance_${numofinstances}.csv"
	
	decrypttime=`java -jar splunkqueryrun.jar splunkquery_decrypt.txt $start_Time $end_Time`
	encrypttime=`java -jar splunkqueryrun.jar splunkquery_encrypt.txt $start_Time $end_Time`
	echo "decrypt,$decrypttime">> "${basePath}/consolidated_result_connpool_${connectionpoolvalue}_instance_${numofinstances}.csv"
	echo "encrypt,$encrypttime">> "${basePath}/consolidated_result_connpool_${connectionpoolvalue}_instance_${numofinstances}.csv"
	
	echo " " >> "${basePath}/consolidated_result_connpool_${connectionpoolvalue}_instance_${numofinstances}.csv"


endTime=`date +"%m/%d/%Y:%T"`


                                                                                                                                                                                                                                                     
