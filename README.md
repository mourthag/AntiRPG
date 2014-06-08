# AntiRPG

A not-really-RPG-but-actually-dungeon-crawler based on the crappy "learn how to program" framework called "Greenfoot" (we didn't have a choice).
In retrospect, I'd estimate 2/3 of our work was to work around said framework and implement stuff of which a good framework should have taken care of.

You control a player in a dungeon and have to kill the boss monster, other monsters you kill get you bonus points. The boss monster is the one with the red pants, it shoots powerful fireballs at you and walks in your direction once it spots you - if it touchs you, it hurts. The basic monsters have white pants for one which shoots fireballs but walks really slowly and brown pants for one which can't shoot, but walks in your direction.
The player is a powerful mage, but sadly absolutely useless at close combat - it /does/ damage, but not enough to really count, we wouldn't recommend it.
You move the player with the arrow keys and turn around with the v and b keys to turn left and right respectively. You don't need to face in the direction you move to, but you'll shoot in that direction when you hit space.
The mage dies once the green bar at the top of the screen which represents it's health is empty.

## Build/Install/Launch

You need Greenfoot. Launch Greenfoot, it calls it's projects "Scenarios", so you need to open the AntiRPG scenario. It will complain while opening the scenario and will claim that there might be problems, but there won't be any.
When you've loaded the game into Greenfoot, you click "Compile", then "Run" and then follow the on-screen instructions. When you die or win you click "Reset" for a new game.

## To do:
* Decorations (well, they should get images, otherwise they don't really contribute to the game)
* Migrate to a real engine as the deadline has passed anyway (godot? seems pretty decent and it's cross platform. Or maybe something of our own, would be a nice challenge but not really results oriented. Or maybe we should just leave it.)
