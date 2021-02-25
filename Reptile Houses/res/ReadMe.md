# REPTILE HOUSE
<h3>About</h3>
The Cold-Blooded Conservancy needed a tracking system for their Reptile Houses. They needed to track a list of
characteristics and also needed the system to implement a lot of functionality which can be viewed below.

<a href="https://ibb.co/V9kX9nz"><img src="https://i.ibb.co/3dPtd6j/functionality.png" alt="functionality" border="0"></a><p>
My Program goes about solving the problem by breaking it down into smaller problems and making sure those work before combining it into one.

# LIST OF FEATURES

My program is able to track all required data and implements all functionality required as follows:
-   Report the natural features that are currently being used in alphabetical order. This list should include the habitat(s) where the natural feature is located and how much space is left in the habitat for additional animals measured in square meters.
-   Look up which habitat(s) that house a particular species. If a species is found but not currently inhabiting a habitat, it should report this.
-   Print a sign for any given habitat that lists the species that it houses along with a description of the species and an indicator of how many of that species is housed in that habitat. Each species description should include their name, their defining characteristics, as well as other interesting features (poisonous, extinct, endangered)
-   Print a “map” that lists all the habitats by location and the natural features in the habitat and species they house.
-   Print an index that lists all species in the Reptile House in alphabetical order and their location(s).
- Add animals to the Reptile House

# How to Run
Extract the folder and open the command prompt in the res folder and run command `java -jar ReptileHouse.jar`

# How to Use the Program
This are the steps on how to use the program.
- First a Species, `PhysicalCharacteristic`s and `Personal Features` object need to be created. This is used to create the `Animal` object.
- Then a `List` that stores a number of features will need to be created.
- Next you create an `AbstractHabitat` object and add the `List` of features using `objectName.add(List)`
- Next you create a List to hold the `AbstractHabitat` object
- Next you create a `reptileHouse` object and add the `Abstract Habitat` object along with required parameters as shown in the UML diagram
- Now at this point all functionality is called from the `reptileHouse` object. You can add `habitat`, add `features` and print various information


## Description of Example Runs
Run 1 -- Filename: Run.txt:
- Creates `animal` class
- Creates a `List` of natural features
- Creates a `Habitat` class ands adds to it the `List` of natural features
- Creates `List` that holds  the `Habitat` class
- Creates a `reptileHouse` class with the `Lis`t of `Habitat` class
- Adds animals to the `reptileHouse`
- Write outputs to the screen


## Design/Model Changes

The initial design accepted an Animal interface and a string meant to represent the animal name to the `addAnimal()`. This was refactored to only accept an Animal Interface because the client could pass any string which can lead to side effects. Also there was no need to accept a string as animal name when the object was already accepted.
Also made all classes implement an interface to abide by the D in SOLID.

## Assumptions
Not a lot of assumptions where made due to the well documented problem but some are:
- There's no limit on the temperature range hence negative degrees are allowed.
- There's an infinite amount of natural features hence string was used instead of a type.
- Lastly, when the habitats in a house were full there's enough space in the house to store the remaining animals.


## Limitations
Currently every functionality runs so the only limitations that can be thought of stems from the design of the project and one stands out. An abstract class is extended by the habitat class but the abstract class only contains abstract methods. It was done this way to promote encapsulation by hiding implementation using protected.

In the future if another class was going to extend it it would have to move the implementation to the abstract class to avoid duplicating code. Also time complexity of functions were not factored in their implementation, this can lead to unforeseen problems when the program scales.


```