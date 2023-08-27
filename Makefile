build:
	docker compose build;

database:
	docker compose build postgres && docker compose up -d postgres;

app:
	docker compose build app && docker compose up -d app;

run:
	make database && make app;
