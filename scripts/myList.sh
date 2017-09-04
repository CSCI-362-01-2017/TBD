#!/bin/bash

# Displays the contents of the script's containing directory.
# taken from http://www.ostricher.com/2014/10/the-right-way-to-get-the-directory-of-a-bash-script/
#SCRIPT_DIR="$( cd "$( dirname "$0" )" && pwd )"

#CONTENTS=$(ls $SCRIPT_DIR)

# Displays the contents of the top-level directory.
CONTENTS=$(ls /)


echo "<!DOCTYPE html>
	<html>
	<body>
	<p>
		$CONTENTS
	</p>
	</body>
	</html>" > temp.html

xdg-open temp.html
