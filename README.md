=== Introduction ===

An easy to use plugin, blueprint lets you specify certain items as craft-able
 ONLY after first obtaining and using an item's blueprint. there are currently no commands available but more commands and configuration are planned for the future.

Blueprints are obtained as drops from mobs. Drop rates can be configured via the config.
To use a blueprint hold it in your hand and right click.

=== Permissions ===

Bypass blueprint checks
- blueprint.bypass


=== Installation and Setup ===

- Ensure that you have Java 7 installed
- Stop the server
- Drop blueprint.jar into your plugins folder
- Start and stop the server
- go into plugins/blueprint/config.yml
* to require blueprints before crafting items, add theitem IDs to the "blueprints" section of config.yml

=== Configuration ===

//dropChance is the rate which mobs will drop blueprints
dropChance: 100

//Blueprints are the items which will require blueprints before crafting
Blueprints:[20,23,25,27,28,29,33,35,41,42,45,46,47]

//players are the current blueprints held by players. 
players:
  Riaku:
    - 355

===== Problems =====

* Blueprint not dropped after creeper explosion

===== Planned Features =====

* Adding Commands to add/remove/modify blueprints
* Add blueprints on pickup
