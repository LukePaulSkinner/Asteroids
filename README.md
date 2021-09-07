# Asteroids

The original asteroids game recreated in Java as well as added features such as:
* A shop and upgrades
* Enemy ships
* Boss battles
* Enemy waves

![Asteroids](https://i.imgur.com/M9cpD3pl.png)
## Controls

| Key  | Action |
| ------------- | ------------- |
| Left arrow key  | Turns the ship left/Previous item in the shop   |
| Right arrow key  | Turns the ship right/Next item in the shop  |
| Up arrow key  | Applies thrust to the ship  |
| Spacebar  | Fires a projectile from the ship/ Select item from the shop  |
| B key | Opens the shop while the ship is near it   |

## Enemy types

### Asteroid

* Three sizes: large, medium and small.
* When destroyed large and small asteroids will break off into smaller asteroids.
* Randomly generated shapes using vector coordinates.
 


### Enemy Ship

* Will locate player and attempt to strafe them.
* Fires projectiles
* Vector coordinate based image


### Enemy Mother Ship

* Stationary
* When above 100 health will spawn large asteroids, when below will spawn pairs of enemy ships.
* Vector coordinate based image


## Shop
Money is droped when defeating enemies and can be picked up by the player to purchase updgrades in the shop.
### Extra life
Costs the player 30 money and grants them an additional life.
### Double shot
Costs 100 money and grants the player an additional projectile when the ship fires.
### Super gun
Costs 500 money and removes the fireing delay from the ships gun.
### Win game
Costs 1000 money and ends the game.
### Exit shop
Costs 0 money to exit the shop and resume the game.

