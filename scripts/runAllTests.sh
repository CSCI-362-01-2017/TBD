#!/bin/bash

SCRIPT_DIR="$( cd "$( dirname "$0" )" &&pwd )"

for pfile in $SCRIPT_DIR/../testCases/*
do
	while IFS='\n' read -r line;
	do		
			
		if [ "$line" = 'java' ]
		then
			javac $SCRIPT_DIR/test1.java
			java test1
			out=$SCRIPT_DIR/test1
			echo $out
		fi
	done < $pfile
done
	echo "<!DOCTYPE html>
		<html>
		<body>
		<p>
			$line
		</p>
		</body>
		</html>" > $SCRIPT_DIR/../temp.html	

	xdg-open $SCRIPT_DIR/../temp.html
exit 0
