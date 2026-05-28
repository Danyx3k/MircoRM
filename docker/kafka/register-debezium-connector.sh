#!/bin/sh
set -e
CONNECT_URL="${DEBEZIUM_CONNECT_URL:-http://debezium-connect:8083}"
CONNECTOR_NAME="${DEBEZIUM_CONNECTOR_NAME:-microrm-postgres}"
CONFIG_FILE="${DEBEZIUM_CONFIG_FILE:-/connector/debezium-connector.json}"

echo "Esperando Debezium Connect en ${CONNECT_URL}..."
until curl -sf "${CONNECT_URL}/" >/dev/null; do
  sleep 2
done

PAYLOAD=$(printf '{"name":"%s","config":%s}' "${CONNECTOR_NAME}" "$(cat "${CONFIG_FILE}")")

if curl -sf "${CONNECT_URL}/connectors/${CONNECTOR_NAME}" >/dev/null; then
  echo "Actualizando conector ${CONNECTOR_NAME}..."
  curl -sf -X PUT "${CONNECT_URL}/connectors/${CONNECTOR_NAME}/config" \
    -H "Content-Type: application/json" \
    -d "$(cat "${CONFIG_FILE}")"
else
  echo "Creando conector ${CONNECTOR_NAME}..."
  curl -sf -X POST "${CONNECT_URL}/connectors" \
    -H "Content-Type: application/json" \
    -d "${PAYLOAD}"
fi

echo ""
echo "Conector CDC registrado."
