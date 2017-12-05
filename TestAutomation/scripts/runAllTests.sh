#!/bin/bash

SCRIPT_DIR="$( cd "$( dirname "$0" )" &&pwd )"	# this directory
DRIVER_DIR=$SCRIPT_DIR/../drivers	# directory containing Java drivers
TEST_DIR=$SCRIPT_DIR/../testCases	# directory containing test cases
PROJECT_DIR=$SCRIPT_DIR/../project	# directory containing Glucosio files
TEMP_DIR=$SCRIPT_DIR/../temp		# directory containing temporary files
HTML_FILE=$SCRIPT_DIR/../reports/report.html	# HTML file to be displayed as report
CLASSPATH=.:$PROJECT_DIR/app/build/intermediates/classes/debug

addToHTML() {	# Function for adding a series of lines to the HTML file
	echo "<tr>" >> $HTML_FILE

	for arg in "$@"
	do
		echo "<th>$arg</th>" >> $HTML_FILE
	done

	echo "</tr>" >> $HTML_FILE
}

# Build project files
(cd $PROJECT_DIR; ./gradlew assemble)

# Compile drivers
(cd $DRIVER_DIR; find -name "*.java" > sources.txt)
(cd $DRIVER_DIR; javac @sources.txt -cp $CLASSPATH -d $TEMP_DIR)
rm -rf $DRIVER_DIR/sources.txt

# Begin HTML file
echo '<!DOCTYPE html>			
	<html>
	<body>

	<table style="width:100%" border="1px solid black">
		<tr>
			<th><u>#</u></th>
			<th><u>TestID</u></th>
			<th><u>Pass/Fail</u></th>
			<th><u>Requirement</u></th>
			<th><u>Component Tested</u></th>
			<th><u>Method Tested</u></th>
			<th><u>Expected Output</u></th>
			<th><u>Actual Output</u></th>
		</tr>' > $HTML_FILE

i=$((1))

# Run for all TXT files in TBD/testCases
for pfile in $TEST_DIR/*.txt
do
	# Split lines of .txt file into an array
	mapfile -t array <$pfile

	if ! [ ${#array[@]} -eq 7 ]	# wrong number of lines
	then
		echo "$TEST_ID has an incorrect number of lines! 
			See test case specification."

	else
		TEST_ID=${array[0]}
		REQUIREMENT=${array[1]}
		COMPONENT=${array[2]}
		METHOD=${array[3]}
		DRIVER=${array[4]}
		INPUT=${array[5]}
		ORACLE=${array[6]}

		# Replace empty info lines
		if [ -z "$TEST_ID" ]	# empty test ID line
		then
			TEST_ID="Unknown Test ID"

		elif [ -z "$REQUIREMENT" ]	# empty requirement line
		then
			REQUIREMENT="Unknown Requirement"

		elif [ -z "$COMPONENT" ]	# empty component name line
		then
			COMPONENT="Unknown Component Name"

		elif [ -z "$METHOD" ]	# empty method name line
		then
			METHOD="Unknown Method Name"

		fi

		# Check required info lines
		if [ -z "$DRIVER" ]	# empty driver line
		then
			echo "$TEST_ID is missing a driver specification!"
			details=("$i." "$TEST_ID" "<font color=blue>N/A</font>" "$REQUIREMENT" "$COMPONENT" "$METHOD")
			addToHTML "${details[@]}"

		else
			# Run driver; collect output
			OUTPUT=$(cd $TEMP_DIR; java -cp $CLASSPATH $DRIVER "$INPUT")

			if [ "$OUTPUT" = "$ORACLE" ]
			then
				# test passed
				details=("$i." "$TEST_ID" "<font color=green>PASS</font>" "$REQUIREMENT" "$COMPONENT" "$METHOD" "$ORACLE" "$OUTPUT")
				addToHTML "${details[@]}"

			else
				# test failed
				details=("$i." "$TEST_ID" "<font color=red>FAIL</font>" "$REQUIREMENT" "$COMPONENT" "$METHOD" "$ORACLE" "$OUTPUT")
				addToHTML "${details[@]}"

			fi
		fi
	fi
	i=$((i + 1))
done

# Clear temporary files
rm -rf $TEMP_DIR/*

# Finish out HTML file
echo "</table>
      </body>
      </html>" >> $HTML_FILE

xdg-open $HTML_FILE

exit 0

