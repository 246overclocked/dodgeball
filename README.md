# Dodgeball
[![Build Status](https://travis-ci.com/246overclocked/dodgeball.svg?token=2FpVbKW7VbxCXxGx8Yi3&branch=master)](https://travis-ci.com/246overclocked/dodgeball)
[![Stories in Ready](https://badge.waffle.io/246overclocked/dodgeball.svg?label=ready&title=Ready)](https://waffle.io/246overclocked/dodgeball)

Overclocked's 2015/16 Pre-Season Project

## Technical Goals
- [x] talons
- [x] pneumatics
- [ ] vision tracking

## Usage
All FRC projects use **Apache Ant** to build and deploy code for the robot. In addition, this project also uses Ant to run unit tests. All libraries and dependencies are included in the project, so no external downloads are needed.

**Install Ant** - First determine if you already have Ant installed: open a terminal and type `ant -version`. If that outputs a version of Ant, then it is already installed and you can skip the rest of this step. If it is not installed, then [download Ant](http://ant.apache.org/bindownload.cgi) and follow the installation instructions [for Windows](http://www.nczonline.net/blog/2012/04/12/how-to-install-apache-ant-on-windows/) or, on OS X, the easiest way is to [install Homebrew](http://brew.sh), open up a terminal, and type `brew install ant`. (If you have MacPorts installed, you can install Ant with `sudo port install apache-ant`.)

### Running Ant on a Mac
All ant commands are run in the main directory of the project (in this case, in the `dodgeball` folder). Run `ant -p` to print project help information:
```
dodgeball$ ant -p
Buildfile: [...]/dodgeball/build.xml
Trying to override old definition of task classloader

Main targets:

 clean         Clean up all build and distribution artifacts.
 compile       Compile the source code.
 debug-deploy  Deploy the jar and start the program running.
 deploy        Deploy the jar and start the program running.
 test          Compile source code and run all junit tests.
Default target: deploy
```
Each "target" is a subcommand you can run. For example `ant deploy` deploys the code to the robot, and is equivallent to deploying from the Eclipse GUI. Most importantly, `ant test` will run automatically run all JUnit tests in the `dodgeball/test` source directory. 

### Running Ant on Windows
To run ANT from the command line on Windows (cmd or powershell): 

1) Add new environment variable ANT_HOME and point it to the place where ANT is installed (as Eclipse Plugin or 
standalone)

2) Append `%ANT_HOME%\bin` to the `%PATH%` envoronment variable

3) Use `cd` command to go to the root of the Project (where build.xml is located)

4) run command: `ant [target]` (see list of Main targets up above); if target is not specified, the default target in build.xml will be executed, 'deploy' in this case.

-- creds to Kostya Nazarenko for this portion of the documentation

### Running Ant from within Eclipse (available on both Mac and PC)
A more finicky and confusing way to run `ant deploy` or `ant test` directly from Eclipse:

1) First make sure you have no Ant Build configurations already set (you can check by going to Run > External Tools > External Tools Configurations... and looking on the left of the window that pops up) and deleting any ones that have already been set. 

2) Go to File > Import... > select Launch Configurations under Run/Debug > check off the 'dodgeball' directory on the left (you should see two configuration files checked off on the right) > Finish. 

3) Right-click on the build.xml file in the dodgeball directory in the package explorer, select Run As > 1 Ant Build (the first option on the list). 

4) You should now see two Ant Build configurations on the left of the window that pops up: 'dodgeball_build.xml' (this one tests and deploys the code to the RoboRio) and 'dodgeball_test.xml' (this one just runs all the tests) - select one of them and click 'Run' at the bottom of the window. You will see the output of the Ant Build in the Eclipse output console. 

5) You can select configurations you've run previously by clicking on the drop-down list next to the deploy button with a red tool chest under it (located just to the right of the usual deploy button) or going back the the 'External Tools Configurations' menu mentioned above. 

## Contributing
Please see [CONTRIBUTING](CONTRIBUTING.md) for more information.

## Credit
Thank you to team 4931 for your [awesome Wiki](https://github.com/frc-4931/2014/wiki/Java) explaining FRC Ant usage! Ant installation instructions kindly borrowed in verbatim.
