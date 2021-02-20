# Equipping Characters
<h3>About</h3>
Jumptastic Games has decided to develop a new role-playing game (Links to an external site.) 
in which characters will be able to enhance their basic abilities by "wearing" different items. They asked me to 
help by designing and implementing a model to help keep track of this. In their game, characters can wear four different types of clothing:

- Headgear: These items go on the character’s head (hats/helmets/visors) and are only useful for defense.
- Footwear: These items go on the character’s feet (boots/sneakers/hoverboard) and are only used for attack. Since character's have 2 feet, they can have 2 of these items.
- Hand gear: These items go on the character’s fingers/hands and can be for either attack or defense. Since character's have 10 fingers, they can have 10 of these items.
- Jewelry: These items go around the character's neck and can be used for either attack or defense. There is no limit to the number of these items that the character can have.


My Program goes about solving the problem by breaking it down into smaller problems and making sure those work before combining it into one.

# LIST OF FEATURES

My program is able to track all required data and implements all functionality required as follows:
-   In addition to current hit points, characters begin with a basic attack power and defensive strength (represented as numerical values). As they go through the game, they can pick up new items based on how many items of a particular type that they can wear.
-   The attack or defensive power of the items that a character is wearing temporarily adds (or subtracts in the case of a cursed item) to the players attack power and defensive strength.
-   When describing what a character is wearing, the names of items of the same type are combined.
-   Some items wear out with each use and thus their benefit decreases each time a player uses them.
-   Characters can fight each other in a battleground.


# How to Run
Extract the folder and open the command prompt in the res folder and run command `java -jar EquipingCharacters.jar`

# How to Use the Program
These are the steps on how to use the program.
- First follow "How to Run" above and run the program.
- The program will automatically pit two characters in a fight.
- When the fight is over enter `1`  to fight again or `0` to close.


## Description of Example Runs
Run 1 -- Filename: Run.txt:
- Creates a chest with the specified number of items.
- Creates two characters and print their information.
- Equip both characters and print their new information.
- Predicts who wins.
- Wears out items that can get worn out.
- Ask user to quit or rematch.



## Design/Model Changes
There were significant changes to how each class operates. The initial design had the four gear classes randomly
generating an item and also stored then as an enumeration. This design was not scalable because each time someone 
had to add a new Item the person will change the enumeration and also the individual classes hence violating the 
open closed principle. Also, some classes were forced to depend on public method that they didn't need.

The new design solved th scalability problem of the last design now if a new item needed to be added it can be added 
to the enumeration just once and created by its class. Also, two interfaces were used to enforce classes only depend
on the public methods they need.

## Assumptions
The assumptions made during the implementation of this project are as follows:
- An item can be allowed to have negative values for power and by doing so makes it a cursed item.
- Hand Gears and Jewelry's can be created with both an attack and defence power.
- Characters start with non-negative attack and defense strength.
- Hit points is calculated by adding 100 to `totalDefencePower`



## Limitations
Currently, every functionality runs, but some limitations that can be thought of 
stems from the design of the project and one stands out. To utilize good dependency injection 
where the `new` keyword was used to create objects can be replaced by factory methods, this would help decouple the
code more and provide good abstraction. 
Also time complexity of functions were not factored in their implementation, this can
lead to unforeseen problems when the program scales.


```