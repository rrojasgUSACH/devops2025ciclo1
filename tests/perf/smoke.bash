mvn spring-boot:run \
  -Dspring-boot.run.profiles=ci \
  -Dspring-boot.run.jvmArguments="-Xms256m -Xmx512m" \
  -Dspring-boot.run.arguments="--server.port=8080"

BASE_URL=http://localhost:8080 BASE_PATH=/ \
RATE=5 DUR=45s VUS=8 MAXVUS=8 \
k6 run --summary-export k6-summary.json tests/perf/safe.js

BASE_URL=http://localhost:8080 BASE_PATH=/ \
RATE=10 DUR=2m VUS=12 MAXVUS=12 \
k6 run --summary-export k6-summary.json tests/perf/safe.js

BASE_URL=http://localhost:8080 BASE_PATH=/ \
RATE=10 DUR=2m VUS=12 MAXVUS=12 \
k6 run -o experimental-prometheus-rw tests/perf/safe.js

