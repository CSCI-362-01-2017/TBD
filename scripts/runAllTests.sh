#!/bin/bash

SCRIPT_DIR="$( cd "$( dirname "$0" )" &&pwd )"

for pfile in $SCRIPT_DIR/../testCases/*
do
	mapfile -t array <$pfile
	echo "${array[4]}"
	if [  -z "${array[4]}"  ]; then
		echo "${array[0]} is missing specified driver!"
	else	
		javac $SCRIPT_DIR/"${array[4]}.java"
		java "${array[4]}"
	fi		

	echo "<!DOCTYPE html>
		<html>
		<body>
		<p>
			"${array[0]}"
		<br>	
		</p>
		</body>
		</html>" > $SCRIPT_DIR/../temp.html	

done

xdg-open $SCRIPT_DIR/../temp.html

exit 0
