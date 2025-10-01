# CRIO_SOLUTION_AND_STUB_START_MODULE_EXERCISES
# CRIO_SOLUTION_AND_STUB_END_MODULE_EXERCISES
#!/bin/bash

# Usage: ./script.sh --input /path/to/query.sql

DB_USER="root"
DB_PASS=""
DB_NAME="sakila"
DB_FILE="data/sakila.sql"

while [[ "$#" -gt 0 ]]; do
    case $1 in
        --input) QUERY_FILE="$2"; shift ;;
        *) echo "Unknown parameter passed: $1"; exit 1 ;;
    esac
    shift
done

if [ -z "$QUERY_FILE" ]; then
    echo "Usage: ./run_query.sh --input /path/to/query.sql"
    exit 1
fi

echo "Initializing MySQL database..."
sudo mysql -u $DB_USER -e "DROP DATABASE IF EXISTS $DB_NAME; CREATE DATABASE $DB_NAME;"
sudo mysql -u $DB_USER $DB_NAME < $DB_FILE

echo "Running query from $QUERY_FILE..."
sudo mysql -u $DB_USER $DB_NAME < $QUERY_FILE  > /dev/null 2>&1

echo "Query results:"
sudo mysql -u $DB_USER $DB_NAME -e "$(cat $QUERY_FILE)"