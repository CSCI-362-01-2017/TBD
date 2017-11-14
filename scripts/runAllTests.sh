#!/bin/bash

SCRIPT_DIR="$( cd "$( dirname "$0" )" &&pwd )"	# this directory
DRIVER_DIR=$SCRIPT_DIR/../drivers	# directory containing Java drivers
TEST_DIR=$SCRIPT_DIR/../testCases	# directory containing test cases
PROJECT_DIR=$SCRIPT_DIR/../project	# directory containing Glucosio files
TEMP_DIR=$SCRIPT_DIR/../temp		# directory containing temporary files
HTML_FILE=$SCRIPT_DIR/../reports/report.html	# HTML file to be displayed as report

addToHTML() {	# Function for adding a series of lines to the HTML file
	echo "<p>" >> $HTML_FILE

	for arg in "$@"
	do
		echo "$arg<br>" >> $HTML_FILE
	done

	echo "<br>" >> $HTML_FILE
}

# Build project files
(cd $PROJECT_DIR; ./gradlew assemble)

# Delete temp.html, if it exists
rm -f $HTML_FILE

# Begin HTML file
echo "<!DOCTYPE html>			
	<html>
	<body>" >> $HTML_FILE

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
			details=("<u>$TEST_ID</u>: <font color=blue>N/A</font>, no driver to run" "$REQUIREMENT" "<b>Component</b>: $COMPONENT" "<b>Method</b>: $METHOD")
			addToHTML "${details[@]}"

		else
			# Compile and run driver; collect output
			(cd $DRIVER_DIR; javac "$DRIVER.java" -cp .:$PROJECT_DIR/app/build/intermediates/classes/debug -d $TEMP_DIR)
			OUTPUT=$(cd $TEMP_DIR; java -cp .:$PROJECT_DIR/app/build/intermediates/classes/debug $DRIVER "$INPUT")

			if [ "$OUTPUT" = "$ORACLE" ]
			then
				# test passed
				details=("$i. <u>$TEST_ID</u>: <font color=green>PASS</font>" "$REQUIREMENT" "<b>Component</b>: $COMPONENT" "<b>Method</b>: $METHOD" "<b>Expected</b>: $ORACLE, <b>Actual</b>: $OUTPUT")
				addToHTML "${details[@]}"

			else
				# test failed
				details=("$i. <u>$TEST_ID</u>: <font color=red>FAIL</font>" "$REQUIREMENT" "<b>Component</b>: $COMPONENT" "<b>Method</b>: $METHOD" "<b>Expected</b>: $ORACLE, <b>Actual</b>: $OUTPUT")
				addToHTML "${details[@]}"

			fi

			# Clear TEMP_DIR
			#rm -rf $TEMP_DIR/*
		fi
	fi
	i=$((i + 1))
done

# Finish out HTML file
echo "</body>
      </html>" >> $HTML_FILE

xdg-open $HTML_FILE

exit 0

