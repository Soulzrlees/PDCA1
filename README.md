# PDCA1
This is a project for Program Design and Construction course in AUT. This project is a about a command line turn based rpg game.

RPG turn-based game (command line program):
Firstly, the title page of the RPG would be printed to show that the game has started.
Prompts the user for their name.
Allow them to select from 3 different classes (mage, ranger, melee)
Melee (High base damage  + low randomized damage + low distance[Only attacks if distance ≤ 1 (close)]) 
Ranger (medium base damage + medium randomized damage + medium distance[Gets penalty if too close (distance ≤ 1), bonus if far])
Mage (low base damage + High randomized damage + long distance[Can attack from anywhere, but bonus scales with distance])
After selecting, a menu of options appears
Fight (PVE)
Gamble something (decide later)
Inventory (the inventory system will have all the features)
Skill points (related to upgrades)
Exit 
If the user selects the Fight option, the fight sequence would activate. Once the user is in a battle, it will be a turn-based game where the users can either 
Attack
Run backwards
Run forward
Potion


Once the user has finished their turn, the Enemy would be able to choose their
Attack
Run further back or forward
Heal
Evade (low probability)
These options are randomised (For now, we might add smarter movesets from the bots to make the game more difficult).

One of two options would occur if the battle is finished (player died or enemy died)
Player died
The user would be prompted with a message that he/she had died. Your gold could potentially be decreased. And the user would be back to the menu interface.
NPC died:
A message is written saying the player has won the battle. An amount of coins is gifted to the player for defeating the enemy along with exp.
For gold, the amount will depend on the level of the npc and the same goes for exp. Each level group (5 - 10 levels) will have a range of coins and exp they can give (the exact value is randomised within the set).

Enemy Types:
Orc (melee type)
Attacks at close range, high base damage, high health
Ice Elves (ranger type)
Attacks from a distance, medium base damage, high evasion + medium health
Necromancer (mage type)
Attacks any distance, low base damage + High random extra damage, low health





Fighting Mechanics:
This is a person vs environment setting. There will be different options the player can choose from, including attack, move back, move forward, heal, and escape. 
For attack, there is a consistent base damage along with a randomised added damage. The player class will also determine buffs depending on distance.
Add in time limits for moves so that it makes the game more fast paced and exciting.

Inventory Mechanics:
This will show the items and resources the player has. This will be a list/array for easy reading for the player. This inventory will also show the players current level (stats), but only simplified (the main details is on the skill points menu). The inventory could store potions (All potions only last for 1 turn)

Potions example:
Strength Buff (Increase random extra damage)
Heal (randomized Increase health)
Swifness (Able to travel backwards or forwards further)
Haste (2 actions in one turn)
Ironskin (50% less damage from enemies)




Gamble Mechanics:
This menu will allow the player to get certain upgrades which will allow them to have advantages on the fighting field. This includes speed buffs, evasion buffs, attack buffs, and more to be decided. This will be possible using gold which is dropped by defeated enemies. 
