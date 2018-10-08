<img align="right" alt="Glowstone logo" width="100" src="etc/logo/logo.png">

# Cubed
This is a fork of [GlowstoneMC/Glowstone](https://github.com/GlowstoneMC/Glowstone) removing the compatibility and adding more features.  

Please go check out [Glowstone](https://github.com/GlowstoneMC/Glowstone/blob/dev/docs/README.md) if you are not familiar with it, they have done and continue to do great work.

# Introduction
Cubed aims to provide a more robust and feature rich experience. 

# Features
- Completely independent from the [Minecraft Server](https://minecraft.net/en-us/download/server/) and the [Bukkit/Spigot API](https://spigotmc.org) (WIP)
- Database backend to replace flatfile 
- WebServices for querying and manipulating data or configuration 

# Build

## Prerequisites
1. Java
2. Git
3. Maven
4. PostgreSQL instance

## Cubed
1. Create a database called `glowstone` on your postgres server 
2. Run liquibase on your database   
```bash
cd glow-db 
mvn clean compile liquibase:update -Durl=localhost:5432/glowstone -Dusername=postgres -Dpassword=postgres
```
2. Install glow-api
```bash
cd glow-api
mvn clean install
```
3. Install glow-server
```bash
cd glow-server
mvn clean install
```
4. Your jar file is located at /target/glowstone.jar

## Cubed WebServices
1. If you have not created your database and ran liquibase, follow the directions above (step 1 & 2)
2. Install glow-webservice
```bash
TBD
```