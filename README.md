# Picasso (Drew Larsen, Colin Whiting, Yoseph Wolde, Mohamed Elhussiny)

An application that allows the user to create expressions that
evaluate to colors and then eventually to images.

The given code base is a good start, but it is sparsely documented
(document each method/part after you understand what it's doing) and,
as your application grows, you may need to refactor.

See the specification for Picasso on the course web site.

## Project Organization

`src` - the source code for the project

`conf` - the configuration files for the project

The `images` directory contains some sample images generated from Picasso.  Some of the expressions for these images can be found in the `expressions` directory.

## Code Base History

This code base originated as a project in a course at Duke University.  The professors realized that the code could be designed better and refactored.  This code base has some code leftover from the original version.

## Extensions

1) Generating random expressions 

Description: Randomly generates an expression and outputs the resulting image.

How To Use: Click the "Random" button. The resulting image will appear within the frame and the associated expression will populate the "Enter expression:" text field. 


2) Showing user's history 

Description: Shows up to the last 10 expressions the user has inputted in the History tab as JButtons within the History Frame.

How To Use: Click the "History" button.  Then, click the expression button you would like to use. The expression will now appear in the main textfield.  The user can edit the expression or evaluate as is by clicking the "Evaluate" button. 


3) Changing the size of the picture

Description: changing the dimensions of the pixmap and the canvas

How To Use: Click on the "Change Size" button. Input a dimension within the specified bounds and press "Okay".
The code should adjust the picture/canvas accordingly.
