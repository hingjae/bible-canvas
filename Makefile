local-db-up:
	docker compose -f docker-compose-local.yml --env-file .env up -d
local-db-down:
	docker compose -f docker-compose-local.yml --env-file .env down --volumes

convert:
	iconv -f CP949 -t UTF-8 ./bible/bible.txt > ./bible/bible_utf8.txt