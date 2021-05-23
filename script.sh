#!/bin/bash

find -name "*.java" > build.txt
javac -d bin @build.txt

cd bin

# Kill any processes of these type already running
# kill $(ps -aux | grep 'java mainProgram*' | awk '{print $2}')

# Servers
java mainProgram.RepositoryMain  &
java mainProgram.ArrivalAirportMain  &
java mainProgram.PlaneMain  &
java mainProgram.DepartureAirportMain  &
sleep 1

# Clients
java mainProgram.HostessMain &
java mainProgram.PilotMain  &
sleep 1

for i in {0..20};
do
  java mainProgram.PassengerMain $i &
done
