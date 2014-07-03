# AntiRPG

A not-really-RPG-but-actually-dungeon-crawler based on the crappy "learn how to program" framework called "Greenfoot" (we didn't have a choice).
In retrospect, I'd estimate 2/3 of our work was to work around said framework and implement stuff of which a good framework should have taken care of.

You control a player in a dungeon and have to kill the boss monster, other monsters you kill get you bonus points. The boss monster is the one with the red pants, it shoots powerful fireballs at you and walks in your direction once it spots you - if it touchs you, it hurts. The basic monsters have white pants for one which shoots fireballs but walks really slowly and brown pants for one which can't shoot, but walks in your direction.
The player is a powerful mage, but sadly absolutely useless at close combat - it /does/ damage, but not enough to really count, we wouldn't recommend it.
You move the player with the arrow keys and turn around with the v and b keys to turn left and right respectively. You don't need to face in the direction you move to, but you'll shoot in that direction when you hit space.
The mage dies once the green bar at the top of the screen which represents it's health is empty.

## Build/Install/Launch

If you just want to try out the game, go to "releases" and download the latest "AntiRPG.jar" file. You need to have Java installed, then you can launch the game by double clicking the .jar file.

If you also want to edit it's probably easiest to use Greenfoot. To load the project into Greenfoot you might need to create a blank "project.greenfoot" file in the game folder so Greenfoot recognizes the project (we removed that file from Git because it's machine generated and annoying to merge, Greenfoot complains but works fine without it).
When you've loaded the game into Greenfoot, you click "Compile", then "Run" and then follow the on-screen instructions. When you die or win you click "Reset" for a new game.

## To do:
* Decorations (well, they should get images, otherwise they don't really contribute to the game)
* Migrate to a real engine as the deadline has passed anyway (godot? seems pretty decent and it's cross platform. Or maybe something of our own, would be a nice challenge but not really results oriented. Or maybe we should just leave it.)

## License for every Java source file in this git and this readme:

Copyright 2014 Julian Spittel and Lukas Bürgi.

AntiRPG is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
