# Image Model
<h3>About</h3>
The task at hand was to build an application that can manipulate images to produce some interesting effects. These effects are blurring, sharpening, greyscale, sepia 
and reducing color density.

My Program goes about solving the problem by breaking it down into smaller problems and making sure those work before combining it into one.

# LIST OF FEATURES

My program is able to track all required data and implements all functionality required as follows:
-   Image blurring 
- Image sharpening
- Convert Image to Sepia
- Convert Image to Greyscale
- Reduce color in an image


# How to Run
Extract the folder and open the command prompt in the res folder and run command `java -jar imageModel.jar`

# How to Use the Program
These are the steps on how to use the program.
- First follow "How to Run" above and run the program.
- New images will be saved in the `project` folder as ***ImageFromJar which will show the effects.


## Design/Model Changes
There were significant changes to how each class operates. The initial design did well to capture the requirements of the program but would have lagged
behind if new changes were to be added. The new design solves this problem by using the power of abstractions. Now any changes in the future can be added easily
without having to edit previous code or code that's already in production.


## Assumptions
The assumptions made during the implementation of this project are as follows:
- All image effects can be applied over and over again producing different type of intensity. 



## Limitations
Currently, every functionality runs, but some limitations that can be thought of
stems from the design of the project and one stands out. To utilize good dependency injection
where the `new` keyword was used to create objects can be replaced by factory methods, this would help decouple the
code more and provide good abstraction.
Also time complexity of functions were not factored in their implementation, this can
lead to unforeseen problems when the program scales.

##Citations
Dog Photos by https://unsplash.com/@karsten116

```