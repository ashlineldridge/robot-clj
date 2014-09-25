# Robot Coding Test

## Problem Description

- The application is a simulation of a toy robot moving on a 5 x 5 unit tabletop
- There are no obstructions on the table surface
- The robot is free to roam the surface of the table but must be prevented from falling to destruction
- Each position of the table is referred to by an X,Y coordinate
- The robot can face NORTH, SOUTH, EAST, or WEST
- The origin of the table is the south west most corner
- All erroneous commands are ignored
- All commands received before a valid `PLACE` command are ignored
 
### Commands

The commands that the robot accepts are as follows:
 
| Command       | Description                                                              |
|---------------|--------------------------------------------------------------------------|
| `PLACE X,Y,F` | Places the robot at X,Y position facing in the F direction               |
| `MOVE`        | Moves the robot one unit in the direction it is currently facing         |
| `LEFT`        | Rotates the robot 90 degress to the left                                 |
| `RIGHT`       | Rotates the robot 90 degress to the right                                |
| `REPORT`      | Prints the current position of the robot to stdout in the format "X,Y,F" |

## Solution Description

My solution to the above problem has been written in Clojure. One of my aims in developing this solution was to avoid using mutable state.

The application consists of two files: `core.clj` and `robot.clj`. The `core.clj` file contains the `-main` function and is responsible for reading commands from standard input and instructing the robot on what to do. The `robot.clj` file contains the robot logic for executing the commands.

The solution has both unit and acceptance tests. Run all of the tests as follows:



### Running the Application
 
 