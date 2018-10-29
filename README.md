<img align="right" alt="Glowstone logo" width="100" src="etc/logo/logo.png">

# Cubed
This is a fork of [GlowstoneMC/Glowstone](https://github.com/GlowstoneMC/Glowstone) removing the compatibility and adding more features.  

Please go check out [Glowstone](https://github.com/GlowstoneMC/Glowstone/blob/dev/docs/README.md) if you are not familiar with it, they have done and continue to do great work.

Check out our private [development git server](https://git.sw4pspace.net/sw4pspace/cubed) for bleeding edge changes also head over to our jenkins server to [download](https://jenkins.sw4pspace.net/jenkins/job/COMPILE%20Cubed%20master%20Nightly/) those bleeding edge changes. 


# Introduction
Cubed aims to provide a more robust and feature rich environment for your [Minecraft](https://minecraft.net) server. 

# Features
- Completely independent from the [Minecraft Server](https://minecraft.net/en-us/download/server/) and the [Bukkit/Spigot API](https://spigotmc.org) (WIP)
- Database backend to replace flatfile 
- WebServices for querying and manipulating data or configuration 
- Frontend webpage to easily manage cubed

# Build

## Prerequisites
1. Java
2. Git
3. Maven
4. PostgreSQL instance

## Steps
1. Create a database called `cubed` on your postgres server 
2. Run `/ci/bare/install.sh`