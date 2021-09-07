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
| Left arrow key  | Turns the ship left  |
| Right arrow key  | Turns the ship right  |
| Up arrow key  | Applies thrust to the ship  |
| Spacebar  | Fires a projectile from the ship  |
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



