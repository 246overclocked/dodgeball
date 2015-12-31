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

Another somewhat easier way to run `ant test` directly from Eclipse (and available on both Mac and PC) is to right-click on the 'build.xml' file in the dodgball repository from the package explorer in Eclipse and go to **Run As > 2 Ant Build... (the second option on the list, not to be confused with the first one)**. A window will pop up with the configuration of the ant script, and by default, all of the targets should be checked off. Uncheck all of them except for 'test' at the top (the first one on the list). Click Apply, and then click Run underneath -- only the portion of the ant script that launches the JUnit tests should run now, and you will see the output in the Eclipse console. Whenever you would like to run `ant test` from now on, you can simply click on the Run button directly to the right of the one for running code (this one has a small red tool chest under it), or go to **Run > External Tools > 1 build.xml**. 

## Contributing
Please see [CONTRIBUTING](CONTRIBUTING.md) for more information.

## Credit
Thank you to team 4931 for your [awesome Wiki](https://github.com/frc-4931/2014/wiki/Java) explaining FRC Ant usage! Ant installation instructions kindly borrowed in verbatim.
