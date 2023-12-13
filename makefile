#!/usr/bin/make

.PHONY: up upd rcon

SHELL = /bin/sh

CURRENT_UID := $(shell id -u)
CURRENT_GID := $(shell id -g)

export CURRENT_UID
export CURRENT_GID

ifneq (,$(wildcard ./.env))
	include .env
	export
endif

up:
	docker compose up server

upd:
	docker compose up -d server

down:
	docker compose down

rcon:
	docker compose exec server rcon-cli

build:
	docker compose exec maven mvn clean install
	cp -f ./plugin/target/ZyunCraftCore-1.0-SNAPSHOT.jar ./server/plugins/
	docker compose exec server rcon-cli reload confirm
