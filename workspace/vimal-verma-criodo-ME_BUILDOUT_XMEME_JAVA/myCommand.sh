DB_NAME="greetings"

mongo <<EOF
use $DB_NAME
db.dropDatabase()
EOF

echo "Database '$DB_NAME' has been deleted."

# pip3 install -r requirements.txt
pytest --pspec --disable-pytest-warnings assessment/main.py > test.txt
./gradlew bootRun

