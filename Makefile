local-db-up:
	docker compose -f docker-compose-local.yml --env-file .env up -d
local-db-down:
	docker compose -f docker-compose-local.yml --env-file .env down --volumes