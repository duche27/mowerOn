# mowerOn 🚀🚀
Technical test for Seat Code

<img src="https://www-static-nw.husqvarna.com/-/images/aprimo/klippo/walk-behind-mowers/photos/studio/bm-885141.webp?v=3b332cf923296e8&format=WEBP_LANDSCAPE_CONTAIN_XL" align="left" width="192px" height="192px"/>
<img align="left" width="0" height="192px" hspace="10"/>

## ℹ️ Introduction

This is a repository intended to serve as a starting point for the Seat Code technical test.

## 🏁 How To Start

1. The project has been developed using openjdk-19.0.1. It uses some Java 15 features.
2. Clone this repository: `git clone https://github.com/duche27/mowerOn.git`.
3. Execute APP

## ℹ️ What I did
As I interpreted from the technical PDF manual, the input is a plain text file and the output is written in a simple system print.
I have added the same expected results to the normal execution as well as to the tests, and several unit tests for the validations that I implemented.

## ℹ️ What I would do if I had more time
1. Develop a system so more than two mowers could be added (or just one). It is not far away from doing that since I use a list for the mowers, 
but it is not possible to do it as it is right now.
2. Develop a small UI or a JavaFX desktop application with UI, and create a simple controller for the communication with the API of the APP.
3. Normally I would show the results (in case we don't have a UI) as a log or as a simple return. This way, the app wouldn't close itself with each execution, 
and we could turn on the mowers as many times as we wanted.
4. Add unit tests for the implemented service methods, since the ones that I added are complete integration tests
