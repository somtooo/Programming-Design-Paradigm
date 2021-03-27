# Image Model
<h3>About</h3>
The task at hand was to update our image application to perform three new features. Also, another task was to add a controller which will control the model.

My Program goes about solving the problem by breaking it down into smaller problems and making sure those work before combining it into one.

# LIST OF FEATURES

My program is able to track all required data and implements all functionality required as follows:
-   Image blurring 
- Image sharpening
- Convert Image to Sepia
- Convert Image to Greyscale
- Reduce color in an image
- Convert an Image to Mosaic
- Pixelate an Image
- Generate a cross stitch pattern of an image


# How to Run
Extract the folder and open the command prompt in the res folder and run command `java -jar ImageModel.jar fileName.txt`
The argument `filename.txt` contains the commands you want the application to perform. An example has been provided in the
`res` folder check `firstBatchFile.txt` to see supported commands. Note there's a csv file called `DMC Cotton Floss converted to RGB Values.csv`
its in the `res` folder and this is needed for the program to run currently. So make sure the csv file the images in the batch file, and the 
batch file all have to be in the  `res` folder when you run the jar file. Also, the outputs of the jar file are stored in the `res` folder.

# How to Use the Program
These are the steps on how to use the program.
- First follow "How to Run" above and run the program and make sure to pass a file name the file must contain commands that mirror
  the batch file examples provided in the `res` folder.
- For each command in your batch folder the outputs describing if they worked or not will be shown on the console window.


## Design/Model Changes
There were significant changes to how each class operates. First I separated the singular interface to account for different
input to function. For example sepia doesn't need to be applied many times but with the singular interface it had a value that it did not need.
Secondly, I moved from a functional approach to an Object-Oriented Approach this was to better represent the image. Lastly a controller was added
to be able to control the model and relive the driver of doing too much work.


## Assumptions
The assumptions made during the implementation of this project are as follows:
- Some images cant be pixelated. 
- I use two symbols to represent a floss color in pattern generation, e.g ab - DMC123
- The image is pixelated before generating a pattern by the program, and the user doesn't need 
  to pixelate before an appropriate pattern is generated. So if the user pixelates it will be pixelated again.
- Filter operations blur and filter can be applied more than once hence the minimum input to any filter operation is one.



## Limitations
Currently, every functionality runs, but some limitations that can be thought of
stems from the design of the project and one stands out. To utilize good dependency injection
where the `new` keyword was used to create objects can be replaced by factory methods, this would help decouple the
code more and provide good abstraction.
Also time complexity of functions were not factored in their implementation, this can
lead to unforeseen problems when the program scales.

##Citations
Dog Photos by https://unsplash.com/@karsten116
Goat Photo from Programming Design Patterns Project 4.

```