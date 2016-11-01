#!/usr/bin/bash -ex

JSONB_PATH=~/java/eclipselink-runtime/jsonb;
BENCH_PATH=`pwd`;

if [ ! -z "$3" ]; then
  PROF="-prof $3"
else
  PROF=""
fi

if [ -z "$2" ]; then
  THREAD_COUNT=4
else
  THREAD_COUNT=$2
fi

if [ -d $JSONB_PATH ]; then
  cd ~/java/eclipselink-runtime/jsonb;
  ./gradlew build -x test install;
fi
cd $BENCH_PATH;
mvn clean install;
java -jar target/benchmarks.jar $1 $PROF -f 1 -t $THREAD_COUNT -i 10 &
renice 10 $!;
#cat results/SerializeWithStackProfiler.txt
#java -XX:+UnlockCommercialFeatures -XX:+FlightRecorder -jar target/benchmarks.jar &
#JMH_PID=$!;
#echo $JMH_PID;
#jcmd $JMH_PID JFR.start duration=10s filename=myrecording.jfr

