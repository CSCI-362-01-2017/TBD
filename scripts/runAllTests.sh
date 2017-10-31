#!/bin/bash

SCRIPT_DIR="$( cd "$( dirname "$0" )" &&pwd )"

../project/./gradlew assemble

javac $SCRIPT_DIR/Driver.java

out=$(java Driver $SCRIPT_DIR/../testCases/)

echo "<!DOCTYPE html>
	<html>
	<body>
	<p>
		$out
	</p>
	</body>
	</html>" > $SCRIPT_DIR/../temp.html

xdg-open $SCRIPT_DIR/../temp.html

exit 0
