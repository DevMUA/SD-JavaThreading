#!/bin/bash

find -name "*.java" > build.txt
javac -d bin @build.txt

cd bin;

java java bin.mainProgram.RepositoryMain  &
java java bin.mainProgram.ArrivalAirportMain  &
java java bin.mainProgram.PlaneMain  &
java java bin.mainProgram.ArrivalAirportMain  &
java java bin.mainProgram.HostessMain  &
java java bin.mainProgram.PilotMain  &

java java bin.mainProgram.PassengerMain &
